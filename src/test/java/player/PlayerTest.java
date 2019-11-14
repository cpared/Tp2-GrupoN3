package player;

import board.Board;
import board.OccupiedCellException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

import piece.*;
import team.*;

class PlayerTest {
    //Assemble
    private Team team = new Blue ();

    //Initiation tests.

    @Test
    void test01NewPlayerIsValid () {
        //Act
        Player player = new Player ( "Mike", team );
        //Assert
        assertNotNull ( player );
    }

    @Test
    void test02NewPlayerWithNamePepeIsNamedPepe () {
        //Act
        Player player = new Player ( "Pepe", team );
        //Assert
        assertEquals ( "Pepe", player.getName () );
    }

    //Point tests.
    /*
    @Test
    void test03Subtracting8PointsFromPlayerLeavesPlayerWith12Points () {
        //Act
        Player player = new Player ( "Lola", team );
        try {
            player.subtractPoints ( 8 );
        } catch (PlayerHas20PointsOnlyException e) {
            e.getMessage ();
        }
        //Assert
        assertEquals ( 12, player.obtainPoints () );
    }

    @Test
    void test04Subtracting20PointsFromPlayerLeavesPlayerWith0Points () {
        //Assemble
        Player player = new Player ( "Archie", team );
        //Act
        try {
            player.subtractPoints ( 2 );
            player.subtractPoints ( 18 );
        } catch (PlayerHas20PointsOnlyException e) {
            e.getMessage ();
        }
        //Assert
        assertEquals ( 0, player.obtainPoints () );
    }

    @Test
    void test05Subtracting30PointsFromPlayerRaisesError () {
        //Assemble
        Player player = new Player ( "Player0003", team );
        //Act
        try {
            player.subtractPoints ( 30 );
            fail ();
        }
        //Assert
        catch ( PlayerHas20PointsOnlyException e ) {
            assertEquals ( 20, player.obtainPoints () );
        }
    }
    */


    //Pieces tests.
    @Test
    void test06PlayerCanChooseASoldier () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );

        //Act
        Piece piece = player.chooseSoldier ( );

        //Assert
        assertEquals ( Soldier.class, piece.getClass () );
    }

    @Test
    void test07PlayerCanChooseACatapult () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );

        //Act
        Piece piece = player.chooseCatapult ( );

        //Assert
        assertEquals ( Catapult.class, piece.getClass () );
    }

    @Test
    void test8PlayerCanChooseARider () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );

        //Act
        Piece piece = player.chooseRider ( );

        //Assert
        assertEquals ( Rider.class, piece.getClass () );
    }

    @Test
    void test9PlayerCanChooseAHealer () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );

        //Act
        Piece piece = player.chooseHealer ( );

        //Assert
        assertEquals ( Healer.class, piece.getClass () );
    }



    //Board tests.
    @Test
    void test10PlayerCanPlaceAPieceOnTheBoard () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        Board board = new Board ();
        Piece piece = player.chooseHealer ( );

        //Act
        player.placePieceOnBoard ( piece, board, 11,0 );

        //Assert
        assertEquals ( piece, board.removePiece ( 11, 0 ) );
    }

    @Test
    void test11PlayerCanMoveAPieceOnTheBoard () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        Board board = new Board ();
        Piece piece = player.chooseHealer ( );
        player.placePieceOnBoard ( piece, board, 11,0 );
        //Act
        player.movePiece ( board, 11, 0, 11, 1 );

        //Assert
        assertEquals ( piece, board.removePiece ( 11, 1 ) );
    }

    // Tests related to teams.
    @Test
    void test12PlayerHasAnAssignedTeam () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        //Act
        Team assignedTeam = player.getTeam ();

        //Assert
        assertEquals ( Blue.class, assignedTeam.getClass () );
    }

    @Test
    void test13PlayerHasRemovedAPiece () throws PlayerHas20PointsOnlyException, NoMembersLeftException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        Piece piece = player.chooseHealer ( );
        Board board = new Board ();
        player.placePieceOnBoard ( piece, board, 11,0 );
        //Act
        player.removePieceFromTeam ();
        //Assert
        assertEquals ( 0, player.getTeam ().numberOfMembersStillOnTeam () );
    }
}