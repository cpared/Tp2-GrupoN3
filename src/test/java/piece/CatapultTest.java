package piece;

import board.CanNotMakeThatMoveException;
import org.junit.jupiter.api.Test;
import team.Blue;
import team.Gold;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CatapultTest {

    @Test
    void test00CreateCatapultWithATeamAndGetTheCorrectTeam(){
        //Assign
        Gold gold = new Gold();
        Catapult catapult = new Catapult(gold);

        //Act

        //Assert
        assertEquals(gold, catapult.getTeam());

    }

    @Test
    void test01CreateCatapultAndGetLifeIs50(){
        //Assign
        Gold gold = new Gold();
        Catapult catapult = new Catapult(gold);

        //Act

        //Assert
        assertEquals(50, catapult.getLife());
    }

    @Test
    void test02CatapultGetCostIs1(){
        //Assign
        Gold gold = new Gold();
        Catapult catapult = new Catapult(gold);

        //Act

        //Assert
        assertEquals(5, catapult.getCost());
    }

    @Test
    void test03CatapultReceiveDamageFromAnotherTeamPieceAndReduceHisLife(){
        //Assign
        Gold gold = new Gold();
        Blue blue = new Blue();
        Catapult catapult = new Catapult(gold);
        Soldier blueSoldier = new Soldier(blue);

        //Act
        blueSoldier.attack(catapult);

        //Assert
        assertEquals(40, catapult.getLife());
    }

    @Test
    void test04CatapultReceiveDamageAndReduceHisLife(){
        //Assign
        Gold gold = new Gold();
        Catapult catapult = new Catapult(gold);

        //Act
        catapult.getAttacked(20);

        //Assert
        assertEquals(30, catapult.getLife());
    }

    @Test
    void test05CatapultReceiveDamageAndReduceHisLifeToCero(){
        //Assign
        Gold gold = new Gold();
        Catapult catapult = new Catapult(gold);

        //Act
        catapult.getAttacked(50);

        //Assert
        assertEquals(0, catapult.getLife());
    }

    @Test
    void test06CatapultReceiveDamageAndReduceHisLifeAndCantReduceMoreThanCero(){
        //Assign
        Gold gold = new Gold();
        Catapult catapult = new Catapult(gold);

        //Act
        catapult.getAttacked(120);

        //Assert
        assertEquals(0, catapult.getLife());
    }

    @Test
    void test07CatapultUseBodyAttackAndRaiseAndError(){
        //Assign
        Gold gold = new Gold();
        Catapult catapult = new Catapult(gold);

        //Act - Assert
        try {
            catapult.getBodyAttack();
        } catch(CanNotMakeThatMoveException e) {
            assertThat(e.getMessage(), containsString("Piece cannot move in that direction"));
        }
    }

    @Test
    void test08CatapultUseDistanceAttackAndHisDamageIs20(){
        //Assign
        Gold gold = new Gold();
        Catapult catapult = new Catapult(gold);

        //Act

        //Assert
        assertEquals(20, catapult.getDistanceAttack());
    }

    @Test
    void test09CatapultGetMoveIs0(){
        //Assign
        Gold gold = new Gold();
        Catapult catapult = new Catapult(gold);

        //Act

        //Assert
        assertEquals(0, catapult.move());
    }

    @Test
    void test10CatapultReceiveHealAndRaiseAndError(){
        //Assign
        Gold gold = new Gold();
        Catapult catapult = new Catapult(gold);
        Healer healer = new Healer(gold);

        //Act
        catapult.getAttacked(20);
        try{
            healer.heal(catapult);
        } catch(CanNotMakeThatMoveException e) {
        assertThat(e.getMessage(), containsString("Piece cannot move in that direction"));
        }
    }

    @Test
    void test12CatapultMakeDistanceAttackAndTheOtherPieceReceiveDamage(){
        //Assign
        Gold gold = new Gold();
        Blue blue = new Blue();
        Catapult catapult = new Catapult(gold);
        Healer healer = new Healer(blue);

        //Act
        catapult.distanceAttack(healer);

        //Assert
        assertEquals(55, healer.getLife());
    }
}