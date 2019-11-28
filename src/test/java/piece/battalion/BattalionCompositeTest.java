package piece.battalion;

import board.Board;
import move.Builder;
import org.junit.jupiter.api.Test;
import piece.Piece;
import piece.PieceFactory;
import team.Team;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BattalionCompositeTest {
    Team team = new Team (1);
    PieceFactory factory = new PieceFactory ( team );
    Piece soldier1 = factory.createSoldier ();
    Piece soldier2 = factory.createSoldier ();
    Piece soldier3 = factory.createSoldier ();
    ArrayList<Piece> soldiers = new ArrayList<Piece>();
    Board board = new Board ( team, new Team (2) );


    @Test
    void test00ABattalionCompositeCanBeCreated (){
        //Assemble
        this.soldiers.add ( soldier1 );
        this.soldiers.add ( soldier2 );
        this.soldiers.add ( soldier3 );
        //Act
        BattalionComposite battalion = new BattalionComposite ( soldiers );
        //Assert
        assertNotNull ( battalion );
    }

    @Test
    void test00APiecesInBattalionCanBeMoved (){
        //Assemble
        this.soldiers.add ( soldier1 );
        this.soldiers.add ( soldier2 );
        this.soldiers.add ( soldier3 );
        BattalionComposite battalion = new BattalionComposite ( soldiers );
        board.placePiece ( soldier1, new Builder ().ToRow ( 3 ).ToColumn ( 4 ).build () );
        board.placePiece ( soldier2, new Builder ().ToRow ( 3 ).ToColumn ( 3 ).build () );
        board.placePiece ( soldier3, new Builder ().ToRow ( 3 ).ToColumn ( 2 ).build () );
        // Act
        board.move ( new Builder ().fromRow ( 3 ).fromColumn ( 3 ).ToRow ( 4 ).ToColumn ( 4 ).build () );
        //Assert
        assertEquals (soldier3, board.removePiece ( new Builder ().fromRow ( 4 ).fromColumn ( 3 ).build () ) );
        assertEquals (soldier2, board.removePiece ( new Builder ().fromRow ( 4 ).fromColumn ( 4 ).build () ) );
        assertEquals (soldier1, board.removePiece ( new Builder ().fromRow ( 4 ).fromColumn ( 5 ).build () ) );

    }
}