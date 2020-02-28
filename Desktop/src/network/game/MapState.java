package network.game;

import java.util.HashSet;
import java.util.Set;

import util.Utils.BoardLocation;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MapState")
public class MapState implements Serializable
{
	/**
	 * MapState Serializable Implementation
	 */
	private static final long serialVersionUID = 5944618345281641176L;
	
	
	public boolean isPaused;
	public Set<BoardLocation> crystalLocations;
	
	public MapState()
	{
		this(false, generateStartingCrystals());		
	}
	
	public MapState(boolean isPaused, Set<BoardLocation> crystalLocations)
	{
		this.isPaused = isPaused;
		this.crystalLocations = new HashSet<BoardLocation>(crystalLocations);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(!(obj instanceof MapState))
			return false;

		MapState other = (MapState) obj;
		return (other.isPaused == this.isPaused) &&
				(other.crystalLocations.equals(this.crystalLocations));
	}
	
	public static Set<BoardLocation> generateStartingCrystals()
	{
		//TODO: Add Random Crystal Location Generation
		HashSet<BoardLocation> startingCrystals = new HashSet<BoardLocation>();
		return startingCrystals;
	}
}
