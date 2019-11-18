package board;

import piece.Piece;
import team.Team;

import java.util.*;

public class Battalion {
    private Collection <Cell> cellArray = new ArrayList <Cell> ();
    private Map<Cell,Piece> piecesArray = new HashMap<Cell,Piece>();
    public Battalion(List<Cell> cells) {
        for (Cell cell : cells) {
            if (!mayBeSoldier(cell.getPiece())){
                cellArray.clear();
                continue;
            }
            cellArray.add(cell);
            if (cellArray.size() == 3){
                break;
            }
        }
        if (cellArray.size() != 3){
            throw new CanNotMakeBattalion();
        }
        changeCells(cellArray);
    }

    private void changeCells(Collection<Cell> cellArray) {
        for (Cell cell: cellArray){
            piecesArray.put(cell,cell.deletePieceFromCell());
            cell.placeBattalion(this);
        }
    }

    private boolean mayBeSoldier(Piece piece){
        return piece.isCost(1);
    }
}