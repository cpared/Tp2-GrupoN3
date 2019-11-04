import java.util.ArrayList;
import java.lang.Math;

class Board {
    private ArrayList<ArrayList> cellArray;
    public void placePiece(Piece piece,int row, int column){
        //smt to check teams or zones, to discuss with the team
        Cell cell = this.getCell(row,column);
        cell.putPieceInCell(piece);
    }
    public void movePiece(int firstRow,int firstColumn,int secondRow,int secondColumn) {
        if (distance(firstRow,firstColumn,secondRow,secondColumn) > 1) {
            throw Exception CanNotMakeThatMoveException;
        }
        Cell originCell = this.getCell(firstRow,firstColumn);
        Cell destinationCell = this.getCell(secondRow,secondColumn);
        Piece piece;
        piece = originCell.deletePieceFromCell();
        try {
            destinationCell.putPieceInCell(piece);
        } catch (Exception OccupiedCellException) {
            originCell.putPieceInCell(piece);
            throw CanNotMakeThatMoveException;
        }
    }

    private Cell getCell(int row, int column) {
        return (Cell) this.cellArray.get(row).get(column);
    }

    private int distance(int firstRow, int firstColumn, int secondRow, int secondColumn) {
        return Math.max(Math.abs(firstRow - secondRow), Math.abs(secondColumn - firstColumn));
    }


}