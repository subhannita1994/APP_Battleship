import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HorizontalAlignmentTest {


    private static boolean setUpIsDone = false;
    @Before
    public void setUp() throws Exception {
        if (setUpIsDone) {
            return;
        }
    }

    @Test
     public void testAlignment()
    {
        Game game = new Game();
        assertEquals("HORIZONTAL",game.getAlignmentX());
    }
}