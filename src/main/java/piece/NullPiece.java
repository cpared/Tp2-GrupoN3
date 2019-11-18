package piece;

import team.Team;

public class NullPiece implements Piece {
    @Override
    public int getLife () {
        return this.life;
    }

    @Override
    public int getCost () {
        return this.cost;
    }

    @Override
    public int move () {
        return 0;
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
    public void attack ( Piece piece ) {}

    @Override
    public void getHealed ( int heal ) {}

    @Override
    public void heal ( Piece receivingPiece ) {
    }

    @Override
    public boolean isCost (int expectedCost) {
        return true;
    }
}
