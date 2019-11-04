import java.lang.Exception;

public class CanNotMakeThatMoveException extends Exception {

    static final String ERROR_MSG = "Piece cannot move in that direction";

    public CanNotMakeThatMoveException (){}

    public String showMessage (){

        return ERROR_MSG;
    }
}
