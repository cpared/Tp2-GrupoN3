package piece;

import board.Board;
import game.Game;
import javafx.util.Pair;
import move.Move;
import piece.battalion.BattalionComposite;
import team.Team;

import java.util.ArrayList;

public interface Piece {
    Team team = new Team ( 5000 , new Game ());
    int life = 0;
    int cost = 0;
    BattalionComposite battalion = null;

    boolean isSameTeamAs ( Piece otherPiece );

    void move ( Board board, Move move );

    void receiveHealed ( int heal );

    void attack ( ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPiece );

    void receiveAttacked ( int damage );

    boolean isCost ( int expectedCost );

    void formPartOfBattalion ( BattalionComposite battalion);

    void notFormPartOfBattalion ( BattalionComposite battalion);

    // These getters are for testing only.
    int getLife ();

    Team getTeam();

    boolean isAlive();

    void penalize(Team team);
}

