package network.game;

import java.util.List;
import java.util.Arrays;

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
	
	
	public int shipScanners;
	public int shipStealth;
	public int[] shipShields;
	public int[][] shipRockets;
	public int shipStrength;
	public int shipDirection;
	public BoardLocation shipLocation;
	
	//public List<RoleState> roleStates;
	
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
