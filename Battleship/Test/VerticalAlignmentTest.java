import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VerticalAlignmentTest {
    Player p ;
    Ship ship;
    Game g;

    @Before
    public void setUp() throws Exception {

        g = new Game();
        p = new Player("test", g);
        ship = new Ship("Carrier",5);
    }

    @Test
    public void testAlignment()
    {
        boolean result = p.checkPossible(ship.getName(),ship.getSize(),1,1,Alignment.HORIZONTAL);
        assertTrue(result);
    }

}