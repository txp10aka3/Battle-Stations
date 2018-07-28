package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class GameFrame extends JFrame
{
	private Dimension screenSize;
	private int frameWidth;
	private int frameHeight;
	private GameScreen gameScreen;
	private Board board;
	public GameFrame()
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameWidth = screenSize.width;
		frameHeight = screenSize.height;
		setBounds(0,0,frameWidth,frameHeight);
		isOpaque();
		board = new Board(10);
		gameScreen = new GameScreen(board, frameWidth, frameHeight);
		add(gameScreen);
		this.addKeyListener(new KeyListener() 
		{

			@Override
			public void keyPressed(KeyEvent e) 
			{	
				if(e.getKeyCode()== e.VK_W)
				{
					board.moveShip(1, new int[][] {{1,0},{0,0},{0,0}});
				}
				if(e.getKeyCode()== e.VK_A)
				{
					board.moveShip(1, new int[][] {{1,-1},{0,0},{0,0}});
				}
				if(e.getKeyCode()== e.VK_D)
				{
					board.moveShip(1, new int[][] {{1,1},{0,0},{0,0}});
					
				}
				if(e.getKeyCode()== e.VK_S)
				{
					board.moveShip(1, new int[][] {{1,2},{0,0},{0,0}});
				}
				gameScreen.updateBoard();
			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) 
			{
				// TODO Auto-generated method stub
				
			}
		
		});
		board.moveShip(2, new int[][] {{1,1},{1,0},{3,0}});
		gameScreen.updateBoard();
		new Thread()
		{
			public void run()
			{
				try 
				{
					sleep(1000);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				gameScreen.animate(2, new int[][] {{0,1},{1,1},{20,0}});
			}
		}.start();
		
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) 
	{
		new GameFrame();
		
	}
}
