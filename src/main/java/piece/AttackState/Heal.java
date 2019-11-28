package piece;

import javafx.util.Pair;

public class Heal  implements AttackState {
    private int myHealPoints;

    public Heal(int healPoints){
        this.myHealPoints = healPoints;
    }

    public void attack( Pair<Piece, Integer> attackedPiece, int distance){}

    public void heal(Pair<Piece, Integer> attackedPiece, int distance){
        if (distance > attackedPiece.getValue()) throw new ItsTooFarToAttackException();
        attackedPiece.getKey().receiveHealed(this.myHealPoints);
    }
}