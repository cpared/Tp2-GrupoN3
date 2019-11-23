package piece;

import board.Board;
import board.CanNotMakeThatMoveException;
import javafx.util.Pair;
import move.Move;
import team.*;

import java.util.ArrayList;
import java.util.Map;


public class Soldier implements Piece {
    private Team team;
    private int cost = 1;
    private int life = 100;
    private int attackRange = 1;
    private BodyAttack myAttack = new BodyAttack(10);
    private PieceDecorator decoration = null;

    public Soldier ( Team team ) {
        this.team = team;
    }

    @Override
    public void attack (ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPiece) {
        if(attackedPiece.getKey().getTeam() == this.team) throw new SameTeamException();
        this.myAttack.attack(attackedPiece, this.attackRange);
    }

    @Override
    public Team getTeam () {
        return this.team;
    }

    @Override
    public void receiveAttacked ( int damage ) {
        this.life -= damage;
        if (this.life <= 0) throw new IAmDeadException();
    }

    public void receiveHealed ( int heal ) {
        this.life += heal;
        if (this.life > 100) this.life = 100;
    }

    @Override
    public void move ( Board board , Move move) {
        if (this.decoration == null) board.movePiece ( move );
        else decoration.move ( board, move );
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

    @Override
    public int getLife () {
        return this.life;
    }
}
