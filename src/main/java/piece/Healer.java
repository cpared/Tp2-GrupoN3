package piece;
import board.CanNotMakeThatMoveException;
import team.*;

public class Healer extends Piece{
    private Team team;
    private int cost = 2;
    private int life = 75;
    private int heal = 15;

    public Healer(Team team) {
        this.team = team;
    }


    public int getLife(){
        return life;
    }
    public int getCost(){
        return this.cost;
    }
    @Override
    public void getHeal(int heal){
        this.life += heal;
    }
    @Override
    public void heal(Piece piece){
        if (this.team.getClass() != piece.getTeam().getClass()){
            throw new CanNotMakeThatMoveException();
        }
        piece.getHeal(heal);

    }
    @Override
    public void getAttacked(int damage){
        this.life -= damage;
    }
    @Override
    public Team getTeam(){
        return this.team;
    }
}
