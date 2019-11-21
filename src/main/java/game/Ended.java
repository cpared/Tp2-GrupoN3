package game;

import board.Board;
import move.Move;
import piece.NullPiece;
import piece.Piece;
import player.Player;
import player.ThereAreOnlyTwoPlayersPerGameException;

public class Ended implements GameState {

    @Override
    public Player newPlayer ( String name ) {
       throw new ThereAreOnlyTwoPlayersPerGameException ();
    }

    @Override
    public Piece chooseSoldier ( Player player ) {
        throw new GameHasEndedException ();
    }

    @Override
    public Piece chooseHealer ( Player player ) {
        throw new GameHasEndedException ();
    }

    @Override
    public Piece chooseRider ( Player player ) {
        throw new GameHasEndedException ();
    }

    @Override
    public Piece chooseCatapult ( Player player ) {
        throw new GameHasEndedException ();
    }

    @Override
    public void playerAttacks ( Player player, Move move ) {
        throw new GameHasEndedException ();
    }

    @Override
    public void playerMovesPieceOnBoard ( Player player, Move move ) {
        throw new GameHasEndedException ();
    }

    @Override
    public void playerPlacesPieceOnBoard ( Player player, Piece piece, Move move ) {
        throw new GameHasEndedException ();
    }

    @Override
    public Piece removePieceFromBoard ( Player player, Move move ) {
        throw new GameHasEndedException ();
    }

    @Override
    public void playerIsReadyToPlay ( Player player ) {
        throw new GameHasEndedException ();
    }

    @Override
    public void playerChoosesBattalion(Player player, Move move){
        throw new GameHasEndedException ();
    }

    // This getter is only for testing, it doesnt belong in the model.
    @Override
    public Board getBoard () {
        throw new GameHasEndedException ();
    }

}
