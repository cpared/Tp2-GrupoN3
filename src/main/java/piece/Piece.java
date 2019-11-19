package piece;

import board.CanNotMakeThatMoveException;
import team.*;

public interface Piece {
    Team team = null;
    int life = 0;
    int cost = 0;

    Team getTeam ();

    int move ();

    void attack ( Piece piece );

    int getLife ();

    void getHealed ( int heal );

    void distanceAttack ( Piece receivingPiece );

    void heal ( Piece receivingPiece );

    void getAttacked ( int damage );

    int getCost ();

    boolean isCost (int expectedCost);

}

