package player;

import board.Board;
import move.Move;
import piece.Piece;
import piece.PieceFactory;
import team.NoMembersLeftException;
import team.PieceDoesNotBelongToTeamException;
import team.Team;

public class Player {
    private String name;
    private Team team;
    private PieceFactory factory;

    public Player ( String name, Team team ) {
        this.name = name;
        this.team = team;
        this.factory = new PieceFactory ( this.team );
    }

    public void placePieceOnBoard ( Piece piece, Board board, Move move ) {
        board.placePiece ( piece, move );
    }

    public void movePiece ( Board board, Move move ) {
        board.move ( move );
    }

    public Piece removePieceFromTeam (Piece removablePiece) throws NoMembersLeftException, PieceDoesNotBelongToTeamException {
        /*
        try {
            this.team.subtractPieceFromTeam (removablePiece);
        } catch ( PieceDoesNotBelongToTeamException e) {
            if (this.team.isNumberOfMembersStillOnTeam ( 0 )) throw new NoMembersLeftException ();
        }
        */
        if (removablePiece == null) return null;
        return this.team.subtractPieceFromTeam (removablePiece);

    }

    public void chosePiece (Piece chosenPiece) throws PlayerHas20PointsOnlyException {
        team.addPieceToTeam (chosenPiece);
    }

    public boolean isNumberOfPiecesOnTeam ( int numberOfMembers ) {
        return this.team.isNumberOfMembersStillOnTeam ( numberOfMembers );
    }

    public boolean equals ( Player player ) {
        return this.name.equals ( player.name ) && this.team.equals ( player.team );
    }

    public void chooseBattalion (Board board, Move move) {
        board.createBattalion ( move );
    }

    public void attack (Board board, Move move) throws PieceDoesNotBelongToTeamException {
        board.attack ( move );
        Piece removed = board.removeDeadPiece ( move );
        this.removePieceFromTeam (removed);

    }

    // This getter is only for testing, they dont belong in the model.
    public String name () {
        return this.name;
    }
}
