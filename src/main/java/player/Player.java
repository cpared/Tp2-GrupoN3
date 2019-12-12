package player;

import Face.*;
import board.Board;
import move.Move;
import piece.Piece;
import team.PieceDoesNotBelongToTeamException;
import team.Team;

public class Player {
    private String name = "";
    private Team team;
    private Face face;
    private Board board;

    public Player ( String name, Team team, Board board ) {
        this.name = name;
        this.team = team;
        this.board = board;
        this.face = new InitialFace ( board, team );
    }

    public void placePieceOnBoard ( Piece piece,  Move move ) {
        //board.placePiece ( piece, move );
        this.face.playerPlacesPieceOnBoard ( piece, move );
    }

    public void movePiece (  Move move ) {
        this.face.playerMovesPieceOnBoard ( move );
    }

    public Piece removePieceFromTeam ( Piece removablePiece ) {
        if (removablePiece == null) return null;
        return this.team.subtractPieceFromTeam ( removablePiece );
    }

    public void chosePiece ( Piece chosenPiece ) throws PlayerHas20PointsOnlyException {
        team.addPieceToTeam ( chosenPiece );
    }

    public boolean isNumberOfPiecesOnTeam ( int numberOfMembers ) {
        return this.team.isNumberOfMembersStillOnTeam ( numberOfMembers );
    }

    public boolean equals ( Player player ) {
        return this.name.equals ( player.name ) && this.team.equals ( player.team );
    }

    public void chooseBattalion ( Move move ) {
        this.face.playerChoosesBattalion ( move );
    }

    public void attack ( Move move ) throws PieceDoesNotBelongToTeamException {
        this.face.playerAttacks ( move );
    }

    public Piece playerChoosesSoldier () throws PlayerHas20PointsOnlyException {
        Piece piece = this.face.playerChoosesSoldier ();
        this.chosePiece ( piece );
        return piece;
    }

    public Piece playerChoosesHealer () throws PlayerHas20PointsOnlyException {
        Piece piece = this.face.playerChoosesHealer ();
        this.chosePiece ( piece );
        return piece;
    }

    public Piece playerChoosesRider () throws PlayerHas20PointsOnlyException {
        Piece piece = this.face.playerChoosesRider ();
        this.chosePiece ( piece );
        return piece;
    }

    public Piece playerChoosesCatapult () throws PlayerHas20PointsOnlyException {
        Piece piece = this.face.playerChoosesCatapult ();
        this.chosePiece ( piece );
        return piece;
    }

    public Piece removeDeadPieceFromBoard ( Move move ) throws PieceDoesNotBelongToTeamException {
        Piece piece = this.face.removeDeadPieceFromBoard ( move );
        return piece;
    }

    public void changeFace () {
        if (this.isNumberOfPiecesOnTeam ( 0 )) throw new PlayerMustChooseAtLeastOnePieceToStartGameException ();
        this.face = new GameFace ( this.board, this, team );
    }

    // This getter is only for testing, they dont belong in the model.
    public String name () {
        return this.name;
    }

    public int getPoints () {
        return this.face.getPoints ();
    }
}
