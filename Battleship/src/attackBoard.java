import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;
import java.util.LinkedList;
public class attackBoard extends JPanel{

	private LinkedList<Coordinate> attacked;
	private String name; private Game game;
	public attackBoard(String name, Game game) {
		this.name = name; this.game = game;
		this.attacked = new LinkedList<Coordinate>();
	}
}
