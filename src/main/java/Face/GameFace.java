package Face;

import board.Board;
import board.CanNotMakeThatMoveException;
import move.Move;
import piece.NullPiece;
import piece.Piece;
import player.APlayerAlreadyExistsException;
import player.Player;
import player.ThereAreOnlyTwoPlayersPerGameException;
import team.NoMembersLeftException;
import team.Team;

public class GameFace implements Face {

    private Player player;
    private Board board;
    boolean state = false;

    public GameFace ( Board board , Player player) {
        this.board = board;
        this.player = player;
    }

    @Override
    public Piece removePieceFromBoard ( Move move ) {
        player.removePieceFromTeam ();
        return board.removePiece ( move );
    }

    @Override
    public void playerAttacks ( Move move ) {
        try {
            this.player.attack ( this.board, move );
        } catch (NoMembersLeftException e) {
            throw e;
        }
    }

    @Override
    public void playerMovesPieceOnBoard ( Move move ) {
        player.movePiece ( this.board, move );
    }

    @Override
    public void playerChoosesBattalion ( Move move ) {
        player.chooseBattalion ( this.board, move );
    }

    //Methods that this class does not implement

    @Override
    public void playerPlacesPieceOnBoard ( Piece piece, Move move ) {
        throw new CanNotMakeThatMoveException ();
    }

    @Override
    public Piece playerChoosesSoldier () {
        throw new CanNotMakeThatMoveException ();
//        return new NullPiece ();
    }

    @Override
    public Piece playerChoosesHealer () {
        throw new CanNotMakeThatMoveException ();
    }

    @Override
    public Piece playerChoosesRider () {
        throw new CanNotMakeThatMoveException ();
    }

    @Override
    public Piece playerChoosesCatapult () {
        throw new CanNotMakeThatMoveException ();
    }

    @Override
    public Player newPlayer ( String name, Team team ) {
        throw new APlayerAlreadyExistsException ();
    }

    // This getter is only for testing, they dont belong in the model.
    @Override
    public Player getPlayer () {
        return this.player;
    }

}
