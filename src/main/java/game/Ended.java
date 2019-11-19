package game;

import board.Board;
import piece.NullPiece;
import piece.Piece;
import player.Player;

public class Ended implements GameState {

    @Override
    public Player newPlayer ( String name ) {
        return null;
    }

    @Override
    public Piece chooseSoldier ( Player player){
        return new NullPiece ();
    }

    @Override
    public Piece chooseHealer (Player player){
        return new NullPiece ();
    }

    @Override
    public Piece chooseRider (Player player ){
        return new NullPiece ();
    }

    @Override
    public Piece chooseCatapult (Player player ){
        return new NullPiece ();
    }

    @Override
    public void playerAttacks ( Player player, int row, int column ) {
    }

    @Override
    public void playerMovesPieceOnBoard ( Player player, int firstRow, int firstColumn, int secondRow, int secondColumn )  {
    }

    @Override
    public void playerPlacesPieceOnBoard ( Player player, Piece piece, int row, int column ) {
    }

    @Override
    public Piece removePieceFromBoard ( Player player, int row, int column ) {
        return null;
    }

    @Override
    public void playerIsReadyToPlay(Player player) {
    }

    // This getter is only for testing, it doesnt belong in the model.
    @Override
    public Board getBoard () {
        return null;
    }

}
