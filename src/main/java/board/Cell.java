package board;

import piece.Piece;
import team.*;

class Cell {
    private Piece piece = null;
    private Object team;

    public Cell (Object chosenTeam) {
        this.team = chosenTeam;
    }

    public void putPieceInCell (Piece pieceNew) {
        if (this.piece != null) {
            throw new OccupiedCellException ();
        }
        this.piece = pieceNew;
    }

    Piece getPiece () {
        if (this.piece == null) {
            throw new EmptyCellException ();
        }
        return this.piece;
    }

    public void putPieceInCell ( Piece pieceNew, Team team ) {
        if (pieceNew.getTeam() != this.team) {
            //Put getIdentifier() in the class of Team so we can check that
            throw new EnemyCellException ();
        }
        putPieceInCell ( pieceNew );
    }

    public Piece deletePieceFromCell () {
        Piece poppedPiece = this.getPiece ();
        this.piece = null;
        return poppedPiece;
    }
}
