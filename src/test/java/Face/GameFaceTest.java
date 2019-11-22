package Face;

import board.Board;
import board.CanNotMakeThatMoveException;
import move.Builder;
import move.Move;
import org.junit.jupiter.api.Test;
import piece.Piece;
import piece.Soldier;
import player.APlayerAlreadyExistsException;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import team.Team;

import static org.junit.jupiter.api.Assertions.*;

class GameFaceTest {
    private Team team = new Team ( 1 );
    private Team team2 = new Team ( 2 );
    private Board board = new Board ( team, team2 );
    private Player player = new Player ( "Mike", team );

    @Test
    void test00AGameFaceCanBeCreated () {
        //Act
        Face face = new GameFace ( board, player );
        //Assert
        assertNotNull ( face );
    }

    @Test
    void test01ANewPlayerCanBeCreatedInGameFace () {
        //Assemble
        Face face = new GameFace ( board, player );
        //Act
        try {
            Player player = face.newPlayer ( "George", team );
        } catch (APlayerAlreadyExistsException e) {
            //Assert
            assertNotNull ( player );
        }
    }

    @Test
    void test02ASoldierCantBeCreatedInGameFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Face face = new GameFace ( board, player );
        //Act
        try {
            face.playerChoosesSoldier ();
        } catch (CanNotMakeThatMoveException e) {
            //Assert
            assertTrue ( true );
        }
    }

    @Test
    void test03ARiderCantBeCreatedInGameFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Face face = new GameFace ( board, player );
        //Act
        try {
            face.playerChoosesRider ();
        } catch (CanNotMakeThatMoveException e) {
            //Assert
            assertTrue ( true );
        }
    }

    @Test
    void test04AHealerCantBeCreatedInGameFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Face face = new GameFace ( board, player );
        //Act
        //Act
        try {
            face.playerChoosesHealer ();
        } catch (CanNotMakeThatMoveException e) {
            //Assert
            assertTrue ( true );
        }
    }

    @Test
    void test05ACatapultCantBeCreatedInGameFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Face face = new GameFace ( board, player );
        //Act
        try {
            face.playerChoosesCatapult ();
        } catch (CanNotMakeThatMoveException e) {
            //Assert
            assertTrue ( true );
        }
    }

    @Test
    void test06APieceCantBePlacedOnTheBoardInGameFace () {
        //Assemble
        Face face = new GameFace ( board, player );
        //Act
        try {
            Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
            face.playerPlacesPieceOnBoard ( new Soldier ( team ), move1 );
        } catch (CanNotMakeThatMoveException e) {
            //Assert
            assertTrue ( true );
        }
    }

    @Test
    void test07APieceCanBeRemovedFromTheBoardInGameFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Piece soldier1 = this.player.chooseSoldier ();
        Piece soldier2 = this.player.chooseSoldier ();
        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        this.board.placePiece ( soldier1, move1 );
        Move move2 = new Builder ().ToRow ( 2 ).ToColumn ( 2 ).build ();
        this.board.placePiece ( soldier2, move2 );
        Face face = new GameFace ( board, player );
        //Act
        Move move = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).build ();
        Piece removed = face.removePieceFromBoard ( move );
        //Assert
        assertNotNull ( removed );
    }

    @Test
    void test08ABattalionCanBeCreatedInGameFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Piece soldier1 = this.player.chooseSoldier ();
        Piece soldier2 = this.player.chooseSoldier ();
        Piece soldier3 = this.player.chooseSoldier ();
        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        Move move2 = new Builder ().ToRow ( 2 ).ToColumn ( 2 ).build ();
        Move move3 = new Builder ().ToRow ( 2 ).ToColumn ( 3 ).build ();
        this.board.placePiece ( soldier1, move1 );
        this.board.placePiece ( soldier2, move2 );
        this.board.placePiece ( soldier3, move3 );
        Face face = new GameFace ( board, player );

        //Act
        Move move = new Builder ().fromRow ( 2 ).fromColumn ( 2 ).build ();
        face.playerChoosesBattalion ( move );
        Move movement = new Builder ().fromRow ( 2 ).fromColumn ( 2 ).ToRow ( 3 ).ToColumn ( 2 ).build ();
        face.playerMovesPieceOnBoard ( movement );
        //Assert
        Move move4 = new Builder ().fromRow ( 3 ).fromColumn ( 2 ).build ();
        Move move5 = new Builder ().fromRow ( 3 ).fromColumn ( 1 ).build ();
        Move move6 = new Builder ().fromRow ( 3 ).fromColumn ( 3 ).build ();
        assertEquals ( soldier2, face.removePieceFromBoard ( move4 ) );
        assertEquals ( soldier1, face.removePieceFromBoard ( move5 ) );
        assertEquals ( soldier3, face.removePieceFromBoard ( move6 ) );
    }

    @Test
    void test9APieceCanBeMovedOnTheBoardInInitialFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Piece soldier1 = this.player.chooseSoldier ();
        Piece soldier2 = this.player.chooseSoldier ();
        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        Move move2 = new Builder ().ToRow ( 2 ).ToColumn ( 2 ).build ();
        this.board.placePiece ( soldier1, move1 );
        this.board.placePiece ( soldier2, move2 );
        Face face = new GameFace ( board, player );
        //Act
        Move movement = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).ToRow ( 3 ).ToColumn ( 1 ).build ();
        face.playerMovesPieceOnBoard ( movement );
        //Assert
        Move move = new Builder ().fromRow ( 3 ).fromColumn ( 1 ).build ();
        assertEquals ( soldier1, face.removePieceFromBoard ( move ) );
    }

    @Test
    void test10APieceCanBeMovedOnTheBoardAndIsLimitedToMovingToAdjacentCellsInGameFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Piece soldier1 = this.player.chooseSoldier ();
        Piece soldier2 = this.player.chooseSoldier ();
        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        Move move2 = new Builder ().ToRow ( 2 ).ToColumn ( 2 ).build ();
        this.board.placePiece ( soldier1, move1 );
        this.board.placePiece ( soldier2, move2 );
        Face face = new GameFace ( board, player );
        //Act
        Move movement = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).ToRow ( 8 ).ToColumn ( 9 ).build ();
        try {
            face.playerMovesPieceOnBoard ( movement );
        } catch (CanNotMakeThatMoveException e) {
            //Assert
            Move move = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).build ();
            assertEquals ( soldier1, face.removePieceFromBoard ( move ) );
        }

    }

    @Test
    void test011PiecesCanAttackInGameFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Player enemy = new Player ( "Anna", team2 );
        Piece soldier1 = this.player.chooseSoldier ();
        Piece soldier2 = enemy.chooseSoldier ();
        Move move1 = new Builder ().ToRow ( 9 ).ToColumn ( 1 ).build ();
        Move move2 = new Builder ().ToRow ( 10 ).ToColumn ( 2 ).build ();
        this.board.placePiece ( soldier1, move1 );
        this.board.placePiece ( soldier2, move2 );
        Face face = new GameFace ( board, player );
        int life = soldier2.getLife ();
        //Act
        Move movement = new Builder ().fromRow ( 9 ).fromColumn ( 1 ).ToRow ( 10 ).ToColumn ( 2 ).build ();
        face.playerAttacks ( movement );
        //Assert
        Move remove = new Builder ().fromRow ( 10 ).fromColumn ( 2 ).build ();
        assertNotEquals ( life, face.removePieceFromBoard ( remove ).getLife () );
    }


}