package game;

import board.Board;
import move.Builder;
import move.Move;
import piece.Piece;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import player.ThereAreOnlyTwoPlayersPerGameException;
import team.NoMembersLeftException;

public class Game {

    private Player player1 = null;
    private Player player2 = null;
    private GameState state;
    private Player available;

    public Game () {
        this.state = new InProgress ();
    }

    public void newPlayer ( String name ) throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException {

        if (this.player1 == null) {
            this.player1 = this.state.newPlayer ( name );
            this.available = this.player1;
        } else if (this.player2 == null) {
            this.player2 = this.state.newPlayer ( name );
        } else throw new ThereAreOnlyTwoPlayersPerGameException ();

    }

    public void playerChoosesBattalion ( Player player, int row, int column ) throws ItIsNotYourTurnException {
        if (!this.isAvailablePlayer ( player )) throw new ItIsNotYourTurnException ();
        Move move = new Builder ().fromRow ( row ).fromColumn ( column ).build ();
        this.state.playerChoosesBattalion ( player, move );

    }

    public void playerMovesPieceOnBoard ( Player player, int firstRow, int firstColumn, int secondRow, int secondColumn ) throws GameHasEndedException, ItIsNotYourTurnException {
        if (!this.isAvailablePlayer ( player )) throw new ItIsNotYourTurnException ();
        Move move = new Builder ().fromRow ( firstRow ).fromColumn ( firstColumn ).ToRow ( secondRow ).ToColumn ( secondColumn ).build ();
        this.state.playerMovesPieceOnBoard ( player, move );
        this.changeAvailablePlayer ();
    }

    public void playerPlacesPieceOnBoard ( Player player, Piece piece, int row, int column ) throws ItIsNotYourTurnException {
        if (!this.isAvailablePlayer ( player )) throw new ItIsNotYourTurnException ();
        Move move = new Builder ().ToRow ( row ).ToColumn ( column ).build ();
        this.state.playerPlacesPieceOnBoard ( player, piece, move );
        this.changeAvailablePlayer ();;
    }

    public Piece removePieceFromBoard ( Player player, int row, int column ) throws GameHasEndedException, NoMembersLeftException {
        Move move = new Builder ().fromRow ( row ).fromColumn ( column ).build ();
        return this.state.removePieceFromBoard ( player, move );
    }

    public void playerAttacks ( Player player, int firstRow, int firstColumn, int secondRow, int secondColumn ) throws GameHasEndedException, NoMembersLeftException, ItIsNotYourTurnException {
        if (!this.isAvailablePlayer ( player )) throw new ItIsNotYourTurnException ();
        Move move = new Builder ().fromRow ( firstRow ).fromColumn ( firstColumn ).ToRow ( secondRow ).ToColumn ( secondColumn ).build ();
        try {
            this.state.playerAttacks ( player, move );
        } catch (NoMembersLeftException e) {
            changeState ( new Ended () );
        }
        this.changeAvailablePlayer ();
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
        if (player.equals ( this.player1 )) this.available = this.player2;
        else this.available = this.player1;
    }

    // Private

    private boolean isAvailablePlayer ( Player player ) {
        return (this.available.equals ( player ));
    }
    public void changeAvailablePlayer () {
        if (this.player1 == null || this.player2 == null) return;
        if (this.player1.equals ( this.available )) this.available = this.player2;
        else this.available = this.player1;
    }

    // These getters are only for testing, they dont belong in the model.
    public Player getPlayer1 () {
        return this.player1;
    }

    public Player getPlayer2 () {
        return this.player2;
    }

    public Board getBoard () {
        return this.state.getBoard ();
    }
    public Player getAvailablePlayer(){
        return available;
    }
    //These getters are for the views.
    public int getPoints ( Player player ) {
        return this.state.getPoints ( player );
    }
}