/**
 * Game
 * Class designed to control game play
 * @version 1.1
 * @author group 3
 */
public class Game {
	
	private  Player p1;
	private  Player p2;
	private boolean takeTurnAttack;
	private int x = 0; 
	private int y = 1;

	public Game() {
		this.p1 = new Player("Player1", this, 3);
		this.p2 = new Player("Player2",this,3);	
		this.p1.addScreen();
		this.p2.addScreen();
	}

	public Player getP1() {
		return this.p1;
	}
	
	public Player getP2() {
		return this.p2;
	}
	public static void main(String[] args) {
		Game game = new Game();
		//setup
		game.getP1().getScreen().showScreen();
		game.getP2().getScreen().hideScreen();
		game.getP1().getScreen().getSelfBoard().setSelfBoardListener(true);
		game.getP2().getScreen().getSelfBoard().setSelfBoardListener(true);	
		//game starts
	}

	/**
	 * Player 1 play with its listeners
	 * @param game
	 */
	public void p1play(Game game) {
		System.out.println("Player1 turn");
		game.getP1().getScreen().getSelfBoard().setSelfBoardListener(false);
		game.getP1().getScreen().getAttackBoard().setAttackBoardListener(true);
	}

	/**
	 * Player 2 play and its listener
	 * @param game
	 */
	public void p2play(Game game) {
		System.out.println("Player2 turn");
		x++;
		if(x>10)	
			y++;
		if(y>10){
			x=1;
			y=1;
		}
		game.getP2().getScreen().getAttackBoard().setHitCoodsP2(x, y);
		game.getP2().getScreen().getSelfBoard().setSelfBoardListener(false);
		game.getP2().getScreen().getAttackBoard().setAttackBoardListener(true);	//ai

	}
	
	public void setTakeTurnAttack(boolean isPlayerTurn){
		this.takeTurnAttack = isPlayerTurn;
	}

	public boolean getTakeTurnAttack() {
		return takeTurnAttack;
	}

}
