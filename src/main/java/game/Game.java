package game;

import player.Player;
import board.*;
import piece.*;
import player.PlayerHas20PointsOnlyException;
import player.ThereAreOnlyTwoPlayersPerGameException;
import team.*;

public class Game {
    public Board board = new Board ();
    private Player player1;
    private Player player2;

    public Game () {
    }

    public void newPlayer ( String name ) throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException {
        Team team = this.assignTeam ();

        Player player = new Player ( name, team );
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

    public Board getBoard () {
        return this.board;
    }

    private Team assignTeam () throws ThereCantBeTwoPlayersOnTheSameTeamException {
        if (this.player1 == null) {
            return new Gold ();
        } else if (this.player2 == null) {
            return new Blue ();
        } else throw new ThereCantBeTwoPlayersOnTheSameTeamException ();
    }

    public void playerMovesPieceOnBoard ( Player player, int firstRow, int firstColumn, int secondRow, int secondColumn ) throws GameHasEndedException {
        this.endGame ();
        player.movePiece ( this.board, firstRow, firstColumn, secondRow, secondColumn );
    }

    public void playerPlacesPieceOnBoard ( Player player, Piece piece, int row, int column ) {
        player.placePieceOnBoard ( piece, this.board, row, column );
    }

    public Piece removePieceFromBoard ( Player player, int row, int column ) throws GameHasEndedException, NoMembersLeftException {
        this.endGame ();
        player.removePieceFromTeam ();
        return board.removePiece ( row, column );
    }

    /*
    public Piece playerChoosesPiece ( Player player, String pieceName ) throws PlayerHas20PointsOnlyException {
        return player.choosePiece ( pieceName );
    }
    */

    private boolean gameHasEnded () {
        return this.player1.isNumberOfPiecesOnTeam ( 0 ) || this.player2.isNumberOfPiecesOnTeam ( 0 );
    }

    public void playerAttacks ( Player player, int row, int column ) throws GameHasEndedException, NoMembersLeftException {
        this.endGame ();
        this.removePieceFromBoard ( player, row, column );
    }

    private void endGame () throws GameHasEndedException {
        boolean state = gameHasEnded ();
        if (state) {
            throw new GameHasEndedException ();
        }
    }

    public Piece playerChoosesSoldier ( Player player ) throws PlayerHas20PointsOnlyException {
        return player.chooseSoldier ( );
    }

    public Piece playerChoosesHealer ( Player player ) throws PlayerHas20PointsOnlyException {
        return player.chooseHealer ( );
    }

    public Piece playerChoosesRider ( Player player ) throws PlayerHas20PointsOnlyException {
        return player.chooseRider ( );
    }

    public Piece playerChoosesCatapult ( Player player ) throws PlayerHas20PointsOnlyException {
        return player.chooseCatapult ( );
    }
    //jason getters
    public boolean isNumberOfMembersOnTeam ( Player player, int numberOfMembers ) {
        return player.isNumberOfPiecesOnTeam ( numberOfMembers );
    }
}