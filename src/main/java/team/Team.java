package team;

import game.Ended;
import game.Game;
import piece.Piece;

import java.util.ArrayList;

public class Team {
    public int identifier;
    private ArrayList<Piece> pieces;
    private Game game;

    public Team (int identifier, Game game) {
        this.identifier = identifier;
        this.pieces = new ArrayList<Piece> ( );
        this.game = game;
    }

    public void addPieceToTeam (Piece newPiece) {
        this.pieces.add ( newPiece );
    }

    public Piece subtractPieceFromTeam (Piece removablePiece) throws  PieceDoesNotBelongToTeamException {
        if (!this.pieces.contains ( removablePiece )) throw  new PieceDoesNotBelongToTeamException ();
        int index = this.pieces.indexOf ( removablePiece );
        Piece piece = this.pieces.remove ( index );
        if (this.pieces.size ()  == 0) game.changeState ( new Ended () );
        return piece;
    }

    public boolean isNumberOfMembersStillOnTeam ( int numberOfMembers ) {
        return this.pieces.size () == numberOfMembers;
    }

    public boolean equals (Team team) {
        return this.identifier == team.identifier;
    }

    public void playerIsReadyToPlay() {
        this.game.playerIsReadyToPlay ( game.getAvailablePlayer () );
    }

}