package piece;

import board.Board;
import board.CanNotMakeThatMoveException;
import move.Move;
import team.*;

public class Healer implements Piece {
    private Team team;
    private int cost = 2;
    private int life = 75;
    private int heal = 15;

    public Healer ( Team team ) {
        this.team = team;
    }

    public int getDistanceAttack () {
        throw new CanNotMakeThatMoveException ();
    }

    public int getBodyAttack () {
        throw new CanNotMakeThatMoveException ();
    }

    @Override
    public int getLife () {
        return life;
    }

    @Override
    public int getCost () {
        return this.cost;
    }

    @Override
    public void getHealed ( int heal ) {
        this.life += heal;
        if (this.life > 75) this.life = 75;
    }

    @Override
    public void heal ( Piece piece ) {
        if (this.team.getClass () != piece.getTeam ().getClass ()) {
            throw new CanNotMakeThatMoveException ();
        }
        piece.getHealed ( heal );

    }

    @Override
    public void getAttacked ( int damage ) {
        this.life -= damage;
        if (this.life < 0) this.life = 0;
    }

    @Override
    public Team getTeam () {
        return this.team;
    }

    @Override
    public void move ( Board board ,Move move ) {
        board.movePiece ( move );
    }

    @Override
    public void attack ( Piece piece ) {
        throw new CanNotMakeThatMoveException (); //Raise another error is correct, but this for now
    }

    @Override
    public void distanceAttack ( Piece receivingPiece ) {
        throw new CanNotMakeThatMoveException (); //Raise another error is correct, but this for now
    }
    @Override
    public boolean isCost (int expectedCost) {
        return this.cost == expectedCost;
    }
}
