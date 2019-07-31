import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShipFleetTest {

    Game game = new Game();
    String a;
    Computer comp = new Computer(a, game);
    @Before
    public void setUp() throws Exception {

        game = new Game();
        comp = new Computer(a,game);
    }

    @Test
    public void testFleet()
    {
        comp.setUpFleet();
        assertEquals(5,comp.getFleetSize());
    }
}