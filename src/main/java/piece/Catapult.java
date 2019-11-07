package piece;

public class Catapult {
    private Team team;
    private int cost = 5;
    private int life = 50;
    private int bodyAttack = 0;
    private int distanceAttack = 20;

    void Catapult(Team team){
        this.team = team;
    }
}
