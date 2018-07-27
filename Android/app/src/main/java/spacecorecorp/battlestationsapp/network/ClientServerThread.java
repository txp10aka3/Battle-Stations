package spacecorecorp.battlestationsapp.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientServerThread extends Thread
{
    private static final String LOG_TAG = "ServerThread";

    private Socket socket;
    private List<GameMessage> incoming;
    private volatile boolean shouldDie;

    public ClientServerThread(Socket connectedSocket)
    {
        socket = connectedSocket;
        incoming = Collections.synchronizedList(new ArrayList<GameMessage>());
        shouldDie = false;
    }

    @Override
    public void run()
    {
        while(!shouldDie)
        {
            if (socket != null)
            {
                BufferedReader reader;
                try
                {
                    InputStream is = socket.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(is));
                }
                catch (IOException e)
                {
                    Log.d(LOG_TAG, "Could not get input stream");
                    continue;
                }
                catch (NullPointerException npe)
                {
                    Log.d(LOG_TAG, "socket was null");
                    continue;
                }

                String jsonMessage = null;
                try
                {
                    jsonMessage = reader.readLine();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                if (jsonMessage != null)
                {
                    GameMessage message = GameMessage.fromJSON(jsonMessage);
                    incoming.add(message);
                }
            }
            else
            {
                try
                {
                    Thread.sleep(100, 0);
                }
                catch (InterruptedException e)
                {
                    Log.e(LOG_TAG, "Sleep Read Interruption");
                }
            }
        }
    }

    public synchronized boolean sendMessage(GameMessage message)
    {
        String toSend = message.toJSON() + "\n";
        if (socket != null && socket.isConnected())
        {
            try
            {
                OutputStream os = socket.getOutputStream();
                os.write(toSend.getBytes());
                return true;
            }
            catch (IOException e)
            {
                Log.d(LOG_TAG, "Could not send data to socket, try to reconnect");
            }
        }
        return false;
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
