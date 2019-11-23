package piece;

import player.PlayerHas20PointsOnlyException;
import team.Team;

public class PieceFactory {
    private Team team;
    private int playerPoints;

    public PieceFactory ( Team team ) {
        this.team = team;
        playerPoints = 20;
    }

    public Piece createSoldier () throws PlayerHas20PointsOnlyException {

        int cost = 1;
        if (cost > this.playerPoints) throw new PlayerHas20PointsOnlyException ();
        Piece soldier = new Soldier ( this.team );
        this.playerPoints -= cost;
        return soldier;
    }

    public Piece createHealer () throws PlayerHas20PointsOnlyException {

        int cost = 2;
        if (cost > this.playerPoints) throw new PlayerHas20PointsOnlyException ();
        Piece healer = new Healer ( this.team );
        this.playerPoints -= cost;
        return healer;
    }

    public Piece createRider () throws PlayerHas20PointsOnlyException {
        int cost = 3;
        if (cost > this.playerPoints) throw new PlayerHas20PointsOnlyException ();
        Piece rider = new Rider ( this.team );
        this.playerPoints -= cost;
        return rider;
    }

    public Piece createCatapult () throws PlayerHas20PointsOnlyException {
        int cost = 5;
        if (cost > this.playerPoints) throw new PlayerHas20PointsOnlyException ();
        Piece rider = new Catapult ( this.team );
        this.playerPoints -= cost;
        return rider;
    }
}


