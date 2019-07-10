
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class extending JFrame for designing the console for game
 * @version 1.1
 * @author group3
 */
public class Screen extends JFrame{

	private int temp;
	private attackBoard ab;
	private selfBoard sb;
	private JLabel selfShips;
	private JLabel selfShipsSunk;
	private JLabel oppoShipsSunk;
	private boolean setupPhase1 = true;
	private boolean setupPhase2 = true;
	private Game game;
	
	public Screen(String name, Game game) {
		super(name);
		this.game = game;
		this.ab = new attackBoard(name,game);
		this.sb = new selfBoard(name, game);
		
		this.setLayout(new BorderLayout());
        this.add(sb, BorderLayout.EAST);
        this.add(ab, BorderLayout.WEST);
        this.add(new JLabel(name), BorderLayout.NORTH);

        JButton next = new JButton("next");

        Box verticalBox = Box.createVerticalBox();

        Box horizontalBox0 = Box.createHorizontalBox();
        horizontalBox0.add(new JLabel("Status for: "+name));
        verticalBox.add(horizontalBox0, BorderLayout.WEST);

        Box horizontalBox1 = Box.createHorizontalBox();
        horizontalBox1.add(new JLabel("Own ships: "));
        selfShips = new JLabel(""+Integer.toString(temp));
        horizontalBox1.add(selfShips);
        verticalBox.add(horizontalBox1, BorderLayout.SOUTH);

        Box horizontalBox2 = Box.createHorizontalBox();
        horizontalBox2.add(new JLabel("Own ships sunk: "));
        selfShipsSunk = new JLabel(""+Integer.toString(temp));
        horizontalBox2.add(selfShipsSunk);
        verticalBox.add(horizontalBox2, BorderLayout.EAST);

        Box horizontalBox3 = Box.createHorizontalBox();
        horizontalBox3.add(new JLabel("Enemy's ships sunk: "));
        oppoShipsSunk = new JLabel(""+Integer.toString(temp));
        horizontalBox3.add(oppoShipsSunk);
        verticalBox.add(horizontalBox3);
        
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("next pressed by "+name);
                if(name.equals("Player1")){
                    temp = game.getP1().getFleet().size();
                    if(setupPhase1){
                        selfShips.setText(Integer.toString(temp));
                        setupPhase1 = false;
                        hideScreen();
                        game.getP2().getScreen().showScreen();
                        System.out.println("Player 1 ships:");
                        game.getP1().printSelfData();
                        //change from ai
                        Coordinate a = new Coordinate(1,1);
        	            Coordinate b = new Coordinate(2,1);
        	            Coordinate c = new Coordinate(3,1);
        	            game.getP2().addShip(a,b,c);
        	            a = new Coordinate(1,3);
        	            b = new Coordinate(2,3);
        	            c = new Coordinate(3,3);
        	            game.getP2().addShip(a, b, c);
        	            System.out.println("Player 2 ships:");
        	            game.getP2().printSelfData();
        	            
        	            //change to ai
                    }
                    else{
                    	hideScreen();
                        game.getP2().getScreen().showScreen();
                        game.p2play(game);
                    }
                    
                }
                if(name.equals("Player2")){
                    temp = game.getP2().getFleet().size();
                    if(setupPhase2){
                        selfShips.setText(Integer.toString(temp));
                        setupPhase2 = false;
                        System.out.println("Game starts!");
                        
                    }
                    	hideScreen();
                        game.getP1().getScreen().showScreen();
                        game.p1play(game);
                    
                        
                    
                    
                }
            }
        });
        this.add(next, BorderLayout.CENTER);
        this.add(verticalBox, BorderLayout.SOUTH);
        this.pack();
        showScreen();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(name+"'s screen created");

	}

    /**
     * Function for fetching different phases of game
     *
     */
	public void hideScreen() { this.setVisible(false);}
	public void showScreen() {this.setVisible(true);}
	public JLabel getselfShipSunk() {return this.selfShipsSunk;}
	public JLabel getSelfShips() {return this.selfShips;}
	public JLabel getOppoShipsSunk() {return this.oppoShipsSunk;}
	public selfBoard getSelfBoard(){

        for(Component child : this.getContentPane().getComponents()){

            if(child instanceof selfBoard ){
                return (selfBoard)child;
            }
        }
        return null;
    }

    /**
     * Method for attack board instance
     * @return
     */
    public attackBoard getAttackBoard(){
        for(Component child : this.getContentPane().getComponents()){
            if(child instanceof attackBoard ){
                return (attackBoard) child;
            }

        }
        return null;
    }
}
