package game;

import Face.*;

import player.Player;
import board.*;
import piece.*;
import player.PlayerHas20PointsOnlyException;
import player.ThereAreOnlyTwoPlayersPerGameException;
import team.*;

public class Game {

    private Player player1;
    private Player player2;
    private Face player1Face;
    private Face player2Face;
    private Team team1 = new Team(1);
    private Team team2 = new Team(2);
    public Board board = new Board ( team1 , team2 );

    public Game () {
        this.player1Face = new InitialFace ( board );
        this.player2Face = new InitialFace ( board );
    }

    public void changeFace ( ) {
        this.player1Face = new GameFace ( board );
        this.player2Face = new GameFace ( board );
    }

    public void newPlayer ( String name ) throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException {

        if (this.player1 == null) {
            this.player1 = this.player1Face.newPlayer ( name , team1 );
        } else if (this.player2 == null) {
            this.player2 = this.player2Face.newPlayer ( name , team2 );
        } else throw new ThereAreOnlyTwoPlayersPerGameException ( );

    }

    public void playerMovesPieceOnBoard ( Player player, int firstRow, int firstColumn, int secondRow, int secondColumn ) throws GameHasEndedException {
        this.endGame ();

        if (player == player1) {
            player1Face.playerMovesPieceOnBoard ( firstRow, firstColumn, secondRow, secondColumn );
        } else  player2Face.playerMovesPieceOnBoard ( firstRow, firstColumn, secondRow, secondColumn );

    }

    public void playerPlacesPieceOnBoard ( Player player, Piece piece, int row, int column ) {
        //player.placePieceOnBoard ( piece, this.board, row, column );
        if (player == player1) {
            player1Face.playerPlacesPieceOnBoard ( piece, row, column );
        } else {
            player2Face.playerPlacesPieceOnBoard( piece, row, column );
        }
    }

    public Piece removePieceFromBoard ( Player player, int row, int column ) throws GameHasEndedException, NoMembersLeftException {
        this.endGame ();
        player.removePieceFromTeam ();
        if (player == player1) return player1Face.removePieceFromBoard ( row, column );
        return player2Face.removePieceFromBoard ( row, column );
        //return board.removePiece ( row, column );
    }


    public void playerAttacks ( Player player, int row, int column ) throws GameHasEndedException, NoMembersLeftException {
        this.endGame ();
        if (player == player1) {
            player1Face.playerAttacks ( row, column );
        } else player2Face.playerAttacks ( row, column );
        player.removePieceFromTeam ();
    }

    public Piece playerChoosesSoldier ( Player player ) {
        if (this.player1 == player) return player1Face.playerChoosesSoldier ();
        return player2Face.playerChoosesSoldier ();
    }

    public Piece playerChoosesHealer ( Player player ) {
        if (this.player1 == player) return player1Face.playerChoosesHealer ();
        return player2Face.playerChoosesHealer ();
    }

    public Piece playerChoosesRider ( Player player )  {
        if (this.player1 == player) return player1Face.playerChoosesRider ();
        return player2Face.playerChoosesRider ();
    }

    public Piece playerChoosesCatapult ( Player player ) {
        if (this.player1 == player) return player1Face.playerChoosesCatapult ();
        return player2Face.playerChoosesCatapult ();
    }

    //jason getters
    public boolean isNumberOfMembersOnTeam ( Player player, int numberOfMembers ) {
        return player.isNumberOfPiecesOnTeam ( numberOfMembers );
    }


    // Private methods.
    private boolean gameHasEnded () {
        return this.player1.isNumberOfPiecesOnTeam ( 0 ) || this.player2.isNumberOfPiecesOnTeam ( 0 );
    }

    private void endGame () throws GameHasEndedException {
        boolean state = gameHasEnded ();
        if (state) {
            throw new GameHasEndedException ();
        }
    }


    // This getters are only for testing, they dont belong in the model.
    public Player getPlayer1 () {
        return this.player1;
    }

    public Player getPlayer2 () {
        return this.player2;
    }

    public Board getBoard () {
        return this.board;
    }
}