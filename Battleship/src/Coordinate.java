/**
 * Class defining its coordinates for managing ships
 * @version 1.1
 */

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		this.x = x; this.y = y;
	}
	public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    /**
     * Comparing coordinate objects
     * @param coordinate
     * @return
     */
    public boolean compareCoord(Coordinate coordinate){
        if(coordinate.getX() == this.x && coordinate.getY() == this.y){
            return true;
        }
        return false;
    }

}
