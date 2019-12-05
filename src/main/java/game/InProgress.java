package game;

import Face.GameFace;
import board.Board;
import move.Move;
import piece.Piece;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import player.PlayerMustChooseAtLeastOnePieceToStartGameException;
import player.ThereAreOnlyTwoPlayersPerGameException;
import team.Team;
import Face.*;


public class InProgress implements GameState {
    private Player player1;
    private Player player2;
    private Face player1Face;
    private Face player2Face;
    private Team team1 = new Team ( 1 );
    private Team team2 = new Team ( 2 );
    public Board board = new Board ( team1, team2 );

    public InProgress () {
        this.player1Face = new InitialFace ( board, this.team1 );
        this.player2Face = new InitialFace ( board, this.team2 );
    }

    private void changeFace ( Player player ) {
        if (player.isNumberOfPiecesOnTeam ( 0 )) throw new PlayerMustChooseAtLeastOnePieceToStartGameException ();

        if (this.player1.equals ( player )) this.player1Face = new GameFace ( this.board, player1 );
        else this.player2Face = new GameFace ( this.board, player2 );

    }

    @Override
    public void playerIsReadyToPlay ( Player player ) {
        changeFace ( player );
    }

    @Override
    public Player newPlayer ( String name ) {

        if (this.player1 == null) {
            this.player1 = this.player1Face.newPlayer ( name );
            return this.player1;
        } else if (this.player2 == null) {
            this.player2 = this.player2Face.newPlayer ( name );
            return this.player2;
        } else throw new ThereAreOnlyTwoPlayersPerGameException ();
    }

    @Override
    public Piece chooseSoldier ( Player player ) throws PlayerHas20PointsOnlyException {
        if (this.player1 == player) return player1Face.playerChoosesSoldier ();
        return player2Face.playerChoosesSoldier ();
    }

    @Override
    public Piece chooseHealer ( Player player ) throws PlayerHas20PointsOnlyException {
        if (this.player1 == player) return player1Face.playerChoosesHealer ();
        return player2Face.playerChoosesHealer ();
    }

    @Override
    public Piece chooseRider ( Player player ) throws PlayerHas20PointsOnlyException {
        if (this.player1 == player) return player1Face.playerChoosesRider ();
        return player2Face.playerChoosesRider ();
    }

    @Override
    public Piece chooseCatapult ( Player player ) throws PlayerHas20PointsOnlyException {
        if (this.player1 == player) return player1Face.playerChoosesCatapult ();
        return player2Face.playerChoosesCatapult ();
    }

    @Override
    public void playerAttacks ( Player player, Move move ) {
        if (player == player1){
            player1Face.playerAttacks ( move );
            player1Face.removePieceFromBoard(move);
        }
        else{
            player2Face.playerAttacks ( move );
            player2Face.removePieceFromBoard(move);
        }
    }

    @Override
    public void playerMovesPieceOnBoard ( Player player, Move move ) {
        if (player == player1) player1Face.playerMovesPieceOnBoard ( move );
        else player2Face.playerMovesPieceOnBoard ( move );

    }

    @Override
    public void playerPlacesPieceOnBoard ( Player player, Piece piece, Move move ) {
        if (player == player1) player1Face.playerPlacesPieceOnBoard ( piece, move );
        else player2Face.playerPlacesPieceOnBoard ( piece, move );
    }

    @Override
    public Piece removePieceFromBoard ( Player player, Move move ) {
        if (player == player1) return player1Face.removePieceFromBoard ( move );
        return player2Face.removePieceFromBoard ( move );
    }

    @Override
    public void playerChoosesBattalion ( Player player, Move move ) {
        if (player == player1) player1Face.playerChoosesBattalion ( move );
        else player2Face.playerChoosesBattalion ( move );
    }

    // This getter is only for testing, it doesnt belong in the model.
    @Override
    public Board getBoard () {
        return this.board;
    }

    // This getter is only for views,
    @Override
    public int getPoints (Player player){
        if (player == player1) return player1Face.getPoints ();
        return player2Face.getPoints ();
    }

    // This getter is only for vi

}
