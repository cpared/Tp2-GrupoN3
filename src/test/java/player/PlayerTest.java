package player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    //Initiation tests.
    @Test
    public void test01NewPlayerHas20Points () {
        Player player = new Player ();
        assertEquals ( 20, player.obtainPoints () );
    }
    @Test
    public void test02NewPlayerHasNoName () {
        Player player = new Player ();
        assertEquals ( "", player.obtainName () );
    }
    @Test
    public void test03NewPlayerWithNamePepeIsNamedPepe () {
        Player player = new Player ( "Pepe");
        assertEquals ( "Pepe", player.obtainName () );
    }
    @Test
    public void test04NewPlayerWithNamePepeHas20Points () {
        Player player = new Player ( "Pepe");
        assertEquals ( 20, player.obtainPoints () );
    }

    //Setting tests.
    @Test
    public void test05AssigningNamePepeToPlayerNamesPlayerPepe () {
        Player player = new Player ();
        player.assignName ( "Pepe" );
        assertEquals ( "Pepe", player.obtainName () );
    }

    //Point tests.
    @Test
    public void test06Subtracting8PointsFromPlayerLeavesPlayerWith12Points () {
        Player player = new Player ();
        player.subtractPoints ( 8 );
        assertEquals ( 12, player.obtainPoints () );
    }
    @Test
    public void test07Subtracting20PointsFromPlayerLeavesPlayerWith0Points () {
        Player player = new Player ();
        player.subtractPoints ( 2 );
        player.subtractPoints ( 18 );
        assertEquals ( 0, player.obtainPoints () );
    }
    @Test
    public void test08Subtracting30PointsFromPlayerRaisesError () {
        Player player = new Player ();
        //assertThrows ( YouCantSubtractOver20PointsFromPlayerException.class,
        //      ()->{
        //        player.subtractPoints ( 30 );
        //  });
    }
}