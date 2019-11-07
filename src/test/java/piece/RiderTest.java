package piece;


import board.CanNotMakeThatMoveException;
import org.junit.jupiter.api.Test;
import team.Blue;
import team.Gold;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

class RiderTest {

    @Test
    void test00CreateRiderWithATeamAndGetTheCorrectTeam(){
        //Assign
        Gold gold = new Gold();
        Rider rider = new Rider(gold);

        //Act

        //Assert
        assertEquals(gold, rider.getTeam());

    }

    @Test
    void test01CreateRiderAndGetLifeIs100(){
        //Assign
        Gold gold = new Gold();
        Rider rider = new Rider(gold);

        //Act

        //Assert
        assertEquals(100, rider.getLife());
    }

    @Test
    void test02RiderGetCostIs3(){
        //Assign
        Gold gold = new Gold();
        Rider rider = new Rider(gold);

        //Act

        //Assert
        assertEquals(3, rider.getCost());
    }

    @Test
    void test03RiderReceiveDamageFromAnotherTeamPieceAndReduceHisLife(){
        //Assign
        Gold gold = new Gold();
        Blue blue = new Blue();
        Rider rider = new Rider(gold);
        Rider blueRider = new Rider(blue);

        //Act
        blueRider.attack(rider);

        //Assert
        assertEquals(95, rider.getLife());
    }

    @Test
    void test04RiderReceiveDamageAndReduceHisLife(){
        //Assign
        Gold gold = new Gold();
        Rider rider = new Rider(gold);

        //Act
        rider.getAttacked(20);

        //Assert
        assertEquals(80, rider.getLife());
    }

    @Test
    void test05RiderReceiveDamageAndReduceHisLifeToCero(){
        //Assign
        Gold gold = new Gold();
        Rider rider = new Rider(gold);

        //Act
        rider.getAttacked(100);

        //Assert
        assertEquals(0, rider.getLife());
    }

    @Test
    void test06RiderReceiveDamageAndReduceHisLifeAndCantReduceMoreThanCero(){
        //Assign
        Gold gold = new Gold();
        Rider rider = new Rider(gold);

        //Act
        rider.getAttacked(120);

        //Assert
        assertEquals(0, rider.getLife());
    }

    @Test
    void test07RiderUseBodyAttackAndHisDamageIs5(){
        //Assign
        Gold gold = new Gold();
        Rider rider = new Rider(gold);

        //Act

        //Assert
        assertEquals(5, rider.getBodyAttack());
    }

    @Test
    void test08RiderUseDistanceAttackAndHisDamageIs15(){
        //Assign
        Gold gold = new Gold();
        Rider rider = new Rider(gold);

        //Act

        //Assert
        assertEquals(15, rider.getDistanceAttack());
    }

    @Test
    void test09RiderGetMoveIs3(){
        //Assign
        Gold gold = new Gold();
        Rider rider = new Rider(gold);

        //Act

        //Assert
        assertEquals(3, rider.move());
    }

    @Test
    void test10RiderReceiveHealAndHisLifeUp(){
        //Assign
        Gold gold = new Gold();
        Rider rider = new Rider(gold);
        Healer healer = new Healer(gold);

        //Act
        rider.getAttacked(20);
        healer.heal(rider);

        //Assert
        assertEquals(95, rider.getLife());
    }

    @Test
    void test11RiderReceiveHealWithOutDamageAndHisLifeCantUp(){
        //Assign
        Gold gold = new Gold();
        Rider rider = new Rider(gold);
        Healer healer = new Healer(gold);

        //Act
        healer.heal(rider);

        //Assert
        assertEquals(100, rider.getLife());
    }

    @Test
    void test12RiderMakeDistanceAttackAndTheOtherPieceReceiveDamage(){
        //Assign
        Gold gold = new Gold();
        Blue blue = new Blue();
        Rider rider = new Rider(gold);
        Healer healer = new Healer(blue);

        //Act
        rider.distanceAttack(healer);

        //Assert
        assertEquals(60, healer.getLife());
    }



}