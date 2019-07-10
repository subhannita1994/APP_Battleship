import java.util.ArrayList;

import javax.swing.JPanel;

import java.awt.*;

public class Player {
	
	private int initShips;
	private Screen screen;
	private ArrayList<Ship> fleet;
	private int boardSize = 11;
	private int[][] attackData = new int[boardSize][boardSize];
    private int[][] selfData = new int[boardSize][boardSize];
    private int numberOfShipSunk = 0;
    private Game game;
    private String name;
    
	public Player(String name, Game game, int initShips) {
		this.initShips = initShips;
		this.name = name; this.game = game;
		this.fleet = new ArrayList<Ship>();

		System.out.println(this.name+" created");
	}
	public void addScreen() { 
		this.screen = new Screen(name,game);
	}
	public ArrayList<Ship> getFleet() {
		return this.fleet;
	}

	public Screen getScreen() {return this.screen;}
	public void addShip(Coordinate c1, Coordinate c2, Coordinate c3) {
		if(c1.getX()==boardSize||c2.getX()==boardSize||c3.getX()==boardSize ||c1.getY()==boardSize||c2.getY()==boardSize||c3.getY()==boardSize){
            System.out.print("\nPreventing adding a ship: fleet is full or user clicked too close to the edge");
        }
        else {
            if (isOvelap(c1, c2, c3) ) {
                if(isOvelapAtRotatePoint(c1,c2,c3) && !isShipCollision(c1,c2,c3)){
                    //add vertical ship at c1,c2,c3
                	Coordinate aNew = new Coordinate(c1.getX(), c1.getY());
                    Coordinate bNew = new Coordinate(c1.getX(), c1.getY() + 1);
                    Coordinate cNew = new Coordinate(c1.getX(), c1.getY() + 2);
                    fleet.add(new Ship(aNew, bNew, cNew));
                    selfData[aNew.getX()][aNew.getY()]=1;
                    selfData[bNew.getX()][bNew.getY()]=1;
                    selfData[cNew.getX()][cNew.getY()]=1;
                }
                else {
                    System.out.print("\nyou should only click first block to rotate");
                }

            } else if(fleet.size() < 5){
                fleet.add(new Ship(c1, c2, c3));
                selfData[c1.getX()][c1.getY()]=1;
                selfData[c2.getX()][c2.getY()]=1;
                selfData[c3.getX()][c3.getY()]=1;
            }
        }
	}
	public boolean isOvelap(Coordinate a, Coordinate b, Coordinate c){
        boolean isA = false;
        boolean isB = false;
        boolean isC = false;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++)
            {
                if((a.getX()==i&&a.getY()==j && selfData[i][j]==1))
                    isA = true;
                
                else
                	isA = false;
                if(b.getX()==i&&b.getY()==j&&selfData[i][j] == 1)
                    isB = true;
                else
                	isB = false;
                
                if(c.getX()==i&&c.getY()==j&&selfData[i][j] == 1)
                    isC = true;
                else
                	isC = false;
            }
        }
        if(isA || isB || isC){
            return true;
        }
        return false;
    }
	public boolean isOvelapAtRotatePoint(Coordinate a, Coordinate b, Coordinate c){
        boolean isA = false;
        boolean isB = false;
        boolean isC = false;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++)
            {
            	if((a.getX()==i&&a.getY()==j && selfData[i][j]==1))
                    isA = true;
                
                else
                	isA = false;
                if(b.getX()==i&&b.getY()==j&&selfData[i][j] == 1)
                    isB = true;
                else
                	isB = false;
                
                if(c.getX()==i&&c.getY()==j&&selfData[i][j] == 1)
                    isC = true;
                else
                	isC = false;
            }
        }
        if(isA && isB && isC){
            return true;
        }
        return false;
    }
	// check for ships collision when ship is rotated
    public boolean isShipCollision (Coordinate a, Coordinate b, Coordinate c){
        deleteShip(a,b,c);
        selfData[a.getX()][a.getY()]=0;
        selfData[b.getX()][b.getY()]=0;
        selfData[c.getX()][c.getY()]=0;

        Coordinate aNew = new Coordinate(a.getX(), a.getY());
        Coordinate bNew = new Coordinate(a.getX(), a.getY() + 1);
        Coordinate cNew = new Coordinate(a.getX(), a.getY() + 2);

        if(aNew.getX()==boardSize||bNew.getX()==boardSize||cNew.getX()==boardSize ||aNew.getY()==boardSize||bNew.getY()==boardSize||cNew.getY()==boardSize){
            addShip(a,b,c);
            selfData[a.getX()][a.getY()]=1;
            selfData[b.getX()][b.getY()]=1;
            selfData[c.getX()][c.getY()]=1;
            return true;
        }

        for (int i = 0; i < fleet.size(); i++) {
            if(fleet.get(i).getA().compareCoord(bNew) || fleet.get(i).getB().compareCoord(bNew) || fleet.get(i).getC().compareCoord(bNew) ||
                    fleet.get(i).getA().compareCoord(cNew) || fleet.get(i).getB().compareCoord(cNew) || fleet.get(i).getC().compareCoord(cNew)){
                addShip(a,b,c);
                selfData[a.getX()][a.getY()]=1;
                selfData[b.getX()][b.getY()]=1;
                selfData[c.getX()][c.getY()]=1;
                return true;
            }
        }
        return false;

    }
  //remove ship from fleet array
    public void deleteShip(Coordinate a, Coordinate b, Coordinate c) {
        Ship temp = new Ship(a, b, c);
        for (Ship ship :fleet) {
            if (ship.compareShip(temp)) {
                fleet.remove(ship);
            }
        }
    }
	public void attackShip(Coordinate hit) {
		System.out.print("For "+this.name+"--");
		for(Ship ship : fleet) {
			System.out.print("Trying to hit ship "+fleet.indexOf(ship)+":");
			ship.Hit(hit);
		}

		System.out.println();
	}
	public void setAttackData(int x, int y, String result) {
        if(result.equals("success")){
            attackData[x][y] = 1;
        }
        else if(result.equals("failure")){
            attackData[x][y] = 2;
        }
        this.printAttackData();
    }
	public int[][] getAttackData(){return this.attackData;}
	public int[][] getSelfData(){return this.selfData;}
	public int getNumberOfOwnShipSunk() {return this.numberOfShipSunk;}
	public boolean isHit(Coordinate point){
		System.out.print("For "+this.name+"--");
        for (Ship ship:fleet){
        	System.out.print("Check if hit ship "+fleet.indexOf(ship)+":");
            
            if(ship.isPointHit(point)){
                return true;
            }
        }
        System.out.println();
        return false; // must return false because
    }

    // isPlayerLost determines if the player lost based on the fleet arraylist size
    public boolean isPlayerLost(){
        if(fleet.size()==0){
        	System.out.println(this.name+" lost");
            return true; // Player lost
        }
        else{
        	System.out.println(this.name+" not lost");
            return false;
        }
    }

    // Boolean method returns true if the Ship was sunk
    public boolean isSunk(Coordinate hitCord){
    	System.out.print("Checking for ship sunk for "+this.name+" ");
        for (Ship ship:fleet){
        	System.out.print("Ship "+fleet.indexOf(ship)+":");
            if(ship.isShipSunk()){
                numberOfShipSunk++;
                fleet.remove(ship);
                return true;
            }
        }
        System.out.println();
        return false;
    }
    public void printSelfData(){
        for (int i = 1; i < selfData.length; i++) {
            for (int j = 1; j < selfData[i].length; j++) {
                System.out.print(selfData[j][i] + " ");
            }
            System.out.println();
        }
    }
    public void printAttackData(){
        for (int i = 1; i < attackData.length; i++) {
            for (int j = 1; j < attackData[i].length; j++) {
                System.out.print(attackData[j][i] + " ");
            }
            System.out.println();
        }
    }
}
