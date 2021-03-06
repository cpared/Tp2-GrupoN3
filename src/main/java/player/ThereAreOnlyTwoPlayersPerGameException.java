
package player;

public class ThereAreOnlyTwoPlayersPerGameException extends RuntimeException {
    private static final String ERROR_MSG = "Game has only two players.";

    public ThereAreOnlyTwoPlayersPerGameException () {
    }

    @Override
    public String getMessage () {
        return ERROR_MSG;
    }
}