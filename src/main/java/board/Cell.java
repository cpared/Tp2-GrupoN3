package board;

import piece.Piece;
import team.Team;

class Cell {
    private Piece piece = null;
    private Team team;

    public Cell ( Team chosenTeam ) {
        this.team = chosenTeam;
    }

    public void putPieceInCell ( Piece newPiece ) {
        if (this.piece != null) {
            throw new OccupiedCellException ();
        }
        this.piece = newPiece;
    }
    public void placePieceInCell ( Piece newPiece ) {
        if (! this.team.equals ( newPiece.getTeam() )) {
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

    public Piece removeDeadPiece() {
        if (!piece.isAlive()) {
            Piece deadPiece = piece;
            this.piece = null;
            return deadPiece;
        }
        return null;
    }
    public boolean isEmpty(){
        return piece == null;
    }
}
