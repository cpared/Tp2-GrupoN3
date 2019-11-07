package player;

import board.Board;
import piece.Piece;
import piece.PieceFactory;

import team.Team;

import java.util.Scanner;

public class Player {
    private String name;
    private int points;
    private Team team;
    private PieceFactory factory = new PieceFactory ();

    public Player ( String name, Team team ) {
        this.points = 20;
        this.name = name;
        this.team = team;
    }

    public String getName () {
        return this.name;
    }

    public int obtainPoints () {
        return this.points;
    }

    public void subtractPoints ( int pointsToSubtract ) throws PlayerHas20PointsOnlyException {
        if (this.points < pointsToSubtract) throw new PlayerHas20PointsOnlyException ();

        this.points = this.points - pointsToSubtract;

    }

    public String obtainName () {
        return this.name;
    }

    public Piece choosePiece ( String pieceName ) throws PlayerHas20PointsOnlyException {
        Piece piece = this.factory.getPiece ( pieceName, this.team );
        this.subtractPoints ( piece.getCost () );
        team.addPieceToTeam ();

        return piece;
    }

    public void placePieceOnBoard ( Piece piece, Board board, int row, int column ) {
        board.placePiece ( piece, row, column );
    }

    public void movePiece ( Board board, int firstRow, int firstColumn, int secondRow, int secondColumn ) {
        board.movePiece ( firstRow, firstColumn, secondRow, secondColumn );
    }

    public Team getTeam () {
        return this.team;
    }

    public void removePieceFromTeam () {
        this.team.subtractPieceFromTeam ();
    }

}
