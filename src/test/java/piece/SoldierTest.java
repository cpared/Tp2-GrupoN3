package piece;

import board.CanNotMakeThatMoveException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SoldierTest {

    @Test
    void test00CreateSoldierWithATeamAndGetTheCorrectTeam () {
        //Assign
        Gold gold = new Gold ();
        Soldier soldier = new Soldier ( gold );

        //Act

        //Assert
        assertEquals ( gold, soldier.getTeam () );

    }

    @Test
    void test01CreateSoldierAndGetLifeIs100 () {
        //Assign
        Gold gold = new Gold ();
        Soldier soldier = new Soldier ( gold );

        //Act

        //Assert
        assertEquals ( 100, soldier.getLife () );
    }

    @Test
    void test02SoldierGetCostIs1 () {
        //Assign
        Gold gold = new Gold ();
        Soldier soldier = new Soldier ( gold );

        //Act

        //Assert
        assertEquals ( 1, soldier.getCost () );
    }

    @Test
    void test03soldierReceiveDamageFromAnotherTeamPieceAndReduceHisLife () {
        //Assign
        Gold gold = new Gold ();
        Blue blue = new Blue ();
        Soldier soldier = new Soldier ( gold );
        Soldier blueSoldier = new Soldier ( blue );

        //Act
        blueSoldier.attack ( soldier );

        //Assert
        assertEquals ( 90, soldier.getLife () );
    }

    @Test
    void test04SoldierReceiveDamageAndReduceHisLife () {
        //Assign
        Gold gold = new Gold ();
        Soldier soldier = new Soldier ( gold );

        //Act
        soldier.getAttacked ( 20 );

        //Assert
        assertEquals ( 80, soldier.getLife () );
    }

    @Test
    void test05SoldierReceiveDamageAndReduceHisLifeToCero () {
        //Assign
        Gold gold = new Gold ();
        Soldier soldier = new Soldier ( gold );

        //Act
        soldier.getAttacked ( 100 );

        //Assert
        assertEquals ( 0, soldier.getLife () );
    }

    @Test
    void test06soldierReceiveDamageAndReduceHisLifeAndCantReduceMoreThanCero () {
        //Assign
        Gold gold = new Gold ();
        Soldier soldier = new Soldier ( gold );

        //Act
        soldier.getAttacked ( 120 );

        //Assert
        assertEquals ( 0, soldier.getLife () );
    }

    @Test
    void test07soldierUseBodyAttackAndHisDamageIs10 () {
        //Assign
        Gold gold = new Gold ();
        Soldier soldier = new Soldier ( gold );

        //Act

        //Assert
        assertEquals ( 10, soldier.getBodyAttack () );
    }

    @Test
    void test08soldierUseDistanceAttackAndHisDamageIs0 () {
        //Assign
        Gold gold = new Gold ();
        Soldier soldier = new Soldier ( gold );

        //Act

        //Assert
        assertEquals ( 0, soldier.getDistanceAttack () );
    }

    @Test
    void test09soldierGetMoveIs3 () {
        //Assign
        Gold gold = new Gold ();
        Soldier soldier = new Soldier ( gold );

        //Act

        //Assert
        assertEquals ( 3, soldier.move () );
    }

    @Test
    void test10SoldierReceiveHealAndHisLifeUp () {
        //Assign
        Gold gold = new Gold ();
        Soldier soldier = new Soldier ( gold );
        Healer healer = new Healer ( gold );

        //Act
        soldier.getAttacked ( 20 );
        healer.heal ( soldier );

        //Assert
        assertEquals ( 95, soldier.getLife () );
    }

    @Test
    void test11soldierReceiveHealWithOutDamageAndHisLifeCantUp () {
        //Assign
        Gold gold = new Gold ();
        Soldier soldier = new Soldier ( gold );
        Healer healer = new Healer ( gold );

        //Act
        healer.heal ( soldier );

        //Assert
        assertEquals ( 100, soldier.getLife () );
    }

    @Test
    void test12soldierMakeDistanceAttackAndTheOtherPieceReceiveDamage () {
        //Assign
        Gold gold = new Gold ();
        Blue blue = new Blue ();
        Soldier soldier = new Soldier ( gold );
        Healer healer = new Healer ( blue );

        //Act - Assert
        try {
            soldier.distanceAttack ( healer );
        } catch (CanNotMakeThatMoveException e) {
            assertThat ( e.getMessage (), containsString ( "Piece cannot move in that direction" ) );
        }
    }

}