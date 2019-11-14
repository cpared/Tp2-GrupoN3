package board;

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
    private Board board = new Board ();
    private PieceFactory factory = new PieceFactory ( new Gold () );
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
            board.movePiece ( 5, 7, 5, 8 );
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
            board.placePiece ( piece, 0, 0 );
            board.movePiece ( 0, 0, 19, 19 );
            fail ();
        }
        //Assert
        catch (CanNotMakeThatMoveException e) {
            assert true;
        }
    }

    @Test
    void test04CanPlaceAPieceInAnEmptyCell () {
        //Act & Assert
        board.placePiece ( piece, 3, 3 );
    }

    @Test
    void test05CanRemoveAPieceOfANonEmptyCell () {
        //Act
        board.placePiece ( piece, 3, 3 );
        Piece poppedPiece = board.removePiece ( 3, 3 );

        //Assert
        assertEquals ( poppedPiece, piece );
    }

    @Test
    void test06WhenYouPopSomethingOfACellIsAPiece () {
        //Act
        board.placePiece ( piece, 3, 3 );

        //Assert
        assertThat ( board.removePiece ( 3, 3 ), instanceOf ( Piece.class ) );
    }

    @Test
    void test07CanPutAPieceIfTheCellIsAlreadyOccupied () {
        //Act
        board.placePiece ( piece, 3, 3 );
        try {
            board.placePiece ( piece, 3, 3 );
            fail ();
        }
        //Assert
        catch (OccupiedCellException e) {
            assert true;
        }
    }
}