package piece;

import javafx.util.Pair;

public class DistanceAttack implements AttackState{

    private int myDistanceAttack;

    public DistanceAttack(int distanceAttack){
        this.myDistanceAttack = distanceAttack;
    }

    public void attack( Pair<Piece, Integer> attackedPiece, int distance){
        if(distance < attackedPiece.getValue()) throw new ItsTooFarToAttackException();
        attackedPiece.getKey().receiveAttacked(this.myDistanceAttack);
    }
}