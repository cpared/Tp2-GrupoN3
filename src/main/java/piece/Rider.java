package piece;

import board.Board;
import criteria.Criteria;
import criteria.SoldierCriteria;
import javafx.util.Pair;
import move.Move;
import piece.AttackState.AttackState;
import piece.AttackState.BodyAttack;
import piece.AttackState.DistanceAttack;
import piece.battalion.BattalionComposite;
import team.*;

import java.util.ArrayList;

public class Rider implements Piece {
    public Team team;
    private int cost ;
    private int life = 100;
    private int attackRange;
    private AttackState riderAttack;
    private boolean alive = true;
    private Criteria attackCriteria= new SoldierCriteria();


    public Rider ( Team team ) {
        this.team = team;
        this.cost = 3;
    }

    @Override
    public void attack (ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPieces) {
        this.setMyAttack(adjacentPieces);
        if(this.isSameTeamAs (attackedPieces.getKey())) throw new SameTeamException();
        this.riderAttack.attack(attackedPieces, this.attackRange);
    }

    private void setMyAttack(ArrayList<Piece> adjacentPieces){
        ArrayList<Piece> allyPieces = new ArrayList<Piece>();
        ArrayList<Piece> enemyPieces = new ArrayList<Piece>();
        adjacentPieces.forEach(piece ->{
            if(this.isSameTeamAs ( piece )) allyPieces.add(piece);
            else{
                enemyPieces.add(piece);
            }
        });

        if (this.attackCriteria.criteria(allyPieces).size() != 0 || enemyPieces.size() == 0){
            this.riderAttack = new DistanceAttack (15);
            this.attackRange = 8;

        }
        else{
            this.riderAttack = new BodyAttack (5);
            this.attackRange = 1;
        }
    }

    @Override
    public void receiveAttacked ( int damage ) {
        this.life -= damage;
        if (this.life <= 0) alive = false;;
    }

    @Override
    public void receiveHealed ( int heal ) {
        this.life += heal;
        if (this.life > 100) this.life = 100;
    }

    @Override
    public boolean isSameTeamAs ( Piece otherPiece ){
        return this.team.equals ( otherPiece.team );
    }

    @Override
    public void move ( Board board , Move move) {
        board.movePiece ( move );
    }


    @Override
    public boolean isCost (int expectedCost) {
        return this.cost == expectedCost;
    }

    @Override
    public void formPartOfBattalion ( BattalionComposite battalion){

    }
    @Override
    public void notFormPartOfBattalion ( BattalionComposite battalion){

    }
    @Override
    public void penalize(Team team) {
        if (!this.team.equals(team)){
            life -= life * 0.05;        }
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
