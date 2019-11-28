package piece;

import javafx.util.Pair;

public class BodyAttack implements AttackState {
    private int myBodyAttackPoints;

    public BodyAttack(int attackPoints){
        this.myBodyAttackPoints = attackPoints;
    }

    public void attack( Pair<Piece, Integer> attackedPiece, int distance){
        if(distance < attackedPiece.getValue()) throw new ItsTooFarToAttackException();
        attackedPiece.getKey().receiveAttacked(this.myBodyAttackPoints);
    }
}