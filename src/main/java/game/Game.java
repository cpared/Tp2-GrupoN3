package game;
import Face.*;
import player.Player;
import board.*;
import piece.*;
import player.PlayerHas20PointsOnlyException;


public class Game {
    //private Face face;
    public Board board = new Board();
    public Player player1;
    public Player player2;

    public Game() throws ThereAreOnlyTwoPlayersPerGameException {
        //newPlayer ("Rose");
        //newPlayer ("Mike");
    }

    public void newPlayer (String name) throws ThereAreOnlyTwoPlayersPerGameException {
        //InitialFace initial = new InitialFace (name);

        Player player = new Player ( name );
        if (this.player1 == null) {
            this.player1 = player;
        } else if (this.player2 == null) {
            this.player2 = player;
        } else throw new ThereAreOnlyTwoPlayersPerGameException ();

    }

    public Player getPlayer1 () {
        return this.player1;
    }

    public Player getPlayer2 () {
        return this.player2;
    }

    public Board getBoard () { return this.board;}

    public void playerChoosePiece(Player player) throws PlayerHas20PointsOnlyException {
        Piece piece= player.choosePiece ();
    }

}