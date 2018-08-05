package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.esotericsoftware.kryonet.Server;

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
	
	private Server server;
	
	public GameFrame()
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameWidth = screenSize.width;
		frameHeight = screenSize.height;
		setBounds(0,0,frameWidth,frameHeight);
		isOpaque();
		board = new Board(10);
		
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
		
		gameScreen = new GameScreen(board, server, frameWidth, frameHeight);
		add(gameScreen);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	{
		new GameFrame();
	}
}
