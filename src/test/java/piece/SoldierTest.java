package piece;

import board.CanNotMakeThatMoveException;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import team.Team;

import java.util.ArrayList;

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
        blueSoldier.attack ( new ArrayList<>(), new Pair<>(soldier,1 ));

        //Assert
        assertEquals ( 90, soldier.getLife () );
    }

    @Test
    void test04SoldierReceiveDamageAndReduceHisLife () {
        //Assign
        Team gold = new Team (1);
        Soldier soldier = new Soldier ( gold );

        //Act
        soldier.receiveAttacked ( 20 );

        //Assert
        assertEquals ( 80, soldier.getLife () );
    }

    @Test
    void test05SoldierReceiveDamageAndReduceHisLifeToCero () {
        //Assign
        Team gold = new Team (1);
        Soldier soldier = new Soldier ( gold );

        //Act
        try{
            soldier.receiveAttacked ( 100 );
        }
        catch(IAmDeadException e){
            assertEquals ( 0, soldier.getLife () );
        }

        //Assert
    }

    @Test
    void test06soldierReceiveDamageAndReduceHisLifeAndCantReduceMoreThanZero () {
        //Assign
        Team gold = new Team (1);
        Soldier soldier = new Soldier ( gold );

        //Act
        try {
            soldier.receiveAttacked(120);
        }
        //Assert
        catch (IAmDeadException e) {
            assert true;
        }
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
        soldier.receiveAttacked ( 20 );
        healer.attack( new ArrayList<>(), new Pair<>(soldier,1 ));

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
        healer.attack( new ArrayList<>(), new Pair<>(soldier,1 ));

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
            soldier.attack( new ArrayList<>(), new Pair<>(healer,1 ));
        } catch (CanNotMakeThatMoveException e) {
            assertThat ( e.getMessage (), containsString ( "Piece cannot move in that direction" ) );
        }
    }

}