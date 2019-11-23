package piece;

import board.Board;
import board.CanNotMakeThatMoveException;
import javafx.util.Pair;
import move.Move;
import team.*;

import java.util.ArrayList;
import java.util.Map;

public class Healer implements Piece {
    public Team team;
    private int cost;
    private int life = 75;
    private int healRange = 1;
    private Heal heal = new Heal ( 15 );
    private PieceDecorator decoration = null;

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
        if (this.life <= 0) throw new IAmDeadException ();
    }

    @Override
    public boolean isSameTeamAs ( Piece otherPiece ) {
        return this.team.equals ( otherPiece.team );
    }

    @Override
    public void move ( Board board, Move move ) {
        board.movePiece ( move );
    }

    @Override
    public void attack ( ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPieces ) {
        if (this.isSameTeamAs ( attackedPieces.getKey () )) throw new ThisPieceCantAttackException ();
        this.heal.heal ( attackedPieces, this.healRange );
    }

    @Override
    public boolean isCost ( int expectedCost ) {
        return this.cost == expectedCost;
    }

    @Override
    public void decorate ( PieceDecorator decorator ) {
        this.decoration = decorator;
    }

    @Override
    public PieceDecorator undecorate ( PieceDecorator decorator ) {
        this.decoration = null;
        return decorator;
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
}
