
public class Player {
	
	private int initShips;
	private selfBoard sb;
	private Screen screen;
	private attackBoard ab;
	
	public Player(String name, Game game, int initShips) {
		this.initShips = initShips;
		this.sb= new selfBoard(name, game);
		this.screen = new Screen(name, game);
		this.ab = new attackBoard(name,game);
	}
	
	public int getInitShips() {
		return this.initShips;
	}

	public selfBoard getSelfBoard() {return this.sb;}
	public Screen getScreen() {return this.screen;}
	public void addShip(Coordinate c1, Coordinate c2, Coordinate c3) {
		
	}
	public attackBoard getAttackBoard() {return this.ab;}
}
