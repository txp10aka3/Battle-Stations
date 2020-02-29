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
	
	
	public TeamState teamStateWhite;
	public TeamState teamStateBlack;
	public MapState mapState;
	
	
	public GameState()
	{
		this(new TeamState(TeamState.TEAM.WHITE), new TeamState(TeamState.TEAM.BLACK), new MapState());
	}
	
	public GameState(TeamState teamStateA, TeamState teamStateB, MapState mapState)
	{
		this.teamStateWhite = teamStateA;
		this.teamStateBlack = teamStateB;
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
				(other.teamStateWhite.equals(this.teamStateWhite)) &&
				(other.teamStateBlack.equals(this.teamStateBlack));
	}
}
