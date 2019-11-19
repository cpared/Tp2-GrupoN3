package Face;

import board.Board;
import move.Move;
import piece.Piece;
import player.Player;
import team.Team;

public class InitialFace implements Face{
    private Player player;
    private Board board;
    boolean state = false;

    public InitialFace ( Board board) {
        this.board = board;
    }

    @Override
    public Player newPlayer (String name, Team team){
        this.player = new Player ( name, team );
        return this.player;
    }

    @Override
    public void playerPlacesPieceOnBoard ( Piece piece, Move move ) {
        player.placePieceOnBoard ( piece, this.board, move );
    }

    @Override
    public Piece removePieceFromBoard ( Move move ) {
       Piece removed = board.removePiece ( move );
       return removed; // Should return the points to the player.
    }

    @Override
    public Piece playerChoosesSoldier ( ){
        return this.player.chooseSoldier ( );
    }

    @Override
    public Piece playerChoosesHealer ( ){
        return this.player.chooseHealer ( );
    }

    @Override
    public Piece playerChoosesRider ( ){
        return this.player.chooseRider ( );
    }

    @Override
    public Piece playerChoosesCatapult ( ){
        return this.player.chooseCatapult ( );
    }

    //Methods that this class does not implement

    @Override
    public void playerAttacks ( Move move ) {

    }

    @Override
    public void playerMovesPieceOnBoard ( Move move ) {
        //player.placePieceOnBoard ( this.board, firstRow, firstColumn, secondRow, secondColumn );
        player.movePiece (this.board, move );
    }

}
