import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */

/**
 * @author GHroup 3
 * @version 1.2
 */
public class ComputerTest {

	private static Game game;
	private static Player player1;
	private static Player player2;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		game = new Game();
		//game.getNormalVariation().doClick();
		//game.getComputerMode().doClick();
		//game.getStartBtn().doClick();
		player1 = game.getP1();
		player2 = game.getP2();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test if player 2 is AI.
	 */
	@Test
	public void testP2() {
		assertTrue(player2 instanceof Computer);
	}
	/**
	 * Test method for {@link Computer#setUpFleet()}.
	 */
	@Test
	public void testSetUpFleet() {
		((Computer) player2).setUpFleet();
		assertEquals(player2.getFleetSize(),5);
	}

	/**
	 * Test method for {@link Computer#attack(int)}.
	 */
	@Test
	public void testAttack() {
		Ship ship = new Ship("Destroyer",2);
		Coordinate[] coordinates = new Coordinate[2];
		for(int j=0;j<2;j++)
			coordinates[j] = new Coordinate(0,j);
		ship.setLocation(coordinates);
		player1.addShip(ship, coordinates);
		player1.hit(new Coordinate(0,0));
		((Computer) player2).attack(1);
		int[][] attackData = player2.getAttackData();
		boolean flag = false;
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
				if(attackData[i][j] != 0 && !(i==0 && j==0))
					flag = true;
		assertTrue(flag);
	}

	

	/**
	 * Test method for {@link Computer#attackRandom(int)}.
	 */
	@Test
	public void testAttackRandom() {
		((Computer) player2).attackRandom(1);
		int[][] attackData = player2.getAttackData();
		boolean flag = false;
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
				if(attackData[i][j] != 0)
					flag = true;
		assertTrue(flag);
	}

}
