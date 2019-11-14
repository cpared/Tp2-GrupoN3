package player;

import board.Board;
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

    public String getName () {
        return this.name;
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

    public void removePieceFromTeam () throws NoMembersLeftException {
        this.team.subtractPieceFromTeam ();
    }

    public Piece chooseSoldier () throws PlayerHas20PointsOnlyException {
        Piece soldier = this.factory.createSoldier ( );
        team.addPieceToTeam ();

        return soldier;
    }

    public Piece chooseRider () throws PlayerHas20PointsOnlyException {
        Piece rider = this.factory.createRider ( );
        team.addPieceToTeam ();

        return rider;
    }

    public Piece chooseHealer () throws PlayerHas20PointsOnlyException {
        Piece healer = this.factory.createHealer ( );
        team.addPieceToTeam ();

        return healer;
    }

    public Piece chooseCatapult () throws PlayerHas20PointsOnlyException {
        Piece catapult = this.factory.createCatapult ( );
        team.addPieceToTeam ();

        return catapult;
    }

}
