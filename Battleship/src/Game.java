
public class Game {
	private  Player p1;
	private  Player p2;
	private boolean takeTurnAttack;
	
	public Game() {
		this.p1 = new Player("Player1", this, 3);
		this.p2 = new Player("Player2",this, 3);
	}
	
	public Player getP1() {return this.p1;}
	public Player getP2() {return this.p2;}
	public static void main(String[] args) {
		Game game = new Game();
		//setup
		game.getP1().getScreen().getSelfBoard().setSelfBoardListener(true);
		game.getP2().getScreen().getSelfBoard().setSelfBoardListener(true);
		//game starts
	}
	public void p1play(Game game) {
		game.getP1().getScreen().getSelfBoard().setSelfBoardListener(false);
		game.getP1().getScreen().getAttackBoard().setAttackBoardListener(false);
	}
	public void p2play(Game game) {
		game.getP2().getScreen().getSelfBoard().setSelfBoardListener(false);
		game.getP2().getScreen().getAttackBoard().setAttackBoardListener(false);
	}
	public void setTakeTurnAttack(boolean isPlayerTurn){
        this.takeTurnAttack = isPlayerTurn;
    }

    public boolean getTakeTurnAttack() {
        return takeTurnAttack;
    }

}
