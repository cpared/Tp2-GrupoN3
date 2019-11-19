package piece;

import board.CanNotMakeThatMoveException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import team.Team;
class SoldierTest {

    @Test
    void test00CreateSoldierWithATeamAndGetTheCorrectTeam () {
        //Assign
        Team gold = new Team (1);
        Soldier soldier = new Soldier ( gold );

        //Act

        //Assert
        assertEquals ( gold, soldier.getTeam () );

    }

    @Test
    void test01CreateSoldierAndGetLifeIs100 () {
        //Assign
        Team gold = new Team (1);
        Soldier soldier = new Soldier ( gold );

        //Act

        //Assert
        assertEquals ( 100, soldier.getLife () );
    }

    @Test
    void test02SoldierGetCostIs1 () {
        //Assign
        Team gold = new Team (1);
        Soldier soldier = new Soldier ( gold );

        //Act

        //Assert
        assertEquals ( 1, soldier.getCost () );
    }

    @Test
    void test03soldierReceiveDamageFromAnotherTeamPieceAndReduceHisLife () {
        //Assign
        Team gold = new Team (1);
        Team blue = new Team (2);
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
        Team gold = new Team (1);
        Soldier soldier = new Soldier ( gold );

        //Act
        soldier.getAttacked ( 20 );

        //Assert
        assertEquals ( 80, soldier.getLife () );
    }

    @Test
    void test05SoldierReceiveDamageAndReduceHisLifeToCero () {
        //Assign
        Team gold = new Team (1);
        Soldier soldier = new Soldier ( gold );

        //Act
        soldier.getAttacked ( 100 );

        //Assert
        assertEquals ( 0, soldier.getLife () );
    }

    @Test
    void test06soldierReceiveDamageAndReduceHisLifeAndCantReduceMoreThanCero () {
        //Assign
        Team gold = new Team (1);
        Soldier soldier = new Soldier ( gold );

        //Act
        soldier.getAttacked ( 120 );

        //Assert
        assertEquals ( 0, soldier.getLife () );
    }

    @Test
    void test07soldierUseBodyAttackAndHisDamageIs10 () {
        //Assign
        Team gold = new Team (1);
        Soldier soldier = new Soldier ( gold );

        //Act

        //Assert
        assertEquals ( 10, soldier.getBodyAttack () );
    }

    @Test
    void test08soldierUseDistanceAttackAndHisDamageIs0 () {
        //Assign
        Team gold = new Team (1);
        Soldier soldier = new Soldier ( gold );

        //Act

        //Assert
        assertEquals ( 0, soldier.getDistanceAttack () );
    }

    @Test
    void test09soldierGetMoveIs3 () {

    }

    @Test
    void test10SoldierReceiveHealAndHisLifeUp () {
        //Assign
        Team gold = new Team (1);
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
        Team gold = new Team (1);
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
        Team gold = new Team (1);
        Team blue = new Team (2);
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