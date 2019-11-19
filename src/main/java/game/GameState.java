package game;


import board.Board;
import move.Move;
import piece.Piece;
import player.Player;

public interface GameState {
    Player winner = null;

    public Player newPlayer ( String name );

    Piece chooseSoldier ( Player player );

    Piece chooseHealer ( Player player );

    Piece chooseRider ( Player player );

    Piece chooseCatapult ( Player player );

    void playerAttacks ( Player player, Move move );

    void playerMovesPieceOnBoard ( Player player, Move move );

    void playerPlacesPieceOnBoard ( Player player, Piece piece, Move move );

    public Piece removePieceFromBoard ( Player player, Move move );

    void playerIsReadyToPlay ( Player player );

    void playerChoosesBattalion (Player player, Move move);

    // This getter is only for testing, it doesnt belong in the model.
    public Board getBoard ();
}

