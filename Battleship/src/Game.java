
public class Game {
	private  Player p1;
	private Screen playerScreen1;
	private  Player p2;
	private Screen playerScreen2;
	private boolean takeTurnAttack;
	
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
		game.getP1().getSelfBoard().setSelfBoardListener(true);
		game.getP2().getSelfBoard().setSelfBoardListener(true);
		//game starts
	}
	public void p1play(Game game) {
		game.getP1().getSelfBoard().setSelfBoardListener(false);
		game.getP1().getAttackBoard().setAttackBoardListener(false);
	}
	public void p2play(Game game) {
		game.getP2().getSelfBoard().setSelfBoardListener(false);
		game.getP2().getAttackBoard().setAttackBoardListener(false);
	}
	public void setTakeTurnAttack(boolean isPlayerTurn){
        this.takeTurnAttack = isPlayerTurn;
    }

    public boolean getTakeTurnAttack() {
        return takeTurnAttack;
    }

}
