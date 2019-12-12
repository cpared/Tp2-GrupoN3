package game;


import board.Board;
import move.Move;
import piece.Piece;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import team.Team;

public interface GameState {

    public Player newPlayer ( String name , Team team);

    Piece chooseSoldier ( Player player ) throws PlayerHas20PointsOnlyException;

    Piece chooseHealer ( Player player ) throws PlayerHas20PointsOnlyException;

    Piece chooseRider ( Player player ) throws PlayerHas20PointsOnlyException;

    Piece chooseCatapult ( Player player ) throws PlayerHas20PointsOnlyException;

    void playerAttacks ( Player player, Move move );

    void playerMovesPieceOnBoard ( Player player, Move move );

    void playerPlacesPieceOnBoard ( Player player, Piece piece, Move move );

    public Piece removePieceFromBoard ( Player player, Move move );

    void playerIsReadyToPlay ( Player player );

    void playerChoosesBattalion ( Player player, Move move );

    // This getter is only for testing, it doesnt belong in the model.
    public Board getBoard ();

    // This getter is only for views,
    public int getPoints ( Player player );

    public Piece getPieceOnCell ( int row, int column );

    void penalizePieces ();
}

