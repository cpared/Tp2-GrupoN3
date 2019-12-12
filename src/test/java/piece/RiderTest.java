package piece;


import game.Game;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import team.Team;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RiderTest {
    private Team team = new Team ( 34 , new Game ());

    @Test
    void test00CreateRiderWithATeamAndGetTheCorrectTeam () {
        //Assemble
        Piece rider = new Rider ( this.team );
        //Act & Assert
        assertTrue ( this.team.equals ( rider.getTeam () ) );

    }

    @Test
    void test01CreateRiderAndGetLifeIs100 () {
        //Assemble
        Piece rider = new Rider ( this.team );

        //Act & Assert
        assertEquals ( 100, rider.getLife () );
    }

    @Test
    void test02RiderGetCostIs3 () {
        //Assemble
        Piece rider = new Rider ( this.team );

        //Act & Assert
        assertTrue ( rider.isCost ( 3 ) );
    }

    @Test
    void test03RiderReceiveDamageFromAnotherTeamPieceAndReduceHisLife () {
        //Assemble
        Team blue = new Team ( 25, new Game () );
        Piece rider = new Rider ( this.team );
        Piece blueRider = new Rider ( blue );

        //Act
        blueRider.attack ( new ArrayList<> ( Arrays.asList ( rider ) ), new Pair<> ( rider, 1 ) );

        //Assert
        assertEquals ( 95, rider.getLife () );
    }

    @Test
    void test04RiderReceiveDamageAndReduceHisLife () {
        //Assemble
        Piece rider = new Rider ( this.team );

        //Act
        rider.receiveAttacked ( 20 );

        //Assert
        assertEquals ( 80, rider.getLife () );
    }

    @Test
    void test05RiderReceiveDamageAndReduceHisLifeToCero () {
        //Assemble
        Piece rider = new Rider ( this.team );

        //Act
        try {
            rider.receiveAttacked ( 100 );
        } catch (IAmDeadException e) {
            assertEquals ( 0, rider.getLife () );
        }

        //Assert
    }

    @Test
    void test06RiderReceiveDamageAndReduceHisLifeAndThrowsException () {
        //Assemble
        Piece rider = new Rider ( this.team );

        //Act
        try {
            rider.receiveAttacked ( 120 );
        }
        //Assert
        catch (IAmDeadException e) {
            assert true;
        }
    }


    @Test
    void test07RiderReceiveHealAndHisLifeUp () {
        //Assemble
        Piece rider = new Rider ( this.team );
        Piece healer = new Healer ( this.team );

        //Act
        rider.receiveAttacked ( 20 );
        healer.attack ( new ArrayList<> (), new Pair<> ( rider, 1 ) );

        //Assert
        assertEquals ( 95, rider.getLife () );
    }

    @Test
    void test08RiderReceiveHealWithOutDamageAndHisLifeCantUp () {
        //Assemble
        Piece rider = new Rider ( this.team );
        Piece healer = new Healer ( this.team );

        //Act
        healer.attack ( new ArrayList<> (), new Pair<> ( rider, 1 ) );

        //Assert
        assertEquals ( 100, rider.getLife () );
    }

    @Test
    void test09RiderMakeDistanceAttackAndTheOtherPieceReceiveDamage () {
        //Assemble
        Team blue = new Team ( 7, new Game () );
        Piece rider = new Rider ( this.team );
        Piece healer = new Healer ( blue );

        //Act
        rider.attack ( new ArrayList<> (), new Pair<> ( healer, 1 ) );

        //Assert
        assertEquals ( 60, healer.getLife () );
    }

}