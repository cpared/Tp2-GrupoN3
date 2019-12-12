package game;
import move.Builder;
import move.Move;
import org.junit.jupiter.api.Test;
import piece.Piece;
import piece.Soldier;
import player.Player;
import team.Team;

import static org.junit.jupiter.api.Assertions.*;

class EndedTest {
    private Team team = new Team(1, new Game ());

    @Test
    void test00AnEndedStateCanBeCreated () {
        //Act
        GameState state = new Ended ();
        //Assert
        assertNotNull ( state );
    }

    @Test
    void test01WhileEndedPiecesCantBeMoved () {
        //Assemble
        GameState state = new Ended ();

        try {
            state.playerMovesPieceOnBoard ( new Player ( "DUDE", new Team (1, new Game ()) , state.getBoard ()) , new Builder ().fromRow ( 2 ).fromColumn ( 2 ).ToRow ( 3 ).ToColumn ( 2 ).build () );
        } catch (GameHasEndedException e ){
            //Assert
            assertTrue ( true );
        }
    }


    @Test
    void test02WhileEndedPiecesCantBeRemoved () {
        //Assemble
        GameState state = new Ended ();
        Piece removed = null;
        try {
            removed = state.removePieceFromBoard ( new Player ( "DUDE", new Team (1, new Game ()), state.getBoard () ) , new Builder ().fromRow ( 2 ).fromColumn ( 2 ).ToRow ( 3 ).ToColumn ( 2 ).build () );
        } catch (GameHasEndedException e ){
            //Assert
            assertNull( removed );
        }
    }


    @Test
    void test03WhileEndedPiecesCantBePlaced () {
        //Assemble
        GameState state = new Ended ();

        try {
           state.playerPlacesPieceOnBoard ( new Player ( "DUDE", new Team (1, new Game ()) , state.getBoard ()) , new Soldier ( new Team (1, new Game ()) ), new Builder ().fromRow ( 2 ).fromColumn ( 2 ).ToRow ( 3 ).ToColumn ( 2 ).build () );
        } catch (GameHasEndedException e ){
            //Assert
            assertTrue ( true );
        }
    }


    @Test
    void test04WhileEndedPiecesCantBeCreated () {
        //Assemble
        GameState state = new Ended ();
        Piece piece= null;
        try {
            piece = state.chooseCatapult ( new Player ( "DUDE", new Team (1, new Game ()) , state.getBoard ()) );
        } catch (GameHasEndedException e ){
            //Assert
            assertNull ( piece );
        }
    }

    @Test
    void test04WhileEndedNewPlayersCantBeCreated () {
        //Assemble
        GameState state = new Ended ();
        //Act
        Player player= null;
        try {
            player = state.newPlayer ( "Al",team );
        } catch (GameHasEndedException e ){
            //Assert
            assertNull ( player );
        }

    }


    @Test
    void test05WhileEndedPiecesCantAttack () {
        //Assemble
        GameState state = new Ended ();

        try {
            state.playerAttacks ( new Player ( "DUDE", new Team (1, new Game ()) , state.getBoard ()) ,  new Builder ().fromRow ( 2 ).fromColumn ( 2 ).ToRow ( 3 ).ToColumn ( 2 ).build () );
        } catch (GameHasEndedException e ){
            //Assert
            assertTrue ( true );
        }
    }


    @Test
    void test05WhileEndedPiecesCanFormABattalion () {
        //Assemble
        GameState state = new Ended ();

        try {
            state.playerChoosesBattalion ( new Player ( "DUDE", new Team (1, new Game ()) , state.getBoard ()) ,  new Builder ().fromRow ( 2 ).fromColumn ( 2 ).ToRow ( 3 ).ToColumn ( 2 ).build () );
        } catch (GameHasEndedException e ){
            //Assert
            assertTrue ( true );
        }
    }

}