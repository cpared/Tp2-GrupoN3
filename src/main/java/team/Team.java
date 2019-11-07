package team;

public class Team {
    private int pieces;

    public Team () {
        this.pieces = 0;
    }

    public void addPieceToTeam () {
        this.pieces = this.pieces + 1;
    }

    public void subtractPieceFromTeam () {
        this.pieces = this.pieces - 1;
    }

    public int numberOfMembersStillOnTeam () {
        return this.pieces;
    }
}