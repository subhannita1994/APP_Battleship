import java.awt.TextArea;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.*;

/**
 * This class is running game server which allows for playing multiplayer game
 * @version 3.0
 * @author group3
 */
public class GameServer extends JFrame{

	private ServerSocket server;
	private Socket connection;
	private Player players[];
	private TextArea outputArea;
	private Game game;
	
	public GameServer() throws Exception 
	{
		super("Battleship server");
		game = new Game();
		players = new Player[2];
		server = new ServerSocket(1113);
		outputArea = new TextArea ();
		outputArea.setText("Game server started on port : 1111\n");
		add( "Center", outputArea);
		setSize(500, 500);
		setVisible(true);	
	}

	/**
	 * method for executing server
	 */

	public void execute()
	{
		try
		{
			for ( int i = 0; i < players.length; i++ ) {
				
				players[i] = new Player( server.accept(), "Player " + (i+1), game, outputArea);
				String mode = players[i].getInputStream().nextLine();
				outputArea.append(mode + " selected by player\n");
				if(mode.equals("computer"))
				{
					outputArea.append("Don't wait for another player. Play with computer\n");
					game.setP1(players[i]);
					game.setP2(new Computer(outputArea, "Computer Player",game));
					break;
				}
				else 
				{
					if(i == 0)
					{
						outputArea.append("Wait for another player to connect\n");
						game.setP1(players[i]);
					}
					else 
					{
						outputArea.append("Both player connected\n");
						game.setP2(players[i]);
					}
				}
			}
			
			game.getP1().ProcessPlacement();
		}
		catch (IOException e)
		{	e.printStackTrace(); System.exit(1);	}
	}

	/**
	 * main method for executing server
	 * @param args
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception 
	{
		GameServer gs = new GameServer();
		gs.

				execute();
	}                    
}
