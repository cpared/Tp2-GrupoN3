package piece;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import team.Team;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        blueSoldier.attack ( new ArrayList<>(), new Pair<>(healer,1 ) );

        //Assert
        assertEquals ( 65, healer.getLife () );
    }

    @Test
    void test04HealerReceiveDamageAndReduceHisLife () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act
        healer.receiveAttacked ( 20 );

        //Assert
        assertEquals ( 55, healer.getLife () );
    }

    @Test
    void test05HealerReceiveDamageAndReduceHisLifeToCero () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act
        healer.receiveAttacked ( 75 );

        //Assert
        assertEquals ( 0, healer.getLife () );
    }

    @Test
    void test06HealerReceiveDamageAndReduceHisLifeAndCantReduceMoreThanCero () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act
        healer.receiveAttacked ( 120 );

        //Assert
        assertEquals ( 0, healer.getLife () );
    }


    @Test
    void test10HealerReceiveHealAndHisLifeUp () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act
        healer.receiveAttacked ( 20 );
        healer.attack ( new ArrayList<>(), new Pair<>(healer,0 ) );

        //Assert
        assertEquals ( 70, healer.getLife () );
    }

    @Test
    void test11HealerReceiveHealWithOutDamageAndHisLifeCantUp () {
        //Assign
        Team gold = new Team (1);
        Healer healer = new Healer ( gold );

        //Act
        healer.attack ( new ArrayList<>(), new Pair<>(healer,0 ) );

        //Assert
        assertEquals ( 75, healer.getLife () );
    }

}