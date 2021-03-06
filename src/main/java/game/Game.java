package game;

import board.Board;
import move.Builder;
import move.Move;
import piece.Piece;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import player.ThereAreOnlyTwoPlayersPerGameException;
import team.Team;
public class Game {

    private Player player1 = null;
    private Player player2 = null;
    private Team team1;
    private Team team2;
    private GameState state;
    private Player available;
    private TurnAdministrator turnAdministrator;

    public Game () {
        this.team1 = new Team ( 1, this );
        this.team2 = new Team ( 2, this );
        this.state = new InProgress ( team1, team2 );
    }

    public void newPlayer ( String name ) throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException {

        if (this.player1 == null) {
            this.player1 = this.state.newPlayer ( name, team1 );
            this.available = this.player1;
        } else if (this.player2 == null) {
            this.player2 = this.state.newPlayer ( name, team2 );
            this.turnAdministrator = new TurnAdministrator ( player1, player2 );

        } else throw new ThereAreOnlyTwoPlayersPerGameException ();
    }

    public void playerChoosesBattalion ( int row, int column ) throws ItIsNotYourTurnException {
        Move move = new Builder ().fromRow ( row ).fromColumn ( column ).build ();
        this.state.playerChoosesBattalion ( this.available, move );
    }

    public void playerMovesPieceOnBoard ( int firstRow, int firstColumn, int secondRow, int secondColumn ) throws GameHasEndedException, ItIsNotYourTurnException {
        Move move = new Builder ().fromRow ( firstRow ).fromColumn ( firstColumn ).ToRow ( secondRow ).ToColumn ( secondColumn ).build ();
        this.state.playerMovesPieceOnBoard ( this.available, move );
        this.changeAvailablePlayer ();
    }

    public void playerPlacesPieceOnBoard ( Piece piece, int row, int column ) throws ItIsNotYourTurnException {
        Move move = new Builder ().ToRow ( row ).ToColumn ( column ).build ();
        this.state.playerPlacesPieceOnBoard ( this.available, piece, move );
        this.changeAvailablePlayer ();

    }

    public Piece removePieceFromBoard ( int row, int column ) throws GameHasEndedException {
        Move move = new Builder ().fromRow ( row ).fromColumn ( column ).build ();
        return this.state.removePieceFromBoard ( this.available, move );
    }

    public void playerAttacks ( int firstRow, int firstColumn, int secondRow, int secondColumn ) throws GameHasEndedException, ItIsNotYourTurnException {
        Move move = new Builder ().fromRow ( firstRow ).fromColumn ( firstColumn ).ToRow ( secondRow ).ToColumn ( secondColumn ).build ();
        this.state.playerAttacks ( this.available, move );
        this.changeAvailablePlayer ();
    }

    public Piece playerChoosesSoldier () throws PlayerHas20PointsOnlyException {
        return this.state.chooseSoldier ( this.available );
    }

    public Piece playerChoosesHealer () throws PlayerHas20PointsOnlyException {
        return this.state.chooseHealer ( this.available );
    }

    public Piece playerChoosesRider () throws PlayerHas20PointsOnlyException {
        return this.state.chooseRider ( this.available );
    }

    public Piece playerChoosesCatapult () throws PlayerHas20PointsOnlyException {
        return this.state.chooseCatapult ( this.available );
    }

    public void changeState ( GameState newState ) {
        this.state = newState;
    }

    public void playerIsReadyToPlay ( Player player ) {
        this.state.playerIsReadyToPlay ( player );
        this.available = this.turnAdministrator.playerIsReadyToPlay ( player );
    }

    // Private

    public void changeAvailablePlayer () {
        if (this.player1 == null || this.player2 == null) return;
        this.available = this.turnAdministrator.changeAvailablePlayer ();
        this.state.penalizePieces ();
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

    public Piece getPiece ( Move move ) {
        return getBoard ().getPiece ( move );
    }

    public Player getAvailablePlayer () {
        return available;
    }

    //These getters are for the views.
    public int getPoints ( Player player ) {
        return this.state.getPoints ( player );
    }

    public boolean cellIsEmpty ( int row, int column ) {
        return getBoard ().cellIsEmpty ( row, column );
    }

    public Piece getPieceOnCell ( int row, int column ) {
        return this.state.getPieceOnCell ( row, column );
    }
}