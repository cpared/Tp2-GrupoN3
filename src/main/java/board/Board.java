package board;

import criteria.BattalionCriteria;
import javafx.util.Pair;
import move.Move;
import piece.Piece;
import piece.battalion.BattalionComposite;
import team.Team;

import java.util.ArrayList;

public class Board {
    protected ArrayList<ArrayList<Cell>> cellArray;

    public Board ( Team firstTeam, Team secondTeam ) {
        cellArray = new ArrayList<ArrayList<Cell>> ();
        for (int i = 0; i < 10; i += 1) {
            this.cellArray.add ( new ArrayList<Cell> () );
            for (int j = 0; j < 20; j++) {
                this.cellArray.get ( i ).add ( new Cell ( firstTeam ) );
            }
        }
        for (int i = 10; i < 20; i++) {
            this.cellArray.add ( new ArrayList<Cell> () );
            for (int j = 0; j < 20; j++) {
                this.cellArray.get ( i ).add ( new Cell ( secondTeam ) );
            }
        }
    }

    public void placePiece ( Piece piece, Move move ) {
        Cell cell = this.destinationCell ( move );
        cell.placePieceInCell ( piece );
    }

    public void movePiece ( Move move ) {
        Piece piece = this.originCell ( move ).deletePieceFromCell ();
        if (!move.isValidMove ()) {
            this.originCell ( move ).putPieceInCell ( piece );
            throw new CanNotMakeThatMoveException ();
        }
        Cell destinationCell = this.destinationCell ( move );
        try {
            destinationCell.putPieceInCell ( piece );
        } catch (Exception OccupiedCellException) {
            this.originCell ( move ).putPieceInCell ( piece );
            throw new CanNotMakeThatMoveException ();
        }
    }
    public void move (Move move, Team team){
        if (this.originCell(move).getPiece().getTeam() != team){
            throw new CanNotMakeThatMoveException ();
        }
        move(move);
    }
    public void move ( Move move ) {
        Piece piece = this.originCell ( move ).deletePieceFromCell ();
        this.originCell ( move ).putPieceInCell ( piece );
        piece.move ( this, move );
    }

    public Piece removePiece ( Move move ) {
        return this.originCell ( move ).deletePieceFromCell ();
    }
    public void attack(Move move, Team team){
        if (this.originCell(move).getPiece().getTeam() != team){
            throw new CanNotMakeThatMoveException ();
        }
        attack(move);
    }
    public void attack ( Move move ) {
        int firstRow = move.fromRow;
        int secondRow = move.toRow;
        int firstColumn = move.fromColumn;
        int secondColumn = move.toColumn;
        ArrayList<Piece> pieceArray = this.adjacentPieces ( (this.adjacentCells ( firstRow, firstColumn )) );
        Pair<Piece, Integer> pieceToAttack = new Pair<Piece, Integer> ( this.destinationCell ( move ).getPiece (), Math.max ( Math.abs ( firstRow - secondRow ), Math.abs ( firstColumn - secondColumn ) ) );
        this.originCell ( move ).getPiece ().attack ( pieceArray, pieceToAttack );
    }

    private ArrayList<Cell> adjacentCells ( int firstRow, int firstColumn ) {
        ArrayList<Cell> cellArrayList = new ArrayList<Cell> ();
        for (int row = firstRow - 1; row < firstRow + 2; row++) {
            for (int column = firstColumn - 1; column < firstColumn + 2; column++) {
                if ((row == firstRow && column == firstColumn) || column < 0 || row < 0 || column > 19 || row > 19) {
                    continue;
                }
                cellArrayList.add ( cellArray.get ( row ).get ( column ) );
            }
        }
        return cellArrayList;
    }

    //Method related to battalion.
    public void createBattalion ( Move move ) {

        ArrayList<Piece> possiblePieces = this.adjacentPieces ( adjacentRowCells ( move ) );
        Boolean isBattalion = this.isBattalion ( possiblePieces );
        if (!isBattalion) throw  new CanNotMakeBattalion ();
        BattalionComposite battallion = new BattalionComposite ( possiblePieces );
    }

    private boolean isBattalion ( ArrayList<Piece> possiblePieces ) {
        BattalionCriteria battalion = new BattalionCriteria ();
        ArrayList<Piece> pieces = battalion.criteria ( possiblePieces );
        return pieces.size () == 3;
    }

    //Private methods.
    private Cell destinationCell ( Move move ) {
        return this.cellArray.get ( move.toRow ).get ( move.toColumn );
    }

    private Cell originCell ( Move move ) {
        return this.cellArray.get ( move.fromRow ).get ( move.fromColumn );

    }

    public ArrayList<Cell> adjacentRowCells ( Move move ) {
        int row = move.fromRow;
        int column = move.fromColumn;
        ArrayList<Cell> cellArrayList = new ArrayList<Cell> ();
        for (int i = column - 1; i < column + 2; i++) {
            if (i < 0 || i > 19) {
                cellArrayList.clear ();
                return cellArrayList;
            }
            cellArrayList.add ( cellArray.get ( row ).get ( i ) );
        }
        return cellArrayList;
    }

    public ArrayList<Piece> adjacentPieces ( ArrayList<Cell> cells ) {

        ArrayList<Piece> pieces = new ArrayList<Piece> ();
        for (Cell cell : cells) {
            try {
                pieces.add ( cell.getPiece () );
            } catch (EmptyCellException e) {
                continue;
            }
        }
        return pieces;
    }

    public Piece removeDeadPiece(Move move) {
        return destinationCell(move).removeDeadPiece();
    }
    public boolean cellIsEmpty(int row,int column){
        return cellArray.get(row).get(column).isEmpty();
    }

    public Piece getPiece(Move move) {
        return originCell(move).getPiece();
    }
}