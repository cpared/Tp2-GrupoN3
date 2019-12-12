package piece;

import player.PlayerHas20PointsOnlyException;
import team.Team;

public class PieceFactory {
    private Team team;
    private int playerPoints;
    private int soldierCost;
    private int healerCost;
    private int riderCost;
    private int catapultCost;

    public PieceFactory ( Team team ) {
        this.team = team;
        playerPoints = 20;
        soldierCost = 1;
        this.healerCost = 2;
        this.riderCost = 3;
        this.catapultCost = 5;
    }

    public Piece createSoldier () throws PlayerHas20PointsOnlyException {

        if (this.soldierCost > this.playerPoints) throw new PlayerHas20PointsOnlyException ();
        Piece soldier = new Soldier ( this.team );
        this.playerPoints -= this.soldierCost;
        return soldier;
    }

    public Piece createHealer () throws PlayerHas20PointsOnlyException {

        if (this.healerCost > this.playerPoints) throw new PlayerHas20PointsOnlyException ();
        Piece healer = new Healer ( this.team );
        this.playerPoints -= this.healerCost;
        return healer;
    }

    public Piece createRider () throws PlayerHas20PointsOnlyException {

        if (this.riderCost > this.playerPoints) throw new PlayerHas20PointsOnlyException ();
        Piece rider = new Rider ( this.team );
        this.playerPoints -= this.riderCost;
        return rider;
    }

    public Piece createCatapult () throws PlayerHas20PointsOnlyException {

        if (this.catapultCost > this.playerPoints) throw new PlayerHas20PointsOnlyException ();
        Piece rider = new Catapult ( this.team );
        this.playerPoints -= this.catapultCost;
        return rider;
    }

    public void eliminatePiece ( Piece eliminatedPiece) {
        if (eliminatedPiece.isCost ( 1 )) {
            this.playerPoints += this.soldierCost;
        } else if (eliminatedPiece.isCost ( 2 )){
            this.playerPoints += this.healerCost;
        } else if (eliminatedPiece.isCost ( 3 )){
            this.playerPoints += this.riderCost;
        } else if (eliminatedPiece.isCost ( 5 )){
            this.playerPoints += this.catapultCost;
        }
    }

    public int getPoints(){
        return this.playerPoints;
    }


}


