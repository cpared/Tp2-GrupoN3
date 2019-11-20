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

