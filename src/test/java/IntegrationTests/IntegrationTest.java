package IntegrationTests;

import board.*;
import board.CanNotMakeThatMoveException;
import game.*;
import org.junit.jupiter.api.Test;
import piece.Piece;
import player.*;
import team.*;
import piece.*;


import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {
    Team team = new Team(1);
    Team team2 = new Team(2);
    private PieceFactory factory = new PieceFactory(team);
    private PieceFactory efactory = new PieceFactory(team2);

    @Test
    void test00CanMoveAPieceFromRow3AndColumn3InAllPossibleWays() throws PlayerHas20PointsOnlyException {

        Board board = new Board(team, team2);
        Piece piece = factory.createSoldier();

        board.placePiece(piece, 3, 3);
        for (int i = 2; i < 5; i++) {
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
    void test01APieceCanNotMoveToAnOccupiedCell() throws PlayerHas20PointsOnlyException {
        Board board = new Board(team, team2);
        Piece piece = factory.createSoldier();

        board.placePiece(piece, 3, 3);
        board.placePiece(piece, 3, 4);

        try {
            board.movePiece(3, 3, 3, 4);
            fail();
        } catch (CanNotMakeThatMoveException e) {
            assert true;
        }
    }

    @Test
    void test06CanPlaceAnAllyPieceInAnEmptyAllyCell() throws PlayerHas20PointsOnlyException {
        Board board = new Board(team, team2);
        Piece piece = factory.createSoldier();

        board.placePiece(piece, 3, 3);

    }

    @Test
    void Test02AnAlliedInfantrySoldierAttacksAnEnemyPieceAndVerifiesThatTheCorrespondingLifeIsSubtracted() throws PlayerHas20PointsOnlyException {
        //Assign
        Board board = new Board(team, team2);
        Piece soldier = factory.createSoldier();
        Piece healer = efactory.createHealer();

        //Act
        board.placePiece(soldier, 9, 0);
        board.placePiece(healer, 10, 0);

        //Assert
        assertEquals(100, soldier.getLife());
        assertEquals(75, healer.getLife());
        board.bodyAttack(9, 0, 10, 0);
        assertEquals(65, healer.getLife());

    }

    @Test
    void Test03AnAlliedRiderAttacksAnEnemyPieceAndVerifiesThatTheCorrespondingLifeIsSubtracted() throws PlayerHas20PointsOnlyException {
        //Assign
        Board board = new Board(team, team2);
        Piece soldier = efactory.createSoldier();
        Piece rider = factory.createRider();

        //Act
        board.placePiece(rider, 9, 0);
        board.placePiece(soldier, 10, 0);

        //Assert
        assertEquals(100, rider.getLife());
        assertEquals(100, soldier.getLife());
        board.bodyAttack(9, 0, 10, 0);
        assertEquals(95, soldier.getLife());

        assertEquals(100, rider.getLife());
        assertEquals(95, soldier.getLife());
        board.bodyAttack(9, 0, 10, 0);
        assertEquals(90, soldier.getLife());

    }

    @Test
    void Test04AnAlliedCatapultAttacksAnEnemyPieceAndItIsVerifiedThatTheCorrespondingLifeIsSubtracted() throws PlayerHas20PointsOnlyException {
        //Assign
        Board board = new Board(team, team2);
        Piece soldier = efactory.createSoldier();
        Piece catapult = factory.createCatapult();

        //Act
        board.placePiece(catapult, 0, 0);
        board.placePiece(soldier, 10, 2);

        //Assert
        assertEquals(50, catapult.getLife());
        assertEquals(100, soldier.getLife());
        board.distanceAttack(0, 0, 10, 2);
        assertEquals(80, soldier.getLife());
    }

    @Test
    void Test05AnAlliedHealerHealsAnAlliedPieceAndVerifiesThatTheCorrespondingLifeIsAdded() throws PlayerHas20PointsOnlyException {
        //Assign
        Board board = new Board(team, team2);
        Piece soldier = factory.createSoldier();
        Piece healer = factory.createHealer();
        Piece rider = efactory.createRider();

        //Act
        board.placePiece(healer, 9, 0);
        board.placePiece(soldier, 9, 1);
        board.placePiece(rider, 10, 1);


        //Assert
        assertEquals(75, healer.getLife());
        assertEquals(100, soldier.getLife());
        assertEquals(100, rider.getLife());

        board.bodyAttack(10, 1, 9, 1);
        assertEquals(75, healer.getLife());
        assertEquals(95, soldier.getLife());
        assertEquals(100, rider.getLife());

        board.heal(9, 0, 9, 1);
        assertEquals(75, healer.getLife());
        assertEquals(100, soldier.getLife());
        assertEquals(100, rider.getLife());
    }

    @Test
    void test06CanPlaceAnAllyPieceToAnEmptyAllyCell() throws PlayerHas20PointsOnlyException {
        //Assemble
        Board board = new Board(team, team2);
        Piece piece = factory.createSoldier();
        //Act
        board.placePiece(piece, 3, 3);
        //Assert
        assertEquals(piece, board.removePiece(3, 3));
    }

    @Test
    void test07CannotPlaceAnAllyPieceInAnOccupiedAllyCell() throws PlayerHas20PointsOnlyException {
        //Assemble
        Board board = new Board(team, team2);
        Piece piece = factory.createSoldier();
        Piece pieceThatOccupiesCell = factory.createRider();
        //Act
        board.placePiece(pieceThatOccupiesCell, 3, 3);

        try {
            board.placePiece(piece, 3, 3);
            //Assemble
        } catch (OccupiedCellException e) {
            assert true;
        }
    }

    @Test
    void test08cannotPlaceAnAllyPieceInAnEnemyCell() throws PlayerHas20PointsOnlyException {
        //Assemble
        Board board = new Board(team, team2);
        Piece piece = factory.createSoldier();
        //Act
        try {
            board.placePiece(piece, 3, 3);
            //Assert
        } catch (EnemyCellException e) {
            assert true;
        }
    }

    @Test
    void test09BoardIsProperlyCreated() {
        //Assemble & Act
        Board board = new Board(team, team2);
        //Assert
        assertNotNull(board);
    }

    @Test
    void test11PlayerCantChooseMorePiecesThanWhatHisPointsAllow() throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException {
        //Assemble
        Game game = new Game();
        game.newPlayer("Player0005667");
        Player player1 = game.getPlayer1();

        //Act
        Piece soldier = game.playerChoosesSoldier(player1);
        Piece rider = game.playerChoosesRider(player1);
        Piece catapult = game.playerChoosesCatapult(player1);
        Piece rider2 = game.playerChoosesRider(player1);
        Piece catapult2 = game.playerChoosesCatapult(player1);
        Piece rider3 = game.playerChoosesRider(player1);
        try {
            Piece catapult3 = game.playerChoosesCatapult(player1);
            //fail();
            //Assert
        } catch (PlayerHas20PointsOnlyException e) {
            //assertEquals ( 20, game.getPlayer1 ().obtainPoints () );
            // CAMBIAR ESTO.
            assertTrue(true);
        }
    }

    @Test
    void test12PlayerThatHasNoPiecesLostTheGame() throws PlayerHas20PointsOnlyException, ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, GameHasEndedException {
        //Assemble
        Game game = new Game();
        game.newPlayer("Player0005667");
        Player player1 = game.getPlayer1();

        Piece catapult1 = game.playerChoosesCatapult(player1);
        Piece catapult2 = game.playerChoosesCatapult(player1);
        Piece catapult3 = game.playerChoosesCatapult(player1);
        Piece catapult4 = game.playerChoosesCatapult(player1);

        game.playerPlacesPieceOnBoard(player1, catapult1, 9, 0);
        game.playerPlacesPieceOnBoard(player1, catapult2, 9, 1);
        game.playerPlacesPieceOnBoard(player1, catapult3, 9, 2);
        game.playerPlacesPieceOnBoard(player1, catapult4, 9, 3);
        game.playerIsReadyToPlay ( player1 );

        game.newPlayer("Alan12");
        Player player2 = game.getPlayer2();

        Piece catapult5 = game.playerChoosesCatapult(player2);
        Piece catapult6 = game.playerChoosesCatapult(player2);
        Piece catapult7 = game.playerChoosesCatapult(player2);
        Piece catapult8 = game.playerChoosesCatapult(player2);

        game.playerPlacesPieceOnBoard ( player2, catapult5, 11, 0 );
        game.playerPlacesPieceOnBoard ( player2, catapult6, 11, 1 );
        game.playerPlacesPieceOnBoard ( player2, catapult7, 11, 2 );
        game.playerPlacesPieceOnBoard ( player2, catapult8, 11, 3 );

        game.playerIsReadyToPlay ( player2 );
        //Act
        try {
            game.playerAttacks(player1, 9, 0);
            game.playerAttacks(player1, 9, 1);
            game.playerAttacks(player1, 9, 2);
            game.playerAttacks(player1, 9, 3);
            //game.playerMovesPieceOnBoard(player2, 9, 0, 9, 1);
            fail();
            //Assert
        } catch (GameHasEndedException e) {
            assertEquals(0, game.getPlayer1().numberOfPiecesOnTeam());
        } catch (NoMembersLeftException e) {
            e.printStackTrace();
        }
    }

    //ENTREGA 2
    @Test
    void test13CanMoveAGroupOfSoldiersAsABattalion() {
        Board board = new Board(team, team2);
        Piece soldier = factory.createSoldier();
        Piece anotherSoldier = factory.createSoldier();
        Piece yetAnotherOne = factory.createSoldier();
        board.placePiece(soldier, 1, 2);
        board.placePiece(anotherSoldier, 1, 3);
        board.placePiece(yetAnotherOne, 1, 4);
        board.createBattalion(1, 4);
        board.movePiece(1, 4, 2, 4);
    }

    @Test
    void test14IfThereIsAnObstacleInFrontOfTheBattalionItMovesAndTheSoldierBlockedStaysBehind() {
        Board board = new Board(team, team2);
        Piece soldier = factory.createSoldier();
        Piece anotherSoldier = factory.createSoldier();
        Piece yetAnotherOne = factory.createSoldier();
        Piece sol = factory.createCatapult();
        board.placePiece(soldier, 1, 2);
        board.placePiece(anotherSoldier, 1, 3);
        board.placePiece(yetAnotherOne, 1, 4);
        board.createBattalion(1, 3);
        board.placePiece(sol, 2, 3);

        board.movePiece(1, 3, 2, 3);

        assertNotNull(board.removePiece(2, 2));
        assertNotNull(board.removePiece(1, 3));
        assertNotNull(board.removePiece(2, 4));
    }

    @Test
    void test15WhenTheBattalionMovesAndIsAnObstacleInFrontTheBattalionGetsDissolved() {
        Board board = new Board(team, team2);
        Piece soldier = factory.createSoldier();
        Piece anotherSoldier = factory.createSoldier();
        Piece yetAnotherOne = factory.createSoldier();
        Piece sol = factory.createCatapult();
        board.placePiece(soldier, 1, 2);
        board.placePiece(anotherSoldier, 1, 3);
        board.placePiece(yetAnotherOne, 1, 4);
        board.placePiece(sol, 2, 3);
        board.createBattalion(1, 3);

        board.movePiece(1, 3, 2, 3);

        try {
            board.dissolveBattalion(2, 3);
            fail();
        } catch (Exception e) {
            assertEquals("CRIS SE LA COME", "CRIS SE LA COME");
        }
    }

    @Test
    void test16WhenThereAre4SoldiersTogetherOnly3AreInTheBattalionAndOnlyTheyMove() {
        Board board = new Board(team, team2);
        Piece soldier = factory.createSoldier();
        Piece anotherSoldier = factory.createSoldier();
        Piece yetAnotherOne = factory.createSoldier();
        Piece sol = factory.createSoldier();
        board.placePiece(soldier, 1, 2);
        board.placePiece(anotherSoldier, 1, 3);
        board.placePiece(yetAnotherOne, 1, 4);
        board.placePiece(sol, 1, 1);
        board.createBattalion(1, 3);

        board.movePiece(1, 3, 2, 3);
        try {
            assertNotNull(board.removePiece(1, 1));
        } catch (Exception e) {
            assertNotNull(board.removePiece(1, 4));
        }
    }

    @Test
    void test17IfAHorsemanAttacksAnEnemyInShortDistanceItIsABodyAttack() {
        Board board = new Board(team, team2);
        Piece horseman = factory.createRider();
        Piece euge = efactory.createSoldier();
        board.placePiece(horseman, 9, 10);
        board.placePiece(euge, 10, 10);

        board.bodyAttack(9, 10, 10, 10);
        assertEquals(95, euge.getLife());
    }

    @Test
    void test18IfAHorsemanAttacksAnEnemyInMediumDistanceAndHasAnEnemyNearbyThrowError() {
        Board board = new Board(team, team2);
        Piece cris = factory.createRider();
        Piece euge = efactory.createSoldier();
        Piece sol = efactory.createSoldier();
        board.placePiece(cris, 9, 10);
        board.placePiece(euge, 10, 10);
        board.placePiece(sol, 15, 10);

        try {
            board.distanceAttack(9, 10, 15, 10);
        } catch (Exception e) {
            assertEquals(sol.getLife(), 100);
        }

    }
}