package spacecorecorp.battlestationsapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

import spacecorecorp.battlestationsapp.network.GameMessage;
import spacecorecorp.battlestationsapp.network.ParseIPAddressTask;

public class MainActivity extends Activity implements View.OnClickListener
{
    private static final String SERVICE_TYPE = "_spacegame._tcp";
    private static final String LOG_TAG = "MainActivity";
    private static final int PERMISSIONS_KEY = 5312;
    private static final int TCP_PORT = 35330;
    private static final int UDP_PORT = 35333;

    private EditText editTextName;
    private EditText editTextIP;
    private Button buttonProceed;

    /*
    private NsdManager nsdManager;
    private InetAddress foundAddress;
    */

    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextUser);
        editTextIP = findViewById(R.id.editTextIP);
        buttonProceed = findViewById(R.id.buttonGo);

        if(!setUpPermissions())
            return;
        else
            Log.d(LOG_TAG, "onCreate");

        buttonProceed.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(editTextName.getText().toString().equals(""))
        {
            new AlertDialog.Builder(getActivity())
                    .setNeutralButton("OK", null)
                    .setTitle("Invalid Input")
                    .setMessage("Please Enter in a User Name")
                    .create()
                    .show();
        }
        else
        {
            InetAddress ipAddress = null;
            if(editTextIP.getText().toString().equals(""))
            {
                new AlertDialog.Builder(getActivity())
                        .setNeutralButton("OK", null)
                        .setTitle("Invalid Input")
                        .setMessage("Please Enter a Valid Address")
                        .create()
                        .show();
                return;
            }
            else
            {
                try
                {
                    ipAddress = new ParseIPAddressTask(editTextIP.getText().toString()).get(500, TimeUnit.MILLISECONDS);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }

            if(ipAddress == null)
            {
                new AlertDialog.Builder(getActivity())
                        .setNeutralButton("OK", null)
                        .setTitle("Could Not Find Server")
                        .setMessage("Please Check the Validity of the IP Address and/or Your Network Connection")
                        .create()
                        .show();
            }
            else
            {
                if(editTextName.getText().toString().equals(""))
                {
                    new AlertDialog.Builder(getActivity())
                            .setNeutralButton("OK", null)
                            .setTitle("Please Enter A Name")
                            .setMessage("Enter the User Name You Would Like to Be Initially Identified By")
                            .create()
                            .show();
                }
                else
                {
                    startConnection(ipAddress, editTextName.getText().toString());
                }
            }
        }
    }

    private void startConnection(InetAddress address, String userName)
    {
        client = new Client();
        client.start();

        try
        {
            Toast.makeText(getApplicationContext(), "Attempting Connection", Toast.LENGTH_SHORT).show();
            client.connect(5000, address, TCP_PORT, UDP_PORT);
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            Toast.makeText(getApplicationContext(), "Connection Failed!", Toast.LENGTH_SHORT).show();
        }

        Kryo kryo = client.getKryo();
        kryo.register(GameMessage.class);

        client.sendTCP(GameMessage.generateStartMessage(userName));

        ActivityDataCache.getInstance().setUsername(userName);
        ActivityDataCache.getInstance().setClient(client);
        Intent waitingRoomIntent = new Intent(getApplicationContext(), WaitRoomActivity.class);
        startActivity(waitingRoomIntent);
    }

    private boolean setUpPermissions()
    {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.CHANGE_WIFI_MULTICAST_STATE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_MULTICAST_STATE}, PERMISSIONS_KEY);
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    private Activity getActivity()
    {
        return this;
    }
}
