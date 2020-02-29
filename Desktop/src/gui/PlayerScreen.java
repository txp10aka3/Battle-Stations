package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.cxf.endpoint.Server;

import network.player.GamePlayer;

public class PlayerScreen extends JPanel 
{
	/**
	 * Requested by JPanel
	 */
	private static final long serialVersionUID = -2036219615503141028L;

	private ArrayList<GamePlayer> players;
	private PlayerPanel panel;
	
	public PlayerScreen(Server server)
	{
		players = new ArrayList<>();
		panel = new PlayerPanel();
	
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		JLabel titleLabel = new JLabel("Please Join the Game Server");
		titleLabel.setFont(new Font(titleLabel.getFont().getFontName(), 20, Font.CENTER_BASELINE));
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor =  GridBagConstraints.CENTER;
		gbc.gridx = gbc.gridy = 0;
		this.add(titleLabel, gbc);
		
		gbc.gridy++;
		this.add(panel, gbc);
		
		JButton readyButton = new JButton("Ready to Begin?");
		gbc.gridy++;
		this.add(readyButton, gbc);
		
		readyButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{			
				//TODO: Handle Start of GameScreen
			}
		});
	}
}
