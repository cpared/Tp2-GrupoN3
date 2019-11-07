package board;

public class OccupiedCellException extends RuntimeException {

    private static final String ERROR_MSG = "Cell is occupied";

    public OccupiedCellException () {

    }

    @Override
    public String getMessage () {

        return ERROR_MSG;
    }
}
