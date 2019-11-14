package board;

import piece.Piece;
import team.Team;

import java.util.ArrayList;
import java.lang.Math;

public class Board {
    private ArrayList<ArrayList<Cell>> cellArray;

    public Board(Object firstTeam, Object secondTeam) {
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
        Piece piece = originCell.deletePieceFromCell();
        if (distance(firstRow, firstColumn, secondRow, secondColumn) > piece.move()) {
            originCell.putPieceInCell(piece);
            throw new CanNotMakeThatMoveException();
        }
        Cell destinationCell = this.getCell(secondRow, secondColumn);

        try {
            destinationCell.putPieceInCell(piece);
        } catch (Exception OccupiedCellException) {
            originCell.putPieceInCell(piece);
            throw new CanNotMakeThatMoveException();
        }
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

    private void moveInRow(int firstRow, int firstColumn, int secondRow) {
        int counter = 0;
        for (int i = -2; i < 3 && counter < 3; i++) {
            try {
                movePiece(firstRow, firstColumn - i, secondRow, firstColumn - i);
                counter += 1;
            } catch (CanNotMakeThatMoveException e) {
                for (int j = -2; j < i; j++) {
                    try {
                        movePiece(secondRow, firstColumn - j, firstRow, firstColumn - j);
                    } catch (Exception f) {
                        continue;
                    }
                    if (3 - i < 3) {
                        //CHANGE IN A FUTURE INTO A NEW EXCEPTION
                        throw new CanNotMakeThatMoveException();
                    }
                }
                counter = 0;
            }
        }
    }
    private void moveInColumn(int firstRow, int firstColumn, int secondColumn) {
        int counter = 0;
        for (int i = -2; i < 3 && counter < 3; i++) {
            try {
                movePiece(firstRow-i, firstColumn, firstRow - i, secondColumn);
                counter += 1;
            } catch (CanNotMakeThatMoveException e) {
                for (int j = -2; j < i; j++) {
                    try {
                        movePiece(firstRow -j, secondColumn, firstRow -j, secondColumn - j);
                    } catch (Exception f) {
                        continue;
                    }
                    if (3 - i < 3) {
                        //CHANGE IN A FUTURE INTO A NEW EXCEPTION
                        throw new CanNotMakeThatMoveException();
                    }
                }
                counter = 0;
            }
        }
    }

    public void moveAsBattalion(int firstRow, int firstColumn, int secondRow, int secondColumn) {
        try {
            moveInRow(firstRow, firstColumn, secondRow);
        }
        catch (CanNotMakeThatMoveException e){
            moveInColumn(firstRow,firstColumn,secondColumn);
        }
    }
}