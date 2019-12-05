package game;

import org.junit.jupiter.api.Test;
import piece.Piece;
import player.Player;
import player.PlayerHas20PointsOnlyException;

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


}