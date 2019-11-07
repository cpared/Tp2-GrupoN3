package piece;

public class Soldier extends Piece{
    private Team team;
    private int cost = 1;
    private int life = 100;
    private int bodyAttack = 10;
    private int distanceAttack = 0;

    void Soldier(Team team){
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
}
