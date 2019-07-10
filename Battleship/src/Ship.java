/**
 * This class will define the ships controlling its placement and its coordinates
 * @version 1.1
 * @author group3
 */
public class Ship {

    private Coordinate a;
    private Coordinate b;
    private Coordinate c;

    // Boolean should be true if the point was hit by another player
    boolean aHit;
    boolean bHit;
    boolean cHit;

    // Constructor
    Ship(Coordinate a,Coordinate b,Coordinate c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Method to compare the ships if they are placed at same position or not
     * @param ship
     * @return
     */
    public boolean compareShip(Ship ship){
        if(ship.getA().compareCoord(this.a) && ship.getB().compareCoord(this.b) && ship.getC().compareCoord(this.c)){
            return true;
        }
        return false;
    }

    public Coordinate getA() {
        return a;
    }

    public Coordinate getB() {
        return b;
    }

    public Coordinate getC() {
        return c;
    }

    /**
     * To check if the ship is hit or not
     * @param hit
     * @return
     */
    public boolean isPointHit(Coordinate hit){
        if(hit.getX() == a.getX()&&hit.getY() == a.getY()){
        	System.out.print("aHit hit");
            return true;
        }
        else if(hit.getX() == b.getX()&&hit.getY() == b.getY()){
        	System.out.print("bHit hit");
            return true;
        }
        else if(hit.getX() == c.getX()&&hit.getY() == c.getY()){
        	System.out.print("cHit hit");
            return true;
        }
        System.out.print("not hit");
        return false;
    }

    /**
     * Function for calling when the ship is hit
     * @param hit
     */
    public void Hit(Coordinate hit){
        if(hit.getY() == a.getY() && hit.getX() == a.getX()){
        	System.out.print("aHit hit");
        	aHit =true;
        }
        if(hit.getY() == b.getY() && hit.getX() == b.getX()){
        	System.out.print("aHit hit");
        	bHit =true;
        }
        if(hit.getY() == c.getY() && hit.getX() == c.getX()){
        	System.out.print("aHit hit");
        	cHit =true;
        }
    }

    /**
     * Function to check if ship is hit or not
     * @return
     */
    public boolean isShipSunk(){
        if(aHit && bHit && cHit){
        	System.out.print("hit");
            return true;
        }
        else{
        	System.out.print("not hit");
            return false;
        }
    }

    /**
     * Function to debug if ship is being hit or not
     */
    public void printShip(){
        String result = "A("+a.getX()+", "+a.getY()+")"+" B("+b.getX()+", "+b.getY()+") C("+c.getX()+", "+c.getY()+")";
        System.out.print(result);
    }
}