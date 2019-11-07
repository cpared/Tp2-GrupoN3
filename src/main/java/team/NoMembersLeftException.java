package team;

public class NoMembersLeftException extends Exception{
    private static final String ERROR_MSG = "Team has no members left to remove.";

    public NoMembersLeftException () {

    }
    @Override
    public String getMessage () {
        return ERROR_MSG;
    }
}
