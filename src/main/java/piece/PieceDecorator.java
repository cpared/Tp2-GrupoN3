package piece;

import board.Board;
import move.Move;

public abstract class PieceDecorator implements Piece {
    protected Piece decoratedPiece;

    public PieceDecorator ( Piece decoratedPiece ) {
        super ();
        this.decoratedPiece = decoratedPiece;
    }

    @Override
    public void receiveAttacked ( int damage ) {
        this.decoratedPiece.receiveAttacked ( damage );
    }

    @Override
    public void receiveHealed ( int heal ) {
        this.decoratedPiece.receiveHealed ( heal );
    }

    @Override
    public void move ( Board board , Move move) {
        this.decoratedPiece.move ( board, move );
    }

}

