import java.util.ArrayList;
import java.awt.*;

public class Player {
	
	private int initShips;
	private Screen screen;
	private ArrayList<Ship> fleet;
	private int[][] attackData = new int[11][11];
    private int[][] selfData = new int[11][11];
    private int numberOfShipSunk = 0;
    
	public Player(String name, Game game, int initShips) {
		this.initShips = initShips;
		this.screen = new Screen(name, game);
		this.fleet = new ArrayList<Ship>();
	}
	
	public ArrayList<Ship> getFleet() {
		return this.fleet;
	}

	public Screen getScreen() {return this.screen;}
	public void addShip(Coordinate c1, Coordinate c2, Coordinate c3) {
		if(c1.getX()==11||c2.getX()==11||c3.getX()==11 ||c1.getY()==11||c2.getY()==11||c3.getY()==11){
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

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++)
            {
                if((a.getX()==i&&a.getY()==j)){
                    if(selfData[i][j] == 1){
                        isA = true;
                    }
                    else{
                        isA = false;
                    }
                }
                if(b.getX()==i&&b.getY()==j){
                    if(selfData[i][j] == 1){
                        isB = true;
                    }
                    else{
                        isB = false;
                    }
                }
                if(c.getX()==i&&c.getY()==j){
                    if(selfData[i][j] == 1){
                        isC = true;
                    }
                    else{
                        isC = false;
                    }
                }
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

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++)
            {
                if((a.getX()==i&&a.getY()==j)){
                    if(selfData[i][j] == 1){
                        isA = true;
                    }
                    else{
                        isA = false;
                    }
                }
                if(b.getX()==i&&b.getY()==j){
                    if(selfData[i][j] == 1){
                        isB = true;
                    }
                    else{
                        isB = false;
                    }
                }
                if(c.getX()==i&&c.getY()==j){
                    if(selfData[i][j] == 1){
                        isC = true;
                    }
                    else{
                        isC = false;
                    }
                }
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

        if(aNew.getX()==11||bNew.getX()==11||cNew.getX()==11 ||aNew.getY()==11||bNew.getY()==11||cNew.getY()==11){
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
        for (int i = 0; i < fleet.size(); i++) {
            if (fleet.get(i).compareShip(temp)) {
                fleet.remove(i);
            }
        }
    }
	public void attackShip(Coordinate hit) {
		for(Ship ship : fleet) {
			ship.Hit(hit);
		}
	}
	public void setAttackData(int x, int y, String result) {
        if(result.equals("success")){
            attackData[x][y] = 1;
        }
        else if(result.equals("failure")){
            attackData[x][y] = 2;
        }
    }
	public int[][] getAttackData(){return this.attackData;}
	public int[][] getSelfData(){return this.selfData;}
	public int getNumberOfOwnShipSunk() {return this.numberOfShipSunk;}
	public boolean isHit(Coordinate point){
        for (int i=0;i<fleet.size();){
            Ship temp = fleet.get(i);
            if(temp.isPointHit(point)){
                return true;
            }
            else{
                i++; // increase the counter if the the ship i didn't contain a point
            }
        }
        return false; // must return false because
    }

    // isPlayerLost determines if the player lost based on the fleet arraylist size
    public boolean isPlayerLost(){
        if(fleet.size()==0){
            return true; // Player lost
        }
        else{
            return false;
        }
    }

    // Boolean method returns true if the Ship was sunk
    public boolean isSunk(Coordinate hitCord){
        for (int i=0;i<fleet.size();){
            Ship temp = fleet.get(i);
            if(temp.isShipSunk()){
                numberOfShipSunk++;
                fleet.remove(i);
                return true;
            }
            else{
                i++; // increase the counter if the ship wasn't sunk
            }
        }
        return false;
    }
}
