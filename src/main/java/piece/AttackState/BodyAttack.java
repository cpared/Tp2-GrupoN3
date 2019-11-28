package piece.AttackState;

import javafx.util.Pair;
import piece.ItsTooFarToAttackException;
import piece.Piece;

public class BodyAttack implements AttackState {
    private int myBodyAttackPoints;

    public BodyAttack(int attackPoints){
        this.myBodyAttackPoints = attackPoints;
    }

    public void attack( Pair<Piece, Integer> attackedPiece, int distance){
        if(distance < attackedPiece.getValue()) throw new ItsTooFarToAttackException ();
        attackedPiece.getKey().receiveAttacked(this.myBodyAttackPoints);
    }
}