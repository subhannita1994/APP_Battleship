import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShipPlacementTest {

    String n;
    Game game = new Game();
    Player p = new Player(n,game);
    Coordinate a,b,c;


    private static boolean setUpIsDone = false;

    @Before
    public void setUp() throws Exception
    {
        if(setUpIsDone)
        {
            return;
        }
    }

    @Test
    public void checkingShipPlacement()
    {
        game = new Game();
        p = new Player(n,game);
        assertEquals();
    }


}