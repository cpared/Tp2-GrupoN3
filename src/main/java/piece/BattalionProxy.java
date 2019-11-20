package piece;

import board.Battalion;
import board.Board;
import board.CanNotMakeBattalion;
import criteria.BattalionCriteria;
import move.Move;
import team.Team;

import java.util.ArrayList;

public class BattalionProxy implements Battalion {
    private ArrayList<Piece> pieces;
    private Board board;
    public RealBattalion battalion = new RealBattalion ( new ArrayList<Piece> (  ) );
    private Move move;
    private PieceDecorator decorator = new SoldierDecorator ( this );

    public BattalionProxy (Board board, ArrayList<Piece> pieces, Move move) {
        this.board = board;
        this.pieces = pieces;
        this.move = move;
    }

    public RealBattalion createBattalion () {
        if (isBattalion ()){
            System.out.println ( "ES BATALLON");
            this.battalion = new RealBattalion ( this.pieces );
            this.battalion.decorate ( this.decorator );
        }
        else throw new CanNotMakeBattalion ();
        return this.battalion;
    }

    public void dissolveBattalion (){
        System.out.println ( "dissolved battalion 99999999999999999999999999999999999999999999999999999999" );
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
        System.out.println ( "proxy is told to move" );
        System.out.println ( this.pieces.size ());
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



    // Cant actually access this methods.
    @Override
    public int getLife () {
        return this.battalion.getLife ();
    }
    public Team getTeam () {
        return this.battalion.getTeam ();
    }
    @Override
    public void getAttacked ( int damage ) {
        this.battalion.getAttacked ( damage );
    }
    @Override
    public int getCost () {
        return 0;
    }
    @Override
    public boolean isCost ( int expectedCost ) {
        return false;
    }
    @Override
    public void attack ( Piece piece ) {
        this.battalion.attack ( piece );
    }
    @Override
    public void getHealed ( int heal ) {
       this.battalion.getHealed ( heal );
    }
    @Override
    public void distanceAttack ( Piece receivingPiece ) {

    }
    @Override
    public void heal ( Piece receivingPiece ) {
        this.battalion.heal ( receivingPiece );
    }
}
