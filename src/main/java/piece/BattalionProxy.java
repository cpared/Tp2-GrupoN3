package piece;

import board.Board;
import board.CanNotMakeBattalion;
import criteria.BattalionCriteria;
import criteria.SoldierCriteria;
import move.Move;
import team.Team;

import java.util.ArrayList;

public class BattalionProxy {
    private ArrayList<Piece> pieces;
    private Board board;
    private BattalionE batallion = new BattalionE ( new ArrayList<Piece> (  ) );

    public BattalionProxy (Board board, ArrayList<Piece> pieces) {
        this.board = board;
        this.pieces = pieces;
        form();
    }

    public void form () {
        if (isBattalion ()) this.batallion = new BattalionE ( this.pieces );
        else throw new CanNotMakeBattalion ();
    }

    private void areSoldiers () {
        SoldierCriteria soldiers = new SoldierCriteria ();
        this.pieces = soldiers.criteria ( this.pieces );
    }

    private void areAdjacent () {
        ArrayList<Piece> adjacents = new ArrayList<Piece> (  );
        for (Piece piece: pieces) {

        }

        if (adjacents.size () < 3) this.pieces = new ArrayList<Piece> (  );
        else this.pieces =  adjacents;
    }

    private void areBattalion () {
        this.areSoldiers ();
        this.areAdjacent ();
        BattalionCriteria battalion = new BattalionCriteria ();
        this.pieces = battalion.criteria ( this.pieces );
    }

    private boolean isBattalion () {
        areBattalion ();
        return this.pieces.size () == 3;
    }

    public int getLife () {
        return this.batallion.getLife ();
    }

    public void move ( Board board, Move move ) {
        this.batallion.move ( board, move );
    }

    public Team getTeam () {
        return this.batallion.getTeam ();
    }

    public void getAttacked ( int damage ) {
        this.batallion.getAttacked ( damage );
    }

    public void attack ( Piece piece ) {
        this.batallion.attack ( piece );
    }

    public void getHealed ( int heal ) {
       this.batallion.getHealed ( heal );
    }

    public void heal ( Piece receivingPiece ) {
        this.batallion.heal ( receivingPiece );
    }
}
