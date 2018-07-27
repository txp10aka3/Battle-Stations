package spacecorecorp.battlestationsapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import spacecorecorp.battlestationsapp.network.GameDiscoveryListener;
import spacecorecorp.battlestationsapp.network.ParseIPAddressTask;

public class MainActivity extends Activity
{
    private static final String SERVICE_TYPE = "_spacegame._tcp";
    private static final String LOG_TAG = "MainActivity";
    private static final int PERMISSIONS_KEY = 5312;

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

        nsdManager = (NsdManager) getSystemService(Context.NSD_SERVICE);

        if(nsdManager != null)
        {
            discoveryListener = new GameDiscoveryListener(nsdManager, new NsdManager.ResolveListener()
            {
                @Override
                public void onResolveFailed(NsdServiceInfo nsdServiceInfo, int i)
                {
                    Log.d(LOG_TAG, "onResolveFailed");
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
                            ipAddress = new ParseIPAddressTask(editTextIP.getText().toString()).get(500, TimeUnit.MILLISECONDS);
                        }
                        catch (Exception ex)
                        {
                            ex.printStackTrace();
                            ipAddress = null;
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
        if(nsdManager != null && discoveryListener != null && setUpPermissions())
            nsdManager.stopServiceDiscovery(discoveryListener);
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

    private Activity getActivity()
    {
        return this;
    }
}
