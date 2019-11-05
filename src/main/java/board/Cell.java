package board;

public class Cell {
    private Piece piece = null;

   // public void putPieceInCell(Piece pieceNew) {
   public void putPieceInCell(Piece pieceNew) throws  OccupiedCellException {
        if (this.getPiece() != null){
            //throw Exception OcuppiedCellException;
            throw new OccupiedCellException();
        }
        this.piece = pieceNew;
    }

    private Piece getPiece() {
        return this.piece;
    }

    //public Piece deletePieceFromCell() {
    public Piece deletePieceFromCell() throws EmptyCellException{
        if (this.getPiece() == null){
            //throw Exception EmptyCellException;
            throw new EmptyCellException();
        }
        Piece poppedPiece = this.piece;
        this.piece = null;
        return poppedPiece;
    }
}
