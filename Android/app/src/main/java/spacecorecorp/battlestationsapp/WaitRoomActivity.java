package spacecorecorp.battlestationsapp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.skyfishjy.library.RippleBackground;

import spacecorecorp.battlestationsapp.network.GameMessage;

public class WaitRoomActivity extends AppCompatActivity
{
    private static final String LOG_TAG = "WaitRoomActivity";

    private RippleBackground ripple;
    private Client receivedClient;
    private String username;

    private Listener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_room);

        receivedClient = ActivityDataCache.getInstance().getClient();
        username = ActivityDataCache.getInstance().getUsername();
        ripple = findViewById(R.id.ripple);

        if(username == null || receivedClient == null)
        {
            Toast.makeText(getApplicationContext(), "Connection Null!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        listener = new Listener()
        {
            public void received (Connection connection, Object object)
            {
                if (object instanceof GameMessage)
                {
                    if(((GameMessage) object).messageHeader.equals(GameMessage.START_MESSAGE_TYPE))
                    {
                        GameMessage.TEAM team = ((GameMessage) object).team;
                        GameMessage.POSITION position = ((GameMessage) object).position;
                        Snackbar.make(findViewById(R.id.waitRoomLayout), "Team " + team + " : " + position, Snackbar.LENGTH_SHORT).show();

                        ActivityDataCache.getInstance().setTeam(team);
                        ActivityDataCache.getInstance().setPosition(position);
                        //TODO: Add Transition to GameActivity
                    }
                    else
                    {
                        Log.d(LOG_TAG, "Unidentified Message Received!");
                        Toast.makeText(getApplicationContext(), "Unidentified Message Received!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Log.d(LOG_TAG, "Classless Message Received!");
                    Toast.makeText(getApplicationContext(), "Classless Message Received!", Toast.LENGTH_SHORT).show();
                }
            }

            public void connected (Connection connection)
            {
                if(!ripple.isRippleAnimationRunning())
                    ripple.startRippleAnimation();
            }

            public void disconnected (Connection connection)
            {
                if(ripple.isRippleAnimationRunning())
                    ripple.stopRippleAnimation();
            }
        };

        receivedClient.addListener(listener);

        if(receivedClient.isConnected() && !ripple.isRippleAnimationRunning())
            ripple.startRippleAnimation();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if(receivedClient.isConnected() && !ripple.isRippleAnimationRunning())
            ripple.startRippleAnimation();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        if(ripple.isRippleAnimationRunning())
            ripple.stopRippleAnimation();
    }

    @Override
    protected void onStop()
    {
        if(ripple.isRippleAnimationRunning())
            ripple.stopRippleAnimation();

        receivedClient.removeListener(listener);
        super.onStop();
    }
}
