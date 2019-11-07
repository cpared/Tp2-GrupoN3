package piece;

public class Rider extends Piece{
    private Team team;
    private int cost = 3;
    private int life = 100;
    private int bodyAttack = 5;
    private int distanceAttack = 15;

    void Rider(Team team){
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
