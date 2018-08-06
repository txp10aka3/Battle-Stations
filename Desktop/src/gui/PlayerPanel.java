package gui;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import network.GamePlayer;

public class PlayerPanel extends JPanel 
{
	/**
	 * Requested by JPanel
	 */
	private static final long serialVersionUID = 8319489027333955979L;
	
	private ArrayList<GamePlayer> players;
	private JPanel componentPanel;
	private BoxLayout subComponentLayout;
	
	public PlayerPanel()
	{
		players = new ArrayList<>();
		
		componentPanel = new JPanel();
		subComponentLayout = new BoxLayout(componentPanel, BoxLayout.Y_AXIS);
		this.setLayout(subComponentLayout);
	}
	
	public void updateList(ArrayList<GamePlayer> newPlayers)
	{
		players = newPlayers;
		for(GamePlayer player : players)
		{
			componentPanel.removeAll();
			//TODO: Find a Way to HAve Text Next to ComboBoxes
		}
	}
}
