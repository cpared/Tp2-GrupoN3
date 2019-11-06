package player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    //Initiation tests.
    @Test
    public void test01NewPlayerHas20Points () {
        Player player = new Player ("Mike");
        assertEquals ( 20, player.obtainPoints () );
    }

    @Test
    public void test02NewPlayerWithNamePepeIsNamedPepe () {
        Player player = new Player ( "Pepe" );
        assertEquals ( "Pepe", player.obtainName () );
    }

    //Point tests.
    @Test
    public void test03Subtracting8PointsFromPlayerLeavesPlayerWith12Points () {
        Player player = new Player ("Lola");
        try {
            player.subtractPoints ( 8 );
        } catch (PlayerHas20PointsOnlyException e) {
            e.getMessage ();
        }
        assertEquals ( 12, player.obtainPoints () );
    }

    @Test
    public void test04Subtracting20PointsFromPlayerLeavesPlayerWith0Points () {
        Player player = new Player ("Archie");
        try {
            player.subtractPoints ( 2 );
            player.subtractPoints ( 18 );
        } catch (PlayerHas20PointsOnlyException e) {
            e.getMessage ();
        }
        assertEquals ( 0, player.obtainPoints () );
    }

    @Test
    public void test05Subtracting30PointsFromPlayerRaisesError () {
        Player player = new Player ("Player0003");

        try {
            player.subtractPoints ( 30 );

        } catch (PlayerHas20PointsOnlyException e) {
            e.printStackTrace ();
            //e.getMessage ();
        }
        assertEquals (20, player.obtainPoints ());
    }

    //Pieces tests.


    //Board tests.
}