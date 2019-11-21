package game;

import Face.Face;
import board.Board;
import move.Builder;
import move.Move;
import org.junit.jupiter.api.Test;
import piece.Piece;
import player.APlayerAlreadyExistsException;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import team.Team;

import static org.junit.jupiter.api.Assertions.*;

class InProgressTest {

    @Test
    void test00AnStateCanBeCreated () {
        //Act
        GameState state = new InProgress ();
        //Assert
        assertNotNull ( state );
    }

    @Test
    void test01ANewPlayerCanBeCreatedInProgressState () {
        //Assemble
        GameState state = new InProgress ();
        //Act
        Player player = state.newPlayer ( "Al" );
        //Assert
        assertNotNull ( player );
    }

    @Test
    void test02TwoPlayersCanBeCreatedInProgressState () {
        //Assemble
        GameState state = new InProgress ();
        //Act
        Player player = state.newPlayer ( "Al" );
        Player player2 = state.newPlayer ( "Pete" );

        assertFalse ( player.equals ( player2 ) );

    }

    @Test
    void test03ASoldierCanBeCreatedInProgressState () throws PlayerHas20PointsOnlyException {
        //Assemble
        GameState state = new InProgress ();
        Player player = state.newPlayer ( "Al" );
        //Act
        Piece piece = state.chooseSoldier ( player );
        //Assert
        assertTrue ( piece.isCost ( 1 ) );
    }

    @Test
    void test04ARiderCanBeCreatedInProgressState () throws PlayerHas20PointsOnlyException {
        //Assemble
        GameState state = new InProgress ();
        Player player = state.newPlayer ( "Al" );
        //Act
        Piece piece = state.chooseRider ( player );
        //Assert
        assertTrue ( piece.isCost ( 3 ) );
    }

    @Test
    void test05AHealerCanBeCreatedInProgressState () throws PlayerHas20PointsOnlyException {
        //Assemble
        GameState state = new InProgress ();
        Player player = state.newPlayer ( "Al" );
        //Act
        Piece piece = state.chooseHealer ( player );
        //Assert
        assertTrue ( piece.isCost ( 2 ) );
    }

    @Test
    void test06ACatapultCanBeCreatedInProgressState () throws PlayerHas20PointsOnlyException {
        //Assemble
        GameState state = new InProgress ();
        Player player = state.newPlayer ( "Al" );
        //Act
        Piece piece = state.chooseCatapult ( player );
        //Assert
        assertTrue ( piece.isCost ( 5 ) );
    }

    @Test
    void test07APieceCanBePlacedOnTheBoardInProgressState () throws PlayerHas20PointsOnlyException {
        //Assemble
        GameState state = new InProgress ();
        Player player = state.newPlayer ( "Al" );
        Piece piece = state.chooseCatapult ( player );
        //Act
        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        state.playerPlacesPieceOnBoard ( player, piece, move1 );
        //Assert
        Move move = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).build ();
        assertEquals ( piece, state.removePieceFromBoard ( player, move ) );
    }

    @Test
    void test08ABattalionCanBeCreatedInProgressState () throws PlayerHas20PointsOnlyException {
        //Assemble
        GameState state = new InProgress ();
        Player player = state.newPlayer ( "Al" );
        Piece soldier1 = state.chooseSoldier ( player );
        Piece soldier2 = state.chooseSoldier ( player );
        Piece soldier3 = state.chooseSoldier ( player );
        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        Move move2 = new Builder ().ToRow ( 2 ).ToColumn ( 2 ).build ();
        Move move3 = new Builder ().ToRow ( 2 ).ToColumn ( 3 ).build ();
        state.playerPlacesPieceOnBoard ( player, soldier1, move1 );
        state.playerPlacesPieceOnBoard ( player, soldier2, move2 );
        state.playerPlacesPieceOnBoard ( player, soldier3, move3 );
        state.playerIsReadyToPlay ( player );

        //Act
        Move move = new Builder ().fromRow ( 2 ).fromColumn ( 2 ).build ();
        state.playerChoosesBattalion ( player, move );
        Move movement = new Builder ().fromRow ( 2 ).fromColumn ( 2 ).ToRow ( 3 ).ToColumn ( 2 ).build ();
        state.playerMovesPieceOnBoard ( player, movement );
        //Assert
        Move move4 = new Builder ().fromRow ( 3 ).fromColumn ( 2 ).build ();
        Move move5 = new Builder ().fromRow ( 3 ).fromColumn ( 1 ).build ();
        Move move6 = new Builder ().fromRow ( 3 ).fromColumn ( 3 ).build ();
        assertEquals ( soldier2, state.removePieceFromBoard ( player, move4 ) );
        assertEquals ( soldier1, state.removePieceFromBoard ( player, move5 ) );
        assertEquals ( soldier3, state.removePieceFromBoard ( player, move6 ) );
    }

}