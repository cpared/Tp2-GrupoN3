package Face;

import board.Board;
import move.Move;
import piece.NullPiece;
import piece.Piece;
import player.Player;
import player.ThereAreOnlyTwoPlayersPerGameException;
import team.Team;

public class GameFace implements Face {

    private Player player;
    private Board board;
    boolean state = false;

    public GameFace ( Board board) {
        this.board = board;
    }

    @Override
    public Piece removePieceFromBoard ( Move move ) {
        player.removePieceFromTeam ();
        return board.removePiece ( move );
    }

    @Override
    public void playerAttacks ( Move move ) {
        this.board.removePiece ( move );
    }

    @Override
    public void playerMovesPieceOnBoard ( Move move ) {
        player.movePiece ( this.board, move );
    }

    //Methods that this class does not implement

    @Override
    public void playerPlacesPieceOnBoard ( Piece piece, Move move ) {

    }

    @Override
    public Piece playerChoosesSoldier ( ){
        return new NullPiece ();
    }

    @Override
    public Piece playerChoosesHealer ( ){
        return new NullPiece ();
    }

    @Override
    public Piece playerChoosesRider ( ){
        return new NullPiece ();
    }

    @Override
    public Piece playerChoosesCatapult ( ){
        return new NullPiece ();
    }

    @Override
    public Player newPlayer (String name, Team team){
        throw new ThereAreOnlyTwoPlayersPerGameException ();
    }

}
