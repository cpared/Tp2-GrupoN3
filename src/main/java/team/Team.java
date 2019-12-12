package team;

import piece.Piece;

import java.util.ArrayList;

public class Team {
    public int identifier;
    private ArrayList<Piece> pieces;

    public Team (int identifier) {
        this.identifier = identifier;
        this.pieces = new ArrayList<Piece> ( );
    }

    public void addPieceToTeam (Piece newPiece) {
        this.pieces.add ( newPiece );
    }

    public Piece subtractPieceFromTeam (Piece removablePiece) throws  PieceDoesNotBelongToTeamException {
        if (!this.pieces.contains ( removablePiece )) throw  new PieceDoesNotBelongToTeamException ();
        int index = this.pieces.indexOf ( removablePiece );
        Piece piece = this.pieces.remove ( index );
        if (this.pieces.size ()  == 0) throw  new NoMembersLeftException ();
        return piece;
    }

    public boolean isNumberOfMembersStillOnTeam ( int numberOfMembers ) {
        return this.pieces.size () == numberOfMembers;
    }

    public boolean equals (Team team) {
        return this.identifier == team.identifier;
    }
}