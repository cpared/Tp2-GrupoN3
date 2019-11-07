package piece;
import team.*;

public class Healer extends Piece{
    private Team team;
    private int cost = 2;
    private int life = 75;
    private int heal = 15;

    void Healer(Team team){
        this.team = team;
    }

    public int getLife(){
        return this.life;
    }

    public int getCost(){
        return this.cost;
    }

    public int getHeal(){
        return this.heal;
    }

    public void recibeAttack(int damage){
        this.life -= damage;
    }

    public void recibeHeal(int heal){
        this.life += heal;
    }
}
