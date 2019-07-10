/**
 * Coordinate
 * Class defining coordinates of grid
 * @version 1.1
 * @author group 3
 */

public class Coordinate {
	private int x;
	private int y;

	public Coordinate(int x, int y) {
		this.x = x; 
		this.y = y;
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
