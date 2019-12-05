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

    public InitialFace ( Board board , Team team ) {
        this.board = board;
        this.team = team;
        this.factory = new PieceFactory ( this.team );
    }

    @Override
    public Player newPlayer ( String name ) {
        if (this.player != null) throw new APlayerAlreadyExistsException ();
        this.player = new Player ( name, team );
        return this.player;
    }

    @Override
    public void playerPlacesPieceOnBoard ( Piece piece, Move move ) {
        player.placePieceOnBoard ( piece, this.board, move );
    }

    @Override
    public Piece removeDeadPieceFromBoard(Move move ) throws PieceDoesNotBelongToTeamException {
        Piece removed = board.removePiece ( move );
        this.factory.eliminatePiece ( removed );
        this.player.removePieceFromTeam ( removed );
        return removed;
    }

    @Override
    public Piece playerChoosesSoldier () throws PlayerHas20PointsOnlyException {
        Piece piece = this.factory.createSoldier ();
        this.player.chosePiece ( piece );
        return piece;
    }

    @Override
    public Piece playerChoosesHealer () throws PlayerHas20PointsOnlyException {
        Piece piece = this.factory.createHealer ();
        this.player.chosePiece ( piece );
        return piece;
    }

    @Override
    public Piece playerChoosesRider () throws PlayerHas20PointsOnlyException {
        Piece piece = this.factory.createRider ();
        this.player.chosePiece ( piece );
        return piece;
    }

    @Override
    public Piece playerChoosesCatapult () throws PlayerHas20PointsOnlyException {
        Piece piece = this.factory.createCatapult ();
        this.player.chosePiece ( piece );
        return piece;
    }

    @Override
    public void playerMovesPieceOnBoard ( Move move ) {
        player.movePiece(board,move);
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
        return this.factory.getPoints();
    }

    @Override
    public Piece getPiece(Move move4) {
        return board.getPiece(move4);
    }
}
