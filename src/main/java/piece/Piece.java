package piece;

import board.CanNotMakeThatMoveException;
import team.*;

public interface Piece {
    public Team team = new Team ();
    public int life = 0;
    public int cost = 0;

    public Team getTeam ();

    public int move ();

    public void attack ( Piece piece );

    public int getLife ();

    public void getHealed ( int heal );

    public void distanceAttack ( Piece receivingPiece );

    public void heal ( Piece receivingPiece );

    public void getAttacked ( int damage );

    public int getCost ();

    public boolean isCost (int expectedCost);

}

