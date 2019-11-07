package game;

import Face.ThereAreOnlyTwoPlayersPerGameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.*;

class GameTest {

    // Tests related to the players.
    @Test
     void test01NewGameWithPlayerMikeHasMikeAsAPlayer () throws ThereAreOnlyTwoPlayersPerGameException {
        Game game = new Game ();
        try {
            game.newPlayer ( "Mike" );
        } catch (ThereAreOnlyTwoPlayersPerGameException e) {
            e.printStackTrace ();
        } catch (ThereCantBeTwoPlayersOnTheSameTeamException e) {
            e.printStackTrace ();
        }
        Assertions.assertEquals ( "Mike", game.getPlayer1 ().getName () );
    }

    @Test
     void test02NewGameWithPlayerMikeAndWithPlayerRickHasBothPlayers () throws ThereAreOnlyTwoPlayersPerGameException {
        Game game = new Game ();
        try {
            game.newPlayer ( "Mike" );
            game.newPlayer ( "Rick" );
        } catch (ThereAreOnlyTwoPlayersPerGameException e) {
            e.printStackTrace ();

        } catch (ThereCantBeTwoPlayersOnTheSameTeamException e) {
            e.printStackTrace ();
        }
        Assertions.assertEquals ( "Mike", game.getPlayer1 ().getName () );
        Assertions.assertEquals ( "Rick", game.getPlayer2 ().getName () );
    }

    @Test
     void test03GameCanOnlyHaveTwoPlayers () throws ThereAreOnlyTwoPlayersPerGameException {
        Game game = new Game ();
        try {
            game.newPlayer ( "Rose" );
            game.newPlayer ( "Patty" );
            game.newPlayer ( "Alfred" );

        } catch (ThereAreOnlyTwoPlayersPerGameException e) {
            e.printStackTrace ();
        } catch (ThereCantBeTwoPlayersOnTheSameTeamException e) {
            e.printStackTrace ();
        }
        Assertions.assertEquals ( "Rose", game.getPlayer1 ().getName () );
        Assertions.assertEquals ( "Patty", game.getPlayer2 ().getName () );
    }

    // Tests related to teams.
    @Test
     void test04GameAssignsTwoDifferentTeams () throws ThereAreOnlyTwoPlayersPerGameException {
        Game game = new Game ();
        try {
            game.newPlayer ( "Rose" );
            game.newPlayer ( "Patty" );

        } catch (ThereAreOnlyTwoPlayersPerGameException e) {
            e.printStackTrace ();
        } catch (ThereCantBeTwoPlayersOnTheSameTeamException e) {
            e.printStackTrace ();
        }
        Assertions.assertEquals ( Gold.class, game.getPlayer1 ().getTeam ().getClass () );
        Assertions.assertEquals ( Blue.class, game.getPlayer2 ().getTeam ().getClass () );
    }
}