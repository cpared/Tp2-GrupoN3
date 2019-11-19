package game;

import board.Board;
import move.Move;
import piece.NullPiece;
import piece.Piece;
import player.Player;

public class Ended implements GameState {

    @Override
    public Player newPlayer ( String name ) {
        return null;
    }

    @Override
    public Piece chooseSoldier ( Player player ) {
        return new NullPiece ();
    }

    @Override
    public Piece chooseHealer ( Player player ) {
        return new NullPiece ();
    }

    @Override
    public Piece chooseRider ( Player player ) {
        return new NullPiece ();
    }

    @Override
    public Piece chooseCatapult ( Player player ) {
        return new NullPiece ();
    }

    @Override
    public void playerAttacks ( Player player, Move move ) {
    }

    @Override
    public void playerMovesPieceOnBoard ( Player player, Move move ) {
    }

    @Override
    public void playerPlacesPieceOnBoard ( Player player, Piece piece, Move move ) {
    }

    @Override
    public Piece removePieceFromBoard ( Player player, Move move ) {
        return null;
    }

    @Override
    public void playerIsReadyToPlay ( Player player ) {
    }

    @Override
    public void playerChoosesBattalion(Player player, Move move){

    }

    // This getter is only for testing, it doesnt belong in the model.
    @Override
    public Board getBoard () {
        return null;
    }

}
