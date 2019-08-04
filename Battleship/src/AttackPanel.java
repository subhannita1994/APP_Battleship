import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AttackPanel extends JPanel implements MouseListener{

	private Socket connection;
	private Scanner input; 
	private PrintStream output; 
	private String mode;
	private JPanel self;	//the JPanel containing the board
	private boolean attackGridListener = false;	//controller for when to turn on attack grid to listening mode
	private JPanel[][] cells = new JPanel[10][10];	//array of JPanels for the board
	
	public AttackPanel(Socket connection, Scanner input, PrintStream output, String mode)
	{
		this.connection = connection;
		this.input = input;
		this.output = output;
		this.mode = mode;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));
        self = new JPanel();
        self.setLayout(new GridLayout(10,10));
        this.add(new JLabel("Select your shots here!"));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
            	JPanel firstCell = new JPanel();
            	firstCell.setName(i+","+j);
            	firstCell.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
                firstCell.setPreferredSize(new Dimension(40, 40)); // for demo purposes only
                firstCell.setBackground(Color.blue);
                cells[j][i] = firstCell;
                self.add(firstCell);
            }
        }
        self.addMouseListener(this);
        this.attackGridListener = true;
        this.add(self);
	}
	

	/**
	 * set the attackGridListener
	 * @param b true if attackBoard is supposed to listen; false otherwise
	 */
	public void setAttackGridListener(boolean b) {
		this.attackGridListener = b;
	}
	

	/** This method is called when player clicks on attack board but ignores if the cell is already clicked before.
	 * Player gets to take all allowable shots per turn before getting to know the outcome (hit/miss) in the next turn
	 * Once all allowable shots are taken the following are done:
	 * 		1. timer for this player is stopped
	 * 		2. shots counter is reset
	 * 		3. judgment is passed on all the shots (hit/miss) 
	 * 		4. show results(or paint) on this player's attack board and opponent's self board according to judgment
	 * 		5. For Salvo variation : if new ships are sunk from opponent's fleet: 
	 * 			5.1. decrease opponent's allowable shots per turn by 1
	 * 			5.2. update oppoSunkShips field
	 * 			5.3. update icons in the center panel
	 * 		6. if all ships sunk from opponent's fleet, display message and close
	 * Hide this Screen and get opponent's gamePlay Screen
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.attackGridListener) {
				this.attackGridListener = false;
				int x = (int) e.getX()/40;
				int y = (int) e.getY()/40;
				System.out.println("Client: Attack Board: clicked "+x+","+y);
				
				//Send attack to server side
				output.format("%s\n", "attack," + x+","+y);
				output.flush();
				
				//get result of attack
				String result1 = input.nextLine();
				String[] lines = result1.split("\\|", 11);

				System.out.println("Client coordinates with result:" + result1);
				if(lines[0].equals("false"))
{
					System.out.println("Client: It's already cliked try again");
}
				//get coordinates from server
				System.out.println("Client: attack result");
				int[][] temp = new int[10][10];
				for(int i=0;i<10;i++)
				{
					String[] t = lines[i+1].split("\\,",10);
					System.out.println(lines[i+1]);
					for(int j=0;j<10;j++)
					{
						try {
						temp[i][j] = Integer.parseInt(t[j]);
						}
						catch(Exception ex){
							temp[i][j] = 0;
						}
					}
				}
				
				draw(temp);
				
				//decide wait for event or not
				String decide = input.nextLine();
				if(decide.equals("getattckdata"))
				{
					this.attackGridListener = true;
					System.out.println("Continue selection");
				}
				else 
				{
					System.out.println("Waiting opponant to finish or winning");
					decide = input.nextLine();
					System.out.print("HHHH " + decide);
					//wait for opponant or winner flow
				}
			
		}
	}

	/**
	 * repaint cells when ships is dragged over them, provided ship can be placed there
	 * @param iStart	starting y coordinate
	 * @param jStart	starting x coordinate
	 */
	public void draw(int[][] temp) {
		Color c = null;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(temp[i][j]==0)
					c = Color.blue;
				else if(temp[i][j]==1)
					c = Color.cyan;
				else if (temp[i][j]==2)
					c = Color.DARK_GRAY;
				else if(temp[i][j]==3)
					c = Color.magenta;
				cells[i][j].setBackground(c);
			}
		}
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
