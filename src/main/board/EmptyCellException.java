public class EmptyCellException extends Exception {

    private static final String ERROR_MSG = "Cell is empty";

    public EmptyCellException (){

    }

    @Override
    public String getMessage (){

        return ERROR_MSG;
    }
}
