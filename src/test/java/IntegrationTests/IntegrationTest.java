package IntegrationTests;

import Face.ThereAreOnlyTwoPlayersPerGameException;
import board.*;
import board.CanNotMakeThatMoveException;
import game.Game;
import game.GameHasEndedException;
import game.ThereCantBeTwoPlayersOnTheSameTeamException;
import org.junit.jupiter.api.Test;
import piece.Piece;
import player.Player;
import player.PlayerHas20PointsOnlyException;
import team.Gold;
import team.Blue;
import piece.*;
import team.NoMembersLeftException;


import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {
    private PieceFactory factory = new PieceFactory ();

    @Test
    void test00CanMoveAPieceFromRow3AndColumn3InAllPossibleWays () {

        Board board = new Board ();
        Piece piece = factory.getPiece ( "SOLDIER", new Gold () );

        board.placePiece ( piece, 3, 3 );
        for (int i = 2; i < 5; i++) {
            for (int j = 2; j < 5; j++) {
                if (i == 3 && j == 3) {
                    continue;
                }
                board.movePiece ( 3, 3, i, j );
                board.movePiece ( i, j, 3, 3 );
            }
        }
    }

    @Test
    void test01APieceCanNotMoveToAnOccupiedCell () {
        Board board = new Board ();
        Piece piece = factory.getPiece ( "SOLDIER", new Gold () );

        board.placePiece ( piece, 3, 3 );
        board.placePiece ( piece, 3, 4 );

        try {
            board.movePiece ( 3, 3, 3, 4 );
            fail ();
        } catch (CanNotMakeThatMoveException e) {
            assert true;
        }
    }

    @Test
    void test06CanPlaceAnAllyPieceInAnEmptyAllyCell () {
        Board board = new Board ();
        Piece piece = factory.getPiece ( "SOLDIER", new Gold () );

        board.placePiece ( piece, 3, 3 );

    }

    @Test
    void Test02AnAlliedInfantrySoldierAttacksAnEnemyPieceAndVerifiesThatTheCorrespondingLifeIsSubtracted () {
        //Assign
        Board board = new Board ();
        Gold gold = new Gold ();
        Blue blue = new Blue ();
        Piece soldier = factory.getPiece ( "SOLDIER", gold );
        Piece healer = factory.getPiece ( "HEALER", blue );

        //Act
        board.placePiece ( soldier, 9, 0 );
        board.placePiece ( healer, 10, 0 );

        //Assert
        assertEquals ( 100, soldier.getLife () );
        assertEquals ( 75, healer.getLife () );
        board.bodyAttack ( 9, 0, 10, 0 );
        assertEquals ( 65, healer.getLife () );

    }

    @Test
    void Test03AnAlliedRiderAttacksAnEnemyPieceAndVerifiesThatTheCorrespondingLifeIsSubtracted () {
        //Assign
        Board board = new Board ();
        Gold gold = new Gold ();
        Blue blue = new Blue ();
        Piece soldier = factory.getPiece ( "SOLDIER", blue );
        Piece rider = factory.getPiece ( "RIDER", gold );

        //Act
        board.placePiece ( rider, 9, 0 );
        board.placePiece ( soldier, 10, 0 );

        //Assert
        assertEquals ( 100, rider.getLife () );
        assertEquals ( 100, soldier.getLife () );
        board.bodyAttack ( 9, 0, 10, 0 );
        assertEquals ( 95, soldier.getLife () );

        assertEquals ( 100, rider.getLife () );
        assertEquals ( 95, soldier.getLife () );
        board.bodyAttack ( 9, 0, 10, 0 );
        assertEquals ( 90, soldier.getLife () );

    }

    @Test
    void Test04AnAlliedCatapultAttacksAnEnemyPieceAndItIsVerifiedThatTheCorrespondingLifeIsSubtracted () {
        //Assign
        Board board = new Board ();
        Gold gold = new Gold ();
        Blue blue = new Blue ();
        Piece soldier = factory.getPiece ( "SOLDIER", blue );
        Piece catapult = factory.getPiece ( "catapult", gold );

        //Act
        board.placePiece ( catapult, 0, 0 );
        board.placePiece ( soldier, 10, 2 );

        //Assert
        assertEquals ( 50, catapult.getLife () );
        assertEquals ( 100, soldier.getLife () );
        board.distanceAttack ( 0, 0, 10, 2 );
        assertEquals ( 80, soldier.getLife () );
    }

    @Test
    void Test05AnAlliedHealerHealsAnAlliedPieceAndVerifiesThatTheCorrespondingLifeIsAdded () {
        //Assign
        Board board = new Board ();
        Gold gold = new Gold ();
        Blue blue = new Blue ();
        Piece soldier = factory.getPiece ( "SOLDIER", gold );
        Piece healer = factory.getPiece ( "healer", gold );
        Piece rider = factory.getPiece ( "rider", blue );

        //Act
        board.placePiece ( healer, 9, 0 );
        board.placePiece ( soldier, 9, 1 );
        board.placePiece ( rider, 10, 1 );


        //Assert
        assertEquals ( 75, healer.getLife () );
        assertEquals ( 100, soldier.getLife () );
        assertEquals ( 100, rider.getLife () );

        board.bodyAttack ( 10, 1, 9, 1 );
        assertEquals ( 75, healer.getLife () );
        assertEquals ( 95, soldier.getLife () );
        assertEquals ( 100, rider.getLife () );

        board.heal ( 9, 0, 9, 1 );
        assertEquals ( 75, healer.getLife () );
        assertEquals ( 100, soldier.getLife () );
        assertEquals ( 100, rider.getLife () );
    }

    @Test
    void test06CanPlaceAnAllyPieceToAnEmptyAllyCell () {
        //Assemble
        Board board = new Board ();
        Piece piece = factory.getPiece ( "SOLDIER", new Gold () );
        //Act
        board.placePiece ( piece, 3, 3 );
        //Assert
        assertEquals ( piece, board.removePiece ( 3,3 ) );
    }

    @Test
    void test07CannotPlaceAnAllyPieceInAnOccupiedAllyCell () {
        //Assemble
        Board board = new Board ();
        Piece piece = factory.getPiece ( "SOLDIER", new Gold () );
        Piece pieceThatOccupiesCell = factory.getPiece ( "RIDER", new Gold () );
        //Act
        board.placePiece ( pieceThatOccupiesCell, 3, 3 );

        try {
            board.placePiece ( piece, 3, 3 );
            //Assemble
        } catch (OccupiedCellException e) {
            assert true;
        }
    }

    @Test
    void test08cannotPlaceAnAllyPieceInAnEnemyCell () {
        //Assemble
        Board board = new Board ();
        Piece piece = factory.getPiece ( "SOLDIER", new Gold () );
        //Act
        try {
            board.placePiece ( piece, 3, 3 );
            //Assert
        } catch (EnemyCellException e) {
            assert true;
        }
    }

    @Test
    void test09BoardIsProperlyCreated () {
        //Assemble & Act
        Board board = new Board ();
        //Assert
        assertNotNull ( board );
    }

    @Test
    void test11PlayerCantChooseMorePiecesThanWhatHisPointsAllow () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Player0005667" );
        Player player1 = game.getPlayer1 ();
        //Act
        try {
            Piece soldier = game.playerChoosesPiece ( player1, "SOLDIER" );
            Piece rider = game.playerChoosesPiece ( player1, "RIDER" );
            Piece catapult = game.playerChoosesPiece ( player1, "CATAPULT" );
            Piece rider2 = game.playerChoosesPiece ( player1, "RIDER" );
            Piece catapult2 = game.playerChoosesPiece ( player1, "CATAPULT" );
            Piece rider3 = game.playerChoosesPiece ( player1, "RIDER" );

            Piece catapult3 = game.playerChoosesPiece ( player1, "CATAPULT" );
            fail();
            //Assert
        } catch (PlayerHas20PointsOnlyException e) {
            assertEquals ( 0, game.getPlayer1 ().obtainPoints () );
        }
    }

    @Test
    void test12PlayerThatHasNoPiecesLostTheGame () throws PlayerHas20PointsOnlyException, ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, GameHasEndedException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Player0005667" );
        Player player1 = game.getPlayer1 ();

        Piece catapult1 = game.playerChoosesPiece ( player1, "CATAPULT" );
        Piece catapult2 = game.playerChoosesPiece ( player1, "CATAPULT" );
        Piece catapult3 = game.playerChoosesPiece ( player1, "CATAPULT" );
        Piece catapult4 = game.playerChoosesPiece ( player1, "CATAPULT" );

        game.playerPlacesPieceOnBoard ( player1, catapult1, 9, 0 );
        game.playerPlacesPieceOnBoard ( player1, catapult2, 9, 1 );
        game.playerPlacesPieceOnBoard ( player1, catapult3, 9, 2 );
        game.playerPlacesPieceOnBoard ( player1, catapult4, 9, 3 );

        game.newPlayer ( "Alan12" );
        Player player2 = game.getPlayer2 ();

        Piece catapult5 = game.playerChoosesPiece ( player2, "CATAPULT" );
        Piece catapult6 = game.playerChoosesPiece ( player2, "CATAPULT" );
        Piece catapult7 = game.playerChoosesPiece ( player2, "CATAPULT" );
        Piece catapult8 = game.playerChoosesPiece ( player2, "CATAPULT" );

        game.playerPlacesPieceOnBoard ( player2, catapult5, 11, 0 );
        game.playerPlacesPieceOnBoard ( player2, catapult6, 11, 1 );
        game.playerPlacesPieceOnBoard ( player2, catapult7, 11, 2 );
        game.playerPlacesPieceOnBoard ( player2, catapult8, 11, 3 );
        //Act
        try {
            game.playerAttacks ( player1, 9, 0 );
            game.playerAttacks ( player1, 9, 1 );
            game.playerAttacks ( player1, 9, 2 );
            game.playerAttacks ( player1, 9, 3 );
            game.playerMovesPieceOnBoard ( player2, 11, 0, 12, 0 );
            fail();
            //Assert
        } catch (GameHasEndedException e) {
            assertEquals ( 0, game.getPlayer1 ().getTeam ().numberOfMembersStillOnTeam () );
        } catch (NoMembersLeftException e) {
            e.printStackTrace ();
        }
    }
}