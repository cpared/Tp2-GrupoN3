package piece;

import board.CanNotMakeThatMoveException;
import game.Game;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import team.Team;

import java.util.ArrayList;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CatapultTest {
    Game game = new Game ();
    private Team team = new Team (1,game);

    @Test
    void test00CreateCatapultWithATeamAndGetTheCorrectTeam () {
        //Assemble
        Catapult piece = new Catapult ( this.team );
        //Act & Assert
        assertTrue ( this.team.equals ( piece.team ) );

    }

    @Test
    void test01CreateCatapultAndGetLifeIs50 () {
        //Assemble
        Catapult piece = new Catapult ( this.team );
        //Act & Assert
        assertEquals ( 50, piece.getLife () );
    }

    @Test
    void test02CatapultGetCostIs1 () {
        //Assemble
        Piece piece = new Catapult ( this.team );

        //Act & Assert
        assertTrue ( piece.isCost ( 5 ) );
    }

    @Test
    void test03CatapultReceiveDamageFromAnotherTeamPieceAndReduceHisLife () {
        //Assemble
        Team blue = new Team (2, new Game());
        Piece piece = new Catapult ( this.team );
        Piece blueSoldier = new Soldier ( blue );

        //Act
        blueSoldier.attack ( new ArrayList<>(), new Pair<>(piece,1 ) );

        //Assert
        assertEquals ( 40, piece.getLife () );
    }

    @Test
    void test04CatapultReceiveDamageAndReduceHisLife () {
        //Assemble
        Piece piece = new Catapult ( this.team );

        //Act
        piece.receiveAttacked ( 20 );

        //Assert
        assertEquals ( 30, piece.getLife () );
    }

    @Test
    void test05CatapultReceiveDamageAndReduceHisLifeToCero () {
        //Assemble
        Piece piece = new Catapult ( this.team );

        //Act
        try{
            piece.receiveAttacked ( 50 );
        }
        catch(IAmDeadException e){
            assertEquals ( 0, piece.getLife () );
        }
    }

    @Test
    void test06CatapultReceiveDamageAndReduceHisLifeAndCantReduceMoreThanCero () {
        //Assemble
        Piece piece = new Catapult ( this.team );

    //Act
        piece.receiveAttacked ( 120 );
        assertFalse(piece.isAlive());
    }



    @Test
    void test07CatapultReceiveHealAndRaiseAndError () {
        //Assemble
        Piece piece = new Catapult ( this.team );
        Piece healer = new Healer ( this.team );

        //Act
        piece.receiveAttacked ( 20 );
        try {
            healer.attack ( new ArrayList<>(), new Pair<>(piece,1 ) );
        } catch (CanNotMakeThatMoveException e) {
            assertThat ( e.getMessage (), containsString ( "Piece cannot move in that direction" ) );
        }
    }

    @Test
    void test08CatapultMakeDistanceAttackAndTheOtherPieceReceiveDamage () {
        //Assemble
        Team blue = new Team (2,game);
        Piece piece = new Catapult ( this.team );
        Piece healer = new Healer ( blue );

        //Act
        piece.attack ( new ArrayList<>(), new Pair<>(healer,1 ) );

        //Assert
        assertEquals ( 55, healer.getLife () );
    }
}