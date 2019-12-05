package piece;

import board.Board;
import javafx.util.Pair;
import move.Move;
import piece.AttackState.AttackState;
import piece.AttackState.BodyAttack;
import piece.battalion.BattalionComposite;
import team.*;

import java.util.ArrayList;


public class Soldier implements Piece {
    public Team team;
    private int cost = 1;
    private int life = 100;
    private int attackRange = 1;
    private AttackState myAttack = new BodyAttack(10);
    public BattalionComposite battalion = null;
    private boolean alive = true;
    public Soldier ( Team team ) {
        this.team = team;
    }

    @Override
    public void attack (ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPieces) {
        if(this.isSameTeamAs (attackedPieces.getKey())) throw new SameTeamException();
        this.myAttack.attack(attackedPieces, this.attackRange);
    }

    @Override
    public boolean isSameTeamAs ( Piece otherPiece ){
        return this.team.equals ( otherPiece.team );
    }

    @Override
    public void receiveAttacked ( int damage ) {
        this.life -= damage;
        if (this.life <= 0){
            alive = false;
        }
    }

    @Override
    public void receiveHealed ( int heal ) {
        this.life += heal;
        if (this.life > 100) this.life = 100;
    }

    @Override
    public void move ( Board board , Move move) {
        if (this.battalion == null) board.movePiece ( move );
        else battalion.move ( board, move );
    }

    @Override
    public boolean isCost (int expectedCost) {
        return this.cost == expectedCost;
    }

    @Override
    public void formPartOfBattalion ( BattalionComposite battalion){
        this.battalion = battalion;
    }
    @Override
    public void notFormPartOfBattalion ( BattalionComposite battalion){
        this.battalion = null;
    }

    // These getters are for testing only.
    @Override
    public Team getTeam(){
        return this.team;
    }
    @Override
    public int getLife () {
        return this.life;
    }
    @Override
    public boolean isAlive() {
        return alive;
    }
}
