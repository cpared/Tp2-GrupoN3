package Face;

import board.Board;
import piece.Piece;
import player.Player;
import team.Team;

public class InitialFace implements Face {
    private Player player;
    private Board board;

    public InitialFace ( Board board) {
        this.board = board;
    }

    @Override
    public Player newPlayer (String name, Team team){
        this.player = new Player ( name, team );
        return this.player;
    }

    @Override
    public void playerPlacesPieceOnBoard ( Piece piece, int row, int column ) {
        player.placePieceOnBoard ( piece, this.board, row, column );
    }

    @Override
    public Piece removePieceFromBoard ( int row, int column ) {
       Piece removed = board.removePiece ( row, column );
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
    public void playerAttacks ( int row, int column ) {

    }

    @Override
    public void playerMovesPieceOnBoard ( int firstRow, int firstColumn, int secondRow, int secondColumn ) {
        //player.placePieceOnBoard ( this.board, firstRow, firstColumn, secondRow, secondColumn );
        player.movePiece (this.board, firstRow, firstColumn, secondRow, secondColumn );
    }
}
