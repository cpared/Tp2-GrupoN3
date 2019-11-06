
package Face;

public class ThereAreOnlyTwoPlayersPerGameException extends Exception {
    private static final String ERROR_MSG = "Game has only two players.";

    public ThereAreOnlyTwoPlayersPerGameException () {
    }

    @Override
    public String getMessage () {
        return ERROR_MSG;
    }
}