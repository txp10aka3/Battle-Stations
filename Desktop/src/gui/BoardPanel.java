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
	private ImageIcon box;
	private ImageIcon ship1;
	private ImageIcon ship2;
	public BoardPanel(Board b, int fw, int fh)
	{
		box = new ImageIcon(BoardPanel.class.getResource("/art/Box.png"));
		ship1 = new ImageIcon(BoardPanel.class.getResource("/art/bbox.png"));
		ship2= new ImageIcon(BoardPanel.class.getResource("/art/rbox.png"));
		frameWidth = fw;
		frameHeight = fh;
		setBounds(0,0,frameWidth-50,frameHeight-50);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		board = b;
		for(int i = 0; i<board.getSize(); i++)
		{
			for(int j = 0; j<board.getSize(); j++)
			{
				gbc.gridx = j;
				gbc.gridy = i;
				if(board.getBoardAtCoordinates(i,j) == 0)
				{
					add(new JLabel(box),gbc);
				}
				if(board.getBoardAtCoordinates(i,j) == 1)
				{
					add(new JLabel(ship1),gbc);
				}
				if(board.getBoardAtCoordinates(i,j) == 2)
				{
					add(new JLabel(ship2),gbc);
				}
			}
		}
		
	}
	
}
