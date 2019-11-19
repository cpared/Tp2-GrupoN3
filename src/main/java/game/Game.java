package game;

import Face.*;

import player.Player;
import board.*;
import piece.*;
import player.ThereAreOnlyTwoPlayersPerGameException;
import team.*;

public class Game {

    private Player player1;
    private Player player2;
    private GameState state;

    public Game () {
        this.state = new InProgress ();
    }

    public void newPlayer ( String name ) throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException {

        if (this.player1 == null) {
            this.player1 = this.state.newPlayer (name);
        } else if (this.player2 == null) {
            this.player2 = this.state.newPlayer (name);
        } else throw new ThereAreOnlyTwoPlayersPerGameException ( );

    }

    public void playerMovesPieceOnBoard ( Player player, int firstRow, int firstColumn, int secondRow, int secondColumn ) throws GameHasEndedException {
        this.state.playerMovesPieceOnBoard ( player, firstRow, firstColumn, secondRow, secondColumn );
    }

    public void playerPlacesPieceOnBoard ( Player player, Piece piece, int row, int column ) {
       this.state.playerPlacesPieceOnBoard ( player, piece, row, column  );
    }

    public Piece removePieceFromBoard ( Player player, int row, int column ) throws GameHasEndedException, NoMembersLeftException {
        return this.state.removePieceFromBoard ( player, row, column );
    }

    public void playerAttacks ( Player player, int row, int column ) throws GameHasEndedException, NoMembersLeftException {
        this.state.playerAttacks ( player, row, column );
    }

    public Piece playerChoosesSoldier ( Player player ) {
        return this.state.chooseSoldier ( player );
    }

    public Piece playerChoosesHealer ( Player player ) {
        return this.state.chooseRider ( player );
    }

    public Piece playerChoosesRider ( Player player )  {
        return this.state.chooseRider ( player );
    }

    public Piece playerChoosesCatapult ( Player player ) {
        return this.state.chooseCatapult ( player );
    }

    public boolean isNumberOfMembersOnTeam ( Player player, int numberOfMembers ) {
        return player.isNumberOfPiecesOnTeam ( numberOfMembers );
    }

    public void changeState (GameState newState){
        this.state = newState;
    }

    public void playerIsReadyToPlay (Player player) {
        this.state.playerIsReadyToPlay (player);
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
        return this.state.getBoard ();
    }
}