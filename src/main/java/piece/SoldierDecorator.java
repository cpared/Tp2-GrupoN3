package piece;

import board.Battalion;
import board.Board;
import move.Move;
import team.Team;

import java.util.ArrayList;

public class SoldierDecorator extends PieceDecorator {
    private BattalionE battalion;


    public SoldierDecorator ( Piece decoratedPiece , BattalionE battalion) {
        super ( decoratedPiece );
        this.battalion = battalion;
    }

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
}
