package board;

import criteria.RiderCriteria;
import move.Move;
import piece.Piece;
import team.Team;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    protected ArrayList<ArrayList<Cell>> cellArray;

    public Board(Team firstTeam, Team secondTeam) {
        cellArray = new ArrayList<ArrayList<Cell>>();
        for (int i = 0; i < 10; i += 1) {
            this.cellArray.add(new ArrayList<Cell>());
            for (int j = 0; j < 20; j++) {
                this.cellArray.get(i).add(new Cell(firstTeam));
            }
        }
        for (int i = 10; i < 20; i++) {
            this.cellArray.add(new ArrayList<Cell>());
            for (int j = 0; j < 20; j++) {
                this.cellArray.get(i).add(new Cell(secondTeam));
            }
        }
    }

    public void placePiece(Piece piece, Move move) {
        Cell cell = this.getCell(move.toRow, move.toColumn);
        cell.putPieceInCell(piece, piece.getTeam());
    }

    public void movePiece( Move move ) {
        Piece piece = this.getCell(move.fromRow, move.fromColumn).deletePieceFromCell();
        //if (distance(move.fromRow, move.fromColumn, move.toRow, move.toColumn) > piece.move()) {
        if (!move.isValidMove ()) {
            this.getCell(move.fromRow, move.fromColumn).putPieceInCell(piece);
            throw new CanNotMakeThatMoveException();
        }
        Cell destinationCell = this.getCell(move.toRow, move.toColumn);

        try {
            destinationCell.putPieceInCell(piece);
        } catch (Exception OccupiedCellException) {
            this.getCell(move.fromRow, move.fromColumn).putPieceInCell(piece);
            throw new CanNotMakeThatMoveException();
        }
    }

    private Cell getCell(int row, int column) {
        return this.cellArray.get(row).get(column);
    }
    /*
    private int distance(int firstRow, int firstColumn, int secondRow, int secondColumn) {
        return Math.max(Math.abs(firstRow - secondRow), Math.abs(secondColumn - firstColumn));
    }
    */
    public Piece removePiece( Move move ) {
        return this.getCell(move.toRow, move.toColumn).deletePieceFromCell();
    }

    public void bodyAttack(int firstRow, int firstColumn, int secondRow, int secondColumn) {

        Piece originPiece = this.getCell(firstRow, firstColumn).getPiece();
        Piece receivingPiece = this.getCell(secondRow, secondColumn).getPiece();
        originPiece.attack(receivingPiece);
    }

    public void distanceAttack(int firstRow, int firstColumn, int secondRow, int secondColumn) {
        Piece originPiece = this.getCell(firstRow, firstColumn).getPiece();
        ArrayList<Piece> piece = new ArrayList<Piece>(Arrays.asList(originPiece));
        RiderCriteria criteria = new RiderCriteria();
        if (criteria.criteria(piece).size() == 1) {
            riderAttack(firstRow,firstColumn,secondRow,secondColumn);
        } else {
            Piece receivingPiece = this.getCell(secondRow, secondColumn).getPiece();
            originPiece.distanceAttack(receivingPiece);
        }
    }

    private void riderAttack(int firstRow, int firstColumn, int secondRow, int secondColumn) {
    }

    public void heal(int firstRow, int firstColumn, int secondRow, int secondColumn) {
        Piece originPiece = this.getCell(firstRow, firstColumn).getPiece();
        Piece receivingPiece = this.getCell(secondRow, secondColumn).getPiece();
        originPiece.heal(receivingPiece);
    }

    public void createBattalion(int firstRow, int firstColumn) {
        new Battalion(firstRow,firstColumn,this);
    }

    public void dissolveBattalion(int firstRow, int firstColumn) {
        this.getCell(firstRow,firstColumn).getBattalion().destroyBattalion();
    }
}