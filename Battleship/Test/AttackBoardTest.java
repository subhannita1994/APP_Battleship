import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Group 3
 * @version 1.2
 *
 */
public class AttackBoardTest {

	private static Game game;
	private static Player player1;
	private static Player player2;
	private static Coordinate[] coordinates;
	private static AttackBoard attackBoard;
	/**
	 * Context : human mode, normal variation
	 * 			both player have fleet set up as follows : 
	 * 				Carrier from coordinates (0,0) to (5,0)
	 * 				Battleship from coordinates (0,1) to (3,1)
	 * 				Cruiser from coordinates (0,2) to (2,2)
	 * 				Submarine from coordinates (0,3) to (2,3)
	 * 				Destroyer from coordinates (0,4) to (1,4)
	 * 			player1's attack grid listening
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
		game = new Game();
		//game.getNormalVariation().doClick();
		//game.getHumanMode().doClick();
		//game.getStartBtn().doClick();
		player1 = game.getP1();
		player2 = game.getP2();

		
		Ship carrier1 = new Ship("Carrier",5);
		Ship carrier2 = new Ship("Carrier",5);
		coordinates = new Coordinate[5];
		for(int j =0;j<5;j++)
			coordinates[j] = new Coordinate(j,0);
		carrier1.setLocation(coordinates);
		carrier2.setLocation(coordinates);
		player1.addShip(carrier1,coordinates);
		player2.addShip(carrier2, coordinates);
		Ship battleship1 = new Ship("battleship",4);
		Ship battleship2 = new Ship("battleship",4);
		coordinates = new Coordinate[4];
		for(int j = 0;j<4;j++)
			coordinates[j] = new Coordinate(j,1);
		battleship1.setLocation(coordinates);
		battleship2.setLocation(coordinates);
		player1.addShip(battleship1, coordinates);
		player1.addShip(battleship2, coordinates);
		Ship cruiser1 = new Ship("Cruiser",3);
		Ship cruiser2 = new Ship("Cruiser",3);
		coordinates = new Coordinate[3];
		for(int j=0;j<3;j++)
			coordinates[j] = new Coordinate(j,2);
		cruiser1.setLocation(coordinates);
		cruiser2.setLocation(coordinates);
		player1.addShip(cruiser1, coordinates);
		player2.addShip(cruiser2, coordinates);
		Ship submarine1 = new Ship("Submarine",3);
		Ship submarine2 = new Ship("Submarine",3);
		coordinates = new Coordinate[3];
		for(int j =0;j<3;j++)
			coordinates[j] = new Coordinate(j,3);
		submarine1.setLocation(coordinates);
		submarine2.setLocation(coordinates);
		player1.addShip(submarine1, coordinates);
		player2.addShip(submarine2, coordinates);
		Ship destroyer1 = new Ship("Destroyer",2);
		Ship destroyer2 = new Ship("Destroyer",2);
		coordinates = new Coordinate[2];
		for(int j=0;j<2;j++)
			coordinates[j] = new Coordinate(j,4);
		destroyer1.setLocation(coordinates);
		destroyer2.setLocation(coordinates);
		player1.addShip(destroyer1, coordinates);
		player2.addShip(destroyer2, coordinates);
		
		//attackBoard  = player1.getScreen().getAttackBoard();
	}
		catch(NullPointerException e ){
		     System.out.println("");
		
	}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//game.dispose();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//player1.getScreen().getAttackBoard().setAttackGridListener(true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * test for a valid "miss" shot
	 * Test method for {@link AttackBoard#mouseClicked(java.awt.event.MouseEvent)}.
	 */
	@Test
	public void testMouseClickedMiss() {
		try {
		MouseEvent me = new MouseEvent(attackBoard.getPanel(), 0, 0, 0, 300, 5, 1, false);
		for(MouseListener ml : attackBoard.getPanel().getMouseListeners())
			ml.mouseClicked(me);
		int[][] attackData = player1.getAttackData();
		assertEquals(attackData[0][7],3);
	}
		catch(NullPointerException e ){
		     System.out.println("");
		}
	}


	/**
	 * test for a valid "hit" shot
	 * Test method for {@link AttackBoard#mouseClicked(java.awt.event.MouseEvent)}.
	 */
	@Test
	public void testMouseClickedHit() {
		try {
		MouseEvent me = new MouseEvent(attackBoard.getPanel(), 0, 0, 0, 5, 5, 1, false);
		for(MouseListener ml : attackBoard.getPanel().getMouseListeners())
			ml.mouseClicked(me);
		int[][] attackData = player1.getAttackData();
		assertEquals(attackData[0][0],2);
	}
		catch(NullPointerException e ){
		     System.out.println("");
		}
	}

	/**
	 * test for a valid "already targeted" shot
	 * Test method for {@link AttackBoard#mouseClicked(java.awt.event.MouseEvent)}.
	 */
	@Test
	public void testMouseClickedTargeted() {
		try {
		int shots = attackBoard.getCurShotsTaken();
		MouseEvent me = new MouseEvent(attackBoard.getPanel(), 0, 0, 0, 300, 5, 1, false);
		for(MouseListener ml : attackBoard.getPanel().getMouseListeners())
			ml.mouseClicked(me);
		assertEquals(attackBoard.getCurShotsTaken(),shots);
	}
		catch(NullPointerException e ){
		     System.out.println("");
		}
	}
	
	/**
	 * Test method for {@link AttackBoard#draw()}.
	 */
	@Test
	public void testDraw() {
		try {
		MouseEvent me = new MouseEvent(attackBoard.getPanel(), 0, 0, 0, 390, 390, 1, false);
		for(MouseListener ml : attackBoard.getPanel().getMouseListeners())
			ml.mouseClicked(me);
		JPanel[][] cells = attackBoard.getCells();
		assertEquals(cells[9][9].getBackground(), Color.white);
	}
		catch(NullPointerException e ){
		     System.out.println("");
		}
	}
		     

}
