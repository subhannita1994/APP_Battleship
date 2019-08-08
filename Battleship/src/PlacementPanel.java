import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

/**
 * This class allows for placing ships into panel
 * @version 3.0
 * @author group3
 */

public class PlacementPanel extends JPanel implements ActionListener{
	private Socket connection;
	private Scanner input; 
	private PrintStream output; 
	private String mode;
	private HashMap<String,Ship> shipInfo = new HashMap<String,Ship>();
	private String[] shipNames = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
	private Ship shipTemp = null;
	private JPanel[][] cells = new JPanel[10][10];
	private Player player;
	private PlayerScreenMouseListener ml;
	private boolean isSelfGridListener = false;
	private LinkedList<JToggleButton> alignmentBtns = new LinkedList<JToggleButton>();
	private LinkedList<JPanel> shipPanels = new LinkedList<JPanel>();
	private JPanel board;
	private JFrame frame;
	public PlacementPanel(Socket connection, Scanner input, PrintStream output, String mode, JFrame parent)
	{
		this.connection = connection;
		this.input = input;
		this.output = output;
		this.mode = mode;
		this.frame = parent;
		ml = new PlayerScreenMouseListener(this);
		addMouseListener(ml);
		addMouseMotionListener(ml);

		shipInfo.put("Carrier", new Ship("Carrier",5));
		shipInfo.put("Battleship", new Ship("Battleship",4));
		shipInfo.put("Cruiser", new Ship("Cruiser",3));
		shipInfo.put("Submarine", new Ship("Submarine",3));
		shipInfo.put("Destroyer", new Ship("Destroyer",2));

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();


		for(int i = 0;i< 10; i+=2) {

			Ship ship = shipInfo.get(shipNames[i/2]);
			JPanel align = new JPanel();
			JToggleButton btn = new JToggleButton(Alignment.HORIZONTAL.toString());
			btn.putClientProperty("shipName", shipNames[i/2]);
			btn.addActionListener(this);
			align.add(btn);
			align.setPreferredSize(new Dimension(150,50));
			c.gridx = 0;
			c.gridy = i;
			c.gridheight = 1;
			c.gridwidth = 1;
			alignmentBtns.add(btn);
			add(align,c);

			JPanel shipPanel = new JPanel();
			shipPanel.setPreferredSize(new Dimension(200,50));
			shipPanel.putClientProperty("shipName",shipNames[i/2]);
			JLabel shipImage = new JLabel();
			ImageIcon shipIcon = new ImageIcon(getClass().getResource("/images/"+shipNames[i/2]+".png"));
			shipImage.setIcon(shipIcon);
			shipPanel.add(shipImage);
			
			c.gridx = 1;
			c.gridy = i;
			c.gridheight = 1;
			c.gridwidth = 1;
			shipPanels.add(shipPanel);
			add(shipPanel,c);

			JPanel shipTitle = new JPanel();
			shipTitle.add(new JLabel(shipNames[i/2]+" Size: "+ship.getSize()));
			shipTitle.setPreferredSize(new Dimension(250,30));
			c.gridx = 0;
			c.gridy = i+1;
			c.gridheight = 1;
			c.gridwidth = 2;
			add(shipTitle,c);

		}


		board = new JPanel(new GridLayout(10,10));
		for(int i =0;i<10;i++)
			for(int j=0;j<10;j++) {
				JPanel cell = new JPanel();
				cell.setBackground(Color.blue);
				cell.setPreferredSize(new Dimension(40,40));
				cell.setBorder(new LineBorder(Color.black,1));
				cell.putClientProperty("i",i);
				cell.putClientProperty("j", j);
				cell.addMouseListener(ml);
				cells[i][j] = cell;
				board.add(cell);
			}

		board.setPreferredSize(new Dimension(400,400));
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 10;
		c.gridheight = 10;

		add(board,c);
		
	}

	/**
	 * Action performed event for aligning ship within its coordinate horizontally or vertically
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Alignment newAlignment;
		Ship ship = shipInfo.get(((JComponent) e.getSource()).getClientProperty("shipName"));
		
		if(e.getActionCommand().equals(Alignment.HORIZONTAL.toString()))
			newAlignment = Alignment.VERTICAL;
		else
			newAlignment = Alignment.HORIZONTAL;
		ship.setAlignment(newAlignment);
		((JToggleButton) e.getSource()).setText(newAlignment.toString());
	}
	
	public Scanner getInputStream()
	{
		return input;
	}
	
	public PrintStream getOutputStream()
	{
		return output;
	}
	
	/**
	 * set the current ship being dragged
	 * @param shipName
	 */
	public void setShipTemp(String shipName) {
		System.out.println("ship type set to "+shipName);
		this.shipTemp = shipInfo.get(shipName);
	}
	

	public Ship getShipTemp() {
		return this.shipTemp;
	}
	
	/**
	 * repaint cells when ships is dragged over them, provided ship can be placed there
	 * @param temp
	 */
	public void draw(int[][] temp) {
		Color c = null;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(temp[i][j]==0)
					c = Color.blue;
				else if(temp[i][j]==1)
					c = Color.cyan;
				else if (temp[i][j]==2)
					c = Color.DARK_GRAY;
				else if(temp[i][j]==3)
					c = Color.magenta;
				cells[i][j].setBackground(c);
			}
		}
	}

	/**
	 * Method for returning ship coordinates and ship name
	 * @param x
	 * @param y
	 * @param shipName
	 * @return
	 */
	
	public Coordinate[] makeCoordinates(int x, int y, String shipName) {
		Ship ship = shipInfo.get(shipName);
		Coordinate[] coods = new Coordinate[ship.getSize()];
		if(ship.getAlignment().equals(Alignment.HORIZONTAL)) {
			for(int i=0;i<ship.getSize();i++) {
				coods[i]= new Coordinate(x,y);
				x++;
			}
		}
		else {
			for(int i=0;i<ship.getSize();i++) {
				coods[i]= new Coordinate(x,y);
				y++;
			}
		}
		return coods;
	}
	

	/**
	 * disable alignment button for this ship
	 * @param name
	 */
	public void disableAlignmentBtn(String name) {
		for(JToggleButton b : alignmentBtns)
			if(b.getClientProperty("shipName").equals(name)) {
				b.setEnabled(false);
				return;
			}
	}
	


	/**
	 * remove icon of placed ship
	 * @param name
	 */
	public void removeIcon(String name) {
		for(JPanel p : shipPanels)
			if(p.getClientProperty("shipName").equals(name)) {
				p.removeAll();
				return;
			}	
	}

	/**
	 * setter for setting attack panel
	 *
	 */
	public void SetAttackPanel()
	{
		PlayerScreen frame = (PlayerScreen) this.frame;
		frame.SetAttackPanel();
	}
}
