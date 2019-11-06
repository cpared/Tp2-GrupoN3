package board;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import piece.Piece;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
class BoardTest {
    private Board board = new Board();
    private Piece piece = new Piece();
    @Test
    void test00TheBoardCanBeCreated(){
        assertNotNull(board);
    }
    @Test
    void test01CanAccessTheCellThatIsInRow5AndColumn7(){
        Board board = new Board();
        Method method;

        try {
            method = Board.class.getDeclaredMethod("getCell", int.class, int.class);
            method.setAccessible(true);
            assertThat(method.invoke(board, 5, 7),instanceOf(Cell.class));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void test02movingApieceFromACellThatIsEmptyRaiseAnException(){
        try {
            board.movePiece(5,7,5,8);
            fail();
        }
        catch (EmptyCellException e) {
            assert true;
        }
    }

    @Test
    void test03CanNotMoveToACellThatHasADistanceGreaterThan1(){
        try{
            board.movePiece(0,0,19,19);
            fail();
        }
        catch (CanNotMakeThatMoveException e) {
            assert true;
        }
    }
    @Test
    void test04CanPlaceAPieceInAnEmptyCell(){
        board.placePiece(piece,3,3);
    }
    @Test
    void test05CanRemoveAPieceOfANonEmptyCell(){
        board.placePiece(piece,3,3);
        Piece poppedPiece = board.removePiece(3,3);
        assertEquals(poppedPiece,piece);
    }
    @Test
    void test06WhenYouPopSomethingOfACellIsAPiece(){
        board.placePiece(piece,3,3);
        assertThat(board.removePiece(3,3),instanceOf(Piece.class));
    }
    @Test
    void test07CanPutAPieceIfTheCellIsAlreadyOccupied(){
        board.placePiece(piece,3,3);
        try{
            board.placePiece(piece,3,3);
            fail();
        }
        catch(OccupiedCellException e){
            assert true;
        }
    }
}