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
    void test06CanMoveAPieceFromRow3AndColumn3InAllPossibleWays(){
        board.placePiece(piece,3,3);
        for (int i =2;i<5;i++) {
            for (int j = 2; j < 5; j++) {
                if (i == 3 && j == 3) {
                    continue;
                }
                board.movePiece(3, 3, i, j);
                board.movePiece(i, j, 3, 3);
            }
        }

    }
    @Test
    void test07APieceCanNotMoveToAnOccupiedCell(){
        board.placePiece(piece,3,3);
        board.placePiece(piece,3,4);

        try{
            board.movePiece(3,3,3,4);
            fail();
        }
        catch (CanNotMakeThatMoveException e){
            assert true;
        }
    }
    @Test
    void test08WhenYouPopSomethingOfACellIsAPiece(){
        board.placePiece(piece,3,3);
        assertThat(board.removePiece(3,3),instanceOf(Piece.class));
    }
    @Test
    void test09CanPutAPieceIfTheCellIsAlreadyOccupied(){
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