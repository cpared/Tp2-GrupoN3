package game;

import Face.*;

import move.Builder;
import move.Move;
import player.Player;
import board.*;
import piece.*;
import player.PlayerHas20PointsOnlyException;
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
            this.player1 = this.state.newPlayer ( name );
        } else if (this.player2 == null) {
            this.player2 = this.state.newPlayer ( name );
        } else throw new ThereAreOnlyTwoPlayersPerGameException ();

    }

    public void playerChoosesBattalion ( Player player, int row, int column ) {
        Move move = new Builder ().fromRow ( row ).fromColumn ( column ).build ();
        this.state.playerChoosesBattalion ( player, move );

    }

    public void playerMovesPieceOnBoard ( Player player, int firstRow, int firstColumn, int secondRow, int secondColumn ) throws GameHasEndedException {
        Move move = new Builder ().fromRow ( firstRow ).fromColumn ( firstColumn ).ToRow ( secondRow ).ToColumn ( secondColumn ).build ();
        this.state.playerMovesPieceOnBoard ( player, move );
    }

    public void playerPlacesPieceOnBoard ( Player player, Piece piece, int row, int column ) {
        Move move = new Builder ().ToRow ( row ).ToColumn ( column ).build ();
        this.state.playerPlacesPieceOnBoard ( player, piece, move );
    }

    public Piece removePieceFromBoard ( Player player, int row, int column ) throws GameHasEndedException, NoMembersLeftException {
        Move move = new Builder ().fromRow ( row ).fromColumn ( column ).build ();
        return this.state.removePieceFromBoard ( player, move );
    }

    public void playerAttacks ( Player player, int firstRow, int firstColumn, int secondRow, int secondColumn ) throws GameHasEndedException, NoMembersLeftException {
        Move move = new Builder ().fromRow ( firstRow ).fromColumn ( firstColumn ).ToRow ( secondRow ).ToColumn ( secondColumn ).build ();
        try {
            this.state.playerAttacks ( player, move );
        } catch (NoMembersLeftException e) {
            changeState ( new Ended () );
        }
    }

    public Piece playerChoosesSoldier ( Player player ) throws PlayerHas20PointsOnlyException {
        return this.state.chooseSoldier ( player );
    }

    public Piece playerChoosesHealer ( Player player ) throws PlayerHas20PointsOnlyException {
        return this.state.chooseHealer ( player );
    }

    public Piece playerChoosesRider ( Player player ) throws PlayerHas20PointsOnlyException {
        return this.state.chooseRider ( player );
    }

    public Piece playerChoosesCatapult ( Player player ) throws PlayerHas20PointsOnlyException {
        return this.state.chooseCatapult ( player );
    }

    public boolean isNumberOfMembersOnTeam ( Player player, int numberOfMembers ) {
        return player.isNumberOfPiecesOnTeam ( numberOfMembers );
    }

    private void changeState ( GameState newState ) {
        this.state = newState;
    }

    public void playerIsReadyToPlay ( Player player ) {
        this.state.playerIsReadyToPlay ( player );
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