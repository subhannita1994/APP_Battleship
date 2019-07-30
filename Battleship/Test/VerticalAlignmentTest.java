import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VerticalAlignmentTest {
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
        assertEquals("VERTICAL",game.getAlignmentY());
    }

}