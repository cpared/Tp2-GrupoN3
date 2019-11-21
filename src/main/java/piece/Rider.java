package piece;

import board.Board;
import board.CanNotMakeThatMoveException;
import criteria.SoldierCriteria;
import javafx.util.Pair;
import move.Move;
import team.*;

import java.util.ArrayList;

public class Rider implements Piece {
    private Team team;
    private int cost = 3;
    private int life = 100;
    private int attackRange;
    private AttackStrategy riderAttack;
    private PieceDecorator decoration = null;

    public Rider ( Team team ) {
        this.team = team;
    }

    @Override
    public int getLife () {
        return this.life;
    }

    @Override
    public int getCost () {
        return this.cost;
    }

    @Override
    public void attack (ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPiece) {
        this.setMyAttack(adjacentPieces);
        if(attackedPiece.getKey().getTeam() == this.team) throw new SameTeamException();
        this.riderAttack.attack(attackedPiece, this.attackRange);
    }

    private void setMyAttack(ArrayList<Piece> adjacentPieces){
        SoldierCriteria soldierCriteria = new SoldierCriteria();
        ArrayList<Piece> allyPieces = new ArrayList<Piece>();
        ArrayList<Piece> enemyPieces = new ArrayList<Piece>();
        adjacentPieces.forEach(piece ->{
            if(piece.getTeam() == this.team) allyPieces.add(piece);
            else{
                enemyPieces.add(piece);
            }
        });

        if (soldierCriteria.criteria(allyPieces).size() != 0 || enemyPieces.size() == 0){
            this.riderAttack = new DistanceAttack(15);
            this.attackRange = 2;

        }
        else{
            this.riderAttack = new BodyAttack(5);
            this.attackRange = 1;
        }
    }

    @Override
    public void receiveAttacked ( int damage ) {
        this.life -= damage;
        if (this.life <= 0) throw new IAmDeadException();
    }

    @Override
    public void receiveHealed ( int heal ) {
        this.life += heal;
        if (this.life > 100) this.life = 100;
    }

    @Override
    public Team getTeam () {
        return this.team;
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
    public void decorate (PieceDecorator decorator){
        this. decoration = decorator;
    }

    @Override
    public PieceDecorator undecorate (PieceDecorator decorator) {
        this.decoration = null;
        return decorator;
    }
}
