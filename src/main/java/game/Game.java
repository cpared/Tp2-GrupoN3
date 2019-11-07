package game;
import Face.*;
import player.Player;
import board.*;
import piece.*;
import player.PlayerHas20PointsOnlyException;
import team.*;

public class Game {
    public Board board = new Board();
    public Player player1;
    public Player player2;

    public Game() throws ThereAreOnlyTwoPlayersPerGameException {
    }

    public void newPlayer (String name) throws ThereAreOnlyTwoPlayersPerGameException {
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

    private Team assignTeam() throws ThereCantBeTwoPlayersOnTheSameTeamException {
        if (this.player1 == null) {
            return new Gold ();
        } else if (this.player2 == null) {
            return new Blue ();
        } else throw new ThereCantBeTwoPlayersOnTheSameTeamException ();
    }

    public void playerMovesPieceOnBoard (int firstRow,int firstColumn,int secondRow,int secondColumn) {
        this.board.movePiece ( firstRow, firstColumn, secondRow, secondColumn );
    }

    public void playerPlacesPieceOnBoard ( Piece piece,int row, int column) {
        board.placePiece ( piece, row, column);
    }

    public void removePieceFromBoard (int row, int column){
        board.removePiece ( row, column );
    }

    public Piece playerChoosesPiece(Player player) throws PlayerHas20PointsOnlyException {
        return player.choosePiece ();
    }
}