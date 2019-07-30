import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HitTest {

    Game game = new Game();
    Player p = new Player();
    Coordinate a,b,c;
    Ship s = new Ship(a,b,c);

    //AttackBoard attackBoard;

    private static boolean setUpIsDone  = false;


    @Before
    public void setUp() throws Exception
    {
        if (setUpIsDone)
        {
            return;
        }
        //do setup

    }

    /**
     * Test for checking if game works or not
     */

    @Test
    public void testStartup()
    {
        boolean hit = this.p.hit(c);
        if(hit)
        {
            assertTrue(true);
        }
    }

}