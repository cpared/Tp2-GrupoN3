import java.lang.Exception;

public class CanNotMakeThatMoveException extends Exception {

    private static final String ERROR_MSG = "Piece cannot move in that direction";

    public CanNotMakeThatMoveException (){

    }

    @Override
    public String getMessage (){

        return ERROR_MSG;
    }
}
