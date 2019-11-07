package piece;
import board.CanNotMakeThatMoveException;
import team.*;

public class Healer implements Piece {
    private Team team;
    private int cost = 2;
    private int life = 75;
    private int heal = 15;

    public Healer(Team team) {
        this.team = team;
    }

    @Override
    public int getLife(){
        return life;
    }
    @Override
    public int getCost(){
        return this.cost;
    }
    @Override
    public void getHealed(int heal){
        this.life += heal;
    }
    @Override
    public void heal(Piece piece){
        if (this.team.getClass() != piece.getTeam().getClass()){
            throw new CanNotMakeThatMoveException();
        }
        piece.getHealed(heal);

    }
    @Override
    public void getAttacked(int damage){
        this.life -= damage;
    }
    @Override
    public Team getTeam(){
        return this.team;
    }
    @Override
    public int move(){
        return 3;
    }
    @Override
    public void attack(Piece piece){
        throw new CanNotMakeThatMoveException(); //Raise another error is correct, but this for now
    }
    @Override
    public void distanceAttack(Piece receivingPiece) {
        throw new CanNotMakeThatMoveException(); //Raise another error is correct, but this for now
    }
}
