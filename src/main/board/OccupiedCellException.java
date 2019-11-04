public class OccupiedCellException extends Exception{

    private static final String ERROR_MSG = "Cell is occupied";

    public OccupiedCellException(){

    }

    @Override
    public String getMessage (){

        return ERROR_MSG;
    }
}
