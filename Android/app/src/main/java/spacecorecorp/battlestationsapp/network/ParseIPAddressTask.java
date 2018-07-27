package spacecorecorp.battlestationsapp.network;

import android.os.AsyncTask;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ParseIPAddressTask extends AsyncTask<Void, Void, InetAddress>
{
    private String ip;

    public ParseIPAddressTask(String ip)
    {
        this.ip = ip;
    }

    @Override
    protected InetAddress doInBackground(Void... voids)
    {
        try
        {
            return InetAddress.getByName(ip);
        }
        catch (UnknownHostException uhe)
        {
            uhe.printStackTrace();
            return null;
        }
    }
}
