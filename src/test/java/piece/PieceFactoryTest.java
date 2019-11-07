package piece;

import org.junit.jupiter.api.Test;
import team.Team;

import static org.junit.jupiter.api.Assertions.*;

class PieceFactoryTest {
    //Assemble
    private PieceFactory factory = new PieceFactory ();
    private Team team = new Team ();

    // verifies board creation.
    @Test
    void test00ThePieceFactoryCanBeCreated () {
        assertNotNull ( factory );
    }

    // verifies that factory creates correct objects of each type.
    @Test
    void test01WhenAskedToCreateARiderFactoryCreatesARider () {
        //Act
        Piece piece = factory.getPiece ( "RIDER", team );
        //Assert
        assertEquals ( Rider.class, piece.getClass () );
    }

    @Test
    void test02WhenAskedToCreateAHealerFactoryCreatesAHealer () {
        //Act
        Piece piece = factory.getPiece ( "HEALER", team );
        //Assert
        assertEquals ( Healer.class, piece.getClass () );
    }

    @Test
    void test03WhenAskedToCreateASoldierFactoryCreatesASoldier () {
        //Act
        Piece piece = factory.getPiece ( "SOLDIER", team );
        //Assert
        assertEquals ( Soldier.class, piece.getClass () );
    }

    @Test
    void test04WhenAskedToCreateACatapultFactoryCreatesACatapult () {
        //Act
        Piece piece = factory.getPiece ( "CATAPULT", team );
        //Assert
        assertEquals ( Catapult.class, piece.getClass () );
    }

    // checks border cases.
    @Test
    void test05FactoryCreatesPieceWhenItReceivesParameterInLowercase () {
        //Act
        Piece piece = factory.getPiece ( "catapult", team );
        //Assert
        assertEquals ( Catapult.class, piece.getClass () );
    }

    @Test
    void test05FactoryCreatesPieceWhenItReceivesParameterWithMixedLowercaseAndUppercase () {
        //Act
        Piece piece = factory.getPiece ( "catAPult", team );
        //Assert
        assertEquals ( Catapult.class, piece.getClass () );
    }

    @Test
    void test07FactoryCreatesPieceWhenItReceivesParameterWithExtraSpaces () {
        //Act
        Piece piece = factory.getPiece ( "RIDER ", team );
        //Assert
        assertEquals ( Rider.class, piece.getClass () );
    }

    @Test
    void test08FactoryDoesNotCreateAPieceWhenItDoesntReceiveAParameter () {
        //Act
        Piece piece = factory.getPiece ( null, team );
        //Assert
        assertNull ( piece );
    }
}