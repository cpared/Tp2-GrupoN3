package board;

import piece.Piece;
import team.Team;

import java.util.ArrayList;
import java.lang.Math;

public class Board {
    private ArrayList<ArrayList<Cell>> cellArray;

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

    public void placePiece(Piece piece, int row, int column) {
        Cell cell = this.getCell(row, column);
        cell.putPieceInCell(piece, piece.getTeam());
    }

    public void movePiece(int firstRow, int firstColumn, int secondRow, int secondColumn) {
        Cell originCell = this.getCell(firstRow, firstColumn);
        movePiece(originCell.deletePieceFromCell(),firstRow,firstColumn,secondRow,secondColumn);
    }
    private void movePiece(Piece piece,int firstRow,int firstColumn, int secondRow,int secondColumn){
        if (distance(firstRow, firstColumn, secondRow, secondColumn) > piece.move()) {
            this.getCell(firstRow, firstColumn).putPieceInCell(piece);
            throw new CanNotMakeThatMoveException();
        }
        Cell destinationCell = this.getCell(secondRow, secondColumn);

        try {
            destinationCell.putPieceInCell(piece);
        } catch (Exception OccupiedCellException) {
            this.getCell(firstRow, firstColumn).putPieceInCell(piece);
            throw new CanNotMakeThatMoveException();
        }
    }
    private void movePiece(Battalion piece,int firstRow,int firstColumn, int secondRow,int secondColumn){

    }
    private Cell getCell(int row, int column) {
        return this.cellArray.get(row).get(column);
    }

    private int distance(int firstRow, int firstColumn, int secondRow, int secondColumn) {
        return Math.max(Math.abs(firstRow - secondRow), Math.abs(secondColumn - firstColumn));
    }

    public Piece removePiece(int row, int column) {
        return this.getCell(row, column).deletePieceFromCell();
    }

    public void bodyAttack(int firstRow, int firstColumn, int secondRow, int secondColumn) {
        Piece originPiece = this.getCell(firstRow, firstColumn).getPiece();
        Piece receivingPiece = this.getCell(secondRow, secondColumn).getPiece();
        originPiece.attack(receivingPiece);
    }

    public void distanceAttack(int firstRow, int firstColumn, int secondRow, int secondColumn) {
        Piece originPiece = this.getCell(firstRow, firstColumn).getPiece();
        Piece receivingPiece = this.getCell(secondRow, secondColumn).getPiece();
        originPiece.distanceAttack(receivingPiece);
    }

    public void heal(int firstRow, int firstColumn, int secondRow, int secondColumn) {
        Piece originPiece = this.getCell(firstRow, firstColumn).getPiece();
        Piece receivingPiece = this.getCell(secondRow, secondColumn).getPiece();
        originPiece.heal(receivingPiece);
    }

    public void createBattalion(int firstRow, int firstColumn) {
        try {
            Battalion battalion = new Battalion(cellArray.subList(firstRow - 2, firstRow + 2).get(firstColumn));
        }
        catch (CanNotMakeBattalion e){
            Battalion battalion = new Battalion(cellArray.get(firstRow).subList(firstColumn -2, firstColumn +2));
        }
    }

    public void dissolvedBattalion(int firstRow, int firstColumn) {
    }
}