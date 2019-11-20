package player;

import board.Board;
import move.Move;
import piece.Piece;
import piece.PieceFactory;

import team.NoMembersLeftException;
import team.Team;

import java.util.Scanner;

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
        System.out.println ( "player tells piece from column "+ move.fromColumn + " from row " + move.fromRow + " to move to column " + move.toColumn + " and row " + move.toRow);
        board.move ( move );
        //board.movePiece ( move );
    }

    public void removePieceFromTeam () throws NoMembersLeftException {
        this.team.subtractPieceFromTeam ();
    }

    public Piece chooseSoldier () throws PlayerHas20PointsOnlyException {
        Piece soldier = this.factory.createSoldier ();
        team.addPieceToTeam ();

        return soldier;
    }

    public Piece chooseRider () throws PlayerHas20PointsOnlyException {
        Piece rider = this.factory.createRider ();
        team.addPieceToTeam ();

        return rider;
    }

    public Piece chooseHealer () throws PlayerHas20PointsOnlyException {
        Piece healer = this.factory.createHealer ();
        team.addPieceToTeam ();

        return healer;
    }

    public Piece chooseCatapult () throws PlayerHas20PointsOnlyException {
        Piece catapult = this.factory.createCatapult ();
        team.addPieceToTeam ();

        return catapult;
    }

    public boolean isNumberOfPiecesOnTeam ( int numberOfMembers ) {
        return this.team.isNumberOfMembersStillOnTeam ( numberOfMembers );
    }

    public int numberOfPiecesOnTeam () {
        return this.team.numberOfMembersStillOnTeam ();
    }

    public boolean equals ( Player player ) {
        return this.name.equals ( player.name ) && this.team.equals ( player.team );
    }

    public void chooseBattalion (Board board, Move move) {
        board.createBattalion ( move );
    }

    // This getter is only for testing, they dont belong in the model.
    public String name () {
        return this.name;
    }
}
