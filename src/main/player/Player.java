package player;

public class Player {
    private String name;
    private int points;

    public Player () {
            this.points = 20;
            this.name = "";
    }

    public Player (String name) {
            this.points = 20;
            this.name = name;
    }

    public void assignName ( String name ) {
            this.name = name;
    }

    public String getName () {
            return this.name;
    }

    public int obtainPoints () {
            return this.points;
    }

    public void subtractPoints (int pointsToSubtract) {
            this.points = this.points - pointsToSubtract;
    }

    public String obtainName () { return this.name; }
}