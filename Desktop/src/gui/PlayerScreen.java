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

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import network.GameMessage;
import network.GamePlayer;

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
		//titleLabel.setFont(new Font(titleLabel.getFont().getFontName(), 20, Font.CENTER_BASELINE));
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor =  GridBagConstraints.CENTER;
		gbc.gridx = gbc.gridy = 0;
		this.add(titleLabel, gbc);
		
		gbc.gridy++;
		this.add(panel, gbc);
		
		JButton readyButton = new JButton("Ready to Begin?");
		gbc.gridy++;
		this.add(readyButton, gbc);
		
		server.addListener(new Listener()
		{
			 public void received (Connection connection, Object object) 
			 {
				 String message = (String) object;
				 GameMessage gameMessage = GameMessage.fromJSON(message);
				 
				 switch(gameMessage.messageHeader)
				 {
				 	case GameMessage.UPDATE_MESSAGE_TYPE:
				 		JOptionPane.showMessageDialog(null, "Invalid Player State");
				 		break;
				 	case GameMessage.STOP_MESSAGE_TYPE:
				 		GamePlayer leavingPlayer = new GamePlayer(gameMessage.messageContent, 
				 				connection.getRemoteAddressTCP().getAddress());
				 		players.remove(leavingPlayer);
				 		panel.updateList(players);
				 		break;
				 	case GameMessage.START_MESSAGE_TYPE:
				 		GamePlayer joiningPlayer = new GamePlayer(gameMessage.messageContent, 
				 				connection.getRemoteAddressTCP().getAddress());
				 		players.add(joiningPlayer);
				 		panel.updateList(players);
				 		break;
				 }
			 }
		});
		
		readyButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				for(GamePlayer player : players)
				{
					if(player.position == null)
					{
						JOptionPane.showMessageDialog(null, "Player Without Role!");
						return;
					}
				}
				
				for(GamePlayer player : players)
				{
					GameMessage startMessage = GameMessage.generateStartMessage(player.userName);
					startMessage.team = player.team;
					startMessage.position = player.position;
					server.sendToAllTCP(startMessage);
				}
				
				//TODO: Handle Start of GameScreen
			}
		});
	}
}
