package game;

public class GameHasEndedException extends Exception {

    private static final String ERROR_MSG = "Game has ended.";

    public GameHasEndedException () {
    }

    @Override
    public String getMessage () {
        return ERROR_MSG;
    }

}
