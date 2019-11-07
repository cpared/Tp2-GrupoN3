package player;

import board.Board;
import piece.Piece;
import team.Team;

import java.util.Scanner;

public class Player {
    private String name;
    private int points;
    private Team team;

    public Player (String name) {
        this.points = 20;
        this.name = name;
    }

    public void name(){
        String entry = "";
        Scanner in = new Scanner (System.in);
        while (entry.toLowerCase ().equals ( "" )) {
            System.out.println ("Enter your name: \"");
            entry = in.nextLine ();
            System.out.println ("Your name is: " + entry);
        }
        this.name = entry;
    }

    public String getName () {
        return this.name;
    }

    public int obtainPoints () {
        return this.points;
    }

    public void subtractPoints (int pointsToSubtract) throws PlayerHas20PointsOnlyException{
        if (this.points < pointsToSubtract) throw new PlayerHas20PointsOnlyException ();

        this.points = this.points - pointsToSubtract;

    }

    public String obtainName () { return this.name; }

    public Piece choosePiece() throws PlayerHas20PointsOnlyException {
        Piece piece = new Piece(new Team());
        int points= 0;
        this.subtractPoints ( points );
        team.addMember ( piece );
        return piece;
    }

    public void placePieceOnBoard( Piece piece, Board board ,int row, int column) {
        board.placePiece ( piece, row, column );
    }

    public void movePiece( Piece piece, Board board, int firstRow,int firstColumn,int secondRow,int secondColumn) {
        board.movePiece ( firstRow, firstColumn, secondRow, secondColumn );
    }
}


/*
package team;
import piece.Piece;

import java.util.ArrayList;

public class Team {
    private ArrayList<Piece> pieces;

    public Team () {
        pieces = new ArrayList<Piece> (  );
    }

    public void addMember (Piece piece){
        pieces.add ( piece );
    }

    public ArrayList<Piece> getMembers () {
        return pieces;
    }

    public boolean teamHasNoMembers() {
        return this.pieces.size () == 0;
    }
}


*/
