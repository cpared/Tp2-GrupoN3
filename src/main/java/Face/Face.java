package Face;

import board.Board;
import move.Move;
import piece.Piece;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import team.Team;

public interface Face {
    Player player = null;
    Board board = null;

    Player newPlayer ( String name, Team team );

    void playerMovesPieceOnBoard ( Move move );

    void playerPlacesPieceOnBoard ( Piece piece, Move move );

    Piece removePieceFromBoard ( Move move );

    void playerAttacks ( Move move );

    Piece playerChoosesSoldier () throws PlayerHas20PointsOnlyException;

    Piece playerChoosesHealer () throws PlayerHas20PointsOnlyException;

    Piece playerChoosesRider () throws PlayerHas20PointsOnlyException;

    Piece playerChoosesCatapult () throws PlayerHas20PointsOnlyException;

    void playerChoosesBattalion(Move move);

    // This getter is only for testing, they dont belong in the model.
    public Player getPlayer ();

}
