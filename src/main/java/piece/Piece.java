package piece;
import team.*;
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

    public void attack(Piece receivingPiece) {
    }

    public void distanceAttack(Piece receivingPiece) {
    }

    public void heal(Piece receivingPiece) {
    }
}
