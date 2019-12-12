package player;

import board.Board;
import game.Game;
import move.Builder;
import move.Move;
import org.junit.jupiter.api.Test;
import piece.Piece;
import piece.PieceFactory;

import team.PieceDoesNotBelongToTeamException;
import team.Team;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    //Assemble
    private Team team = new Team ( 1 , new Game());
    private Board board = new Board ( team, new Team ( 2 , new Game ()) );
    private PieceFactory factory = new PieceFactory ( this.team );

    //Initiation tests.

    @Test
    void test01NewPlayerIsValid () {
        //Act
        Player player = new Player ( "Mike", team, board );
        //Assert
        assertNotNull ( player );
    }

    @Test
    void test02NewPlayerWithNamePepeIsNamedPepe () {
        //Act
        Player player = new Player ( "Pepe", team, board );
        //Assert
        assertEquals ( "Pepe", player.name () );
    }

    //Point tests.

    @Test
    void test03Subtracting20PointsFromPlayerLeavesPlayerWithPoints () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Archie", team, board );
        //Act
        Piece piece1 = this.factory.createCatapult ();
        Piece piece2 = this.factory.createCatapult ();
        Piece piece3 = this.factory.createCatapult ();
        Piece piece4 = this.factory.createCatapult ();

        player.chosePiece ( piece1 );
        player.chosePiece ( piece2 );
        player.chosePiece ( piece3 );
        player.chosePiece ( piece4 );
        //Assert
        assertTrue ( player.isNumberOfPiecesOnTeam ( 4 ) );
    }

    @Test
    void test04Subtracting30PointsFromPlayerRaisesError () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team, board );
        Piece piece1 = this.factory.createCatapult ();
        Piece piece2 = this.factory.createCatapult ();
        Piece piece3 = this.factory.createCatapult ();
        Piece piece4 = this.factory.createCatapult ();

        player.chosePiece ( piece1 );
        player.chosePiece ( piece2 );
        player.chosePiece ( piece3 );
        player.chosePiece ( piece4 );
        //Act
        try {
            Piece piece5 = this.factory.createCatapult ();
            player.chosePiece ( piece5 );
        }
        //Assert
        catch (PlayerHas20PointsOnlyException e) {
            assertTrue ( player.isNumberOfPiecesOnTeam ( 4 ) );
        }
    }

    //Pieces tests.
    @Test
    void test05PlayerCanChooseASoldier () throws PlayerHas20PointsOnlyException, PieceDoesNotBelongToTeamException {
        //Assemble
        Player player = new Player ( "Player0003", team, board );
        Piece piece = this.factory.createSoldier ();
        Piece piece2 = this.factory.createSoldier ();


        //Act
        player.chosePiece ( piece );
        player.chosePiece ( piece2 );

        //Assert
        assertEquals ( piece, player.removePieceFromTeam ( piece ) );
    }

    @Test
    void test06PlayerCanChooseACatapult () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team, board );
        Piece piece = this.factory.createCatapult ();
        Piece piece2 = this.factory.createSoldier ();
        player.chosePiece ( piece2 );
        //Act
        player.chosePiece ( piece );

        //Assert
        assertEquals ( piece, player.removePieceFromTeam ( piece ) );
    }

    @Test
    void test7layerCanChooseARider () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team, board );
        Piece piece = this.factory.createRider ();
        Piece piece2 = this.factory.createSoldier ();
        player.chosePiece ( piece2 );
        //Act
        player.chosePiece ( piece );

        //Assert
        assertEquals ( piece, player.removePieceFromTeam ( piece ) );
    }

    @Test
    void test8PlayerCanChooseAHealer () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team, board );
        Piece piece = this.factory.createHealer ();
        Piece piece2 = this.factory.createSoldier ();
        player.chosePiece ( piece2 );
        //Act
        player.chosePiece ( piece );

        //Assert
        assertEquals ( piece, player.removePieceFromTeam ( piece ) );
    }


    //Board tests.
    @Test
    void test9PlayerCanPlaceAPieceOnTheBoard () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team, board );
        Piece piece = this.factory.createHealer ();
        player.chosePiece ( piece );
        Move move = new Builder ().ToRow ( 2 ).ToColumn ( 0 ).build ();

        //Act
        player.placePieceOnBoard ( piece, move );

        //Assert
        Move move2 = new Builder ().fromRow ( 2 ).fromColumn ( 0 ).build ();
        assertEquals ( piece, board.removePiece ( move2 ) );
    }

    @Test
    void test10PlayerCanMoveAPieceOnTheBoard () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player player = new Player ( "Player0003", team, board );
        Piece piece = this.factory.createHealer ();
        player.chosePiece ( piece );
        Move move = new Builder ().ToRow ( 2 ).ToColumn ( 0 ).build ();
        player.placePieceOnBoard ( piece, move );
        //Act
        Move move2 = new Builder ().fromRow ( 2 ).fromColumn ( 0 ).ToRow ( 2 ).ToColumn ( 1 ).build ();
        player.movePiece ( move2 );

        //Assert
        Move move3 = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).build ();
        assertEquals ( piece, board.removePiece ( move3 ) );
    }

    // Tests related to teams.

    @Test
    void test11PlayerHasRemovedAPiece () throws PlayerHas20PointsOnlyException, PieceDoesNotBelongToTeamException {
        //Assemble
        Player player = new Player ( "Player0003", team, board );
        Piece piece = this.factory.createHealer ();
        Piece piece2 = this.factory.createSoldier ();
        player.chosePiece ( piece2 );
        player.chosePiece ( piece );
        Move move = new Builder ().ToRow ( 2 ).ToColumn ( 0 ).build ();
        player.placePieceOnBoard ( piece, move );
        //Act
        player.removePieceFromTeam ( piece );
        //Assert
        assertTrue ( player.isNumberOfPiecesOnTeam ( 1 ) );
    }
}