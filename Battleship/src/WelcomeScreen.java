

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Class for defining welcome screen for allowing user in selecting gameplay option
 * @author group3
 * @version 3.0
 */

public class WelcomeScreen extends JFrame implements ActionListener{
	
	private JRadioButton salvoVariation;
	private JRadioButton normalVariation;
	private JRadioButton computerMode;
	private JRadioButton humanMode;
	private ButtonGroup variation;
	private ButtonGroup mode;
	private JButton start;
	private Socket connection;
	private String hostIp; 
	private Scanner input; 
	private PrintStream output; 
	public WelcomeScreen(Socket connection, Scanner input, PrintStream output) 
	{
		this.connection = connection;
		this.input = input;
		this.output = output;
		
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Welcome to Battleship Game!", JLabel.CENTER), BorderLayout.NORTH);
		JPanel gameInstructions = new JPanel();
		gameInstructions.setLayout(new GridBagLayout());
		//TODO : game instructions
		this.add(gameInstructions, BorderLayout.CENTER);
		JPanel selection = new JPanel();
		selection.setLayout(new BorderLayout());
		selection.add(new JLabel("Select a variation and mode of play"), BorderLayout.NORTH);
		variation = new ButtonGroup();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		mode = new ButtonGroup();
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		salvoVariation = new JRadioButton("Salvo Variation");
		salvoVariation.setActionCommand("salvo");
		normalVariation = new JRadioButton("Normal Variation");
		normalVariation.setActionCommand("normal");
		variation.add(salvoVariation);
		variation.add(normalVariation);
		panel1.add(normalVariation,BorderLayout.NORTH);
		panel1.add(salvoVariation,BorderLayout.SOUTH);
		computerMode = new JRadioButton("Play against Computer");
		computerMode.setActionCommand("computer");
		humanMode = new JRadioButton("Play against another player");
		humanMode.setActionCommand("human");
		mode.add(computerMode);
		mode.add(humanMode);
		panel2.add(computerMode,BorderLayout.SOUTH);
		panel2.add(humanMode,BorderLayout.NORTH);
		selection.add(panel1, BorderLayout.WEST);
		selection.add(panel2, BorderLayout.EAST);
		start = new JButton("Start Game!");
		start.addActionListener(this);
		selection.add(start,BorderLayout.SOUTH);
		this.add(selection, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	/**
	 * Action listener to start button to initiate game setup.
	 * Displays error message if one of {salvo, normal} variations and one of {human, computer} mode not selected.
	 * Creates Player objects accordingly and their respective screens.
	 * @param	e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(variation.getSelection() == null || mode.getSelection() == null)
			JOptionPane.showMessageDialog(this,"Select a variation as well as mode of play");
		else {
			//Game g = new Game();
			//player1 = new Player("Player 1",g);
			if(mode.getSelection().getActionCommand().equals("computer")) {
				System.out.println("computer selected by client");
				output.format("%s\n", "computer");
				output.flush();
				//player2 = new Computer("Player 2",g);
			}
			else {
				System.out.println("human selected by client");
				output.format("%s\n", "human");
				output.flush();
				//player2 = new Player("Player 2",this);
			}
			
			///g.setP1(player1);
			//g.setP2(player2);
			//player1.addScreen();
			//player2.addScreen();
			
			if(variation.getSelection().getActionCommand().equals("salvo")) {
				System.out.println("salvo selected by client");
				output.format("%s\n", "salvo");
				output.flush();
				//shots per turn set to 5 initially by default
			}
			else { 
				System.out.println("normal selected by client");
				output.format("%s\n", "normal");
				output.flush();
				//player1.getScreen().getAttackBoard().setCurShotsPerTurn(1);
				//player2.getScreen().getAttackBoard().setCurShotsPerTurn(1);

			}
			this.setVisible(false);
			PlayerScreen ps = new PlayerScreen(connection, input, output, variation.getSelection().getActionCommand());
			//TODO: game instructions
			//JOptionPane.showMessageDialog(this,"To set up a ship on your board : "
			//		+ "Step 1 - select alignment of ship. "
			//		+ "Step 2 - drag a ship and drop on to board. "
			//		+ "On dragging to board, green cells will show if you can place the ship there");
			//player1.getScreen().getSelfBoard().setSelfGridListener(true);
			//try {
				//player1.getScreen().setUpScreen();
			//} catch (InterruptedException e1) {
			//	e1.printStackTrace();
			//}
			//player2.getScreen().setVisible(false);
		}	
	}

	/**
	 * Method for radiobutton
	 * @return the salvoVariation radio button for deliberate clicks during testing
	 */
	public JRadioButton getSalvoVariation() {
		return salvoVariation;
	}

	/**
	 * method for normal variation
	 * @return the normalVariation radio button for deliberate clicks during testing
	 */
	public JRadioButton getNormalVariation() {
		return normalVariation;
	}

	/**
	 * method for returning computer state
	 * @return the computerMode radio button for firing deliberate clicks during testing
	 */
	public JRadioButton getComputerMode() {
		return computerMode;
	}

	/**
	 * method for returning human state
	 * @return the humanMode radio button for firing deliberate clicks during testing
	 */
	public JRadioButton getHumanMode() {
		return humanMode;
	}

	/**
	 * method for button start
	 * @return the start button to initiate game and firing deliberate clicks during 	testing
	 */
	public JButton getStartBtn() {
		return start;
	}
}
