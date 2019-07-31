import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Game g;
    Player p;
    Ship ship;

    private Coordinate[] makeCoordinates(int x, int y, Ship ship) {
        Coordinate[] coods = new Coordinate[ship.getSize()];
        if(ship.getAlignment().equals(Alignment.HORIZONTAL)) {
            for(int i=0;i<ship.getSize();i++) {
                coods[i]= new Coordinate(x,y);
                x++;
            }
        }
        else {
            for(int i=0;i<ship.getSize();i++) {
                coods[i]= new Coordinate(x,y);
                y++;
            }
        }
        return coods;
    }
    @Before
    public void setUp() throws Exception {
        g = new Game();
        p = new Player("test", g);
    }

    @Test
    public void testCheckPossible1()
    {
        ship.setAlignment(Alignment.HORIZONTAL);
        Coordinate[] coordinates = this.makeCoordinates(1, 1, ship);
        ship.setLocation(coordinates);
        p.addShip(ship,coordinates);
        boolean result = p.checkPossible("Carrier",5,1,1,Alignment.HORIZONTAL);
        assertFalse(result);

    }
}