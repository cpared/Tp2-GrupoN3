package board;
import piece.Piece;
import team.*;
class Cell {
    private Piece piece = null;
    private Team team;
    public Cell(Team chosenTeam){
        this.team = chosenTeam;
    }
   // public void putPieceInCell(Piece pieceNew) {
    public void putPieceInCell(Piece pieceNew){
        if (this.piece != null){
            //throw Exception OccupiedCellException;
            throw new OccupiedCellException();
        }
        this.piece = pieceNew;
    }

    Piece getPiece() {
        if (this.piece == null) {
            throw new EmptyCellException();
        }
        return this.piece;
    }
    public void putPieceInCell(Piece pieceNew, Team team){
        if (team.getClass() != this.team.getClass()){
            throw new EnemyCellException();
        }
        putPieceInCell(pieceNew);
    }
    //public Piece deletePieceFromCell() {
    public Piece deletePieceFromCell(){
        Piece poppedPiece = this.getPiece();
        this.piece = null;
        return poppedPiece;
    }
}
