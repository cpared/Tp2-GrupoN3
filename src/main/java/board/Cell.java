package board;

import piece.Piece;
import team.*;

class Cell {
    private Piece piece = null;
    private Object team;

    public Cell ( Object chosenTeam ) {
        this.team = chosenTeam;
    }

    public void putPieceInCell ( Piece newPiece ) {
        if (this.team.equals ( newPiece.team )) {
            throw new EnemyCellException ();
        } else if (this.piece != null) {
            throw new OccupiedCellException ();
        }
        this.piece = newPiece;
    }

    Piece getPiece () {
        if (this.piece == null) {
            throw new EmptyCellException ();
        }
        return this.piece;
    }

    public Piece deletePieceFromCell () {
        Piece poppedPiece = this.getPiece ();
        this.piece = null;
        return poppedPiece;
    }
}
