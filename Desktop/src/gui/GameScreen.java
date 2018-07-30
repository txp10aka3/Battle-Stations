package gui;

import java.util.Arrays;

import javax.swing.JPanel;

public class GameScreen extends JPanel
{
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
