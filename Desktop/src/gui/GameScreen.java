package gui;

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
}
