package piece;

import board.Board;
import board.CanNotMakeThatMoveException;
import javafx.util.Pair;
import move.Move;
import team.*;

import java.util.ArrayList;
import java.util.Map;

public class Healer implements Piece {
    private Team team;
    private int cost = 2;
    private int life = 75;
    private int healRange = 1;
    private Heal heal = new Heal(15);
    private PieceDecorator decoration = null;

    public Healer ( Team team ) {
        this.team = team;
    }

    @Override
    public int getLife () {
        return this.life;
    }


    @Override
    public void receiveHealed ( int heal ) {
        this.life += heal;
        if (this.life > 75) this.life = 75;
    }

    @Override
    public void receiveAttacked ( int damage ) {
        this.life -= damage;
        if (this.life <= 0) throw new IAmDeadException();
    }

    @Override
    public Team getTeam () {
        return this.team;
    }

    @Override
    public void move ( Board board ,Move move ) {
        board.movePiece ( move );
    }

    @Override
    public void attack (ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPiece ) {
        if(attackedPiece.getKey().getTeam() != this.team) throw new ThisPieceCantAttackException();
        this.heal.heal(attackedPiece, this.healRange);
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
