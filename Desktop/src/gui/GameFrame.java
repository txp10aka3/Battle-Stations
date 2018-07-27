package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;



public class GameFrame extends JFrame
{
	private Dimension screenSize;
	private int frameWidth;
	private int frameHeight;
	private GameScreen gameScreen;
	public GameFrame()
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameWidth = screenSize.width;
		frameHeight = screenSize.height;
		setBounds(0,0,frameWidth,frameHeight);
		isOpaque();
		
		gameScreen = new GameScreen(frameWidth, frameHeight);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	{
		new GameFrame();
		
	}
}
