package team;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void test00CanCreateATeam () {
        //Assemble & Act
        Team team = new Team (2444);
        //Assert
        assertNotNull ( team );
    }

    @Test
    void test01CanCreatTwoDifferentTeams () {
        //Assemble & Act
        Team team1 = new Team (4545);
        Team team2 = new Team (2333);
        //Assert
        assertFalse ( team1.equals ( team2 ) );
    }

    @Test
    void test02PiecesCanBeAddedToTeamAndTheNumberOfMembersWillIncrease () {
        //Assemble
        Team team = new Team(8);
        //Act
        team.addPieceToTeam ();
        team.addPieceToTeam ();
        //Assert
        assertEquals ( 2, team.numberOfMembersStillOnTeam () );
    }

    @Test
    void test03PiecesCanBeRemovedFromTeam () throws NoMembersLeftException {
        //Assemble
        Team team = new Team (2);

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
        Team team = new Team(2);

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