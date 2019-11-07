package board;
import piece.Piece;
import piece.Team;
class Cell {
    private Piece piece = null;
    private Team team;
    public Cell(Team chosenTeam){
        this.team = chosenTeam;
    }
   // public void putPieceInCell(Piece pieceNew) {
    public void putPieceInCell(Piece pieceNew){
        if (this.getPiece() != null){
            //throw Exception OccupiedCellException;
            throw new OccupiedCellException();
        }
        this.piece = pieceNew;
    }

    public Piece getPiece() {
        return this.piece;
    }
    public void putPieceInCell(Piece pieceNew, Team team){
        if (team.getClass() != this.team.getClass()){
            throw new EnemyCellException();
        }
        putPieceInCell(pieceNew);
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
