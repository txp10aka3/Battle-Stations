package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardPanel extends JPanel
{
	private int frameWidth;
	private int frameHeight;
	private Board board;
	private ImageIcon space;
	private ImageIcon blueShip0;
	private ImageIcon blueShip1;
	private ImageIcon blueShip2;
	private ImageIcon blueShip3;
	private ImageIcon redShip0;
	private ImageIcon redShip1;	
	private ImageIcon redShip2;	
	private ImageIcon redShip3;	
	
	private ImageIcon blueShip0b; 
	private ImageIcon spaceb;
	public BoardPanel(Board b, int fw, int fh)
	{
		space = new ImageIcon(BoardPanel.class.getResource("/art/emptySpace3.png"));
		spaceb = new ImageIcon(BoardPanel.class.getResource("/art/emptySpace4.png"));
		blueShip0 = new ImageIcon(BoardPanel.class.getResource("/art/dblueship30.png"));
		blueShip1 = new ImageIcon(BoardPanel.class.getResource("/art/dblueship31.png"));
		blueShip2 = new ImageIcon(BoardPanel.class.getResource("/art/dblueship32.png"));
		blueShip3 = new ImageIcon(BoardPanel.class.getResource("/art/dblueship33.png"));
		redShip0 = new ImageIcon(BoardPanel.class.getResource("/art/dredship30.png"));
		redShip1 = new ImageIcon(BoardPanel.class.getResource("/art/dredship31.png"));
		redShip2 = new ImageIcon(BoardPanel.class.getResource("/art/dredship32.png"));
		redShip3 = new ImageIcon(BoardPanel.class.getResource("/art/dredship33.png"));
		
		blueShip0b = new ImageIcon(BoardPanel.class.getResource("/art/dblueship30b.png"));
		
		frameWidth = fw;
		frameHeight = fh;
		setBounds(0,0,frameWidth-50,frameHeight-50);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		board = b;
		int animation = 0;
		for(int i = 0; i<board.getSize(); i++)
		{
			for(int j = 0; j<board.getSize(); j++)
			{
				animation = (int) (Math.random()*3);
				gbc.gridx = j;
				gbc.gridy = i;
				if(board.getBoardAtCoordinates(i,j) == 0)
				{
					if(animation == 0)
					{
						add(new JLabel(spaceb),gbc);    // change for animation
					}
					else
					{
						add(new JLabel(spaceb),gbc);
					}
					
				}
				if(board.getBoardAtCoordinates(i,j) == 1)
				{
					if(board.getShipR(1) == 0)
					{
						if(animation == 0)
						{
							add(new JLabel(blueShip0),gbc);
						}
						else
						{
							add(new JLabel(blueShip0b),gbc);
						}
						
					}
					if(board.getShipR(1) == 1)
					{
						add(new JLabel(blueShip1),gbc);
					}
					if(board.getShipR(1) == 2)
					{
						add(new JLabel(blueShip2),gbc);
					}
					if(board.getShipR(1) == 3)
					{
						add(new JLabel(blueShip3),gbc);
					}
					
				}
				if(board.getBoardAtCoordinates(i,j) == 2)
				{
					if(board.getShipR(2) == 0)
					{
						add(new JLabel(redShip0),gbc);
					}
					if(board.getShipR(2) == 1)
					{
						add(new JLabel(redShip1),gbc);
					}
					if(board.getShipR(2) == 2)
					{
						add(new JLabel(redShip2),gbc);
					}
					if(board.getShipR(2) == 3)
					{
						add(new JLabel(redShip3),gbc);
					}
					
				}
			}
		}
		
	}
	
}
