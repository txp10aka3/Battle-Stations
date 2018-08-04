package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HostServerThread extends Thread 
{
	public static final int PORT = 33333;
	
    private ServerSocket socket;
    private List<GameMessage> incoming;
    private volatile boolean shouldDie;
	
	public HostServerThread()
	{
		incoming = Collections.synchronizedList(new ArrayList<GameMessage>());
		shouldDie = false;
		
		try 
		{
			socket = new ServerSocket(PORT);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			socket = null;
		}
	}
	
	@Override
	public void run()
	{
		while(!shouldDie)
        {
            if (socket != null)
            {
            	try 
            	{
					Socket newSocket = socket.accept();
				} 
            	catch (IOException e) 
            	{
					e.printStackTrace();
				}
            }
            else
            {
            	
            }
        }
	}
	
    public List<GameMessage> getReceived()
    {
        List<GameMessage> received = new ArrayList<>(incoming);
        incoming.clear();
        return received;
    }

    public void setToDie()
    {
        shouldDie = true;
    }
}
