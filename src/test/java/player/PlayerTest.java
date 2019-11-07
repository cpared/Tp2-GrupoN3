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
    void test01NewPlayerHas20Points () {
        //Act
        Player player = new Player ( "Mike", team );
        //Assert
        assertEquals ( 20, player.obtainPoints () );
    }

    @Test
    void test02NewPlayerWithNamePepeIsNamedPepe () {
        //Act
        Player player = new Player ( "Pepe", team );
        //Assert
        assertEquals ( "Pepe", player.obtainName () );
    }

    //Point tests.
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

    //Pieces tests.
    @Test
    void test06PlayerCanChooseASoldier () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );

        //Act
        Piece piece = player.choosePiece ( "SOLDIER" );

        //Assert
        assertEquals ( Soldier.class, piece.getClass () );
    }

    @Test
    void test07PlayerChoosingASoldierSubtracts1PointFromPlayer () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        //Act
        player.choosePiece ( "SOLDIER" );

        //Assert
        assertEquals ( 19, player.obtainPoints () );
    }

    @Test
    void test08PlayerCanChooseACatapult () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );

        //Act
        Piece piece = player.choosePiece ( "CATAPULT" );

        //Assert
        assertEquals ( Catapult.class, piece.getClass () );
    }

    @Test
    void test09PlayerChoosingACatapultSubtracts5PointFromPlayer () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        //Act
        player.choosePiece ( "CATAPULT" );

        //Assert
        assertEquals ( 15, player.obtainPoints () );
    }

    @Test
    void test10PlayerCanChooseARider () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );

        //Act
        Piece piece = player.choosePiece ( "RIDER" );

        //Assert
        assertEquals ( Rider.class, piece.getClass () );
    }

    @Test
    void test11PlayerChoosingARiderSubtracts3PointFromPlayer () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        //Act
        player.choosePiece ( "RIDER" );

        //Assert
        assertEquals ( 17, player.obtainPoints () );
    }

    @Test
    void test12PlayerCanChooseAHealer () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );

        //Act
        Piece piece = player.choosePiece ( "HEALER" );

        //Assert
        assertEquals ( Healer.class, piece.getClass () );
    }

    @Test
    void test13PlayerChoosingAHealerSubtracts3PointFromPlayer () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        //Act
        player.choosePiece ( "HEALER" );

        //Assert
        assertEquals ( 18, player.obtainPoints () );
    }

    //Board tests.
    @Test
    void test14PlayerCanPlaceAPieceOnTheBoard () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        Board board = new Board ();
        Piece piece = player.choosePiece ( "HEALER" );

        //Act
        player.placePieceOnBoard ( piece, board, 11,0 );

        //Assert
        assertEquals ( piece, board.removePiece ( 11, 0 ) );
    }

    @Test
    void test15PlayerCanPlaceAPieceOnTheBoardAndMaintainsPoints () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        Board board = new Board ();
        Piece piece = player.choosePiece ( "HEALER" );
        //Act
        player.placePieceOnBoard ( piece, board, 11,0 );

        //Assert
        assertEquals ( 18, player.obtainPoints () );
    }

    @Test
    void test16PlayerCanMoveAPieceOnTheBoard () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        Board board = new Board ();
        Piece piece = player.choosePiece ( "HEALER" );
        player.placePieceOnBoard ( piece, board, 11,0 );
        //Act
        player.movePiece ( board, 11, 0, 11, 1 );

        //Assert
        assertEquals ( piece, board.removePiece ( 11, 1 ) );
    }

    // Tests related to teams.
    @Test
    void test17PlayerHasAnAssignedTeam () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        //Act
        Team assignedTeam = player.getTeam ();

        //Assert
        assertEquals ( Blue.class, assignedTeam.getClass () );
    }

    @Test
    void test18PlayerHasRemovedAPiece () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        Piece piece = player.choosePiece ( "HEALER" );
        Board board = new Board ();
        player.placePieceOnBoard ( piece, board, 11,0 );
        //Act
        player.removePieceFromTeam ();
        //Assert
        assertEquals ( 0, player.getTeam ().numberOfMembersStillOnTeam () );
    }
}