package danil;

/**
 * @author Danil Kolesnikov danil.kolesnikov@sjsu.edu
 * @author Minh Phan minh.phan@sjsu.edu
 * CS 151 HW4 Fall 2017
 */
public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    //compare coordinate objects
    public boolean compareCoord(Coordinate coordinate){
        if(coordinate.getX() == this.x && coordinate.getY() == this.y){
            return true;
        }
        return false;
    }

    public String toString(){
        return "\nX=" + x + " and Y=" + y;
    }

}