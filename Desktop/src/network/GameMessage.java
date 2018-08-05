package network;

import org.json.JSONException;
import org.json.JSONObject;

public class GameMessage
{
    private static final String MESSAGE_TYPE_TAG = "type";
    private static final String MESSAGE_CONTENT_TAG = "msg";
    private static final String MESSAGE_SENDER_TEAM_TAG = "tem";
    private static final String MESSAGE_SENDER_ROLE_TAG = "rol";
    private static final String MESSAGE_TRUE_CONTENT = "tcn";

    public static final String START_MESSAGE_TYPE = "sta";
    public static final String UPDATE_MESSAGE_TYPE = "upd";
    public static final String STOP_MESSAGE_TYPE = "stp";

    public enum TEAM {RED, BLUE}
    public enum POSITION
    {
        CAPTAIN, NAVIGATOR, GUNNER,
        SHIELDS, SENSOR, BEAM
    }

    public String messageHeader;
    public String messageContent;
    public TEAM team;
    public POSITION position;
    public String trueContent;

    private GameMessage()
    {
        team = null;
        position = null;
        trueContent = null;
    }

    public static GameMessage generateStartMessage(String userName)
    {
        GameMessage message = new GameMessage();
        message.messageHeader = START_MESSAGE_TYPE;
        message.messageContent = userName;
        return message;
    }

    public static GameMessage generateUpdateMessage(TEAM team, POSITION pos, String trueContent)
    {
        GameMessage message = new GameMessage();
        message.messageHeader = UPDATE_MESSAGE_TYPE;
        JSONObject jsonObject =  new JSONObject();
        try
        {
            jsonObject.put(MESSAGE_SENDER_TEAM_TAG, team == TEAM.RED ? "red" : "blue");
            jsonObject.put(MESSAGE_SENDER_ROLE_TAG, pos.name());
            jsonObject.put(MESSAGE_TRUE_CONTENT, trueContent);
        }
        catch (JSONException json)
        {
            json.printStackTrace();
            return null;
        }
        message.messageContent = jsonObject.toString();
        return message;
    }

    public static GameMessage generateStopMessage(String userName)
    {
        GameMessage message = new GameMessage();
        message.messageHeader = STOP_MESSAGE_TYPE;
        message.messageContent = userName;
        return message;
    }

    public String toJSON()
    {
        try
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(MESSAGE_TYPE_TAG, messageHeader);
            jsonObject.put(MESSAGE_CONTENT_TAG, messageContent);
            return jsonObject.toString();
        }
        catch (JSONException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public static GameMessage fromJSON(String json)
    {
        JSONObject jsonObject;
        GameMessage message = new GameMessage();
        try
        {
            jsonObject = new JSONObject(json);
            message.messageHeader = jsonObject.getString(MESSAGE_TYPE_TAG);
            message.messageContent = jsonObject.getString(MESSAGE_CONTENT_TAG);
            if(message.messageHeader.equals(UPDATE_MESSAGE_TYPE))
            {
                JSONObject subObject = new JSONObject(message.messageContent);
                message.team = subObject.getString(MESSAGE_SENDER_TEAM_TAG).equals("red") ? TEAM.RED : TEAM.BLUE;
                message.position = positionFromName(subObject.getString(MESSAGE_SENDER_ROLE_TAG));
                message.trueContent = subObject.getString(MESSAGE_TRUE_CONTENT);
            }
        }
        catch (JSONException jsonex)
        {
            jsonex.printStackTrace();
        }
        return message;
    }

    public static POSITION positionFromName(String name)
    {
        if(name.equals(POSITION.BEAM.name()))
        {
            return POSITION.BEAM;
        }
        else if(name.equals(POSITION.CAPTAIN.name()))
        {
            return POSITION.CAPTAIN;
        }
        else if(name.equals(POSITION.GUNNER.name()))
        {
            return POSITION.GUNNER;
        }
        else if(name.equals(POSITION.SENSOR.name()))
        {
            return POSITION.SENSOR;
        }
        else if(name.equals(POSITION.NAVIGATOR.name()))
        {
            return POSITION.NAVIGATOR;
        }
        else if(name.equals(POSITION.SHIELDS.name()))
        {
            return POSITION.SHIELDS;
        }
        else
        {
            return null;
        }
    }
}
