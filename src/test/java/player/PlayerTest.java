package player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

import team.*;

class PlayerTest {
    //Assemble
    private Team team = new Blue ();

    //Initiation tests.

    @Test
    void test01NewPlayerHas20Points () {
        //Act
        Player player = new Player ( "Mike", team );
        //Assert
        assertEquals ( 20, player.obtainPoints () );
    }

    @Test
    void test02NewPlayerWithNamePepeIsNamedPepe () {
        //Act
        Player player = new Player ( "Pepe", team );
        //Assert
        assertEquals ( "Pepe", player.obtainName () );
    }

    //Point tests.
    @Test
    void test03Subtracting8PointsFromPlayerLeavesPlayerWith12Points () {
        //Act
        Player player = new Player ( "Lola", team );
        try {
            player.subtractPoints ( 8 );
        } catch (PlayerHas20PointsOnlyException e) {
            e.getMessage ();
        }
        //Assert
        assertEquals ( 12, player.obtainPoints () );
    }

    @Test
    void test04Subtracting20PointsFromPlayerLeavesPlayerWith0Points () {
        //Assemble
        Player player = new Player ( "Archie", team );
        //Act
        try {
            player.subtractPoints ( 2 );
            player.subtractPoints ( 18 );
        } catch (PlayerHas20PointsOnlyException e) {
            e.getMessage ();
        }
        //Assert
        assertEquals ( 0, player.obtainPoints () );
    }

    @Test
    void test05Subtracting30PointsFromPlayerRaisesError () {
        //Assemble
        Player player = new Player ( "Player0003", team );
        //Act
        try {
            player.subtractPoints ( 30 );
            fail ();
        }
        //Assert
        catch ( PlayerHas20PointsOnlyException e ) {
            assertEquals ( 20, player.obtainPoints () );
        }
    }

    //Pieces tests.


    //Board tests.
}