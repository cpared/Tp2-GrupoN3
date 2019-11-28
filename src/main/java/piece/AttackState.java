package piece;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public interface AttackStage {
    public void attack(Pair<Piece, Integer> attackedPiece, int distance);
}

class DistanceAttack implements AttackStage{
    private int myDistanceAttack;

    public DistanceAttack(int distanceAttack){
        this.myDistanceAttack = distanceAttack;
    }

    public void attack(Pair<Piece, Integer> attackedPiece, int distance){
        if(distance < attackedPiece.getValue()) throw new ItsTooFarToAttackException();
        attackedPiece.getKey().receiveAttacked(this.myDistanceAttack);
    }
}

class BodyAttack implements  AttackStage{
    private int myBodyAttackPoints;

    public BodyAttack(int attackPoints){
        this.myBodyAttackPoints = attackPoints;
    }

    public void attack(Pair<Piece, Integer> attackedPiece, int distance){
        if(distance < attackedPiece.getValue()) throw new ItsTooFarToAttackException();
        attackedPiece.getKey().receiveAttacked(this.myBodyAttackPoints);
    }
}

class Heal implements AttackStage{
    private int myHealPoints;

    public Heal(int healPoints){
        this.myHealPoints = healPoints;
    }

    public void attack(Pair<Piece, Integer> attackedPiece, int distance){}

    public void heal(Pair<Piece, Integer> attackedPiece, int distance){
        if (distance > attackedPiece.getValue()) throw new ItsTooFarToAttackException();
        attackedPiece.getKey().receiveHealed(this.myHealPoints);
    }
}

