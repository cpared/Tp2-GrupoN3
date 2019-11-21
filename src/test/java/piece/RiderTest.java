package piece;


import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import team.Team;

import java.util.ArrayList;

class RiderTest {

    @Test
    void test00CreateRiderWithATeamAndGetTheCorrectTeam () {
        //Assign
        Team gold = new Team (34);
        Rider rider = new Rider ( gold );

        //Act

        //Assert
        assertEquals ( gold, rider.getTeam () );

    }

    @Test
    void test01CreateRiderAndGetLifeIs100 () {
        //Assign
        Team gold = new Team (6);
        Rider rider = new Rider ( gold );

        //Act

        //Assert
        assertEquals ( 100, rider.getLife () );
    }

    @Test
    void test02RiderGetCostIs3 () {
        //Assign
        Team gold = new Team (23);
        Rider rider = new Rider ( gold );

        //Act

        //Assert
        assertEquals ( 3, rider.getCost () );
    }

    @Test
    void test03RiderReceiveDamageFromAnotherTeamPieceAndReduceHisLife () {
        //Assign
        Team gold = new Team (23);
        Team blue = new Team (25);
        Rider rider = new Rider ( gold );
        Rider blueRider = new Rider ( blue );

        //Act
        blueRider.attack ( new ArrayList<>(), new Pair<>(rider,1 ));

        //Assert
        assertEquals ( 95, rider.getLife () );
    }

    @Test
    void test04RiderReceiveDamageAndReduceHisLife () {
        //Assign
        Team gold = new Team (9);
        Rider rider = new Rider ( gold );

        //Act
        rider.receiveAttacked ( 20 );

        //Assert
        assertEquals ( 80, rider.getLife () );
    }

    @Test
    void test05RiderReceiveDamageAndReduceHisLifeToCero () {
        //Assign
        Team gold = new Team (67);
        Rider rider = new Rider ( gold );

        //Act
        rider.receiveAttacked ( 100 );

        //Assert
        assertEquals ( 0, rider.getLife () );
    }

    @Test
    void test06RiderReceiveDamageAndReduceHisLifeAndCantReduceMoreThanCero () {
        //Assign
        Team gold = new Team (45);
        Rider rider = new Rider ( gold );

        //Act
        rider.receiveAttacked ( 120 );

        //Assert
        assertEquals ( 0, rider.getLife () );
    }

    @Test
    void test07RiderUseBodyAttackAndHisDamageIs5 () {
        //Assign
        Team gold = new Team (6);
        Rider rider = new Rider ( gold );

        //Act

        //Assert
        assertEquals ( 5, rider.getBodyAttack () );
    }

    @Test
    void test08RiderUseDistanceAttackAndHisDamageIs15 () {
        //Assign
        Team gold = new Team (76);
        Rider rider = new Rider ( gold );

        //Act

        //Assert
        assertEquals ( 15, rider.getDistanceAttack () );
    }

    @Test
    void test09RiderGetMoveIs3 () {

    }

    @Test
    void test10RiderReceiveHealAndHisLifeUp () {
        //Assign
        Team gold = new Team (4);
        Rider rider = new Rider ( gold );
        Healer healer = new Healer ( gold );

        //Act
        rider.getAttacked ( 20 );
        healer.heal ( rider );

        //Assert
        assertEquals ( 95, rider.getLife () );
    }

    @Test
    void test11RiderReceiveHealWithOutDamageAndHisLifeCantUp () {
        //Assign
        Team gold = new Team (8);
        Rider rider = new Rider ( gold );
        Healer healer = new Healer ( gold );

        //Act
        healer.heal ( rider );

        //Assert
        assertEquals ( 100, rider.getLife () );
    }

    @Test
    void test12RiderMakeDistanceAttackAndTheOtherPieceReceiveDamage () {
        //Assign
        Team gold = new Team (9);
        Team blue = new Team (7);
        Rider rider = new Rider ( gold );
        Healer healer = new Healer ( blue );

        //Act
        rider.distanceAttack ( healer );

        //Assert
        assertEquals ( 60, healer.getLife () );
    }


}