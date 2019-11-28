package piece.AttackState;

import javafx.util.Pair;
import piece.ItsTooFarToAttackException;
import piece.Piece;

public class DistanceAttack implements AttackState{

    private int myDistanceAttack;

    public DistanceAttack(int distanceAttack){
        this.myDistanceAttack = distanceAttack;
    }

    public void attack( Pair<Piece, Integer> attackedPiece, int distance){
        if(distance < attackedPiece.getValue()) throw new ItsTooFarToAttackException ();
        attackedPiece.getKey().receiveAttacked(this.myDistanceAttack);
    }
}