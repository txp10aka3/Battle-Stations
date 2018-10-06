package spacecorecorp.battlestationsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.skyfishjy.library.RippleBackground;

import spacecorecorp.battlestationsapp.network.GameMessage;

public class WaitRoomActivity extends AppCompatActivity
{
    private RippleBackground ripple;
    private Client receivedClient;
    private String username;

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
        }

        receivedClient.addListener(new Listener()
        {
            public void received (Connection connection, Object object)
            {
                if (object instanceof GameMessage)
                {

                }
            }
        });
    }
}
