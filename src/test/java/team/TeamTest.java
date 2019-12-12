package team;

import game.Game;
import org.junit.jupiter.api.Test;
import piece.Piece;
import piece.Soldier;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void test00CanCreateATeam () {
        //Assemble & Act
        Team team = new Team (2444, new Game ());
        //Assert
        assertNotNull ( team );
    }

    @Test
    void test01CanCreatTwoDifferentTeams () {
        //Assemble & Act
        Team team1 = new Team (4545, new Game ());
        Team team2 = new Team (2333, new Game ());
        //Assert
        assertFalse ( team1.equals ( team2 ) );
    }

    @Test
    void test02PiecesCanBeAddedToTeamAndTheNumberOfMembersWillIncrease () {
        //Assemble
        Team team = new Team(8, new Game ());
        Piece piece = new Soldier (team);
        Piece piece2 = new Soldier (team);
        //Act
        team.addPieceToTeam (piece);
        team.addPieceToTeam (piece2);
        //Assert
        assertTrue ( team.isNumberOfMembersStillOnTeam (2) );
    }

    @Test
    void test03PiecesCanBeRemovedFromTeam () {
        //Assemble
        Team team = new Team (2, new Game ());
        Piece piece = new Soldier (team);
        Piece piece2 = new Soldier (team);
        Piece piece3 = new Soldier (team);
        Piece piece4 = new Soldier (team);
        team.addPieceToTeam (piece);
        team.addPieceToTeam (piece2);
        team.addPieceToTeam (piece3);
        team.addPieceToTeam (piece4);

        //Act
        team.subtractPieceFromTeam (piece);
        team.subtractPieceFromTeam (piece2);
        //Assert
        assertTrue (  team.isNumberOfMembersStillOnTeam (2) );
    }

    @Test
    void test04PiecesCanBeRemovedFromTeamAndRemovingThemReturnsTheExpectedPieces () {
        //Assemble
        Team team = new Team (2, new Game ());
        Piece piece = new Soldier (team);
        Piece piece2 = new Soldier (team);
        Piece piece3 = new Soldier (team);
        Piece piece4 = new Soldier (team);
        team.addPieceToTeam (piece);
        team.addPieceToTeam (piece2);
        team.addPieceToTeam (piece3);
        team.addPieceToTeam (piece4);

        //Act
        Piece removed = team.subtractPieceFromTeam (piece);
        Piece removed2 = team.subtractPieceFromTeam (piece2);
        //Assert
        assertEquals ( piece, removed );
        assertEquals ( piece2, removed2 );
    }

    @Test
    void test05RemoveMorePiecesThanWhatYouCanRemoveRaisesError () {
        //Assemble
        Team team = new Team(2, new Game ());
        Piece piece = new Soldier (team);
        team.addPieceToTeam (piece);

        //Act
        try{
            team.subtractPieceFromTeam (piece);
            team.subtractPieceFromTeam (piece);
            //Assert
        } catch (PieceDoesNotBelongToTeamException e) {
            assertTrue (  team.isNumberOfMembersStillOnTeam (0) );
        }
    }
}