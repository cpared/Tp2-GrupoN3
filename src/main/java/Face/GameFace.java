package Face;

import board.Board;
import board.CanNotMakeThatMoveException;
import move.Move;
import piece.Piece;
import player.Player;
import team.Team;

public class GameFace implements Face {

    private Player player;
    private Board board;
    boolean state = false;
    private Team team;

    public GameFace ( Board board , Player player, Team team) {
        this.board = board;
        this.player = player;
        this.team = team;
    }

    @Override
    public Piece removeDeadPieceFromBoard(Move move ) {
        Piece removed = board.removeDeadPiece( move );
        player.removePieceFromTeam (removed);
        return removed;
    }

    @Override
    public void playerAttacks ( Move move ) {
        board.attack ( move, team );
    }

    @Override
    public void playerMovesPieceOnBoard ( Move move ) {
        board.move ( move, team );
    }

    @Override
    public void playerChoosesBattalion ( Move move ) {
        //player.chooseBattalion ( this.board, move );
        board.createBattalion ( move );
    }

    //Methods that this class does not implement

    @Override
    public void playerPlacesPieceOnBoard ( Piece piece, Move move ) {
        throw new CanNotMakeThatMoveException ();
    }

    @Override
    public Piece playerChoosesSoldier () {
        throw new CanNotMakeThatMoveException ();
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


    // This getter is only for testing, they dont belong in the model.
    @Override
    public Player getPlayer () {
        return this.player;
    }

    @Override
    public Piece getPiece(Move move){
        return board.getPiece(move);
    }

    @Override
    public int getPoints () {
        return 0;
    }
}
