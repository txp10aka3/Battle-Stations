package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import network.GamePlayer;

public class PlayerPanel extends JPanel 
{
	/**
	 * Requested by JPanel
	 */
	private static final long serialVersionUID = 8319489027333955979L;
	
	private ArrayList<GamePlayer> players;
	private GridBagConstraints gbc;
	private String[] stationsArray;
	
	public PlayerPanel()
	{
		gbc = new GridBagConstraints();
		players = new ArrayList<>();
		setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		stationsArray = new String[]{"Cap", "Nav", "Gun", "Scan", "Sheild", "Beam"};
		players.add(new GamePlayer("Thomas", null));
		updateList(players);
	}
	
	public void updateList(ArrayList<GamePlayer> newPlayers)
	{
		removeAll();
		validate();
		players = newPlayers;
		gbc.gridy=0;
		for(GamePlayer player: players)
		{
			gbc.gridx=0;
			add(new JLabel(player.getName()), gbc);
			gbc.gridx=1;
			add(new JComboBox<String>(stationsArray), gbc);
			gbc.gridy++;
		}
		validate();
		
	}

}
