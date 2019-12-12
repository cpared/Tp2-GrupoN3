package game;

import org.junit.jupiter.api.Test;
import piece.Piece;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import team.Team;

import static org.junit.jupiter.api.Assertions.*;

class InProgressTest {
    Game game = new Game ();
    private Team team = new Team(1,game);
    private Team team2 = new Team(1,game);

    @Test
    void test00AnStateCanBeCreated () {
        //Act
        GameState state = new InProgress (team, team2);
        //Assert
        assertNotNull ( state );
    }

    @Test
    void test01ANewPlayerCanBeCreatedInProgressState () {
        //Assemble
        GameState state = new InProgress (team, team2);
        //Act
        Player player = state.newPlayer ( "Al",team );
        //Assert
        assertNotNull ( player );
    }

    @Test
    void test02TwoPlayersCanBeCreatedInProgressState () {
        //Assemble
        GameState state = new InProgress (team, team2);
        //Act
        Player player = state.newPlayer ( "Al" ,team);
        Player player2 = state.newPlayer ( "Pete", new Team(2,game) );

        assertFalse ( player.equals ( player2 ) );

    }

    @Test
    void test03ASoldierCanBeCreatedInProgressState () throws PlayerHas20PointsOnlyException {
        //Assemble
        GameState state = new InProgress (team, team2);
        Player player = state.newPlayer ( "Al" ,team);
        //Act
        Piece piece = state.chooseSoldier ( player );
        //Assert
        assertTrue ( piece.isCost ( 1 ) );
    }

    @Test
    void test04ARiderCanBeCreatedInProgressState () throws PlayerHas20PointsOnlyException {
        //Assemble
        GameState state = new InProgress (team, team2);
        Player player = state.newPlayer ( "Al",team);
        //Act
        Piece piece = state.chooseRider ( player );
        //Assert
        assertTrue ( piece.isCost ( 3 ) );
    }

    @Test
    void test05AHealerCanBeCreatedInProgressState () throws PlayerHas20PointsOnlyException {
        //Assemble
        GameState state = new InProgress (team, team2);
        Player player = state.newPlayer ( "Al" ,team);
        //Act
        Piece piece = state.chooseHealer ( player );
        //Assert
        assertTrue ( piece.isCost ( 2 ) );
    }

    @Test
    void test06ACatapultCanBeCreatedInProgressState () throws PlayerHas20PointsOnlyException {
        //Assemble
        GameState state = new InProgress (team, team2);
        Player player = state.newPlayer ( "Al",team );
        //Act
        Piece piece = state.chooseCatapult ( player );
        //Assert
        assertTrue ( piece.isCost ( 5 ) );
    }


}