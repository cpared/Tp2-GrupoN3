package piece;

import board.Board;
import javafx.util.Pair;
import move.Move;
import team.Team;

import java.util.ArrayList;

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
    public void receiveAttacked ( int damage ) {
    }

    @Override
    public void attack (ArrayList<Piece> adjacentPieces, Pair<Piece, Integer> attackedPiece) {}

    @Override
    public void receiveHealed ( int heal ) {}

/*    @Override
    public void heal ( Piece receivingPiece ) {
    }*/

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
