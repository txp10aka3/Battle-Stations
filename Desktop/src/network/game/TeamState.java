package network.game;

import java.util.Arrays;

import util.Utils;
import util.Utils.BoardLocation;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TeamState")
public class TeamState implements Serializable
{
	/**
	 * TeamState Serializable Implementation
	 */
	private static final long serialVersionUID = 2790392082472110100L;
	
//	public static final int DIRECTION_UP = 0;
//	public static final int DIRECTION_RIGHT = 1;
//	public static final int DIRECTION_DOWN = 2;
//	public static final int DIRECTION_LEFT = 3;
	public enum TEAM
	{
		BLACK, WHITE
	}
	
	public int shipScanners;
	public int shipStealth;
	public int shipStrength;
	public int shipDirection;
	public int[] shipShields;
	public int[][] shipRockets;
	public BoardLocation shipLocation;
	
	public TeamState(TEAM team)
	{
		this(0, 0, 4, 0, new int[]{0,0,0,0}, new int[][] {{0,0,0,0},{0,0,0,0}},
				team == TEAM.WHITE ? new BoardLocation(Utils.BOARD_SIZE-1, 0) :
					new BoardLocation(0, Utils.BOARD_SIZE-1));
	}
	
	public TeamState(int shipScanners, int shipStealth, int shipStrength, int shipDirection,
			int[] shipShields, int[][] shipRockets, BoardLocation shipLocation)
	{
		this.shipScanners = shipScanners;
		this.shipStealth = shipStealth;
		this.shipStrength = shipStrength;
		this.shipDirection = shipDirection;
		this.shipLocation = shipLocation;
		this.shipShields = Arrays.copyOf(shipShields, shipShields.length);
		this.shipRockets = Arrays.stream(shipRockets).map(int[]::clone).toArray(int[][]::new);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(!(obj instanceof TeamState))
			return false;

		TeamState other = (TeamState) obj;
		boolean hasSameShields = Arrays.equals(other.shipShields, this.shipShields);
		boolean hasSameRockets = Arrays.deepEquals(other.shipRockets, this.shipRockets);
		return (other.shipScanners == this.shipScanners) &&
				(other.shipStealth == this.shipStealth) &&
				(other.shipStrength == this.shipStrength) &&
				(other.shipDirection == this.shipDirection) &&
				(other.shipLocation.equals(this.shipLocation))
				&& hasSameShields && hasSameRockets;
	}
}
