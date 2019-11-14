package piece;

import org.junit.jupiter.api.Test;
import player.PlayerHas20PointsOnlyException;
import team.Team;

import static org.junit.jupiter.api.Assertions.*;

class PieceFactoryTest {
    //Assemble
    private Team team = new Team ();
    private PieceFactory factory = new PieceFactory ( team );

    // verifies board creation.
    @Test
    void test00ThePieceFactoryCanBeCreated () {
        assertNotNull ( factory );
    }

    // verifies that factory creates correct objects of each type.
    @Test
    void test01WhenAskedToCreateARiderFactoryCreatesARider () throws PlayerHas20PointsOnlyException {
        //Act
        Piece piece = factory.createRider ();
        //Assert
        assertEquals ( Rider.class, piece.getClass () );
    }

    @Test
    void test02WhenAskedToCreateAHealerFactoryCreatesAHealer () throws PlayerHas20PointsOnlyException {
        //Act
        Piece piece = factory.createHealer ();
        //Assert
        assertEquals ( Healer.class, piece.getClass () );
    }

    @Test
    void test03WhenAskedToCreateASoldierFactoryCreatesASoldier () throws PlayerHas20PointsOnlyException {
        //Act
        Piece piece = factory.createSoldier ();
        //Assert
        assertEquals ( Soldier.class, piece.getClass () );
    }

    @Test
    void test04WhenAskedToCreateACatapultFactoryCreatesACatapult () throws PlayerHas20PointsOnlyException {
        //Act
        Piece piece = factory.createCatapult ();
        //Assert
        assertEquals ( Catapult.class, piece.getClass () );
    }
}

/*
    @Test
    void test13PlayerChoosingAHealerSubtracts3PointFromPlayer () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        //Act
        player.chooseHealer ( );

        //Assert
        assertEquals ( 18, player.obtainPoints () );
    }

 */