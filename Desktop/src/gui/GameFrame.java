package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import network.GameDatabase;
import network.player.GamePlayer;

public class GameFrame extends JFrame
{
	/**
	 * Requested by JFrame
	 */
	private static final long serialVersionUID = 4236762546533051047L;
	
	private Dimension screenSize;
	private int frameWidth;
	private int frameHeight;
	private GameScreen gameScreen;
	private Board board;
	private JPanel panel;
	
	private Server server;
	private ArrayList<GamePlayer> players;
	
	public GameFrame()
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameWidth = screenSize.width;
		frameHeight = screenSize.height;
		setBounds(0, 0, frameWidth, frameHeight);
		board = new Board(10);
		
		players = null;
		JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(GameDatabase.class);
        factoryBean.setResourceProvider(new SingletonResourceProvider(new GameDatabase()));
 
        Map<Object, Object> extensionMappings = new HashMap<Object, Object>();
        extensionMappings.put("xml", MediaType.APPLICATION_XML);
        extensionMappings.put("json", MediaType.APPLICATION_JSON);
        factoryBean.setExtensionMappings(extensionMappings);
 
        List<Object> providers = new ArrayList<Object>();
        providers.add(new JAXBElementProvider());
        providers.add(new JacksonJsonProvider());
        factoryBean.setProviders(providers);
 
        factoryBean.setAddress("http://localhost:8080/");
        server = factoryBean.create();
		
		/*
		 * gameScreen = new GameScreen(board, server, frameWidth, frameHeight);
		 * add(gameScreen);
		 */
		
		goToStartScreen();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void goToStartScreen()
	{
		swapPanel(new StartScreen());
	}
	
	public void goToPlayersScreen()
	{
		swapPanel(new PlayerScreen(server));
	}
	
	public void gotToGameScreen(ArrayList<GamePlayer> player)
	{
		players.addAll(player);
		swapPanel(new GameScreen(board, server, players, frameWidth, frameHeight));
	}
	
	private void swapPanel(JPanel newFrame)
	{
		if(panel != null)
			this.remove(panel);
		this.add(newFrame);
		this.revalidate();
		panel = newFrame;
	}
	
	public Server getServer()
	{
		return server;
	}
}
