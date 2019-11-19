package piece;

import board.Board;
import board.CanNotMakeThatMoveException;
import move.Move;
import team.Team;

public abstract class PieceDecorator implements Piece {
    protected Piece decoratedPiece;

    public PieceDecorator ( Piece decoratedPiece ) {
        super ();
        this.decoratedPiece = decoratedPiece;
    }

    public void removePieceDecoration (PieceDecorator toRemove) {
        if (decoratedPiece == null || toRemove == null)  return;
        //decoratedPiece.removeDecorator(toRemove);
    }

    @Override
    public int getLife () {
        return decoratedPiece.getLife ();
    }

    @Override
    public void getAttacked ( int damage ) {
        this.decoratedPiece.getAttacked ( damage );
    }

    @Override
    public void getHealed ( int heal ) {
        this.decoratedPiece.getHealed ( heal );
    }

    @Override
    public void move ( Board board , Move move) {
        this.decoratedPiece.move ( board, move );
    }

    @Override
    public void heal ( Piece receivingPiece ) {
        this.decoratedPiece.heal ( receivingPiece );
    }
}

