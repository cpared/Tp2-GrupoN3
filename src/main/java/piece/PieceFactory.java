package piece;

import player.PlayerHas20PointsOnlyException;
import team.Team;

public class PieceFactory {
    private Team team = new Team ();
    private int playerPoints = 20;

    public PieceFactory ( Team team ) {
        this.team = team;
    }

    public Piece createSoldier ( )  throws PlayerHas20PointsOnlyException {
        Piece soldier = new Soldier ( this.team );
        if ( soldier.cost > this.playerPoints ) throw new PlayerHas20PointsOnlyException ( );
        this.playerPoints -= soldier.cost;
        return soldier;
    }

    public Piece createHealer ( ) throws PlayerHas20PointsOnlyException {
        Piece healer = new Healer ( this.team );
        if ( healer.cost > this.playerPoints ) throw new PlayerHas20PointsOnlyException ( );
        this.playerPoints -= healer.cost;
        return healer;
    }

    public Piece createRider ( ) throws PlayerHas20PointsOnlyException {
        Piece rider = new Rider ( this.team );
        if ( rider.cost > this.playerPoints ) throw new PlayerHas20PointsOnlyException ( );
        this.playerPoints -= rider.cost;
        return rider;
    }

    public Piece createCatapult ( ) throws PlayerHas20PointsOnlyException{
        Piece catapult = new Catapult ( this.team );
        if ( catapult.cost > this.playerPoints ) throw new PlayerHas20PointsOnlyException ( );
        this.playerPoints -= catapult.cost;
        return catapult;
    }
}

/*
game loop pattern.
 */
