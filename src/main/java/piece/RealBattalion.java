package piece;

import board.Battalion;
import board.Board;
import board.CanNotMakeBattalion;
import board.CanNotMakeThatMoveException;
import move.Builder;
import move.Move;
import team.Team;

import java.security.KeyStore;
import java.util.ArrayList;

public class RealBattalion implements Battalion {
    private ArrayList<Piece> soldiers;
    private PieceDecorator decorator;

    private Team team;
    private int life;

    public RealBattalion ( ArrayList<Piece> soldiers ) {

        this.soldiers = soldiers;
    }


    @Override
    public int getLife () {
        return 0;
    }

    @Override
    public int getCost () {
        return 0;
    }

    @Override
    public void move ( Board board, Move move ) {
        int fromRow = move.fromRow;
        int fromColumn = move.fromColumn -1;
        int toRow = move.toRow ;
        int toColumn = move.toColumn -1 ;
        ArrayList<Piece> couldntMove = new ArrayList<Piece> (  );
        for (Piece soldier : soldiers) {
            Move fixedMove = new Builder ().fromRow ( fromRow ).fromColumn ( fromColumn ).ToRow ( toRow ).ToColumn ( toColumn ).build ();
            soldier.undecorate ( this.decorator );
            try {
                soldier.move ( board, fixedMove );
            } catch (CanNotMakeThatMoveException e){
                couldntMove.add ( soldier );
                fromColumn++;
                toColumn++;
                continue;
            }
            fromColumn++;
            toColumn++;
            soldier.decorate ( this.decorator );

        }
        soldiers.removeAll ( couldntMove );
        if (couldntMove.size ()>0) throw new CanNotMakeBattalion ();
    }

    @Override
    public Team getTeam () {
        return this.team;
    }

    @Override
    public void distanceAttack ( Piece receivingPiece ) {
    }

    @Override
    public void getAttacked ( int damage ) {
    }

    @Override
    public void attack ( Piece piece ) {
    }

    @Override
    public void getHealed ( int heal ) {
    }

    @Override
    public void heal ( Piece receivingPiece ) {
    }

    @Override
    public boolean isCost ( int expectedCost ) {
        return true;
    }

    @Override
    public void decorate ( PieceDecorator decorator ) {
        this.decorator = decorator;

        for (Piece soldier : soldiers) {
            soldier.decorate ( decorator );
        }
    }

    @Override
    public PieceDecorator undecorate ( PieceDecorator decorator ) {
        this.decorator = null;
        for (Piece soldier : soldiers) {
            soldier.undecorate ( decorator );
        }
        return decorator;
    }

    @Override
    public RealBattalion createBattalion (){
        return null;
    }
}