/*
 * class for displaying the ships panel in setup phase
 * @author Group 3
 * @version 1.2
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;

public class FleetSetup extends JPanel implements ActionListener{
	
	private JPanel panel;
	private ButtonGroup group;
	private HashMap<String,Integer> shipInfo = new HashMap<String,Integer>();
	private Player player;
	
	//constructs fleet panel for Player p
	public FleetSetup(Player p) {
		super();
		this.player = p;
		this.panel = new JPanel(new GridLayout(5,4));
		this.group = new ButtonGroup();
	    shipInfo.put("Carrier", 5);
	    shipInfo.put("Battleship", 4);
	    shipInfo.put("Cruiser", 3);
	    shipInfo.put("Submarine", 3);
	    shipInfo.put("Destroyer", 2);
	    for(Object str : shipInfo.keySet().toArray()) {
	    	String s = (String) str;
			JRadioButton btn = new JRadioButton(s);
			btn.putClientProperty("id", str);
			btn.putClientProperty("type", "radio");
			btn.addActionListener(this);
			JToggleButton allign = new JToggleButton(Alignment.HORIZONTAL.toString());
			allign.addActionListener(this);
			allign.putClientProperty("id", str);
			allign.putClientProperty("type", "align");
			allign.putClientProperty("state", Alignment.HORIZONTAL);
			group.add(btn);
			group.add(allign);
			panel.add(btn);
			panel.add(allign);
		}
	    
		System.out.println("Fleet for "+p.getName()+" created");
	}
	
	//return button group panel
	public JPanel getPanel() {
		return this.panel;
	}

	//action listener : send ship type to self board listener
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().getClass().toString().equals("class javax.swing.JRadioButton")) {
			System.out.println(((String)((JRadioButton)e.getSource()).getClientProperty("type"))+" selected");
			this.player.getScreen().getSelfBoard().setShipInfo(((String)((JRadioButton)e.getSource()).getClientProperty("id")));
			this.player.getScreen().getSelfBoard().setSelfGridListener(true);
		}
		else if(e.getSource().getClass().toString().equals("class javax.swing.JToggleButton")) {
			JToggleButton btn = (JToggleButton)e.getSource();
			System.out.println(((String)btn.getClientProperty("type"))+" selected");
			Alignment curAlign = (Alignment) btn.getClientProperty("state");
			Alignment desiredAlign;
			if(curAlign.equals(Alignment.HORIZONTAL))
				desiredAlign = Alignment.VERTICAL;
			else
				desiredAlign = Alignment.HORIZONTAL;
			boolean b = this.player.getScreen().getSelfBoard().changeAlign(((String)btn.getClientProperty("id")),desiredAlign);	
			if(b) {
				//change of alignment was possible
				btn.putClientProperty("state", null);
				btn.putClientProperty("state", desiredAlign);
				btn.setText(desiredAlign.toString());
			}
		}
	}
	
	//get state of alignment button for ship of type shipName
	public Alignment getAlignment(String shipName) {
		for(AbstractButton btn : Collections.list(group.getElements())) {
			if(btn.getClientProperty("type").equals("align") && btn.getClientProperty("id").equals(shipName))
				return (Alignment) btn.getClientProperty("state");
		}
		return null;
		
	}

	//gray out radiobutton of this shipName
	public void grayOut(String shipName) {
		for(AbstractButton btn : Collections.list(this.group.getElements())) {
			if(btn instanceof JRadioButton && btn.getClientProperty("id").equals(shipName))
				((JRadioButton)btn).setEnabled(false);
		}
		
	}
	
	
	
	
}
