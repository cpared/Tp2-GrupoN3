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
    private Team team = new Team ( 1 );

    @Test
    void test00CreateSoldierWithATeamAndGetTheCorrectTeam () {
        //Assemble
        Piece piece = new Soldier ( this.team );

        //Act & Assert
        assertTrue ( this.team.equals ( piece.getTeam () ) );

    }

    @Test
    void test01CreateSoldierAndGetLifeIs100 () {
        //Assemble

        Piece piece = new Soldier ( this.team );
        //Act & Assert
        assertEquals ( 100, piece.getLife () );
    }

    @Test
    void test02SoldierGetCostIs1 () {
        //Assemble
        Piece piece = new Soldier ( this.team );

        //Act & Assert
        assertTrue ( piece.isCost ( 1 ) );
    }

    @Test
    void test03soldierReceiveDamageFromAnotherTeamPieceAndReduceHisLife () {
        //Assemble

        Team blue = new Team ( 2 );
        Piece soldier = new Soldier ( this.team );
        Piece blueSoldier = new Soldier ( blue );

        //Act
        blueSoldier.attack ( new ArrayList<> (), new Pair<> ( soldier, 1 ) );

        //Assert
        assertEquals ( 90, soldier.getLife () );
    }

    @Test
    void test04SoldierReceiveDamageAndReduceHisLife () {
        //Assemble

        Piece piece = new Soldier ( this.team );

        //Act
        piece.receiveAttacked ( 20 );

        //Assert
        assertEquals ( 80, piece.getLife () );
    }

    @Test
    void test05SoldierReceiveDamageAndReduceHisLifeToCero () {
        //Assemble

        Piece piece = new Soldier ( this.team );

        //Act
        try {
            piece.receiveAttacked ( 100 );
        } catch (IAmDeadException e) {
            assertEquals ( 0, piece.getLife () );
        }

        //Assert
    }

    @Test
    void test06soldierReceiveDamageAndReduceHisLifeAndCantReduceMoreThanZero () {
        //Assemble

        Piece piece = new Soldier ( this.team );

        //Act
        try {
            piece.receiveAttacked ( 120 );
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
        //Assemble

        Piece piece = new Soldier ( this.team );
        Piece healer = new Healer ( this.team );

        //Act
        piece.receiveAttacked ( 20 );
        healer.attack ( new ArrayList<> (), new Pair<> ( piece, 1 ) );

        //Assert
        assertEquals ( 95, piece.getLife () );
    }

    @Test
    void test11soldierReceiveHealWithOutDamageAndHisLifeCantUp () {
        //Assemble

        Piece piece = new Soldier ( this.team );
        Piece healer = new Healer ( this.team );

        //Act
        healer.attack ( new ArrayList<> (), new Pair<> ( piece, 1 ) );

        //Assert
        assertEquals ( 100, piece.getLife () );
    }

    @Test
    void test12soldierMakeDistanceAttackAndTheOtherPieceReceiveDamage () {
        //Assemble

        Team blue = new Team ( 2 );
        Piece piece = new Soldier ( this.team );
        Piece healer = new Healer ( blue );

        //Act - Assert
        try {
            piece.attack ( new ArrayList<> (), new Pair<> ( healer, 1 ) );
        } catch (CanNotMakeThatMoveException e) {
            assertThat ( e.getMessage (), containsString ( "Piece cannot move in that direction" ) );
        }
    }

}