package piece;

import board.CanNotMakeThatMoveException;
import team.*;

public class Catapult implements Piece {
    private Team team;
    private int cost = 5;
    private int life = 50;
    private int bodyAttack = 0;
    private int distanceAttack = 20;

    public Catapult ( Team team ) {
        this.team = team;
    }

    public void getBodyAttack () {
        throw new CanNotMakeThatMoveException ();
    }

    @Override
    public int getLife () {
        return this.life;
    }

    public int getDistanceAttack () {
        return this.distanceAttack;
    }

    @Override
    public int getCost () {
        return this.cost;
    }

    @Override
    public int move () {
        return 0;
    }

    @Override
    public Team getTeam () {
        return this.team;
    }

    @Override
    public void distanceAttack ( Piece receivingPiece ) {
        receivingPiece.getAttacked ( this.distanceAttack );
    }

    @Override
    public void getAttacked ( int damage ) {
        this.life -= damage;
        if (this.life < 0) this.life = 0;
    }

    @Override
    public void attack ( Piece piece ) {
        throw new CanNotMakeThatMoveException (); //Raise another error is correct, but this for now
    }

    @Override
    public void getHealed ( int heal ) {
        throw new CanNotMakeThatMoveException ();
    }

    @Override
    public void heal ( Piece receivingPiece ) {
        throw new CanNotMakeThatMoveException ();
    }

    @Override
    public boolean isCost (int expectedCost) {
        return this.cost == expectedCost;
    }

}
