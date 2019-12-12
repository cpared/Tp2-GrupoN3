package game;

import Face.GameFace;
import board.Board;
import move.Builder;
import move.Move;
import piece.Piece;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import player.PlayerMustChooseAtLeastOnePieceToStartGameException;
import player.ThereAreOnlyTwoPlayersPerGameException;
import team.NoMembersLeftException;
import team.Team;
import Face.*;


public class InProgress implements GameState {
    private Team team1;
    private Team team2;
    public Board board;

    public InProgress ( Team team1, Team team2 ) {
        this.team1 = team1;
        this.team2 = team2;
        this.board = new Board ( team1, team2 );
    }

    @Override
    public void playerIsReadyToPlay ( Player player ) {
        player.changeFace ();
    }

    @Override
    public Player newPlayer ( String name, Team team ) {
        return new Player ( name, team, board );
    }

    @Override
    public Piece chooseSoldier ( Player player ) throws PlayerHas20PointsOnlyException {
        return player.playerChoosesSoldier ();
    }

    @Override
    public Piece chooseHealer ( Player player ) throws PlayerHas20PointsOnlyException {
        return player.playerChoosesHealer ();
    }

    @Override
    public Piece chooseRider ( Player player ) throws PlayerHas20PointsOnlyException {
        return player.playerChoosesRider ();
    }

    @Override
    public Piece chooseCatapult ( Player player ) throws PlayerHas20PointsOnlyException {
        return player.playerChoosesCatapult ();
    }

    @Override
    public void playerAttacks ( Player player, Move move ) {
        player.attack ( move );
        player.removeDeadPieceFromBoard ( move );
    }

    @Override
    public void playerMovesPieceOnBoard ( Player player, Move move ) {
        player.movePiece ( move );
    }

    @Override
    public void playerPlacesPieceOnBoard ( Player player, Piece piece, Move move ) {
        player.placePieceOnBoard ( piece, move );
    }

    @Override
    public Piece removePieceFromBoard ( Player player, Move move ) {
        try {
            return player.removeDeadPieceFromBoard ( move );
        } catch (NoMembersLeftException e) {
            throw new GameHasEndedException ();
        }
    }

    @Override
    public void playerChoosesBattalion ( Player player, Move move ) {
        player.chooseBattalion ( move );
    }

    // This getter is only for testing, it doesnt belong in the model.
    @Override
    public Board getBoard () {
        return this.board;
    }

    // This getter is only for views,
    @Override
    public int getPoints ( Player player ) {
        return player.getPoints ();
    }

    @Override
    public Piece getPieceOnCell ( int row, int column ) {
        Move move = new Builder ().fromRow ( row ).fromColumn ( column ).build ();
        return this.board.getPiece ( move );
    }

    @Override
    public void penalizePieces () {
        board.penalizePieces ();
    }

}
