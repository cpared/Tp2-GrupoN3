package piece;

import board.Board;
import move.Move;
import team.Team;

import java.util.ArrayList;

public class BattalionE implements Piece {
    private ArrayList<Piece> soldiers;

    private Team team;
    private int life;

    public BattalionE ( ArrayList<Piece> soldiers ) {

        this.soldiers = soldiers;
    }


    @Override
    public int getLife () {
        for (Piece soldier : soldiers) {
            this.life += soldier.getLife ();
        }
        return this.life;
    }

    @Override
    public int getCost () {
        return 0;
    }

    @Override
    public void move ( Board board, Move move ) {
        for (Piece soldier : soldiers) {
            soldier.move ( board, move );
        }
    }

    @Override
    public Team getTeam () {
        return this.team;
    }

    @Override
    public void distanceAttack ( Piece receivingPiece ) {
    }

    @Override
    public void getAttacked ( int damage ) {
        for (Piece soldier : soldiers) {
            soldier.getAttacked ( damage );
        }

    }

    @Override
    public void attack ( Piece piece ) {
        for (Piece soldier : soldiers) {
            soldier.attack ( piece );
        }
    }

    @Override
    public void getHealed ( int heal ) {
        for (Piece soldier : soldiers) {
            soldier.getHealed ( heal );
        }

    }

    @Override
    public void heal ( Piece receivingPiece ) {
    }

    @Override
    public boolean isCost ( int expectedCost ) {
        return true;
    }
}