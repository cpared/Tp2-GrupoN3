package game;
import Face.*;
import player.Player;
import board.*;
import piece.*;
import player.PlayerHas20PointsOnlyException;
import team.*;

public class Game {
    public Board board = new Board();
    private Player player1;
    private Player player2;

    public Game() {
    }

    public void newPlayer (String name) throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException {
        Team team = this.assignTeam ();

        Player player = new Player ( name , team);
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

    private Team assignTeam() throws ThereCantBeTwoPlayersOnTheSameTeamException {
        if (this.player1 == null) {
            return new Gold ();
        } else if (this.player2 == null) {
            return new Blue ();
        } else throw new ThereCantBeTwoPlayersOnTheSameTeamException ();
    }

    public void playerMovesPieceOnBoard (Player player, int firstRow,int firstColumn,int secondRow,int secondColumn) throws GameHasEndedException {
        this.endGame ();
        System.out.println ( this.gameHasEnded () );
        player.movePiece ( this.board, firstRow, firstColumn, secondRow, secondColumn );
    }

    public void playerPlacesPieceOnBoard ( Player player,Piece piece,int row, int column) {
        player.placePieceOnBoard ( piece, this.board, row, column);
    }

    public void removePieceFromBoard ( Player player, int row, int column) throws GameHasEndedException {
        this.endGame ();
        player.pieceHasBeenRemoved();
        board.removePiece ( row, column );
    }

    public Piece playerChoosesPiece( Player player, String pieceName ) throws PlayerHas20PointsOnlyException {
        return player.choosePiece ( pieceName );
    }

    private boolean gameHasEnded () {
        return this.player1.getTeam ().numberOfMembersStillOnTeam () == 0 || this.player2.getTeam ().numberOfMembersStillOnTeam () == 0;
    }

    public void playerAttacks (Player player, int row, int column) throws GameHasEndedException {
        this.endGame ();
        this.removePieceFromBoard (player, row, column );
    }

    private void endGame () throws GameHasEndedException {
        boolean state = gameHasEnded ();
        if (state) { throw new GameHasEndedException (); }
    }
}