package spacecorecorp.battlestationsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.InetAddress;
import java.net.UnknownHostException;

import spacecorecorp.battlestationsapp.network.GameDiscoveryListener;

public class MainActivity extends AppCompatActivity
{
    private static final String SERVICE_TYPE = "_spacegame._tcp";
    private static final String LOG_TAG = "MainActivity";

    private EditText editTextName;
    private EditText editTextIP;
    private Button buttonProceed;

    private NsdManager nsdManager;
    private GameDiscoveryListener discoveryListener;
    private InetAddress foundAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        editTextName = findViewById(R.id.editTextUser);
        editTextIP = findViewById(R.id.editTextIP);
        buttonProceed = findViewById(R.id.buttonGo);

        nsdManager = (NsdManager) getSystemService(Context.NSD_SERVICE);

        if(nsdManager != null)
        {
            discoveryListener = new GameDiscoveryListener(nsdManager, new NsdManager.ResolveListener()
            {
                @Override
                public void onResolveFailed(NsdServiceInfo nsdServiceInfo, int i)
                {

                }

                @Override
                public void onServiceResolved(NsdServiceInfo nsdServiceInfo)
                {
                    if(nsdServiceInfo.getServiceType().equals(SERVICE_TYPE))
                    {
                        foundAddress = nsdServiceInfo.getHost();
                    }
                    Log.d(LOG_TAG, "onServiceResolved : " + nsdServiceInfo.getServiceType());
                }
            });
            nsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener);
        }

        buttonProceed.setOnClickListener(new View.OnClickListener()
        {
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
                        ipAddress = foundAddress;
                    }
                    else
                    {
                        try
                        {
                            ipAddress = InetAddress.getByName(editTextIP.getText().toString());
                        }
                        catch (UnknownHostException uhe)
                        {
                            uhe.printStackTrace();
                        }
                    }

                    if(ipAddress == null)
                    {
                        new AlertDialog.Builder(getActivity())
                                .setNeutralButton("OK", null)
                                .setTitle("Invalid Input")
                                .setMessage("Please Check the Validity of the IP Address")
                                .create()
                                .show();
                    }
                    else
                    {
                        //TODO: Start Some Connection
                    }
                }
            }
        });
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
        if(nsdManager != null)
            nsdManager.stopServiceDiscovery(discoveryListener);
    }

    private Activity getActivity()
    {
        return this;
    }
}
