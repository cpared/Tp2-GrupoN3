package Face;

import board.Board;
import move.Builder;
import move.Move;
import org.junit.jupiter.api.Test;
import piece.Piece;
import player.APlayerAlreadyExistsException;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import team.Team;

import static org.junit.jupiter.api.Assertions.*;

class InitialFaceTest {
    Team team = new Team ( 1 );
    Team team2 = new Team ( 2 );

    @Test
    void test00AnInitialFaceCanBeCreated () {
        //Act
        Face initial = new InitialFace ( new Board ( this.team, this.team2 ), this.team );
        //Assert
        assertNotNull ( initial );
    }


    @Test
    void test03ASoldierCanBeCreatedInInitialFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Team team = new Team ( 1 );
        Face initial = new InitialFace ( new Board ( this.team, this.team2 ), this.team );
        //Act
        Piece piece = initial.playerChoosesSoldier ();
        //Assert
        assertTrue ( piece.isCost ( 1 ) );
    }

    @Test
    void test04ARiderCanBeCreatedInInitialFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Team team = new Team ( 1 );
        Face initial = new InitialFace ( new Board ( this.team, this.team2 ), this.team );
        //Act
        Piece piece = initial.playerChoosesRider ();
        //Assert
        assertTrue ( piece.isCost ( 3 ) );
    }

    @Test
    void test05AHealerCanBeCreatedInInitialFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Team team = new Team ( 1 );
        Face initial = new InitialFace ( new Board ( this.team, this.team2 ), this.team );

        //Act
        Piece piece = initial.playerChoosesHealer ();
        //Assert
        assertTrue ( piece.isCost ( 2 ) );
    }

    @Test
    void test06ACatapultCanBeCreatedInInitialFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Team team = new Team ( 1 );
        Face initial = new InitialFace ( new Board ( this.team, this.team2 ), this.team );
        //Act
        Piece piece = initial.playerChoosesCatapult ();
        //Assert
        assertTrue ( piece.isCost ( 5 ) );
    }

    @Test
    void test07APieceCanBePlacedOnTheBoardInInitialFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Team team = new Team ( 1 );
        Face initial = new InitialFace ( new Board ( this.team, this.team2 ), this.team );
        Piece piece = initial.playerChoosesCatapult ();
        //Act
        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        initial.playerPlacesPieceOnBoard ( piece, move1 );
        //Assert
        Move move = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).build ();
        assertEquals ( piece, initial.getPiece( move ) );
    }

    @Test
    void test08APieceCanBeRemovedFromTheBoardInInitialFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Team team = new Team ( 1 );
        Face initial = new InitialFace ( new Board ( this.team, this.team2 ), this.team );
        Piece piece = initial.playerChoosesCatapult ();
        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        initial.playerPlacesPieceOnBoard ( piece, move1 );
        //Act
        Move move = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).build ();
        Piece removed = initial.getPiece( move );
        //Assert
        assertNotNull ( removed );
    }

    @Test
    void test09ABattalionCantBeCreatedInInitialFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Team team = new Team ( 1 );
        Face initial = new InitialFace ( new Board ( this.team, this.team2 ), this.team );
        Piece soldier1 = initial.playerChoosesSoldier ();
        Piece soldier2 = initial.playerChoosesSoldier ();
        Piece soldier3 = initial.playerChoosesSoldier ();
        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        Move move2 = new Builder ().ToRow ( 2 ).ToColumn ( 2 ).build ();
        Move move3 = new Builder ().ToRow ( 2 ).ToColumn ( 3 ).build ();
        initial.playerPlacesPieceOnBoard ( soldier1, move1 );
        initial.playerPlacesPieceOnBoard ( soldier2, move2 );
        initial.playerPlacesPieceOnBoard ( soldier3, move3 );

        //Act
        Move move = new Builder ().fromRow ( 2 ).fromColumn ( 2 ).build ();
        initial.playerChoosesBattalion ( move );
        Move movement = new Builder ().fromRow ( 2 ).fromColumn ( 2 ).ToRow ( 3 ).ToColumn ( 2 ).build ();
        initial.playerMovesPieceOnBoard ( movement );
        //Assert
        Move move4 = new Builder ().fromRow ( 3 ).fromColumn ( 2 ).build ();
        Move move5 = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).build ();
        Move move6 = new Builder ().fromRow ( 2 ).fromColumn ( 3 ).build ();
        assertEquals ( soldier2, initial.getPiece( move4 ) );
        assertEquals ( soldier1, initial.getPiece( move5 ) );
        assertEquals ( soldier3, initial.getPiece( move6 ) );
    }

    @Test
    void test010PiecesCantBeAttackInInitialFace () throws PlayerHas20PointsOnlyException {
        //Assemble
        Team team = new Team ( 1 );
        Face initial = new InitialFace ( new Board ( this.team, this.team2 ), this.team );
        Piece soldier1 = initial.playerChoosesSoldier ();
        Piece soldier2 = initial.playerChoosesSoldier ();
        Piece soldier3 = initial.playerChoosesSoldier ();
        Move move1 = new Builder ().ToRow ( 2 ).ToColumn ( 1 ).build ();
        Move move2 = new Builder ().ToRow ( 2 ).ToColumn ( 2 ).build ();
        Move move3 = new Builder ().ToRow ( 2 ).ToColumn ( 3 ).build ();
        initial.playerPlacesPieceOnBoard ( soldier1, move1 );
        initial.playerPlacesPieceOnBoard ( soldier2, move2 );
        initial.playerPlacesPieceOnBoard ( soldier3, move3 );
        int life = soldier1.getLife ();
        //Act
        Move movement = new Builder ().fromRow ( 2 ).fromColumn ( 2 ).ToRow ( 2 ).ToColumn ( 1 ).build ();
        initial.playerAttacks ( movement );
        //Assert
        Move remove = new Builder ().fromRow ( 2 ).fromColumn ( 1 ).build ();
        assertEquals ( life, initial.removeDeadPieceFromBoard( remove ).getLife () );
    }
}