package Face;

import board.Board;
import move.Move;
import piece.Piece;
import player.Player;
import team.Team;

public interface Face {
    Player player1 = null;
    Player player2 = null;
    Board board = null;

    Player newPlayer ( String name, Team team );

    void playerMovesPieceOnBoard ( Move move );

    void playerPlacesPieceOnBoard ( Piece piece, Move move );

    Piece removePieceFromBoard ( Move move );

    void playerAttacks ( Move move );

    Piece playerChoosesSoldier ();

    Piece playerChoosesHealer ();

    Piece playerChoosesRider ();

    Piece playerChoosesCatapult ();

    void playerChoosesBattalion(Move move);

}
