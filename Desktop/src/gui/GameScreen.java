package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;


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
		setFocusable(true);
		
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
				if(e.getKeyCode()== e.VK_SPACE)
				{
					board.fire(1);
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
				
				//server commands
				board.setShipScanners(1, 3);
				board.setShipShields(2, new int[] {0,0,0,0});
				board.setShipStrength(1, 5);
				board.setShipRkts(1, new int[][] {{0,1,2,3},{2,1,2,3}});
				
				
				/*
				board.moveShip(2, new int[][] {{1,1},{1,0},{3,0}});
				updateBoard();
				*/
				//animate(2, new int[][]{{1,1},{1,0},{3,0}});
			}
		}.start();
		
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
