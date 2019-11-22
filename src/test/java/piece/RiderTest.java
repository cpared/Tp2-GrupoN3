package piece;


import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import team.Team;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        blueRider.attack ( new ArrayList<>(Arrays.asList(rider)), new Pair<>(rider,1 ));

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
        try{
            rider.receiveAttacked ( 100 );
        }
        catch(IAmDeadException e){
            assertEquals ( 0, rider.getLife () );
        }

        //Assert
    }

    @Test
    void test06RiderReceiveDamageAndReduceHisLifeAndThrowsException () {
        //Assign
        Team gold = new Team (45);
        Rider rider = new Rider ( gold );

        //Act
        try {
            rider.receiveAttacked(120);
        }
        //Assert
        catch (IAmDeadException e) {
            assert true;
        }
    }


    @Test
    void test07RiderReceiveHealAndHisLifeUp () {
        //Assign
        Team gold = new Team (4);
        Rider rider = new Rider ( gold );
        Healer healer = new Healer ( gold );

        //Act
        rider.receiveAttacked ( 20 );
        healer.attack ( new ArrayList<>(), new Pair<>(rider,1 ) );

        //Assert
        assertEquals ( 95, rider.getLife () );
    }

    @Test
    void test08RiderReceiveHealWithOutDamageAndHisLifeCantUp () {
        //Assign
        Team gold = new Team (8);
        Rider rider = new Rider ( gold );
        Healer healer = new Healer ( gold );

        //Act
        healer.attack ( new ArrayList<>(), new Pair<>(rider,1 ) );

        //Assert
        assertEquals ( 100, rider.getLife () );
    }

    @Test
    void test09RiderMakeDistanceAttackAndTheOtherPieceReceiveDamage () {
        //Assign
        Team gold = new Team (9);
        Team blue = new Team (7);
        Rider rider = new Rider ( gold );
        Healer healer = new Healer ( blue );

        //Act
        rider.attack ( new ArrayList<>(), new Pair<>(healer,1 ) );

        //Assert
        assertEquals ( 60, healer.getLife () );
    }

}