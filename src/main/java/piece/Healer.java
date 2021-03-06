package piece;

import board.Board;
import javafx.util.Pair;
import move.Move;
import piece.AttackState.Heal;
import piece.battalion.BattalionComposite;
import team.Team;

import java.util.ArrayList;

public class Healer implements Piece {
    public Team team;
    private int cost;
    private int life = 75;
    private int healRange = 1;
    private Heal heal = new Heal ( 15 );
    private boolean alive = true;
    public Healer ( Team team ) {
        this.team = team;
        this.cost = 2;
    }


    @Override
    public void receiveHealed ( int heal ) {
        this.life += heal;
        if (this.life > 75) this.life = 75;
    }

    @Override
    public void receiveAttacked ( int damage ) {
        this.life -= damage;
        if (this.life <= 0) alive = false;;
    }

    @Override
    public boolean isSameTeamAs ( Piece otherPiece ) {
        return this.team.equals ( otherPiece.getTeam() );
    }

    @Override
    public void move ( Board board, Move move ) {
        board.movePiece ( move );
    }

    @Override
    public void attack ( ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPieces ) {
        if (!this.isSameTeamAs ( attackedPieces.getKey () )) throw new ThisPieceCantAttackException ();
        this.heal.attack ( attackedPieces, this.healRange );
    }

    @Override
    public boolean isCost ( int expectedCost ) {
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
