package player;

public class PlayerHas20PointsOnlyException extends Exception {

    private static final String ERROR_MSG = "Player has only 20 points, subtracting over 20 points is not valid.";

    public PlayerHas20PointsOnlyException () {

    }

    @Override
    public String getMessage () {
        return ERROR_MSG;
    }
}
