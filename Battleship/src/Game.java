import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Main driver class of Battleship Game
 * @author Group 3
 * @version 1.2
 */
public class Game {

	private Player player1;	
	private Player player2;
	private HashMap<String,Ship> shipInfo = new HashMap<String,Ship>();

	/**
	 * Class constructor setting up the GUI of the initial welcome screen 
	 */
	public Game() {

		super();
		shipInfo.put("Carrier", new Ship("Carrier",5));
		shipInfo.put("Battleship", new Ship("Battleship",4));
		shipInfo.put("Cruiser", new Ship("Cruiser",3));
		shipInfo.put("Submarine", new Ship("Submarine",3));
		shipInfo.put("Destroyer", new Ship("Destroyer",2));

	}

	public HashMap<String,Ship> getShipInfo()
	{
		return shipInfo;
	}
	public void setP1(Player p)
	{
		this.player1 = p;
	}
	
	public void setP2(Player p)
	{
		this.player2 = p;
	}
	
	/**
	 * @return the first player
	 */
	public Player getP1() {
		return this.player1;
	}

	/**
	 * @return the second player
	 */
	public Player getP2() {
		return this.player2;
	}

	/**
	 * @param	p	a player
	 * @return the opponent of the specified player
	 */
	public Player getOppo(Player p) {
		if(p.getName().equals("Player 1"))
			return this.player2;
		else
			return this.player1;
	}
	
	public void startGameOnNetwork()
	{
		Player currentPlayer = this.getP1();
		while(true)
		{
			//1. ask player to do attack
			currentPlayer.askToDoAttack();
			
			//2. Process attck
			if(currentPlayer.getCurShots() == currentPlayer.getTotalShots())
			{
				for(Coordinate c : currentPlayer.getShots().keySet()) {	
					currentPlayer.getShots().put(c, this.getOppo(currentPlayer).hit(c));	//update each shot with hit/miss
				}
				

				//draw();	//draw hit and miss shots
				currentPlayer.getShots().clear();
				Player oppo = this.getOppo(currentPlayer);
				LinkedList<Ship> sunkShips = oppo.getSunkShips();
				if(sunkShips.size() == 5) {
					
					//JOptionPane.showMessageDialog(this,"Congratulations! "+currentPlayer.getName()+" is the winner!");
					System.out.println(currentPlayer.getName()+" wins");
					System.exit(0);
				}
				
				//HETAL to decide the flow
				/*
				if(sunkShips.size()>this.oppoSunkShips) {
					System.out.println("new ships sunk");
					oppo.getScreen().getAttackBoard().decreaseCurShotsPerTurn(sunkShips.size()-this.oppoSunkShips);	//update current shots per turn for opponent player
					oppoSunkShips = sunkShips.size();//update oppoSunkShips
					this.player.getScreen().getFleetAttack().updateFleet(sunkShips, oppo);
					oppo.getScreen().getFleetAttack().updateFleet(sunkShips, oppo);
				}*/
				

				//3. check mode else change player
				currentPlayer = oppo;
			}
			
			
			
		}
	}
}
