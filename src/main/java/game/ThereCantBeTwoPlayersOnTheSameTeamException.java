package game;

public class ThereCantBeTwoPlayersOnTheSameTeamException extends RuntimeException {
    private static final String ERROR_MSG = "Players cant be on the same team.";

    public ThereCantBeTwoPlayersOnTheSameTeamException () {
    }

    @Override
    public String getMessage () {
        return ERROR_MSG;
    }
}
