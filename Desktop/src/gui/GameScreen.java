package gui;

import javax.swing.JPanel;

public class GameScreen extends JPanel
{
	private int framewidth;
	private int frameheight;
	private Board board;
	
	public GameScreen(int fw, int fh) 
	{
		framewidth = fw;
		frameheight = fh;
	}
}
