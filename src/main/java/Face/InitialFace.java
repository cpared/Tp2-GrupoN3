package Face;

import board.Board;
import move.Move;
import piece.Piece;
import player.APlayerAlreadyExistsException;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import team.Team;

public class InitialFace implements Face {
    private Player player;
    private Board board;

    public InitialFace ( Board board ) {
        this.board = board;
    }

    @Override
    public Player newPlayer ( String name, Team team ) {
        if (this.player != null) throw new APlayerAlreadyExistsException ();
        this.player = new Player ( name, team );
        return this.player;
    }

    @Override
    public void playerPlacesPieceOnBoard ( Piece piece, Move move ) {
        player.placePieceOnBoard ( piece, this.board, move );
    }

    @Override
    public Piece removePieceFromBoard ( Move move ) {
        Piece removed = board.removePiece ( move );
        return removed; // Should return the points to the player.
    }

    @Override
    public Piece playerChoosesSoldier () throws PlayerHas20PointsOnlyException {
        return this.player.chooseSoldier ();
    }

    @Override
    public Piece playerChoosesHealer () throws PlayerHas20PointsOnlyException {
        return this.player.chooseHealer ();
    }

    @Override
    public Piece playerChoosesRider () throws PlayerHas20PointsOnlyException {
        return this.player.chooseRider ();
    }

    @Override
    public Piece playerChoosesCatapult () throws PlayerHas20PointsOnlyException {
        return this.player.chooseCatapult ();
    }

    @Override
    public void playerMovesPieceOnBoard ( Move move ) {
        Piece piece = this.board.removePiece ( move );
        player.placePieceOnBoard ( piece, this.board, move );
    }

    //Methods that this class does not implement

    @Override
    public void playerAttacks ( Move move ) {

    }

    @Override
    public void playerChoosesBattalion ( Move move ) {
    }

    // This getter is only for testing, they dont belong in the model.
    @Override
    public Player getPlayer () {
        return this.player;
    }
}
