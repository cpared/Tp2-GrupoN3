package piece;

import board.Board;
import game.Game;
import javafx.util.Pair;
import move.Move;
import piece.battalion.BattalionComposite;
import team.Team;

import java.util.ArrayList;

public class NullPiece implements Piece {
    public Team team = new Team(500, new Game ());

    @Override
    public void move ( Board board , Move move) {
    }

    @Override
    public boolean isSameTeamAs ( Piece otherPiece ){
        return this.team.equals ( otherPiece.team );
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
    public void formPartOfBattalion ( BattalionComposite battalion){

    }
    @Override
    public void notFormPartOfBattalion ( BattalionComposite battalion){

    }

    // These getters are for testing only.
    @Override
    public Team getTeam(){
        return this.team;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void penalize(Team team) {

    }

    @Override
    public int getLife () {
        return this.life;
    }
}
