package team;

import piece.Piece;

import java.util.ArrayList;

public class Team {
    private int pieces;
    private ArrayList<Piece> piece;

    public Team () {
        this.pieces = 0;
        this.piece = new ArrayList<Piece> ( );
    }

    public void addPieceToTeam () {
        this.pieces = this.pieces + 1;
    }

    public void subtractPieceFromTeam () throws NoMembersLeftException{
        if (this.pieces == 0){ throw new NoMembersLeftException (); }
        else this.pieces = this.pieces - 1;
    }

    public boolean isNumberOfMembersStillOnTeam ( int numberOfMembers ) {
        return this.pieces == numberOfMembers;
    }

    public int numberOfMembersStillOnTeam ( ) {
        return this.pieces;
    }
}