package network.player;

public class GamePlayer 
{
	public String userName;
	
	public GamePlayer(String userName)
	{
		this.userName = userName;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(!(other instanceof GamePlayer))
			return false;
		
		GamePlayer otherPlayer = (GamePlayer) other;
		return otherPlayer.userName.equals(this.userName);
	}
	
	public String getName()
	{
		return userName;
	}
}
