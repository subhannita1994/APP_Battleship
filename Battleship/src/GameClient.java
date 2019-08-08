import java.io.*;
import java.net.*;
import java.util.Formatter;
import java.util.Scanner;

/**
 * This class will allow to run client for different players
 * @author group3
 * @version 3.0
 */
public class GameClient 
{
	private Socket connection;
	private String hostIp; 
	private Scanner input; 
	private PrintStream  output; 
	public GameClient(String hostIp)
	{
		this.hostIp = hostIp;
		StartConnection();
		
		//Create UI
		
	}

	/**
	 * establishing connection through socket
	 *
	 */
	
	public void StartConnection()
	{
		try {
			connection = new Socket(hostIp, 1113 );
			input = new Scanner( connection.getInputStream() );    
			output = new PrintStream( connection.getOutputStream() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WelcomeScreen ws = new WelcomeScreen(connection, input, output);
	}

	/**
	 * main function
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		GameClient gc;
		if ( args.length == 0 )
			gc = new GameClient("moore");
		else
			gc = new GameClient(args[0]);
	}
}              
