package player;

import board.Board;
import move.Builder;
import move.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import piece.*;
import team.*;

class PlayerTest {
    //Assemble
    private Team team = new Team ( 1 );
    private Board board = new Board ( team, new Team ( 2 ) );

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
        assertEquals ( "Pepe", player.name () );
    }

    //Point tests.

    @Test
    void test03Subtracting20PointsFromPlayerLeavesPlayerWithPoints () {
        //Assemble
        Player player = new Player ( "Archie", team );
        //Act
        player.chooseCatapult ();
        player.chooseCatapult ();
        player.chooseCatapult ();
        player.chooseCatapult ();
        //Assert
        assertTrue ( player.isNumberOfPiecesOnTeam ( 4 ) );
    }

    @Test
    void test04Subtracting30PointsFromPlayerRaisesError () {
        //Assemble
        Player player = new Player ( "Player0003", team );
        player.chooseCatapult ();
        player.chooseCatapult ();
        player.chooseCatapult ();
        player.chooseCatapult ();
        //Act
        try {
            player.chooseCatapult ();
            player.chooseCatapult ();
        }
        //Assert
        catch ( PlayerHas20PointsOnlyException e ) {
            assertTrue ( player.isNumberOfPiecesOnTeam ( 4 ) );
        }
    }

    //Pieces tests.
    @Test
    void test05PlayerCanChooseASoldier () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );

        //Act
        Piece piece = player.chooseSoldier ();

        //Assert
        assertEquals ( Soldier.class, piece.getClass () );
    }

    @Test
    void test06PlayerCanChooseACatapult () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );

        //Act
        Piece piece = player.chooseCatapult ();

        //Assert
        assertEquals ( Catapult.class, piece.getClass () );
    }

    @Test
    void test7layerCanChooseARider () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );

        //Act
        Piece piece = player.chooseRider ();

        //Assert
        assertEquals ( Rider.class, piece.getClass () );
    }

    @Test
    void test8PlayerCanChooseAHealer () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );

        //Act
        Piece piece = player.chooseHealer ();

        //Assert
        assertEquals ( Healer.class, piece.getClass () );
    }


    //Board tests.
    @Test
    void test9PlayerCanPlaceAPieceOnTheBoard () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        Piece piece = player.chooseHealer ();
        Move move = new Builder ().ToRow ( 2 ).ToColumn ( 0 ).build ();

        //Act
        player.placePieceOnBoard ( piece, board, move );

        //Assert
        assertEquals ( piece, board.removePiece ( move ) );
    }

    @Test
    void test10PlayerCanMoveAPieceOnTheBoard () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        Piece piece = player.chooseHealer ();
        Move move = new Builder ().ToRow ( 2 ).ToColumn ( 0 ).build ();
        player.placePieceOnBoard ( piece, board, move );
        //Act
        Move move2 = new Builder ().fromRow ( 2 ).fromColumn ( 0 ).ToRow ( 2 ).ToColumn ( 1 ).build ();
        player.movePiece ( board, move2 );

        //Assert
        Move move3 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        assertEquals ( piece, board.removePiece ( move3 ) );
    }

    // Tests related to teams.

    @Test
    void test11PlayerHasRemovedAPiece () throws PlayerHas20PointsOnlyException, NoMembersLeftException {
        //Assemble
        Player player = new Player ( "Player0003", team );
        Piece piece = player.chooseHealer ();
        Move move = new Builder ().ToRow ( 2 ).ToColumn ( 0 ).build ();
        player.placePieceOnBoard ( piece, board, move );
        //Act
        player.removePieceFromTeam ();
        //Assert
        assertEquals ( 0, player.numberOfPiecesOnTeam () );
    }
}