package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GameScreen extends JPanel
{
	/**
	 * Requested by JPanel
	 */
	private static final long serialVersionUID = 4988911646435079480L;
	
	private int frameWidth;
	private int frameHeight;
	private Board board;
	private BoardPanel boardPanel;
	
	public GameScreen(Board b, int fw, int fh) 
	{
		frameWidth = fw;
		frameHeight = fh;
		setBounds(0,0,frameWidth,frameHeight);
		board = b;
		boardPanel = new BoardPanel(board, frameWidth, frameHeight);
		add(boardPanel);
		
		this.addKeyListener(new KeyListener() 
		{

			@Override
			public void keyPressed(KeyEvent e) 
			{	
				if(e.getKeyCode()== KeyEvent.VK_W)
				{
					board.moveShip(1, new int[][] {{1,0},{0,0},{0,0}});
				}
				if(e.getKeyCode()== KeyEvent.VK_A)
				{
					board.moveShip(1, new int[][] {{0,-1},{0,0},{0,0}});
				}
				if(e.getKeyCode()== KeyEvent.VK_D)
				{
					board.moveShip(1, new int[][] {{0,1},{0,0},{0,0}});
					
				}
				if(e.getKeyCode()== KeyEvent.VK_S)
				{
					board.moveShip(1, new int[][] {{0,2},{0,0},{0,0}});
				}
				updateBoard();
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
		
		/*
		board.moveShip(2, new int[][] {{1,1},{1,0},{3,0}});
		updateBoard();
		*/
	}
	
	public void updateBoard()
	{
		remove(boardPanel);
		boardPanel = new BoardPanel(board, frameWidth, frameHeight);
		add(boardPanel);
		revalidate();
	}
	public void animate(int ship, int[][] movement)
	{
		int[][][] moves = new int[movement.length][movement.length][2];
		for(int i = 0; i<moves.length; i++)
		{
			moves[i] = movement.clone();
			
			for(int j = 0; j<movement.length; j++)
			{
				//remove(boardPanel);                              attempts to stop glitches 
				if(j!=i)
				{
					moves[i][j] = new int[]{0,0};
				}
				else
				{
					if(moves[i][j][0] >1)
					{
						animate(ship, new int[][] {{moves[i][j][0]-1,0}});
						moves[i][j][0] = 1;
					}
				}
			}
			board.moveShip(ship, moves[i]);
			updateBoard();
			try 
			{
				Thread.sleep(500);
			}
			catch(InterruptedException e) 
		    {
			    e.printStackTrace();
		    }
		}
		
	}
}
