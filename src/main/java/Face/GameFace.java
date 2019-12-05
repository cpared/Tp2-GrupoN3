package Face;

import board.Board;
import board.CanNotMakeThatMoveException;
import move.Move;
import piece.Piece;
import player.APlayerAlreadyExistsException;
import player.Player;
import team.NoMembersLeftException;

public class GameFace implements Face {

    private Player player;
    private Board board;
    boolean state = false;

    public GameFace ( Board board , Player player) {
        this.board = board;
        this.player = player;
    }

    @Override
    public Piece removeDeadPieceFromBoard(Move move ) {
        Piece removed = board.removeDeadPiece( move );
        player.removePieceFromTeam (removed);
        return removed;
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
    public Player newPlayer ( String name ) {
        throw new APlayerAlreadyExistsException ();
    }

    // This getter is only for testing, they dont belong in the model.
    @Override
    public Player getPlayer () {
        return this.player;
    }

    public Piece getPiece(Move move){
        return board.getPiece(move);
    }
    @Override
    public int getPoints () {
        return 0;
    }
}
