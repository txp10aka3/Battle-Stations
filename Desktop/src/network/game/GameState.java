package network.game;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GameState")
public class GameState implements Serializable
{
	/**
	 * GameState Serializable Implementation
	 */
	private static final long serialVersionUID = 642156970056497974L;
	
	
	public TeamState teamStateA;
	public TeamState teamStateB;
	public MapState mapState;
	
	
	public GameState()
	{
		this(new TeamState(), new TeamState(), new MapState());
	}
	
	public GameState(TeamState teamStateA, TeamState teamStateB, MapState mapState)
	{
		this.teamStateA = teamStateA;
		this.teamStateB = teamStateB;
		this.mapState = mapState;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(!(obj instanceof GameState))
			return false;

		GameState other = (GameState) obj;
		return (other.mapState.equals(this.mapState)) &&
				(other.teamStateA.equals(this.teamStateA)) &&
				(other.teamStateB.equals(this.teamStateB));
	}
}
