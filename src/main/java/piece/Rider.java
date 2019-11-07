package piece;
import team.*;

public class Rider extends Piece{
    private Team team;
    private int cost = 3;
    private int life = 100;
    private int bodyAttack = 5;
    private int distanceAttack = 15;

    public Rider(Team team) {
        this.team = team;
    }


    public int getBodyAttack(){
        return this.bodyAttack;
    }

    public int getLife(){
        return this.life;
    }

    public int getDistanceAttack(){
        return this.distanceAttack;
    }

    public int getCost(){
        return this.cost;
    }

    @Override
    public void attack(Piece piece) {
        piece.getAttacked(this.bodyAttack);
    }

    @Override
    public void distanceAttack(Piece receivingPiece) {
        receivingPiece.getAttacked(this.distanceAttack);
    }
    @Override
    public void getAttacked(int damage){
        this.life -= damage;
    }
    @Override
    public void getHeal(int heal){
        this.life += heal;
    }
    @Override
    public Team getTeam(){
        return this.team;
    }
}
