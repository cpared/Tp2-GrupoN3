package piece.AttackState;

import javafx.util.Pair;
import piece.Piece;

public interface AttackState {
    public void attack( Pair<Piece, Integer> attackedPiece, int distance);
}



