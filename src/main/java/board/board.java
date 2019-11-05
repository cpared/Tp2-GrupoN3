package board;
import piece.Piece;
import java.util.ArrayList;
import java.lang.Math;

class Board {
    private ArrayList<ArrayList<Cell>> cellArray;

    Board() {
        cellArray = new ArrayList<ArrayList<Cell>>();
        for (int i = 0; i < 20; i += 1){
            this.cellArray.add(new ArrayList<Cell>());
            for (int j =0; j<20 ;j++) {
                this.cellArray.get(i).add(new Cell ());
            }
        }
    }

    public void placePiece(Piece piece,int row, int column){
        //smt to check teams or zones, to discuss with the team
        Cell cell = this.getCell(row,column);
        cell.putPieceInCell(piece);
        /*try {
            cell.putPieceInCell(piece);
        } catch (Exception e) {
            e.getMessage();
        }*/
    }

    public void movePiece(int firstRow,int firstColumn,int secondRow,int secondColumn) {
    //public void movePiece(int firstRow,int firstColumn,int secondRow,int secondColumn) throws CanNotMakeThatMoveException {
        if (distance(firstRow,firstColumn,secondRow,secondColumn) > 1) {
    //        throw Exception CanNotMakeThatMoveException;
            throw new CanNotMakeThatMoveException();
        }
        Cell originCell = this.getCell(firstRow,firstColumn);
        Cell destinationCell = this.getCell(secondRow,secondColumn);


        Piece piece;
        piece = originCell.deletePieceFromCell();
        try {
            destinationCell.putPieceInCell(piece);
        } catch (Exception OccupiedCellException) {
            originCell.putPieceInCell(piece);
            throw new CanNotMakeThatMoveException();
        }

        /*try{
            Piece piece = originCell.deletePieceFromCell();
            destinationCell.putPieceInCell(piece);
        } catch (Exception e) {
            e.getMessage();
        }*/


    }

    private Cell getCell(int row, int column) {
        return this.cellArray.get(row).get(column);
    }

    private int distance(int firstRow, int firstColumn, int secondRow, int secondColumn) {
        return Math.max(Math.abs(firstRow - secondRow), Math.abs(secondColumn - firstColumn));
    }


    public Piece removePiece(int row, int column) {
        return this.getCell(row,column).deletePieceFromCell();
    }
}