import Cell
class Board{
    public void movePiece(cell,destinationCell){
        if(distance(cell,destinationCell) < 1) {
            throw Exception CanNotMakeThatMoveException;
        }
        piece = deletePieceFromCell(cell);
        try {
            putPieceInCell(destinationCell);
        }
        catch (Exception OcupiedCellException){
            putPieceInCell(Cell)
            throw CanNotMakeThatMoveException
        }