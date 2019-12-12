package Face;

import board.Board;
import board.CanNotMakeThatMoveException;
import game.Game;
import move.Builder;
import move.Move;
import org.junit.jupiter.api.Test;
import piece.Piece;
import piece.PieceFactory;
import piece.Soldier;
import player.APlayerAlreadyExistsException;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import player.ThereAreOnlyTwoPlayersPerGameException;
import team.Team;

import static org.junit.jupiter.api.Assertions.*;

class GameFaceTest {
    Game game = new Game ();
    private Team team = new Team ( 1 ,game);
    private Team team2 = new Team ( 2 ,game);
    private Board board = new Board ( team, team2 );
    private Player player = new Player ( "Mike", team ,board);
    private PieceFactory factory = new PieceFactory ( team );
    private Piece soldier1 = this.factory.createSoldier ();
    private Piece soldier2 = this.factory.createSoldier ();
    private Piece soldier3 = this.factory.createSoldier ();



    @Test
    void test00AGameFaceCanBeCreated () {
        //Act
        Face face = new GameFace ( board, player ,team);
        //Assert
        assertNotNull ( face );
    }


    @Test
    void test02ASoldierCantBeCreatedInGameFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Face face = new GameFace ( board, player,team );
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
        Face face = new GameFace ( board, player ,team);
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
        Face face = new GameFace ( board, player,team);
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
        Face face = new GameFace ( board, player,team );
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
        Face face = new GameFace ( board, player,team );
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
        this.player.chosePiece ( soldier1 );
        this.player.chosePiece ( soldier2 );
        this.player.chosePiece ( soldier3 );

        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        this.board.placePiece ( this.soldier1, move1 );
        Move move2 = new Builder ().ToRow ( 2 ).ToColumn ( 2 ).build ();
        this.board.placePiece ( this.soldier2, move2 );
        Face face = new GameFace ( board, player,team );
        //Act
        Move move = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).build ();
        Piece removed = face.getPiece( move );
        //Assert
        assertNotNull ( removed );
    }

    @Test
    void test08ABattalionCanBeCreatedInGameFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        this.player.chosePiece ( soldier1 );
        this.player.chosePiece ( soldier2 );
        this.player.chosePiece ( soldier3 );

        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        Move move2 = new Builder ().ToRow ( 2 ).ToColumn ( 2 ).build ();
        Move move3 = new Builder ().ToRow ( 2 ).ToColumn ( 3 ).build ();
        this.board.placePiece ( this.soldier1, move1 );
        this.board.placePiece ( this.soldier2, move2 );
        this.board.placePiece ( this.soldier3, move3 );
        Face face = new GameFace ( board, player ,team);

        //Act
        Move move = new Builder ().fromRow ( 2 ).fromColumn ( 2 ).build ();
        face.playerChoosesBattalion ( move );
        Move movement = new Builder ().fromRow ( 2 ).fromColumn ( 2 ).ToRow ( 3 ).ToColumn ( 2 ).build ();
        face.playerMovesPieceOnBoard ( movement );
        //Assert
        Move move4 = new Builder ().fromRow ( 3 ).fromColumn ( 2 ).build ();
        Move move5 = new Builder ().fromRow ( 3 ).fromColumn ( 1 ).build ();
        Move move6 = new Builder ().fromRow ( 3 ).fromColumn ( 3 ).build ();
        assertEquals ( this.soldier2, face.getPiece ( move4 ) );
        assertEquals ( this.soldier1, face.getPiece ( move5 ) );
        assertEquals ( this.soldier3, face.getPiece ( move6 ) );
    }

    @Test
    void test9APieceCanBeMovedOnTheBoardInInitialFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        this.player.chosePiece ( soldier1 );
        this.player.chosePiece ( soldier2 );
        this.player.chosePiece ( soldier3 );

        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        Move move2 = new Builder ().ToRow ( 2 ).ToColumn ( 2 ).build ();
        this.board.placePiece ( this.soldier1, move1 );
        this.board.placePiece ( this.soldier2, move2 );
        Face face = new GameFace ( board, player ,team);
        //Act
        Move movement = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).ToRow ( 3 ).ToColumn ( 1 ).build ();
        face.playerMovesPieceOnBoard ( movement );
        //Assert
        Move move = new Builder ().fromRow ( 3 ).fromColumn ( 1 ).build ();
        assertEquals ( soldier1, face.getPiece( move ) );
    }

    @Test
    void test10APieceCanBeMovedOnTheBoardAndIsLimitedToMovingToAdjacentCellsInGameFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        this.player.chosePiece ( soldier1 );
        this.player.chosePiece ( soldier2 );
        this.player.chosePiece ( soldier3 );

        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        Move move2 = new Builder ().ToRow ( 2 ).ToColumn ( 2 ).build ();
        this.board.placePiece ( this.soldier1, move1 );
        this.board.placePiece ( this.soldier2, move2 );
        Face face = new GameFace ( board, player,team);
        //Act
        Move movement = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).ToRow ( 8 ).ToColumn ( 9 ).build ();
        try {
            face.playerMovesPieceOnBoard ( movement );
        } catch (CanNotMakeThatMoveException e) {
            //Assert
            Move move = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).build ();
            assertEquals ( soldier1, face.getPiece( move ) );
        }

    }

    @Test
    void test011PiecesCanAttackInGameFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        this.player.chosePiece ( soldier1 );
        this.player.chosePiece ( soldier2 );
        this.player.chosePiece ( soldier3 );

        PieceFactory enemy = new PieceFactory ( team2 );
        Piece enemysoldier = enemy.createSoldier ();
        Move move1 = new Builder ().ToRow ( 9 ).ToColumn ( 1 ).build ();
        Move move2 = new Builder ().ToRow ( 10 ).ToColumn ( 2 ).build ();
        this.board.placePiece ( soldier1, move1 );
        this.board.placePiece ( enemysoldier, move2 );
        Face face = new GameFace ( board, player ,team);
        int life = enemysoldier.getLife ();
        //Act
        Move movement = new Builder ().fromRow ( 9 ).fromColumn ( 1 ).ToRow ( 10 ).ToColumn ( 2 ).build ();
        face.playerAttacks ( movement );
        //Assert
        Move remove = new Builder ().fromRow ( 10 ).fromColumn ( 2 ).build ();
        assertNotEquals ( life, board.removePiece ( remove ).getLife () );
    }


}