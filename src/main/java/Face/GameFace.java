package Face;

import board.Board;
import piece.NullPiece;
import piece.Piece;
import player.Player;
import player.ThereAreOnlyTwoPlayersPerGameException;
import team.Team;

public class GameFace implements Face {

    private Player player;
    private Board board;

    public GameFace ( Board board) {
        this.board = board;
    }

    @Override
    public Piece removePieceFromBoard ( int row, int column ) {
        player.removePieceFromTeam ();
        return board.removePiece ( row, column );
    }

    @Override
    public void playerAttacks ( int row, int column ) {
        this.board.removePiece (  row, column );
    }

    @Override
    public void playerMovesPieceOnBoard ( int firstRow, int firstColumn, int secondRow, int secondColumn ) {
        player.movePiece ( this.board, firstRow, firstColumn, secondRow, secondColumn );
    }

    //Methods that this class does not implement

    @Override
    public void playerPlacesPieceOnBoard ( Piece piece, int row, int column ) {

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
