package board;

import criteria.RiderCriteria;
import move.Move;
import piece.BattalionProxy;
import piece.Piece;
import team.Team;

import java.util.ArrayList;
import java.util.Arrays;

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
        cell.putPieceInCell ( piece, piece.getTeam () );
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

    public void move ( Move move ) {
        Piece piece = this.originCell ( move ).deletePieceFromCell ();
        this.originCell ( move ).putPieceInCell ( piece );
        piece.move ( this, move );
    }

    public Piece removePiece ( Move move ) {
        return this.destinationCell ( move ).deletePieceFromCell ();
    }

    public void bodyAttack ( Move move ) {
        Piece originPiece = this.originCell ( move ).getPiece ();
        Piece receivingPiece = this.destinationCell ( move ).getPiece ();
        originPiece.attack ( receivingPiece );
    }

    public void distanceAttack ( Move move ) {

        Piece originPiece = this.originCell ( move ).getPiece ();
        ArrayList<Piece> piece = new ArrayList<Piece> ( Arrays.asList ( originPiece ) );
        RiderCriteria criteria = new RiderCriteria ();
        if (criteria.criteria ( piece ).size () == 1) {
            riderAttack ( move );
        } else {

            Piece receivingPiece = this.destinationCell ( move ).getPiece ();
            originPiece.distanceAttack ( receivingPiece );
        }
    }

    private void riderAttack ( Move move ) {
    }

    public void heal ( Move move ) {
        Piece originPiece = this.originCell ( move ).getPiece ();
        Piece receivingPiece = this.destinationCell ( move ).getPiece ();

        originPiece.heal ( receivingPiece );
    }


    //Method related to battalion.
    public void createBattalion ( Move move ) {

        ArrayList<Piece> possiblePieces = this.adjacentPieces ( adjacentRowCells ( move ) );
        BattalionProxy proxy = new BattalionProxy ( this, possiblePieces, move );
        proxy.createBattalion ();
    }

    //Private methods.
    private Cell destinationCell ( Move move ) {
        return this.cellArray.get ( move.toRow ).get ( move.toColumn );
    }

    private Cell originCell ( Move move ) {
        return this.cellArray.get ( move.fromRow ).get ( move.fromColumn );

    }

    public ArrayList<Cell> adjacentRowCells(Move move){
        int row = move.toRow;
        int column = move.toColumn;
        ArrayList<Cell> cellArrayList = new ArrayList<Cell>();
        for (int i = column-1;i<column+2;i++){
            if (i < 0 || i >19){
                cellArrayList.clear();
                return cellArrayList;
            }
            cellArrayList.add(cellArray.get(row).get(i));
        }
        return cellArrayList;
    }

    public ArrayList<Piece> adjacentPieces(ArrayList<Cell> cells){

        ArrayList<Piece> pieces = new ArrayList<Piece>();
        for (Cell cell: cells){
            pieces.add(cell.getPiece ());
        }
        return pieces;
    }
}