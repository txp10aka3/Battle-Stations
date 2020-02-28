package network;

import network.game.GameState;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("gamedatabase")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//@Produces("text/xml")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class GameDatabase 
{
	private GameState gameState = new GameState();
	
	@POST
	@Path("gamestate")
	public GameState getGameState()
	{
		return gameState;
	}
}
