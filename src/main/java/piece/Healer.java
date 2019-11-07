package piece;
import board.CanNotMakeThatMoveException;
import team.*;

public class Healer extends Piece{
    private Team team;
    private int cost = 2;
    private int life = 75;
    private int heal = 15;

    public Healer(Team team) {
        super(team);
    }



    public int getCost(){
        return this.cost;
    }


    @Override
    public void heal(Piece piece){
        if (this.team.getClass() != piece.getTeam().getClass()){
            throw new CanNotMakeThatMoveException();
        }
        piece.getHeal(heal);

    }

}
