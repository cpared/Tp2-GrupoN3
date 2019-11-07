package piece;
import team.*;
public class Piece {
    private Team team;
    public void Piece(Team team){
        this.team = team;
    }

    public Team getTeam() {
        return this.team;
    }
    public int move(){
        return 1;
    }

}
