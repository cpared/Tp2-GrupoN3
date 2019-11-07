package team;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void test00CanCreateATeam () {
        //Assemble & Act
        Team team = new Team ();
        //Assert
        assertNotNull ( team );
    }

    @Test
    void test01CanCreateABlueTeam () {
        //Assemble & Act
        Team team = new Blue ();
        //Assert
        assertNotNull ( team );
    }

    @Test
    void test02CanCreateAGoldTeam () {
        //Assemble & Act
        Team team = new Gold ();
        //Assert
        assertNotNull ( team );
    }

    @Test
    void test03PiecesCanBeAddedToTeamAndTheNumberOfMembersWillIncrease () {
        //Assemble
        Team team = new Gold();
        //Act
        team.addPieceToTeam ();
        team.addPieceToTeam ();
        //Assert
        assertEquals ( 2, team.numberOfMembersStillOnTeam () );
    }

    @Test
    void test04PiecesCanBeRemovedFromTeam () throws NoMembersLeftException {
        //Assemble
        Team team = new Gold();

        team.addPieceToTeam ();
        team.addPieceToTeam ();
        team.addPieceToTeam ();
        team.addPieceToTeam ();

        //Act
        team.subtractPieceFromTeam ();
        team.subtractPieceFromTeam ();
        //Assert
        assertEquals ( 2, team.numberOfMembersStillOnTeam () );
    }

    @Test
    void test05RemoveMorePiecesThanWhatYouCanRemoveRaisesError () {
        //Assemble
        Team team = new Gold();

        team.addPieceToTeam ();

        //Act
        try{
            team.subtractPieceFromTeam ();
            team.subtractPieceFromTeam ();
            fail();
            //Assert
        } catch (NoMembersLeftException e) {
            assertEquals ( 0, team.numberOfMembersStillOnTeam () );
        }
    }
}