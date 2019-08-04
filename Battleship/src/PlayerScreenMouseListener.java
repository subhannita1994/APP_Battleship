import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRootPane;

/**
 * class to listen to mouse events all across the panel
 * @author Group 3
 * @version 1.2
 */
public class PlayerScreenMouseListener extends MouseAdapter
{

	private PlayerScreen gt;
	private LinkedList<String> placedShips;

	/**
	 * construct a mouse listener for the entire SelfBoard panel gt
	 * @param gt	selfBoard panel
	 */
	public PlayerScreenMouseListener(PlayerScreen gt) {
		this.gt = gt;
		this.placedShips = new LinkedList<String>();
	}
	/**
	 * During fleet placement phase, set shipTemp to the ship that has been pressed on, provided this ship is not already placed
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
			String shipName = (String) ((JComponent)((JComponent) e.getSource()).getComponentAt(e.getPoint())).getClientProperty("shipName");
			if(shipName!=null && !placedShips.contains(shipName)) {
				gt.setShipTemp(shipName);
				System.out.println("pressed "+shipName);
			}
		
	}

	/**
	 * During fleet placement phase, place the dragged ship onto board (if possible)
	 * Disable dragging of this ship in future
	 * Reset shipTemp to null
	 * if entire fleet is placed, fire hidden submit button of this player's Screen
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

		if(gt.getShipTemp()!=null) {
			try {
				
				
				Point p = new Point((int)(e.getPoint().getX()-350), (int)e.getPoint().getY());
				JComponent cell = (JComponent) ((JComponent) e.getSource()).getComponentAt(e.getPoint()).getComponentAt(p);

				if(cell.getClientProperty("j") == null || cell.getClientProperty("i") == null)
					return;
				Ship ship = gt.getShipTemp();
				System.out.println("release "+ship.getName()+" to "+cell.getName());
				int x = (int)cell.getClientProperty("j");
				int y = (int)cell.getClientProperty("i");
				
				gt.getOutputStream().format("%s\n", "checkPossible," + ship.getSize() +","+ x+","+y +"," +ship.getAlignment());
				gt.getOutputStream().flush();
				
				
				Boolean result = Boolean.parseBoolean(gt.getInputStream().nextLine());
				System.out.println("Client: checkpossible" + result);
				//HETAL CHANGE THIS
				
				if(result) {
					
					//call server to add
					gt.getOutputStream().format("%s\n", "makeCoordinates," + x+","+y +"," +ship.getName()+ "," +ship.getAlignment());
					gt.getOutputStream().flush();
					
					//get coordinates from server
					String coordinates = gt.getInputStream().nextLine();
					System.out.println("Client coordinates:" + coordinates);
					String[] lines = coordinates.split("\\|", 10);
					int[][] temp = new int[10][10];
					for(int i=0;i<10;i++)
					{
						String[] t = lines[i].split("\\,",10);
						for(int j=0;j<10;j++)
						{
							try {
							temp[i][j] = Integer.parseInt(t[j]);
							}
							catch(Exception ex){
								temp[i][j] = 0;
							}
						}
					}
					gt.draw(temp);;
					placedShips.add(ship.getName());	//add this ship to placedShips
					gt.disableAlignmentBtn(ship.getName());	//disable alignment button for this ship
					gt.removeIcon(ship.getName());
					System.out.println("Client: " + placedShips.toString()+" are placed");
					//if(gt.getPlayer().getFleetSize()==5)
					//	gt.getPlayer().getScreen().getSubmit().doClick();	//call this screen's actionPerformed(submit)
					
				}

			}catch(Exception notACell) {
				System.out.println(notACell.getMessage());
			}
			gt.setShipTemp(null);
		}
	}
	
	

	/**
	 * During fleet placement phase, repaint cells if dragged ship can placed 
	 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		
			if(gt.getShipTemp()!=null) {
				try {
					Point p = new Point((int)(e.getPoint().getX()-350), (int)e.getPoint().getY());
					JComponent cell = (JComponent) ((JComponent) e.getSource()).getComponentAt(e.getPoint()).getComponentAt(p);
					System.out.println("dragged from"+gt.getShipTemp().getName()+" to "+cell.getClientProperty("i")+","+cell.getClientProperty("j"));
					//gt.drawTemporaryPlacement((int)cell.getClientProperty("i"),(int)cell.getClientProperty("j"));

				}catch(Exception notACell) {
				}
			}

	}}