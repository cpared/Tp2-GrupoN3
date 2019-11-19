package board;

import piece.Piece;
import team.Team;

import java.util.*;

public class Battalion {
    private List <ArrayList<Integer>> cellPositionsArray = new ArrayList <ArrayList <Integer>> ();
    private Board thisBoard = null;


    public Battalion(int row, int column,Board  board) {
        boolean skip = false;
        for (int i = row -2; i < row + 3; i++) {
            try {
                Cell cell = board.cellArray.get(i).get(column);
                if (!mayBeSoldier(cell.getPiece())) {
                    cellPositionsArray.clear();
                    continue;
                }
            }
            catch(Exception e){
                cellPositionsArray.clear();
                continue;
            }
            cellPositionsArray.add(new ArrayList<Integer>(Arrays.asList(i,column)));
            if (cellPositionsArray.size() == 3){
                skip = true;
                break;
            }
        }
        for (int i = column -2;(!skip) && i < column + 3; i++) {
            try {
                Cell cell = board.cellArray.get(row).get(i);
                if (!mayBeSoldier(cell.getPiece())) {
                    cellPositionsArray.clear();
                    continue;
                }
            }
            catch(Exception e){
                cellPositionsArray.clear();
                continue;
            }
            cellPositionsArray.add(new ArrayList<Integer>(Arrays.asList(row,i)));
            if (cellPositionsArray.size() == 3){
                break;
            }
        }
        if (cellPositionsArray.size() != 3){
            throw new CanNotMakeBattalion();
        }
        this.thisBoard = board;
        changeCells(cellPositionsArray);
    }

    private void changeCells(Collection<ArrayList <Integer>> cellArray) {
        for (ArrayList<Integer> integers: cellArray){
            thisBoard.cellArray.get(integers.get(0)).get(integers.get(1)).placeBattalion(this);
        }
    }

    private boolean mayBeSoldier(Piece piece){
        return piece.isCost(1);
    }



    protected void destroyBattalion() {
        for (ArrayList<Integer> integers : cellPositionsArray) {
            Cell cell = thisBoard.cellArray.get(integers.get(0)).get(integers.get(1));
            cell.popBattalion();
        }
    }
}