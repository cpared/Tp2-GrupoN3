package game;

import Face.ThereAreOnlyTwoPlayersPerGameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {

    // Tests related to the players.
    @Test
    public void test01NewGameWithPlayerMikeHasMikeAsAPlayer () throws ThereAreOnlyTwoPlayersPerGameException {
        Game game = new Game ();
        try {
            game.newPlayer ( "Mike" );
        } catch (ThereAreOnlyTwoPlayersPerGameException e) {
            e.printStackTrace ();
        }
        Assertions.assertEquals ( "Mike", game.getPlayer1 ().getName () );
    }

    @Test
    public void test02NewGameWithPlayerMikeAndWithPlayerRickHasBothPlayers () throws ThereAreOnlyTwoPlayersPerGameException {
        Game game = new Game ();
        try {
            game.newPlayer ( "Mike" );
            game.newPlayer ( "Rick" );
        } catch (ThereAreOnlyTwoPlayersPerGameException e) {
            e.printStackTrace ();
        }
        Assertions.assertEquals ( "Mike", game.getPlayer1 ().getName () );
        Assertions.assertEquals ( "Rick", game.getPlayer2 ().getName () );
    }

    @Test
    public void test03GameCanOnlyHaveTwoPlayers () throws ThereAreOnlyTwoPlayersPerGameException {
        Game game = new Game ();
        try {
            game.newPlayer ( "Rose" );
            game.newPlayer ( "Patty" );
            game.newPlayer ( "Alfred" );

        } catch (ThereAreOnlyTwoPlayersPerGameException e) {
            e.printStackTrace ();
        }
        Assertions.assertEquals ( "Rose", game.getPlayer1 ().getName () );
        Assertions.assertEquals ( "Patty", game.getPlayer2 ().getName () );
    }
}