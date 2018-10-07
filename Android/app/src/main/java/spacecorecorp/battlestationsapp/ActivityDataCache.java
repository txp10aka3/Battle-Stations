package spacecorecorp.battlestationsapp;

import com.esotericsoftware.kryonet.Client;

import spacecorecorp.battlestationsapp.network.GameMessage;

public class ActivityDataCache
{
    private static final ActivityDataCache ourInstance = new ActivityDataCache();

    public static ActivityDataCache getInstance()
    {
        return ourInstance;
    }

    private volatile String username;
    private volatile Client client;
    private volatile GameMessage.TEAM team;
    private volatile GameMessage.POSITION position;

    private ActivityDataCache()
    {
        client = null;
        username = null;
        team = null;
        position = null;
    }

    public String getUsername()
    {
        return username;
    }

    public Client getClient()
    {
        return client;
    }

    public GameMessage.TEAM getTeam()
    {
        return team;
    }

    public GameMessage.POSITION getPosition()
    {
        return position;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setTeam(GameMessage.TEAM team)
    {
        this.team = team;
    }

    public void setPosition(GameMessage.POSITION position)
    {
        this.position = position;
    }
}