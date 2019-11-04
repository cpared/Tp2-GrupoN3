public class Cell {
    private Piece piece = null;
    public int distance(Cell destinationCell) {
        return 1;
    }

    public void putPieceInCell(Piece pieceNew) {
        if (this.getPiece() != null){
            throw Exception OcuppiedCellException;
        }
        this.piece = pieceNew;
    }

    private Piece getPiece() {
        return this.piece;
    }

    public Piece deletePieceFromCell() {
        if (this.getPiece() == null){
            throw Exception EmptyCellException;
        }
        Piece poppedPiece = this.piece;
        this.piece = null;
        return poppedPiece;
    }
}
