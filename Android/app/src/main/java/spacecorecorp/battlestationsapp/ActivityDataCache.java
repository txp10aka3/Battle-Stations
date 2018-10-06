package spacecorecorp.battlestationsapp;

import com.esotericsoftware.kryonet.Client;

public class ActivityDataCache
{
    private static final ActivityDataCache ourInstance = new ActivityDataCache();

    public static ActivityDataCache getInstance()
    {
        return ourInstance;
    }

    private volatile String username;
    private volatile Client client;

    private ActivityDataCache()
    {
        client = null;
        username = null;
    }

    public String getUsername()
    {
        return username;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}