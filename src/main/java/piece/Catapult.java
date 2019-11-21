package piece;

import board.Board;
import board.CanNotMakeThatMoveException;
import javafx.util.Pair;
import move.Move;
import team.*;

import java.util.ArrayList;

public class Catapult implements Piece {
    private Team team;
    private int cost = 5;
    private int life = 50;
    private int attackRange = 3;
    private DistanceAttack distanceAttack = new DistanceAttack(20);
    private PieceDecorator decoration = null;

    public Catapult ( Team team ) {
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
    public void move ( Board board , Move move) {
        board.movePiece ( move );
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

    @Override
    public void attack (ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPiece) {
        if (attackedPiece.getKey().getTeam() == this.team) throw new SameTeamException();
        this.distanceAttack.attack(attackedPiece, this.attackRange);
    }

    @Override
    public void receiveHealed ( int heal ) {}

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
