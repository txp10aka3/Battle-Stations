package network;

import java.net.InetAddress;

public class GamePlayer 
{
	public String userName;
	public InetAddress ip;
	public GameMessage.TEAM team;
	public GameMessage.POSITION position;
	
	public GamePlayer(String userName, InetAddress ip)
	{
		this.userName = userName;
		this.ip = ip;
		team = null;
		position = null;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(!(other instanceof GamePlayer))
			return false;
		
		GamePlayer otherPlayer = (GamePlayer) other;
		return otherPlayer.userName.equals(this.userName);
	}
}
