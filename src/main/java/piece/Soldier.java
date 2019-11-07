package piece;

public class Soldier {
    private Team team;
    private int cost = 1;
    private int life = 100;
    private int bodyAttack = 10;
    private int distanceAttack = 0;

    void Soldier(Team team){
        this.team = team;
    }
}
