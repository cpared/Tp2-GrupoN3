package piece;
import board.CanNotMakeThatMoveException;
import team.*;

public class Rider implements Piece {
    private Team team;
    private int cost = 3;
    private int life = 100;
    private int bodyAttack = 5;
    private int distanceAttack = 15;

    public Rider(Team team) {
        this.team = team;
    }

    public int getBodyAttack(){
        return this.bodyAttack;
    }
    @Override
    public int getLife(){
        return this.life;
    }

    public int getDistanceAttack(){
        return this.distanceAttack;
    }
    @Override
    public int getCost(){
        return this.cost;
    }

    @Override
    public void attack(Piece piece) {
        piece.getAttacked(this.bodyAttack);
    }

    @Override
    public void distanceAttack(Piece receivingPiece) {
        receivingPiece.getAttacked(this.distanceAttack);
    }
    @Override
    public void getAttacked(int damage){
        this.life -= damage;
    }
    @Override
    public void getHealed(int heal){
        this.life += heal;
    }
    @Override
    public Team getTeam(){
        return this.team;
    }
    @Override
    public int move(){
        return 3;
    }
    @Override
    public void heal(Piece receivingPiece) {
        throw new CanNotMakeThatMoveException ();
    }
}
