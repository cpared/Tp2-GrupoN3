package game;


import board.Board;
import piece.Piece;
import player.Player;

public interface GameState {
    Player winner = null;

    public Player newPlayer ( String name ) ;

    Piece chooseSoldier (Player player);

    Piece chooseHealer (Player player );

    Piece chooseRider (Player player );

    Piece chooseCatapult ( Player player);

    void playerAttacks ( Player player, int row, int column );

    void playerMovesPieceOnBoard ( Player player, int firstRow, int firstColumn, int secondRow, int secondColumn );

    void playerPlacesPieceOnBoard ( Player player, Piece piece, int row, int column );

    public Piece removePieceFromBoard ( Player player, int row, int column ) ;

    void playerIsReadyToPlay(Player player);

    // This getter is only for testing, it doesnt belong in the model.
    public Board getBoard ();
}

