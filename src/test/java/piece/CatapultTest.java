package piece;

import board.CanNotMakeThatMoveException;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import team.Team;

import java.util.ArrayList;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CatapultTest {

    @Test
    void test00CreateCatapultWithATeamAndGetTheCorrectTeam () {
        //Assign
        Team gold = new Team (1);
        Catapult catapult = new Catapult ( gold );

        //Act

        //Assert
        assertEquals ( gold, catapult.getTeam () );

    }

    @Test
    void test01CreateCatapultAndGetLifeIs50 () {
        //Assign
        Team gold = new Team (1);
        Catapult catapult = new Catapult ( gold );

        //Act

        //Assert
        assertEquals ( 50, catapult.getLife () );
    }

    @Test
    void test02CatapultGetCostIs1 () {
        //Assign
        Team gold = new Team (1);
        Catapult catapult = new Catapult ( gold );

        //Act

        //Assert
        assertEquals ( 5, catapult.getCost () );
    }

    @Test
    void test03CatapultReceiveDamageFromAnotherTeamPieceAndReduceHisLife () {
        //Assign
        Team gold = new Team (1);
        Team blue = new Team (2);
        Catapult catapult = new Catapult ( gold );
        Soldier blueSoldier = new Soldier ( blue );

        //Act
        blueSoldier.attack ( new ArrayList<>(), new Pair<>(catapult,1 ) );

        //Assert
        assertEquals ( 40, catapult.getLife () );
    }

    @Test
    void test04CatapultReceiveDamageAndReduceHisLife () {
        //Assign
        Team gold = new Team (1);
        Catapult catapult = new Catapult ( gold );

        //Act
        catapult.receiveAttacked ( 20 );

        //Assert
        assertEquals ( 30, catapult.getLife () );
    }

    @Test
    void test05CatapultReceiveDamageAndReduceHisLifeToCero () {
        //Assign
        Team gold = new Team (1);
        Catapult catapult = new Catapult ( gold );

        //Act
        catapult.receiveAttacked ( 50 );

        //Assert
        assertEquals ( 0, catapult.getLife () );
    }

    @Test
    void test06CatapultReceiveDamageAndReduceHisLifeAndCantReduceMoreThanCero () {
        //Assign
        Team gold = new Team (1);
        Catapult catapult = new Catapult ( gold );

        //Act
        catapult.receiveAttacked ( 120 );

        //Assert
        assertEquals ( 0, catapult.getLife () );
    }



    @Test
    void test07CatapultReceiveHealAndRaiseAndError () {
        //Assign
        Team gold = new Team (1);
        Catapult catapult = new Catapult ( gold );
        Healer healer = new Healer ( gold );

        //Act
        catapult.receiveAttacked ( 20 );
        try {
            healer.attack ( new ArrayList<>(), new Pair<>(catapult,1 ) );
        } catch (CanNotMakeThatMoveException e) {
            assertThat ( e.getMessage (), containsString ( "Piece cannot move in that direction" ) );
        }
    }

    @Test
    void test08CatapultMakeDistanceAttackAndTheOtherPieceReceiveDamage () {
        //Assign
        Team gold = new Team (1);
        Team blue = new Team (2);
        Catapult catapult = new Catapult ( gold );
        Healer healer = new Healer ( blue );

        //Act
        catapult.attack ( new ArrayList<>(), new Pair<>(healer,1 ) );

        //Assert
        assertEquals ( 55, healer.getLife () );
    }
}