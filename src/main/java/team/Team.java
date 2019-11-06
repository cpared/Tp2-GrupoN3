package team;

import piece.Piece;

import java.util.ArrayList;

public class Team {
    private ArrayList<Piece> pieces;

    public Team () {
        pieces = new ArrayList<Piece> ();
    }

    public void addMember ( Piece piece ) {
        pieces.add ( piece );
    }

    public ArrayList<Piece> getMembers () {
        return pieces;
    }

    public boolean teamHasNoMembers () {
        return this.pieces.size () == 0;
    }
}