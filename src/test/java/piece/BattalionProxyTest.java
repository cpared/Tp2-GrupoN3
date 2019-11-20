package piece;

import board.Battalion;
import board.Board;
import board.CanNotMakeBattalion;
import game.Game;
import game.GameHasEndedException;
import game.ThereCantBeTwoPlayersOnTheSameTeamException;
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
        BattalionProxy proxy = new BattalionProxy ( board, soldiers , move );
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
        BattalionProxy proxy = new BattalionProxy ( board, soldiers , move );
        try {
            proxy.createBattalion ();
            // Assert
        } catch (CanNotMakeBattalion e) {
            assertTrue( true );
        }
    }
    @Test
    void test03WhenAPieceMoverAllThePiecesInTheBattalionMove () {
        // Assemble
        Board board = new Board ( team1, new Team ( 2 ) );
        Move move1 = new Builder ().ToColumn ( 1 ).ToRow ( 1 ).build ();
        Move move2 = new Builder ().ToColumn ( 2 ).ToRow ( 1 ).build ();
        Move move3 = new Builder ().ToColumn ( 3 ).ToRow ( 1 ).build ();
        soldiers.add ( soldier1 );
        soldiers.add ( soldier2 );
        soldiers.add ( soldier3 );
        this.board.placePiece ( soldier1, move1 );
        this.board.placePiece ( soldier2, move2 );
        this.board.placePiece ( soldier3, move3 );
        // Act

        //BattalionProxy proxy = new BattalionProxy ( board, soldiers , move2 );
        Battalion proxy = new BattalionProxy ( board, soldiers, move2 );

        RealBattalion battalion = proxy.createBattalion ();
        // Assert

        Move move4 = new Builder ().fromColumn (2).fromRow ( 1 ).ToColumn ( 2 ).ToRow ( 2 ).build ();
        this.board.movePiece ( move4 );

        Move move5 = new Builder ().ToColumn ( 1 ).ToRow ( 2 ).build ();
        Move move6 = new Builder ().ToColumn ( 2 ).ToRow ( 2 ).build ();
        Move move7 = new Builder ().ToColumn ( 3 ).ToRow ( 2 ).build ();

        //assertEquals (soldier1.getCost (), this.board.removePiece ( move5 ).getCost ());
        assertEquals (soldier2.getCost (), this.board.removePiece ( move6 ).getCost ());
        //assertEquals (soldier3.getCost (), this.board.removePiece ( move7 ).getCost ());

        assertEquals (soldier1.getCost (), this.board.removePiece ( move1 ).getCost ());
        assertEquals (soldier3.getCost (), this.board.removePiece ( move2 ).getCost ());
    }
    @Test
    void test04WhenAPieceMoverAllThePiecesInTheBattalionMove () {
        // Assemble
        Board board = new Board ( team1, new Team ( 2 ) );
        Move move1 = new Builder ().ToColumn ( 1 ).ToRow ( 1 ).build ();
        Move move2 = new Builder ().ToColumn ( 2 ).ToRow ( 1 ).build ();
        Move move3 = new Builder ().ToColumn ( 3 ).ToRow ( 1 ).build ();
        this.board.placePiece ( soldier1, move1 );
        this.board.placePiece ( soldier2, move2 );
        this.board.placePiece ( soldier3, move3 );
        // Act
        ArrayList<Piece> possiblePieces = this.board.adjacentPieces ( this.board.adjacentRowCells ( move2 ) );


        BattalionProxy proxy = new BattalionProxy ( board, possiblePieces , move2 );
        RealBattalion battalion = proxy.createBattalion ();
        // Assert
        Move move4 = new Builder ().fromColumn (2).fromRow ( 1 ).ToColumn ( 2 ).ToRow ( 2 ).build ();
        this.board.movePiece ( move4 );

        Move move5 = new Builder ().ToColumn ( 1 ).ToRow ( 2 ).build ();
        Move move6 = new Builder ().ToColumn ( 2 ).ToRow ( 2 ).build ();
        Move move7 = new Builder ().ToColumn ( 3 ).ToRow ( 2 ).build ();

        //assertEquals (soldier1.getCost (), this.board.removePiece ( move5 ).getCost ());
        assertEquals (soldier2.getCost (), this.board.removePiece ( move6 ).getCost ());
        //assertEquals (soldier3.getCost (), this.board.removePiece ( move7 ).getCost ());

        assertEquals (soldier1.getCost (), this.board.removePiece ( move1 ).getCost ());
        assertEquals (soldier3.getCost (), this.board.removePiece ( move2 ).getCost ());
    }


    @Test
    void test05WhenAPieceMoverAllThePiecesInTheBattalionMove () throws ThereCantBeTwoPlayersOnTheSameTeamException, GameHasEndedException {
        // Assemble
        Game game = new Game ();
        game.newPlayer ( "Pepe" );
        Piece cat1 = game.playerChoosesCatapult ( game.getPlayer1 () );
        Piece cat2 = game.playerChoosesCatapult ( game.getPlayer1 () );
        Piece cat3 = game.playerChoosesCatapult ( game.getPlayer1 () );
        Piece cat4 = game.playerChoosesCatapult ( game.getPlayer1 () );
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), cat1, 9,1 );
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), cat1, 9,2 );
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), cat1, 9,3 );
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), cat1, 9,4 );
        game.newPlayer ( "Juan" );
        game.playerIsReadyToPlay ( game.getPlayer1 () );
        Piece sol1 = game.playerChoosesSoldier ( game.getPlayer2 () );
        Piece sol2 = game.playerChoosesSoldier ( game.getPlayer2 () );
        Piece sol3 = game.playerChoosesSoldier ( game.getPlayer2 () );
        Piece sol4 = game.playerChoosesSoldier ( game.getPlayer2 () );
        game.playerPlacesPieceOnBoard ( game.getPlayer2 (), sol1, 12,1 );
        game.playerPlacesPieceOnBoard ( game.getPlayer2 (), sol2, 12,2 );
        game.playerPlacesPieceOnBoard ( game.getPlayer2 (), sol3, 12,3 );
        game.playerPlacesPieceOnBoard ( game.getPlayer2 (), sol4, 12,4 );
        game.playerIsReadyToPlay ( game.getPlayer2 () );
        // Act
        game.playerChoosesBattalion ( game.getPlayer2 (), 12, 2 );

        // Act
        System.out.println ( "soldier1" + sol1);
        System.out.println ( "soldier2" + sol2);
        System.out.println ( "soldier3" + sol3);
        game.playerMovesPieceOnBoard ( game.getPlayer2 (), 12, 2, 13, 2 );
        // Assert

        assertEquals (soldier1.getCost (), game.removePieceFromBoard ( game.getPlayer2 (), 13,1 ).getCost ());
        assertEquals (soldier2.getCost (), game.removePieceFromBoard ( game.getPlayer2 (), 13,2 ).getCost ());
        assertEquals (soldier3.getCost (), game.removePieceFromBoard ( game.getPlayer2 (), 13,3 ).getCost ());
    }

}