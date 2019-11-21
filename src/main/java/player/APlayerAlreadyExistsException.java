package player;

public class APlayerAlreadyExistsException extends RuntimeException {
    private static final String ERROR_MSG = "A player already exists in the game. You cant create a new player at this point.";

    public APlayerAlreadyExistsException () {

    }

    @Override
    public String getMessage () {
        return ERROR_MSG;
    }
}
