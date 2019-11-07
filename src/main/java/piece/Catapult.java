package piece;

public class Catapult extends Piece{
    private Team team;
    private int cost = 5;
    private int life = 50;
    private int bodyAttack = 0;
    private int distanceAttack = 20;

    void Catapult(Team team){
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

    public void recibeAttack(int damage){
        this.life -= damage;
    }

    public void recibeHeal(int heal){
        this.life += heal;
    }
}
