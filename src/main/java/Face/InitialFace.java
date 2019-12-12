package Face;

import board.Board;
import move.Move;
import piece.Piece;
import piece.PieceFactory;
import player.APlayerAlreadyExistsException;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import team.PieceDoesNotBelongToTeamException;
import team.Team;

public class InitialFace implements Face {
    private Player player;
    private Board board;
    private PieceFactory factory;
    private Team team;

    public InitialFace ( Board board, Team team ) {
        this.board = board;
        this.team = team;
        this.factory = new PieceFactory ( this.team );
    }


    @Override
    public void playerPlacesPieceOnBoard ( Piece piece, Move move ) {
        board.placePiece ( piece, move );
        if (factory.getPoints ()==0) team.playerIsReadyToPlay ();
    }

    @Override
    public Piece removeDeadPieceFromBoard ( Move move ) throws PieceDoesNotBelongToTeamException {
        Piece removed = board.removePiece ( move );
        this.factory.eliminatePiece ( removed );
        return removed;
    }

    @Override
    public Piece playerChoosesSoldier () throws PlayerHas20PointsOnlyException {
        return this.factory.createSoldier ();
    }

    @Override
    public Piece playerChoosesHealer () throws PlayerHas20PointsOnlyException {
        return this.factory.createHealer ();
    }

    @Override
    public Piece playerChoosesRider () throws PlayerHas20PointsOnlyException {
        return this.factory.createRider ();
    }

    @Override
    public Piece playerChoosesCatapult () throws PlayerHas20PointsOnlyException {
        return this.factory.createCatapult ();
    }

    @Override
    public void playerMovesPieceOnBoard ( Move move ) {
        board.move ( move, team );
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

    @Override
    public int getPoints () {
        return this.factory.getPoints ();
    }

    @Override
    public Piece getPiece ( Move move4 ) {
        return board.getPiece ( move4 );
    }
}
