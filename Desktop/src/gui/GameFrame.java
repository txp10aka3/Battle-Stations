package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.esotericsoftware.kryonet.Server;

import network.GameMessage;
import network.GamePlayer;

public class GameFrame extends JFrame
{
	/**
	 * Requested by JFrame
	 */
	private static final long serialVersionUID = 4236762546533051047L;
	private static final int TCP_PORT = 35330;
	private static final int UPD_PORT = 35333;
	
	private Dimension screenSize;
	private int frameWidth;
	private int frameHeight;
	private GameScreen gameScreen;
	private Board board;
	private JPanel panel;
	
	private Server server;
	private ArrayList<GamePlayer> players;
	
	public GameFrame()
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameWidth = screenSize.width;
		frameHeight = screenSize.height;
		setBounds(0,0,frameWidth,frameHeight);
		board = new Board(10);
		
		players = null;
		server = new Server();
		server.start();
		try 
		{
			server.bind(TCP_PORT, UPD_PORT);
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Networking Not Found!");
		}
		
		server.getKryo().register(GameMessage.class);
		
		/*
		 * gameScreen = new GameScreen(board, server, frameWidth, frameHeight);
		 * add(gameScreen);
		 */
		
		goToStartScreen();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void goToStartScreen()
	{
		swapPanel(new StartScreen());
	}
	
	public void goToPlayersScreen()
	{
		swapPanel(new PlayerScreen(server));
	}
	
	public void gotToGameScreen(ArrayList<GamePlayer> player)
	{
		players.addAll(player);
		swapPanel(new GameScreen(board, server, players, frameWidth, frameHeight));
	}
	
	private void swapPanel(JPanel newFrame)
	{
		if(panel != null)
			this.remove(panel);
		this.add(newFrame);
		this.revalidate();
		panel = newFrame;
	}
	
	public Server getServer()
	{
		return server;
	}
	
	public static void main(String[] args) 
	{
		new GameFrame();
	}
}
