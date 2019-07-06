
public class Game {
	private  Player p1;
	private Screen playerScreen1;
	private  Player p2;
	private Screen playerScreen2;
	public Game() {
		this.p1 = new Player("Player1", this, 3);
		this.playerScreen1 = new Screen("Player1",this);
		this.p2 = new Player("Player2",this, 3);
		this.playerScreen2 = new Screen("Player2",this);
	}
	
	public Player getP1() {return this.p1;}
	public Player getP2() {return this.p2;}
	public static void main(String[] args) {
		Game game = new Game();
		//setup
		game.getP1().getSelfBoard().setSelfGridListener(true);
		game.getP2().getSelfBoard().setSelfBoardListener(true);
		//game starts
	}
	public void p1play() {
		
	}
	public void p2play() {
		
	}

}
