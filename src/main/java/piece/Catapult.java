package piece;

import board.Board;
import javafx.util.Pair;
import move.Move;
import piece.AttackState.DistanceAttack;
import piece.battalion.BattalionComposite;
import team.SameTeamException;
import team.Team;

import java.util.ArrayList;

public class Catapult implements Piece {
    public Team team;
    private int cost;
    private int life = 50;
    private int attackRange = 20;
    private DistanceAttack distanceAttack = new DistanceAttack(20);
    private boolean alive = true;

    public Catapult ( Team team ) {
        this.team = team;
        this.cost = 5;
    }


    @Override
    public void move ( Board board , Move move) {
        board.movePiece ( move );
    }

    @Override
    public boolean isSameTeamAs ( Piece otherPiece ){
        return this.team.equals ( otherPiece.team );
    }

    @Override
    public void receiveAttacked ( int damage ) {
        this.life -= damage;
        if (this.life <= 0) throw new IAmDeadException();
    }

    @Override
    public void attack (ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPieces) {
        if (this.isSameTeamAs ( attackedPieces.getKey() )) throw new SameTeamException ();
        this.distanceAttack.attack(attackedPieces, this.attackRange);
    }

    @Override
    public void receiveHealed ( int heal ) {}

    @Override
    public boolean isCost (int expectedCost) {
        return this.cost == expectedCost;
    }

    @Override
    public void formPartOfBattalion ( BattalionComposite battalion){

    }

    @Override
    public void notFormPartOfBattalion(BattalionComposite battalion) {

    }

    // These getters are for testing only.
    @Override
    public Team getTeam(){
        return this.team;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public int getLife () {
        return this.life;
    }
}
