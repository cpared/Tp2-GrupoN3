package piece;

import board.Board;
import javafx.util.Pair;
import move.Move;
import team.Team;

import java.util.ArrayList;

public interface Piece {
    Team team = new Team ( 5000 );
    int life = 0;
    int cost = 0;
    PieceDecorator decoration = null;

    boolean isSameTeamAs ( Piece otherPiece );

    void move ( Board board, Move move );

    void receiveHealed ( int heal );

    void attack ( ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPiece );

    void receiveAttacked ( int damage );

    boolean isCost ( int expectedCost );

    void decorate ( PieceDecorator decorator );

    PieceDecorator undecorate ( PieceDecorator decorator );

    // These getters are for testing only.
    int getLife ();

    Team getTeam();

    boolean isAlive();
}

