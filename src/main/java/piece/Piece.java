package piece;

public class Piece {
    private Team team;
    public Piece(Team team){
        this.team = team;
    }
    public Team getTeam() {
        return this.team;
    }
    public int move(){
        return 1;
    }
}
