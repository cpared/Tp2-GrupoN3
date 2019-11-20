package piece;

import board.Board;
import move.Move;
import team.Team;

public class NullPiece implements Piece {
    @Override
    public int getLife () {
        return this.life;
    }

    @Override
    public int getCost () {
        return this.cost;
    }

    @Override
    public void move ( Board board , Move move) {
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
    }

    @Override
    public void attack ( Piece piece ) {}

    @Override
    public void getHealed ( int heal ) {}

    @Override
    public void heal ( Piece receivingPiece ) {
    }

    @Override
    public boolean isCost (int expectedCost) {
        return true;
    }

    @Override
    public void decorate ( PieceDecorator decorator ) {

    }

    @Override
    public PieceDecorator undecorate ( PieceDecorator decorator ) {
        return null;
    }
}
