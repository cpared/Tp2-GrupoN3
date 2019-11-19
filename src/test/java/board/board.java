package board;

import move.Builder;
import move.Move;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import piece.*;
import player.PlayerHas20PointsOnlyException;
import team.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    //Assemble
    Team team = new Team (1);
    private Board board = new Board (team,new Team (2));
    private PieceFactory factory = new PieceFactory ( team );
    private Piece piece = factory.createSoldier ( );

    BoardTest () throws PlayerHas20PointsOnlyException {
    }

    @Test
    void test00TheBoardCanBeCreated () {
        //Assert
        assertNotNull ( board );
    }

    @Test
    void test01CanAccessTheCellThatIsInRow5AndColumn7 () {
        //Assemble
        Method method;
        //Act
        try {
            method = Board.class.getDeclaredMethod ( "getCell", int.class, int.class );
            method.setAccessible ( true );
            //Assert
            assertThat ( method.invoke ( board, 5, 7 ), instanceOf ( Cell.class ) );
        } catch (Exception e) {
            fail ();
        }
    }

    @Test
    void test02movingApieceFromACellThatIsEmptyRaiseAnException () {
        //Act
        try {
            Move move = new Builder ().fromRow ( 5 ).fromColumn ( 7 ).ToRow ( 5 ).ToColumn ( 8 ).build ();
            board.movePiece ( move );
            fail ();
        }
        //Assert
        catch (EmptyCellException e) {
            assert true;
        }
    }

    @Test
    void test03CanNotMoveToACellThatHasADistanceGreaterThan1 () {
        //Act
        try {
            Move move = new Builder ().fromRow ( 0 ).fromColumn ( 0 ).build ();
            board.placePiece ( piece, move);
            Move move2 = new Builder ().fromRow ( 0 ).fromColumn ( 0 ).ToRow ( 19 ).ToColumn ( 19 ).build ();
            board.movePiece ( move2 );
            fail ();
        }
        //Assert
        catch (CanNotMakeThatMoveException e) {
            assert true;
        }
    }

    @Test
    void test04CanPlaceAPieceInAnEmptyCell () {
        //Assemble
        Move move = new Builder ().fromRow ( 3 ).fromColumn ( 3 ).build ();
        //Act & Assert
        board.placePiece ( piece, move );
    }

    @Test
    void test05CanRemoveAPieceOfANonEmptyCell () {
        //Assemble
        Move move = new Builder ().fromRow ( 3 ).fromColumn ( 3 ).build ();

        //Act
        board.placePiece ( piece, move );
        Piece poppedPiece = board.removePiece ( move );

        //Assert
        assertEquals ( poppedPiece, piece );
    }

    @Test
    void test06WhenYouPopSomethingOfACellIsAPiece () {
        //Assemble
        Move move = new Builder ().fromRow ( 3 ).fromColumn ( 3 ).build ();

        //Act
        board.placePiece ( piece, move );

        //Assert
        assertThat ( board.removePiece ( move ), instanceOf ( Piece.class ) );
    }

    @Test
    void test07CanPutAPieceIfTheCellIsAlreadyOccupied () {
        //Assemble
        Move move = new Builder ().fromRow ( 3 ).fromColumn ( 3 ).build ();

        //Act
        board.placePiece ( piece, move );
        try {
            board.placePiece ( piece, move );
            fail ();
        }
        //Assert
        catch (OccupiedCellException e) {
            assert true;
        }
    }
}