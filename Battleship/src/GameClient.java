import java.io.*;
import java.net.*;
import java.util.Formatter;
import java.util.Scanner;

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
	
	public static void main(String[] args) throws Exception
	{
		GameClient gc;
		if ( args.length == 0 )
			gc = new GameClient("127.0.0.1"); 
		else
			gc = new GameClient(args[0]);
	}
}              