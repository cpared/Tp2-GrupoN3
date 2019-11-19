package board;

import criteria.RiderCriteria;
import move.Move;
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

    private Cell destinationCell ( Move move ) {
        return this.cellArray.get ( move.toRow ).get ( move.toColumn );
    }

    private Cell originCell ( Move move ) {
        return this.cellArray.get ( move.fromRow ).get ( move.fromColumn );

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

    public void createBattalion ( int firstRow, int firstColumn ) {
        new Battalion ( firstRow, firstColumn, this );
    }

    public void dissolveBattalion ( Move move ) {
        //this.getCell(firstRow,firstColumn).getBattalion().destroyBattalion();
        this.originCell ( move ).getBattalion ().destroyBattalion ();
    }
}