package spacecorecorp.battlestationsapp.network;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;

public class GameDiscoveryListener implements NsdManager.DiscoveryListener
{
    private static final String LOG_TAG = "DiscoveryListener";

    private NsdManager manager;
    private NsdManager.ResolveListener listener;

    public GameDiscoveryListener(NsdManager manager, NsdManager.ResolveListener listener)
    {
        this.manager = manager;
        this.listener = listener;
    }

    @Override
    public void onStopDiscoveryFailed(String serviceType, int errorCode)
    {
        Log.d(LOG_TAG, "onStopDiscoveryFailed");
    }

    @Override
    public void onStartDiscoveryFailed(String serviceType, int errorCode)
    {
        Log.d(LOG_TAG, "onStartDiscoveryFailed");
    }

    @Override
    public void onServiceLost(NsdServiceInfo service)
    {
        Log.d(LOG_TAG, "onServiceLost");
    }

    @Override
    public void onServiceFound(NsdServiceInfo service)
    {
        Log.d(LOG_TAG, "onServiceFound");
        manager.resolveService(service, listener);
    }

    @Override
    public void onDiscoveryStopped(String serviceType)
    {
        Log.d(LOG_TAG, "onDiscoveryStopped : " + serviceType);
    }

    @Override
    public void onDiscoveryStarted(String serviceType)
    {
        Log.d(LOG_TAG, "onDiscoveryStarted : " + serviceType);
    }
}
