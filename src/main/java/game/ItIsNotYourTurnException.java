package game;

public class ItIsNotYourTurnException extends RuntimeException {
    private static final String ERROR_MSG = "It is not your turn.";

    public  ItIsNotYourTurnException (){

    }

    @Override
    public String getMessage () {
        return ERROR_MSG;
    }
}
