package piece;

import board.Battalion;
import board.Board;
import move.Move;
import team.Team;

import java.util.ArrayList;

public class SoldierDecorator extends PieceDecorator {
    private Piece battalion;


    public SoldierDecorator ( Piece battalion) {
        super (battalion);
        this.battalion = battalion;
    }
    /*
    public SoldierDecorator ( RealBattalion battalion) {
        super (battalion);
        this.battalion = battalion;
    }
    */

    @Override
    public int getLife () {
        return this.battalion.getLife ();
    }

    @Override
    public int getCost () {
        return this.battalion.getLife ();
    }

    @Override
    public void move ( Board board , Move move ) {
        System.out.println ( "decorator is told to move" );
        this.battalion.move ( board , move );
    }


    @Override
    public Team getTeam () {
        return this.battalion.getTeam ();
    }

    @Override
    public void distanceAttack ( Piece receivingPiece ) {
    }

    @Override
    public void getAttacked ( int damage ) {
        this.battalion.getAttacked ( damage );
    }

    @Override
    public void attack ( Piece piece ) {
        this.battalion.attack ( piece );
    }

    @Override
    public void getHealed ( int heal ) {
        this.battalion.getHealed ( heal );
    }

    @Override
    public void heal ( Piece receivingPiece ) {
        this.battalion.heal ( receivingPiece );
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
