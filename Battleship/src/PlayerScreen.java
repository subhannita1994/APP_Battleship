import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

public class PlayerScreen extends JFrame {
	private Socket connection;
	private Scanner input; 
	private PrintStream output; 
	private String mode;
	
	
	private JPanel mainPanel;
	public PlayerScreen(Socket connection, Scanner input, PrintStream output, String mode)  
	{
		super();
		
		this.connection = connection;
		this.input = input;
		this.output = output;
		this.mode = mode;
		
		mainPanel = new PlacementPanel(connection, input, output, mode, this);
		add(mainPanel);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void SetAttackPanel()
	{
		remove(mainPanel);
		String decide = input.nextLine();
		if(decide.equals("getattckdata"))
		{
			System.out.println("Continue selection");
		}
		this.mainPanel = new AttackPanel(connection, input, output, mode);
		add(mainPanel);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
