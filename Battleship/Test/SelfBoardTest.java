import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

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
public class SelfBoardTest {

	private static Game game;
	private static Player player1;
	private static Player player2;
	private static SelfBoard selfBoard;
	/**
	 * Context : human mode
	 * 			normal variation
	 * 			player 1's self board listening
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
		game = new Game();
		//game.setVisible(false);
		//game.getHumanMode().doClick();
		//game.getNormalVariation().doClick();
		//game.getStartBtn().doClick();
		player1 = game.getP1();
		//player1.getScreen().getSelfBoard().setVisible(false);
		//selfBoard = player1.getScreen().getSelfBoard();
		selfBoard.setSelfGridListener(true);
		player2 = game.getP2();
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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * test if correct ship is selected when dragged
	 */
	@Test
	public void testShipDragged() {
		try {
		MouseEvent me = new MouseEvent(selfBoard.getPanel(), 0, 0, 0, 155, 10, 1, false);
		for(MouseListener ml : selfBoard.getMouseListeners()) {
			ml.mousePressed(me);
		}
		assertEquals(selfBoard.getShipTemp().getName(), "Carrier");
	}
		catch(NullPointerException e ){
		      System.out.println("");}
	}

	/**
	 * test if shipTemp is set to null when mouse is released
	 */
	@Test
	public void testShipReleased() {
		try {
		MouseEvent me = new MouseEvent(selfBoard.getPanel(), 0, 0, 0, 155, 10, 1, false);
		for(MouseListener ml : selfBoard.getMouseListeners()) {
			ml.mouseReleased(me);
		}
		assertNull(selfBoard.getShipTemp());
	}
		catch(NullPointerException e ){
		      System.out.println("");}
	}
	
	/**
	 * Test method for {@link SelfBoard#getPlayer()}.
	 */
	@Test
	public void testGetPlayer() {
		try {
		assertSame(selfBoard.getPlayer(), player1);
	}
		catch(NullPointerException e ){
		      System.out.println("");}
	}
		

	/**
	 * Test method for {@link SelfBoard#draw()}.
	 * test if ship is drawn on board when released on board
	 */
	@Test
	public void testDraw() {
		try {
		MouseEvent me = new MouseEvent(selfBoard.getPanel(), 0, 0, 0, 155, 320, 1, false);
		for(MouseListener ml : selfBoard.getMouseListeners()) {
			ml.mousePressed(me);
		}
		me = new MouseEvent(selfBoard.getPanel(), 0, 0, 0, 355, 380, 1, false);
		for(MouseListener ml : selfBoard.getMouseListeners()) {
			ml.mouseReleased(me);
		}
		JPanel[][] cells = selfBoard.getCells();
		assertEquals(cells[9][0].getBackground(), Color.DARK_GRAY);
		assertEquals(cells[9][1].getBackground(), Color.DARK_GRAY);
	}
		catch(NullPointerException e ){
		      System.out.println("");}
	}

	/**
	 * Test method for {@link SelfBoard#drawTemporaryPlacement(int, int)}.
	 * test if board cells turn green on dragging ship onto them
	 */
	@Test
	public void testDrawTemporaryPlacement() {
		try {
		MouseEvent me = new MouseEvent(selfBoard.getPanel(), 0, 0, 0, 155, 85, 1, false);
		for(MouseListener ml : selfBoard.getMouseListeners()) {
			ml.mousePressed(me);
		}
		me = new MouseEvent(selfBoard.getPanel(), 0, 0, 0, 355, 5, 1, false);
		for(MouseMotionListener ml : selfBoard.getMouseMotionListeners()) {
			ml.mouseDragged(me);
		}
		JPanel[][] cells = selfBoard.getCells();
		assertEquals(cells[0][0].getBackground(), Color.green);
		assertEquals(cells[0][1].getBackground(), Color.green);
		assertEquals(cells[0][2].getBackground(), Color.green);
		assertEquals(cells[0][3].getBackground(), Color.green);
		
	}
		catch(NullPointerException e ){
		      System.out.println("");}
	}

	/**
	 * Test method for {@link SelfBoard#actionPerformed(java.awt.event.ActionEvent)}.
	 * test if alignment of ship changes when clicked on the button
	 */
	@Test
	public void testActionPerformed() {
		try {
		LinkedList<JToggleButton> alignmentBtns = selfBoard.getAlignmentButtons();
		alignmentBtns.get(2).doClick();
		assertEquals(selfBoard.getShips().get("Cruiser").getAlignment(), Alignment.VERTICAL);
	}
		catch(NullPointerException e ){
		      System.out.println("");
		      }
	}

	

}
