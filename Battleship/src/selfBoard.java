import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
public class selfBoard extends JPanel{

	private String name;
	private Game game;
	private JPanel self;
	private JPanel temp;
	private boolean isSelfBoardListener;

    private Point firstPoint = new Point(0,0);
    private Point secondNextPoint = new Point(0,0);
    private Point thirdNextPoint = new Point(0,0);
    private JPanel secondNextCell = null;
    private JPanel thirdNextCell = null;

    private JPanel thePanel = new JPanel();
	
	public selfBoard(String name, Game game) {
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

        JPanel firstCell = new JPanel();
        firstCell.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
        firstCell.setPreferredSize(new Dimension(20, 20)); // for demo purposes only
        firstCell.setBackground(Color.black);

        firstCell.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isSelfBoardListener) {
                    firstPoint = firstCell.getLocation();
                    double xPos = (firstPoint.getX()/20+1);
                    int x = (int) xPos;
                    double yPos = (firstPoint.getY()/20+1);
                    int y = (int) yPos;

                    double xPos2 = (firstPoint.getX()/20+2);
                    int x2 = (int) xPos2;
                    double yPos2 = (firstPoint.getY()/20+1);
                    int y2 = (int) yPos2;

                    double xPos3 = (firstPoint.getX()/20+3);
                    int x3 = (int) xPos3;
                    double yPos3 = (firstPoint.getY()/20+1);
                    int y3 = (int) yPos3;


                    System.out.print("\nLocation (X: " + x + " Y: " + y + ")");

                    secondNextPoint = new Point((int)(firstPoint.getX()+20),(int)(firstPoint.getY()));
                    thirdNextPoint = new Point((int)(firstPoint.getX()+40),(int)(firstPoint.getY()));
                    Coordinate a = new Coordinate(x,y);
                    Coordinate b = new Coordinate(x2,y2);
                    Coordinate c = new Coordinate(x3,y3);

                    getComp2(secondNextPoint);
                    getComp3(thirdNextPoint);

                    if(name.equals("Player1")){
                        game.getP1().addShip(a,b,c); // Create new ship object
                        draw();
                    }
                    if(name.equals("Player2")){
                        game.getP2().addShip(a,b,c); // Create new ship object
                        draw();
                    }
                }
            }
        });
        return firstCell;
    }
    public void setSelfBoardListener (boolean selfBoardListener){
        this.isSelfBoardListener = selfBoardListener;
    }
    public void getComp2(Point newPoint){
        secondNextCell = this.getComponentAt(newPoint);
    }
    public void getComp3(Point newPoint){
        thirdNextCell = this.getComponentAt(newPoint);
    }
    public void draw(){


        int[][] temp=null;
        if(name.equals("Player1")){
            temp = game.getP1().getSelfData();
        }
        else if(name.equals("Player2")){
            temp = game.getP2().getSelfData();
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++)
            {
                if(temp[i][j]==1){
                    int x = numberToPanel(i);
                    int y = numberToPanel(j);

                    Point p = new Point(x,y);
                    getJpanel(p);
                    thePanel.setBackground(Color.CYAN);
                }
               if(temp[i][j]==0){
                   int x = numberToPanel(i);
                   int y = numberToPanel(j);
//                   System.out.println("\ninside black "+x +"      "+ y);

                   Point p = new Point(Math.abs(x),Math.abs(y));
                   getJpanel(p);

                   thePanel.setBackground(Color.BLACK);

                }
            }
        }
    }
    public void getJpanel(Point newPoint){
        thePanel = this.getComponentAt(newPoint);
    }
    public int numberToPanel(int s){
        int temp = (s-1)*20;
        return temp;
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
