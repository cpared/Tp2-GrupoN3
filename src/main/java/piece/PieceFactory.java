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
        Piece soldier = new Soldier ( this.team );
        if (soldier.getCost() > this.playerPoints) throw new PlayerHas20PointsOnlyException ();
        else {
            this.playerPoints -= soldier.getCost();
            return soldier;
        }
    }

    public Piece createHealer () throws PlayerHas20PointsOnlyException {
        Piece healer = new Healer ( this.team );
        if (healer.getCost() > this.playerPoints) throw new PlayerHas20PointsOnlyException ();
        else this.playerPoints -= healer.getCost();
        return healer;
    }

    public Piece createRider () throws PlayerHas20PointsOnlyException {
        Piece rider = new Rider ( this.team );
        if (rider.getCost() > this.playerPoints) throw new PlayerHas20PointsOnlyException ();
        else this.playerPoints -= rider.getCost();
        return rider;
    }

    public Piece createCatapult () throws PlayerHas20PointsOnlyException {
        Piece catapult = new Catapult ( this.team );
        if (catapult.getCost() > this.playerPoints) throw new PlayerHas20PointsOnlyException ();
        else {
            this.playerPoints -= catapult.getCost();
            return catapult;
        }
    }
}


