package board;

import java.lang.Exception;

public class EnemyCellException extends RuntimeException{

    private static final String ERROR_MSG = "Cannot place an ally piece in enemy area";

    public EnemyCellException (){

    }

    @Override
    public String getMessage (){

        return ERROR_MSG;
    }
}