package player;

public class PlayerMustChooseAtLeastOnePieceToStartGameException extends RuntimeException{
    private static final String ERROR_MSG = "Player has to choose at least one piece to be able to play the game.";

    public PlayerMustChooseAtLeastOnePieceToStartGameException () {
    }

    @Override
    public String getMessage () {
        return ERROR_MSG;
    }
}
