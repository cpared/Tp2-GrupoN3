package piece;
import team.*;
public class Healer {
    private Team team;
    private int cost = 2;
    private int life = 75;
    private int heal = 15;

    void Healer(Team team){
        this.team = team;
    }
}
