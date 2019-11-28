package piece;

import board.Board;
import board.CanNotMakeBattalion;
import criteria.BattalionCriteria;
import javafx.util.Pair;
import move.Move;
import team.Team;

import java.util.ArrayList;

public class BattalionProxy implements Battalion {
    private ArrayList<Piece> pieces;
    private Board board;
    public RealBattalion battalion = new RealBattalion ( new ArrayList<Piece> (  ) );
    private Move move;
    private PieceDecorator decorator = new SoldierDecorator ( this );
    public Team team;
    private boolean alive = true;

    public BattalionProxy (Board board, ArrayList<Piece> pieces, Move move) {
        this.board = board;
        this.pieces = pieces;
        this.move = move;
    }

    public RealBattalion createBattalion () {
        if (isBattalion ()){
            this.battalion = new RealBattalion ( this.pieces );
            this.battalion.decorate ( this.decorator );
        }
        else throw new CanNotMakeBattalion ();
        return this.battalion;
    }

    public void dissolveBattalion (){
        this.battalion.undecorate ( this.decorator );
    }

    private void areBattalion () {
        BattalionCriteria battalion = new BattalionCriteria ();
        this.pieces = battalion.criteria ( this.pieces );
    }

    private boolean isBattalion () {
        areBattalion ();
        return this.pieces.size () == 3;
    }

    @Override
    public void move ( Board board, Move move ) {
        try {
            this.battalion.move ( board, move );
        } catch (CanNotMakeBattalion e) {
            this.dissolveBattalion ();
        }
    }

    @Override
    public void decorate ( PieceDecorator decorator ) {
    if (this.isBattalion ()) this.battalion.decorate ( decorator );

    }

    @Override
    public PieceDecorator undecorate ( PieceDecorator decorator ) {
        if (this.isBattalion ()) return this.battalion.undecorate ( decorator );
        else return decorator;
    }

    @Override
    public boolean isSameTeamAs ( Piece otherPiece ){
        return this.team.equals ( otherPiece.team );
    }
    @Override
    public void receiveAttacked ( int damage ) {
        this.battalion.receiveAttacked ( damage );
    }
    @Override
    public boolean isCost ( int expectedCost ) {
        return false;
    }
    @Override
    public void attack ( ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPiece ) {
        this.battalion.attack ( adjacentPieces, attackedPiece);
    }
    @Override
    public void receiveHealed ( int heal ) {
       this.battalion.receiveHealed ( heal );
    }


    // These getters are for testing only.
    @Override
    public Team getTeam(){
        return this.team;
    }
    @Override
    public int getLife () {
        return this.battalion.getLife ();
    }
    @Override
    public boolean isAlive() {
        return alive;
    }
}
