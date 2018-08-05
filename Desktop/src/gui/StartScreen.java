package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class StartScreen extends JPanel
{
	/**
	 * Requested by JPanel
	 */
	private static final long serialVersionUID = -4530832044829531443L;

	public StartScreen()
	{
		JButton start = new JButton("Start Game");
		StartScreen panel = this;
		start.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				((GameFrame) SwingUtilities.getWindowAncestor(panel)).goToPlayersScreen();
			}
		});
	}
}
