package piece;

public class Piece {
    private Team team = new Gold();
    public Piece(){
    }
    public Piece(Team team){
        this.team = team;
    }
    public Team getTeam() {
        return this.team;
    }
}
