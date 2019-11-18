package piece;

import board.CanNotMakeThatMoveException;
import org.junit.jupiter.api.Test;
import team.Team;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HealerTest {

    @Test
    void test00CreateHealerWithATeamAndGetTheCorrectTeam () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act

        //Assert
        assertEquals ( gold, healer.getTeam () );

    }

    @Test
    void test01CreateHealerAndGetLifeIs75 () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act

        //Assert
        assertEquals ( 75, healer.getLife () );
    }

    @Test
    void test02HealerGetCostIs2 () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act

        //Assert
        assertEquals ( 2, healer.getCost () );
    }

    @Test
    void test03HealerReceiveDamageFromAnotherTeamPieceAndReduceHisLife () {
        //Assign
        Team gold = new Team (1);
        Team blue = new Team (2);
        Healer healer = new Healer ( gold );
        Soldier blueSoldier = new Soldier ( blue );

        //Act
        blueSoldier.attack ( healer );

        //Assert
        assertEquals ( 65, healer.getLife () );
    }

    @Test
    void test04HealerReceiveDamageAndReduceHisLife () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act
        healer.getAttacked ( 20 );

        //Assert
        assertEquals ( 55, healer.getLife () );
    }

    @Test
    void test05HealerReceiveDamageAndReduceHisLifeToCero () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act
        healer.getAttacked ( 75 );

        //Assert
        assertEquals ( 0, healer.getLife () );
    }

    @Test
    void test06HealerReceiveDamageAndReduceHisLifeAndCantReduceMoreThanCero () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act
        healer.getAttacked ( 120 );

        //Assert
        assertEquals ( 0, healer.getLife () );
    }

    @Test
    void test07HealerUseBodyAttackAndRaiseAnError () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act - Assert
        try {
            healer.getBodyAttack ();
        } catch (CanNotMakeThatMoveException e) {
            assertThat ( e.getMessage (), containsString ( "Piece cannot move in that direction" ) );
        }
    }

    @Test
    void test08HealerUseDistanceAttackAndRaiseAnError () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act - Assert
        try {
            healer.getBodyAttack ();
        } catch (CanNotMakeThatMoveException e) {
            assertThat ( e.getMessage (), containsString ( "Piece cannot move in that direction" ) );
        }
    }

    @Test
    void test09HealerGetMoveIs3 () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act

        //Assert
        assertEquals ( 3, healer.move () );
    }

    @Test
    void test10HealerReceiveHealAndHisLifeUp () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act
        healer.getAttacked ( 20 );
        healer.heal ( healer );

        //Assert
        assertEquals ( 70, healer.getLife () );
    }

    @Test
    void test11HealerReceiveHealWithOutDamageAndHisLifeCantUp () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act
        healer.heal ( healer );

        //Assert
        assertEquals ( 75, healer.getLife () );
    }

    @Test
    void test12HealerMakeDistanceAttackAndRaiseAnError () {
        //Assign
        Team gold = new Team (1);
        Team blue = new Team (2);
        Soldier soldier = new Soldier ( gold );
        Healer healer = new Healer ( blue );

        //Act - Assert
        try {
            healer.distanceAttack ( soldier );
        } catch (CanNotMakeThatMoveException e) {
            assertThat ( e.getMessage (), containsString ( "Piece cannot move in that direction" ) );
        }
    }
}