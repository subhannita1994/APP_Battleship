
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

                if(name.equals("Player1")){
                    temp = game.getP1().getInitShips();
                    if(setupPhase1){
                        selfShips.setText(Integer.toString(temp));
                        setupPhase1 = false;
                    }
                    else{
                        game.p1play();
                    }
                    hideScreen();
                    game.getP2().getScreen().showScreen();
                }
                if(name.equals("Player2")){
                    temp = game.getP2().getInitShips();
                    if(setupPhase2){
                        selfShips.setText(Integer.toString(temp));
                        setupPhase2 = false;
                    }
                    else{
                        game.p2play();
                    }
                    hideScreen();
                    game.getP1().getScreen().showScreen();
                }
            }
        });
        this.add(next, BorderLayout.CENTER);
        this.add(verticalBox, BorderLayout.SOUTH);
        this.pack();
        showScreen();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	private void hideScreen() { this.setVisible(false);}
	private void showScreen() {this.setVisible(true);}
}
