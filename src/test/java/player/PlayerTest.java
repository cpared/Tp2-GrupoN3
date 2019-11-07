package player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import team.*;

/*class PlayerTest {
    //Initiation tests.
    private Team team= new Blue();
    @Test
     void test01NewPlayerHas20Points () {
        Player player = new Player ("Mike", team);
        assertEquals ( 20, player.obtainPoints () );
    }

    @Test
     void test02NewPlayerWithNamePepeIsNamedPepe () {
        Player player = new Player ( "Pepe", team );
        assertEquals ( "Pepe", player.obtainName () );
    }

    //Point tests.
    @Test
     void test03Subtracting8PointsFromPlayerLeavesPlayerWith12Points () {
        Player player = new Player ("Lola", team);
        try {
            player.subtractPoints ( 8 );
        } catch (PlayerHas20PointsOnlyException e) {
            e.getMessage ();
        }
        assertEquals ( 12, player.obtainPoints () );
    }

    @Test
     void test04Subtracting20PointsFromPlayerLeavesPlayerWith0Points () {
        Player player = new Player ("Archie",team);
        try {
            player.subtractPoints ( 2 );
            player.subtractPoints ( 18 );
        } catch (PlayerHas20PointsOnlyException e) {
            e.getMessage ();
        }
        assertEquals ( 0, player.obtainPoints () );
    }

    @Test
     void test05Subtracting30PointsFromPlayerRaisesError () {
        Player player = new Player ("Player0003", team);

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
}*/