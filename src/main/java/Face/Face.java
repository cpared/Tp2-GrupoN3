package Face;
import board.Board;
import piece.Piece;
import player.Player;
import team.Team;

public interface Face {
    Player player1 = null;
    Player player2 = null;
    Board board = null;

    Player newPlayer (String name, Team team);

    void playerMovesPieceOnBoard ( int firstRow, int firstColumn, int secondRow, int secondColumn );

    void playerPlacesPieceOnBoard ( Piece piece, int row, int column );

    Piece removePieceFromBoard ( int row, int column );

    void playerAttacks ( int row, int column );

    Piece playerChoosesSoldier ( );

    Piece playerChoosesHealer ( );

    Piece playerChoosesRider ( );

    Piece playerChoosesCatapult ( );

}
