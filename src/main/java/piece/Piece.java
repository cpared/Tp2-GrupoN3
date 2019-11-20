package piece;

import board.Board;
import board.CanNotMakeThatMoveException;
import move.Move;
import team.*;

public interface Piece {
    Team team = null;
    int life = 0;
    int cost = 0;
    PieceDecorator decoration = null;

    Team getTeam ();

    void move ( Board board ,Move move );

    void attack ( Piece piece );

    int getLife ();

    void getHealed ( int heal );

    void distanceAttack ( Piece receivingPiece );

    void heal ( Piece receivingPiece );

    void getAttacked ( int damage );

    int getCost ();

    boolean isCost (int expectedCost);

    void decorate (PieceDecorator decorator);

    PieceDecorator undecorate (PieceDecorator decorator);
}

