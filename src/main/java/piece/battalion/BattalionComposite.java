package piece.battalion;

import board.Board;
import board.CanNotMakeBattalion;
import board.CanNotMakeThatMoveException;
import javafx.util.Pair;
import move.Builder;
import move.Move;
import piece.Piece;
import team.Team;

import java.util.ArrayList;

public class BattalionComposite {
    private ArrayList<Piece> soldiers;

    public BattalionComposite ( ArrayList<Piece> soldiers ) {
        this.soldiers = soldiers;
        this.formPartOfBattalion ( this );
    }

    public void move ( Board board, Move move ) {
        int fromRow = move.fromRow;
        int fromColumn = move.fromColumn -1;
        int toRow = move.toRow ;
        int toColumn = move.toColumn -1 ;
        ArrayList<Piece> couldntMove = new ArrayList<Piece> (  );
        for (Piece soldier : soldiers) {
            Move fixedMove = new Builder ().fromRow ( fromRow ).fromColumn ( fromColumn ).ToRow ( toRow ).ToColumn ( toColumn ).build ();
            soldier.notFormPartOfBattalion ( this );
            try {
                soldier.move ( board, fixedMove );
            } catch (CanNotMakeThatMoveException e){
                couldntMove.add ( soldier );
                fromColumn++;
                toColumn++;
                continue;
            }
            fromColumn++;
            toColumn++;
            soldier.formPartOfBattalion ( this );

        }
        soldiers.removeAll ( couldntMove );
        if (couldntMove.size ()>0){
            this.notFormPartOfBattalion ( this );
        }
    }

    private void formPartOfBattalion ( BattalionComposite battalion){
        for (Piece soldier : soldiers) {
            soldier.formPartOfBattalion ( battalion );
        }
    }

    private void notFormPartOfBattalion ( BattalionComposite battalion){
        for (Piece soldier : soldiers) {
            soldier.notFormPartOfBattalion ( battalion );
        }
    }

}