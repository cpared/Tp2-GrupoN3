package piece;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import team.Team;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HealerTest {
    Team team = new Team ( 1 );

    @Test
    void test00CreateHealerWithATeamAndGetTheCorrectTeam () {
        //Assemble
        Piece piece = new Healer ( this.team );

        //Act & Assert
        assertTrue ( this.team.equals ( piece.getTeam () ) );
    }

    @Test
    void test01CreateHealerAndGetLifeIs75 () {
        //Assemble
        Piece piece = new Healer ( this.team );

        //Act & Assert
        assertEquals ( 75, piece.getLife () );
    }

    @Test
    void test02HealerGetCostIs2 () {
        //Assemble
        Piece piece = new Healer ( this.team );

        //Act & Assert
        assertTrue ( piece.isCost ( 2 ) );
    }

    @Test
    void test03HealerReceiveDamageFromAnotherTeamPieceAndReduceHisLife () {
        //Assemble
        Team team2 = new Team ( 2 );
        Piece healer = new Healer ( this.team );
        Piece soldier = new Soldier ( team2 );

        //Act
        soldier.attack ( new ArrayList<> (), new Pair<> ( healer, 1 ) );

        //Assert
        assertEquals ( 65, healer.getLife () );
    }

    @Test
    void test04HealerReceiveDamageAndReduceHisLife () {
        //Assemble
        Piece piece = new Healer ( this.team );

        //Act
        piece.receiveAttacked ( 20 );

        //Assert
        assertEquals ( 55, piece.getLife () );
    }

    @Test
    void test05HealerReceiveDamageAndReduceHisLifeToCero () {
        //Assemble
        Piece piece = new Healer ( this.team );

        //Act
        try {
            piece.receiveAttacked ( 75 );
        } catch (IAmDeadException e) {
            assertEquals ( 0, piece.getLife () );
        }
        //Assert
    }

    @Test
    void test06HealerReceiveDamageAndReduceHisLifeAndCantReduceMoreThanCero () {
        //Assemble
        Piece piece = new Healer ( this.team );

        //Act
        try {
            piece.receiveAttacked ( 120 );
        } catch (IAmDeadException e) {
            assert true;
        }
    }


    @Test
    void test10HealerReceiveHealAndHisLifeUp () {
        //Assemble
        Piece piece = new Healer ( this.team );

        //Act
        piece.receiveAttacked ( 20 );
        piece.attack ( new ArrayList<> (), new Pair<> ( piece, 1 ) );

        //Assert
        assertEquals ( 70, piece.getLife () );
    }

    @Test
    void test11HealerReceiveHealWithOutDamageAndHisLifeCantUp () {
        //Assemble
        Piece piece = new Healer ( this.team );

        //Act
        piece.attack ( new ArrayList<> (), new Pair<> ( piece, 1 ) );

        //Assert
        assertEquals ( 75, piece.getLife () );
    }

}