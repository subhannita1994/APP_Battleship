import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;
import java.util.LinkedList;
public class attackBoard extends JPanel{

	private String name; private Game game;
	private JPanel self;
	private JPanel temp;
	private boolean isAttackBoardListener;
	private int enemyShipSunkP1 = 0;
	private int enemyShipSunkP2 = 0;
	private JPanel thePanel = new JPanel();
	
	
	public attackBoard(String name, Game game) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        self = new JPanel();
        self.setLayout(new GridLayout(0,10));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                temp = getCell();
                self.add(temp);
            }
        }
        this.add(self);
		this.name = name; this.game = game;
		
	}
	
	private JPanel getCell()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        panel.setPreferredSize(new Dimension(20, 20)); // for demo purposes only

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isAttackBoardListener) {

                    Point i = panel.getLocation();
                    double xPos = (i.getX() / 20 + 1);
                    int x = (int) xPos;
                    double yPos = (i.getY() / 20 + 1);
                    int y = (int) yPos;

                    if (name.equals("Player1")) {
                        if(!game.getTakeTurnAttack()) {
                            game.setTakeTurnAttack(true);
                            Coordinate hit = new Coordinate(x, y);
                            game.getP2().attackShip(hit);

                            boolean success = game.getP2().isHit(hit);
                            if (success) {
                                game.getP1().setAttackData(x, y, "success");
                                draw();
                            } else {
                                game.getP1().setAttackData(x, y, "failure");
                                draw();
                            }

                            boolean isSunk = game.getP2().isSunk(hit);
                            if (isSunk) {
                                enemyShipSunkP1++;
                                game.getP1().getScreen().getOppoShipsSunk().setText(Integer.toString(enemyShipSunkP1));
                                JOptionPane.showMessageDialog(panel, "Player's 2 ship was sunk! Congratulations!\nclick OK will transition to player 2 screen");
                                game.getP1().getScreen().hideScreen();	//check
                                game.getP2().getScreen().showScreen();	//check
                                String ownShipSunkPlayer2 = Integer.toString(game.getP2().getNumberOfOwnShipSunk());
                                game.getP2().getScreen().getselfShipSunk().setText(ownShipSunkPlayer2);
                            }
                        }
                        boolean lost = game.getP2().isPlayerLost();
                        //change from
                            if (lost) {
                                game.setState(game.getEndOfTheGame());
                                JOptionPane.showMessageDialog(panel, "You(player 1) WON! Congratulations!\nClick OK will Exit the game");
                                game.p1play(game);
                            }
                        //change to
                        }
                        if (name.equals("Player2")) {
                            if(game.getTakeTurnAttack()) {
                                game.setTakeTurnAttack(false);
                                Coordinate hit = new Coordinate(x, y);
                                game.getP1().attackShip(hit);

                                boolean success = game.getP1().isHit(hit);
                                if (success) {
                                    System.out.print("player2 attack");
                                    game.getP2().setAttackData(x, y, "success");
                                    draw();
                                } else {
                                    game.getP2().setAttackData(x, y, "failure");
                                    draw();
                                }

                                boolean isSunk = game.getP1().isSunk(hit);
                                if (isSunk) {
                                    enemyShipSunkP2++;
                                    game.getP2().getScreen().getOppoShipsSunk().setText(Integer.toString(enemyShipSunkP2));
                                    JOptionPane.showMessageDialog(panel, "Player's 1 ship was sunk! Congratulations!\nclick OK will transition to player 1 screen");
                                    game.getP2().getScreen().hideScreen();	//check
                                    game.getP1().getScreen().showScreen();
                                    String ownShipSunkPlayer1 = Integer.toString(game.getP1().getNumberOfOwnShipSunk());
                                    game.getP1().getScreen().getselfShipSunk().setText(ownShipSunkPlayer1);
                                }
                            }
                                boolean lost = game.getP1().isPlayerLost();
                                //change from
                                if (lost) {
                                    game.setState(game.getEndOfTheGame());
                                    JOptionPane.showMessageDialog(panel, "You(player 2) WON! Congratulations!\nClick OK will Exit the game");
                                    game.p2play(game);
                                }
                                //change to
                            }
                        }


            }
        });

        return panel;
    }
	
	public void setAttackBoardListener (boolean b){
        this.isAttackBoardListener = b;

    }
    public void getJpanel(Point newPoint){
        thePanel =  this.getComponentAt(newPoint);	//check
    }
    
    public void draw(){

        int[][] temp=null;
        if(name.equals("Player1")){
            temp = game.getP1().getAttackData();
        }
        else if(name.equals("Player2")){
            temp = game.getP2().getAttackData();
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++)
            {
                if(temp[i][j]==1){
                    int x = numberToPanel(i);
                    int y = numberToPanel(j);

                    Point p = new Point(x,y);
                    getJpanel(p);
                    thePanel.setBackground(Color.GREEN);
                }
                if(temp[i][j]==2){
                    int x = numberToPanel(i);
                    int y = numberToPanel(j);

                    Point p = new Point(x,y);
                    getJpanel(p);
                    thePanel.setBackground(Color.WHITE);

                }
            }
        }
    }
    
    public int numberToPanel(int s){
        int temp = (s-1)*20;
        return temp;
    }

    public boolean getAttackBoardListener() {
        return isAttackBoardListener;
    }
    public JPanel getComponentAt( Point p) {
        Component comp = null;
        for (Component child : self.getComponents()) {
            if (child.getBounds().contains(p)) {
                comp = child;
            }
        }
        return (JPanel)comp;
    }
}
