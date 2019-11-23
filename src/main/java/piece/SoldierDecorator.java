package piece;

import board.Board;
import javafx.util.Pair;
import move.Move;
import team.Team;

import java.util.ArrayList;

public class SoldierDecorator extends PieceDecorator {
    private Piece battalion;


    public SoldierDecorator ( Piece battalion) {
        super (battalion);
        this.battalion = battalion;
    }

    @Override
    public int getLife () {
        return this.battalion.getLife ();
    }

    @Override
    public void move ( Board board , Move move ) {
        this.battalion.move ( board , move );
    }


    @Override
    public Team getTeam () {
        return this.battalion.getTeam ();
    }


    @Override
    public void receiveAttacked ( int damage ) {
        this.battalion.receiveAttacked ( damage );
    }

    @Override
    public void attack ( ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPiece ) {
        this.battalion.attack ( adjacentPieces, attackedPiece );
    }

    @Override
    public void receiveHealed ( int heal ) {
        this.battalion.receiveHealed ( heal );
    }

    @Override
    public boolean isCost ( int expectedCost ) {
        return this.battalion.isCost ( expectedCost );
    }

    @Override
    public void decorate ( PieceDecorator decorator ) {
    }

    @Override
    public PieceDecorator undecorate ( PieceDecorator decorator ) {
        return null;
    }
}
