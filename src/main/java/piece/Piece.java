package piece;

import board.Board;
import board.CanNotMakeThatMoveException;
import javafx.util.Pair;
import move.Move;
import team.*;

import java.util.ArrayList;
import java.util.Map;

public interface Piece {
    Team team = null;
    int life = 0;
    int cost = 0;
    PieceDecorator decoration = null;

    Team getTeam ();

    void move ( Board board ,Move move );

    int getLife ();

    void receiveHealed ( int heal );

    void attack(ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPiece);

    void receiveAttacked(int damage);

    boolean isCost (int expectedCost);

    void decorate (PieceDecorator decorator);

    PieceDecorator undecorate (PieceDecorator decorator);
}

