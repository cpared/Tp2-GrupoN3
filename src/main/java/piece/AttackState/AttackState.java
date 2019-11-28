package piece;

import javafx.util.Pair;

public interface AttackState {
    public void attack(Pair<Piece, Integer> attackedPiece, int distance);
}



