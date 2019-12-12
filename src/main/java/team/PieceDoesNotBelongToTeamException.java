package team;

public class PieceDoesNotBelongToTeamException extends RuntimeException {
    private static final String ERROR_MSG = "Piece does not belong on this team.";

    public PieceDoesNotBelongToTeamException () {

    }
    @Override
    public String getMessage () {
        return ERROR_MSG;
    }
}
