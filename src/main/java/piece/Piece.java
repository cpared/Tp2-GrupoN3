package piece;

import board.CanNotMakeThatMoveException;
import team.*;

public interface Piece {
    public Team team = new Team ();
    public int life = 0;
    public int cost = 0;

    public Team getTeam ();

    public int move ();

    public void attack ( Piece piece );

    public int getLife ();

    public void getHealed ( int heal );

    public void distanceAttack ( Piece receivingPiece );

    public void heal ( Piece receivingPiece );

    public void getAttacked ( int damage );

    public int getCost ();

}


/*
package piece;
import board.CanNotMakeThatMoveException;
import team.*;
public interface Piece implements PieceCreator {
    private Team team;
    private int life;
    public Piece(Team team){
        this.team = team;
    }

    public Piece() {
    }

    public Team getTeam() {
        return this.team;
    }
    public int move();

    public void attack(Piece piece){
        throw new CanNotMakeThatMoveException(); //Raise another error is correct, but this for now
    }
    public int getLife(){
        return life;
    }
    public void getHeal(int heal){
        throw new CanNotMakeThatMoveException();
    }
    public void distanceAttack(Piece receivingPiece) {
        throw new CanNotMakeThatMoveException(); //Raise another error is correct, but this for now
    }

    public void heal(Piece receivingPiece) {
        throw new CanNotMakeThatMoveException();
    }
    public void getAttacked(int damage){

    }
}
 */