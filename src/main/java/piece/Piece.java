package piece;
import board.CanNotMakeThatMoveException;
import team.*;
public class Piece {
    private Team team;
    private int life;
    public Piece(Team team){
        this.team = team;
    }

    public Team getTeam() {
        return this.team;
    }
    public int move(){
        return 1;
    }
    public void attack(Piece piece){
        throw new CanNotMakeThatMoveException(); //Raise another error is correct, but this for now
    }
    public int getLife(){
        return life;
    }
    public void getHeal(int heal){
        this.life += heal;
    }
    public void distanceAttack(Piece receivingPiece) {
        throw new CanNotMakeThatMoveException(); //Raise another error is correct, but this for now
    }

    public void heal(Piece receivingPiece) {
        throw new CanNotMakeThatMoveException();
    }
    public void getAttacked(int damage){
        this.life -= damage;
    }
}
