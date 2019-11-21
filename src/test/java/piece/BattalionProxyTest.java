package piece;

import board.Board;
import board.CanNotMakeBattalion;
import game.GameHasEndedException;
import game.ThereCantBeTwoPlayersOnTheSameTeamException;
import javafx.util.Pair;
import move.Builder;
import move.Move;
import org.junit.jupiter.api.Test;
import team.Team;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BattalionProxyTest {
    // Assemble
    Team team1 = new Team ( 1 );
    private Board board = new Board ( team1, new Team ( 2 ) );
    private PieceFactory factory = new PieceFactory ( team1 );
    private Piece soldier1 = factory.createSoldier ();
    private Piece soldier2 = factory.createSoldier ();
    private Piece soldier3 = factory.createSoldier ();
    private Piece rider = factory.createRider ();
    private ArrayList<Piece> soldiers = new ArrayList<Piece> ();

    @Test
    void test00CanCreateAValidProxy () {
        // Assemble
        Move move = new Builder ().ToColumn ( 1 ).ToRow ( 1 ).build ();

        // Act
        BattalionProxy proxy = new BattalionProxy ( board, new ArrayList<Piece> (), move );
        // Assert
        assertNotNull ( proxy );
    }

    @Test
    void test01BattalionProxyCreatesBattalionOnlyWhenItMeetsTheCriteria () {
        // Assemble
        Move move = new Builder ().ToColumn ( 1 ).ToRow ( 1 ).build ();
        soldiers.add ( soldier1 );
        soldiers.add ( soldier2 );
        soldiers.add ( soldier3 );
        // Act
        BattalionProxy proxy = new BattalionProxy ( board, soldiers, move );
        // Assert
        assertNotNull ( proxy.createBattalion () );
    }

    @Test
    void test02DoesntBattalionProxyCreatesBattalionWhenItDoesntMeetTheCriteria () {
        // Assemble
        Move move = new Builder ().ToColumn ( 1 ).ToRow ( 1 ).build ();
        soldiers.add ( soldier1 );
        soldiers.add ( soldier2 );
        soldiers.add ( factory.createRider () );
        // Act
        BattalionProxy proxy = new BattalionProxy ( board, soldiers, move );
        try {
            proxy.createBattalion ();
            // Assert
        } catch (CanNotMakeBattalion e) {
            assertTrue ( true );
        }
    }

    @Test
    void test03WhenAPieceMoverAllThePiecesInTheBattalionMove () {
        // Assemble
        Move move1 = new Builder ().ToColumn ( 1 ).ToRow ( 1 ).build ();
        Move move2 = new Builder ().ToColumn ( 2 ).ToRow ( 1 ).build ();
        Move move3 = new Builder ().ToColumn ( 3 ).ToRow ( 1 ).build ();
        soldiers.add ( soldier1 );
        soldiers.add ( soldier2 );
        soldiers.add ( soldier3 );
        this.board.placePiece ( soldier1, move1 );
        this.board.placePiece ( soldier2, move2 );
        this.board.placePiece ( soldier3, move3 );
        BattalionProxy proxy = new BattalionProxy ( board, soldiers, move2 );
        proxy.createBattalion ();

        // Act
        Move move4 = new Builder ().fromColumn ( 2 ).fromRow ( 1 ).ToColumn ( 2 ).ToRow ( 2 ).build ();
        proxy.move ( board, move4 );

        // Assert
        Move move5 = new Builder ().ToColumn ( 1 ).ToRow ( 2 ).build ();
        Move move6 = new Builder ().ToColumn ( 2 ).ToRow ( 2 ).build ();
        Move move7 = new Builder ().ToColumn ( 3 ).ToRow ( 2 ).build ();

        assertEquals ( soldier1, this.board.removePiece ( move5 ) );
        assertEquals ( soldier2, this.board.removePiece ( move6 ) );
        assertEquals ( soldier3, this.board.removePiece ( move7 ) );
    }

    @Test
    void test04WhenBattalionIsToldToGetHealedThePiecesDontGetHealed () {
        // Assemble

        Move move1 = new Builder ().ToColumn ( 1 ).ToRow ( 1 ).build ();
        Move move2 = new Builder ().ToColumn ( 2 ).ToRow ( 1 ).build ();
        Move move3 = new Builder ().ToColumn ( 3 ).ToRow ( 1 ).build ();
        this.board.placePiece ( soldier1, move1 );
        this.board.placePiece ( soldier2, move2 );
        this.board.placePiece ( soldier3, move3 );

        ArrayList<Piece> possiblePieces = this.board.adjacentPieces ( this.board.adjacentRowCells ( move2 ) );
        BattalionProxy proxy = new BattalionProxy ( board, possiblePieces, move2 );
        RealBattalion battalion = proxy.createBattalion ();

        int life1 = soldier1.getLife ();
        int life2 = soldier2.getLife ();
        int life3 = soldier3.getLife ();
        // Act

        proxy.receiveHealed ( 5 );

        // Assert

        assertEquals ( life1, this.board.removePiece ( move1 ).getLife () );
        assertEquals ( life2, this.board.removePiece ( move2 ).getLife () );
        assertEquals ( life3, this.board.removePiece ( move3 ).getLife () );

    }

    @Test
    void test05WhenBattalionIsToldToGetAttackedThePiecesDontGetAttacked () throws ThereCantBeTwoPlayersOnTheSameTeamException, GameHasEndedException {
        // Assemble

        Move move1 = new Builder ().ToColumn ( 1 ).ToRow ( 1 ).build ();
        Move move2 = new Builder ().ToColumn ( 2 ).ToRow ( 1 ).build ();
        Move move3 = new Builder ().ToColumn ( 3 ).ToRow ( 1 ).build ();
        this.board.placePiece ( soldier1, move1 );
        this.board.placePiece ( soldier2, move2 );
        this.board.placePiece ( soldier3, move3 );

        ArrayList<Piece> possiblePieces = this.board.adjacentPieces ( this.board.adjacentRowCells ( move2 ) );
        BattalionProxy proxy = new BattalionProxy ( board, possiblePieces, move2 );
        RealBattalion battalion = proxy.createBattalion ();

        int life1 = soldier1.getLife ();
        int life2 = soldier2.getLife ();
        int life3 = soldier3.getLife ();
        // Act

        proxy.receiveAttacked ( 20 );

        // Assert

        assertEquals ( life1, this.board.removePiece ( move1 ).getLife () );
        assertEquals ( life2, this.board.removePiece ( move2 ).getLife () );
        assertEquals ( life3, this.board.removePiece ( move3 ).getLife () );
    }


    @Test
    void test06WhenBattalionIsToldToAttackThePiecesDontAttack () throws ThereCantBeTwoPlayersOnTheSameTeamException, GameHasEndedException {
        // Assemble

        Move move1 = new Builder ().ToColumn ( 1 ).ToRow ( 1 ).build ();
        Move move2 = new Builder ().ToColumn ( 2 ).ToRow ( 1 ).build ();
        Move move3 = new Builder ().ToColumn ( 3 ).ToRow ( 1 ).build ();
        Move move4 = new Builder ().ToColumn ( 4 ).ToRow ( 1 ).build ();
        this.board.placePiece ( soldier1, move1 );
        this.board.placePiece ( soldier2, move2 );
        this.board.placePiece ( soldier3, move3 );
        this.board.placePiece ( rider, move4 );

        ArrayList<Piece> possiblePieces = this.board.adjacentPieces ( this.board.adjacentRowCells ( move2 ) );
        BattalionProxy proxy = new BattalionProxy ( board, possiblePieces, move2 );
        RealBattalion battalion = proxy.createBattalion ();

        int life = rider.getLife ();
        // Act

        proxy.attack ( new ArrayList<>(), new Pair<>(rider,1 ) );

        // Assert
        assertEquals ( life, this.board.removePiece ( move4 ).getLife () );
    }
}