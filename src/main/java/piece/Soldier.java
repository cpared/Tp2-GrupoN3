package piece;
import board.CanNotMakeThatMoveException;
import team.*;
public class Soldier implements Piece {
    private Team team;
    private int cost = 1;
    private int life = 100;
    private int bodyAttack = 10;
    private int distanceAttack = 0;

    public Soldier(Team team) {
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
    public void attack(Piece piece){
        piece.getAttacked(this.bodyAttack);
    }
    @Override
    public Team getTeam(){
        return this.team;
    }
    @Override
    public void getAttacked(int damage){
        this.life -= damage;
        if(this.life < 0) this.life = 0;
    }
    @Override
    public void getHealed(int heal){
        this.life += heal;
        if(this.life > 100) this.life = 100;
    }
    @Override
    public int move(){
        return 3;
    }
    @Override
    public void distanceAttack(Piece receivingPiece) {
        throw new CanNotMakeThatMoveException(); //Raise another error is correct, but this for now
    }
    @Override
    public void heal(Piece receivingPiece) {
        throw new CanNotMakeThatMoveException();
    }


}
