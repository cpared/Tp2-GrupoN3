package IntegrationTests;
import board.*;
import board.CanNotMakeThatMoveException;
import org.junit.jupiter.api.Test;
import piece.Piece;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    @Test
    void test00CanMoveAPieceFromRow3AndColumn3InAllPossibleWays(){

        Board board = new Board();
        Piece piece = new Piece();

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
    void test01APieceCanNotMoveToAnOccupiedCell(){
        Board board = new Board();
        Piece piece = new Piece();

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
    public void test11PlayerCantChooseMorePiecesThanWhatHisPointsAllow () {



    }
    @Test
    public void test12PlayerThatHasNoPiecesLostTheGame () {


    }
}