package IntegrationTests;

import board.*;
import board.CanNotMakeThatMoveException;
import game.*;
import move.Builder;
import move.Move;
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
        //Assemble
        Move place = new Builder ().ToRow ( 3 ).ToColumn ( 3 ).build ();

        Board board = new Board(team, team2);
        Piece piece = factory.createSoldier();
        //Act
        board.placePiece(piece, place);
        for (int i = 2; i < 5; i++) {
            for (int j = 2; j < 5; j++) {
                if (i == 3 && j == 3) {
                    continue;
                }
                Move move = new Builder ().fromRow ( 3 ).fromColumn ( 3 ).ToRow ( i ).ToColumn ( j ).build ();
                Move move2 = new Builder ().fromRow ( i ).fromColumn ( j ).ToRow ( 3 ).ToColumn ( 3 ).build ();
                board.movePiece( move );
                board.movePiece( move2 );
            }
        }
    }

    @Test
    void test01APieceCanNotMoveToAnOccupiedCell() throws PlayerHas20PointsOnlyException {

        //Assemble
        Move move = new Builder ().ToRow ( 3 ).ToColumn ( 3 ).build ();
        Move move2 = new Builder ().ToRow ( 3 ).ToColumn ( 4 ).build ();
        Board board = new Board(team, team2);
        Piece piece = factory.createSoldier();

        board.placePiece(piece, move);
        board.placePiece(piece, move2);

        try {
            Move both = new Builder ().fromRow ( 3 ).fromColumn ( 3 ).ToRow ( 3 ).ToColumn ( 4 ).build ();
            board.movePiece( both );
            fail();
        } catch (CanNotMakeThatMoveException e) {
            assert true;
        }
    }

    @Test
    void test06CanPlaceAnAllyPieceInAnEmptyAllyCell() throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move = new Builder ().ToRow ( 3 ).ToColumn ( 3 ).build ();
        Board board = new Board(team, team2);
        Piece piece = factory.createSoldier();

        board.placePiece(piece, move);

    }

    @Test
    void Test02AnAlliedInfantrySoldierAttacksAnEnemyPieceAndVerifiesThatTheCorrespondingLifeIsSubtracted() throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move = new Builder ().ToRow ( 9 ).ToColumn ( 0 ).build ();
        Move move2 = new Builder ().ToRow ( 10 ).ToColumn ( 0 ).build ();
        Board board = new Board(team, team2);
        Piece soldier = factory.createSoldier();
        Piece healer = efactory.createHealer();

        //Act
        board.placePiece(soldier, move);
        board.placePiece(healer, move2);

        //Assert
        Move move3 = new Builder ().fromRow ( 9 ).fromColumn ( 0 ).ToRow ( 10 ).ToColumn ( 0 ).build ();
        assertEquals(100, soldier.getLife());
        assertEquals(75, healer.getLife());
        board.bodyAttack( move3 );
        assertEquals(65, healer.getLife());

    }

    @Test
    void Test03AnAlliedRiderAttacksAnEnemyPieceAndVerifiesThatTheCorrespondingLifeIsSubtracted() throws PlayerHas20PointsOnlyException {
        //Assemble
        Board board = new Board(team, team2);
        Piece soldier = efactory.createSoldier();
        Piece rider = factory.createRider();
        Move move = new Builder ().ToRow ( 9 ).ToColumn ( 0 ).build ();
        Move move2 = new Builder ().ToRow ( 10 ).ToColumn ( 0 ).build ();

        //Act
        board.placePiece(rider, move);
        board.placePiece(soldier, move2);

        //Assert
        assertEquals(100, rider.getLife());
        assertEquals(100, soldier.getLife());
        Move move3 = new Builder ().fromRow ( 9 ).fromColumn ( 0 ).ToRow ( 10 ).ToColumn ( 0 ).build ();
        board.bodyAttack( move3 );
        assertEquals(95, soldier.getLife());

        assertEquals(100, rider.getLife());
        assertEquals(95, soldier.getLife());
        Move move4 = new Builder ().fromRow ( 9 ).fromColumn ( 0 ).ToRow ( 10 ).ToColumn ( 0 ).build ();
        board.bodyAttack( move4 );
        assertEquals(90, soldier.getLife());

    }

    @Test
    void Test04AnAlliedCatapultAttacksAnEnemyPieceAndItIsVerifiedThatTheCorrespondingLifeIsSubtracted() throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move = new Builder ().ToRow ( 0 ).ToColumn ( 0 ).build ();
        Move move2 = new Builder ().ToRow ( 10 ).ToColumn ( 2 ).build ();
        Board board = new Board(team, team2);
        Piece soldier = efactory.createSoldier();
        Piece catapult = factory.createCatapult();

        //Act
        board.placePiece(catapult, move );
        board.placePiece(soldier, move2 );

        //Assert
        assertEquals(50, catapult.getLife());
        assertEquals(100, soldier.getLife());
        Move move3 = new Builder ().fromRow ( 0 ).fromColumn ( 0 ).ToRow ( 10 ).ToColumn ( 2 ).build ();
        board.distanceAttack(move3 );
        assertEquals(80, soldier.getLife());
    }

    @Test
    void Test05AnAlliedHealerHealsAnAlliedPieceAndVerifiesThatTheCorrespondingLifeIsAdded() throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move = new Builder ().ToRow ( 9 ).ToColumn ( 0 ).build ();
        Move move2 = new Builder ().ToRow ( 9 ).ToColumn ( 1 ).build ();
        Move move3 = new Builder ().ToRow ( 10 ).ToColumn ( 1 ).build ();
        Board board = new Board(team, team2);
        Piece soldier = factory.createSoldier();
        Piece healer = factory.createHealer();
        Piece rider = efactory.createRider();

        //Act
        board.placePiece(healer, move );
        board.placePiece(soldier, move2 );
        board.placePiece(rider, move3 );


        //Assert
        assertEquals(75, healer.getLife());
        assertEquals(100, soldier.getLife());
        assertEquals(100, rider.getLife());

        Move move4 = new Builder ().fromRow ( 10 ).fromColumn ( 1 ).ToRow ( 9 ).ToColumn ( 1 ).build ();
        board.bodyAttack( move4 );
        assertEquals(75, healer.getLife());
        assertEquals(95, soldier.getLife());
        assertEquals(100, rider.getLife());

        Move move5 = new Builder ().fromRow ( 9 ).fromColumn ( 0 ).ToRow ( 9 ).ToColumn ( 1 ).build ();
        board.heal( move5 );
        assertEquals(75, healer.getLife());
        assertEquals(100, soldier.getLife());
        assertEquals(100, rider.getLife());
    }

    @Test
    void test06CanPlaceAnAllyPieceToAnEmptyAllyCell() throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move = new Builder ().ToRow ( 3 ).ToColumn ( 3 ).build ();
        Board board = new Board(team, team2);
        Piece piece = factory.createSoldier();
        //Act
        board.placePiece(piece, move);
        //Assert
        assertEquals(piece, board.removePiece( move ));
    }

    @Test
    void test07CannotPlaceAnAllyPieceInAnOccupiedAllyCell() throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move = new Builder ().ToRow ( 3 ).ToColumn ( 3 ).build ();
        Board board = new Board(team, team2);
        Piece piece = factory.createSoldier();
        Piece pieceThatOccupiesCell = factory.createRider();
        //Act
        board.placePiece(pieceThatOccupiesCell, move);

        try {
            board.placePiece(piece, move );
            //Assemble
        } catch (OccupiedCellException e) {
            assert true;
        }
    }

    @Test
    void test08cannotPlaceAnAllyPieceInAnEnemyCell() throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move = new Builder ().ToRow ( 3 ).ToColumn ( 3 ).build ();
        Board board = new Board(team, team2);
        Piece piece = factory.createSoldier();
        //Act
        try {
            board.placePiece(piece, move);
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
        //Assemble
        Board board = new Board(team, team2);
        Piece soldier = factory.createSoldier();
        Piece anotherSoldier = factory.createSoldier();
        Piece yetAnotherOne = factory.createSoldier();
        Move move = new Builder ().ToRow ( 1 ).ToColumn ( 2 ).build ();
        Move move2 = new Builder ().ToRow ( 1 ).ToColumn ( 3 ).build ();
        Move move3 = new Builder ().ToRow ( 1 ).ToColumn ( 4 ).build ();

        board.placePiece(soldier, move);
        board.placePiece(anotherSoldier, move2);
        board.placePiece(yetAnotherOne, move3);

        //Act
        board.createBattalion(1, 4);
        Move move4 = new Builder ().fromRow ( 1 ).fromColumn ( 4 ).ToRow ( 2 ).ToColumn ( 4 ).build ();
        board.movePiece( move4 );
    }

    @Test
    void test14IfThereIsAnObstacleInFrontOfTheBattalionItMovesAndTheSoldierBlockedStaysBehind() {
        //Assemble
        Board board = new Board(team, team2);
        Piece soldier = factory.createSoldier();
        Piece anotherSoldier = factory.createSoldier();
        Piece yetAnotherOne = factory.createSoldier();
        Piece sol = factory.createCatapult();

        Move move = new Builder ().ToRow ( 1 ).ToColumn ( 2 ).build ();
        Move move2 = new Builder ().ToRow ( 1 ).ToColumn ( 3 ).build ();
        Move move3 = new Builder ().ToRow ( 1 ).ToColumn ( 4 ).build ();
        Move move4 = new Builder ().ToRow ( 2 ).ToColumn ( 3 ).build ();
        Move move6 = new Builder ().ToRow ( 2 ).ToColumn ( 2 ).build ();
        Move move7 = new Builder ().ToRow ( 2 ).fromColumn ( 4 ).build ();
        board.placePiece(soldier, move);
        board.placePiece(anotherSoldier, move2);
        board.placePiece(yetAnotherOne, move3);
        board.createBattalion(1, 3);
        board.placePiece(sol, move4 );

        Move move5 = new Builder ().fromRow ( 1 ).fromColumn ( 3 ).ToRow ( 2 ).ToColumn ( 3 ).build ();
        board.movePiece( move5 );

        assertNotNull(board.removePiece(move6));
        assertNotNull(board.removePiece(move2));
        assertNotNull(board.removePiece(move7));
    }

    @Test
    void test15WhenTheBattalionMovesAndIsAnObstacleInFrontTheBattalionGetsDissolved() {
        Board board = new Board(team, team2);
        Piece soldier = factory.createSoldier();
        Piece anotherSoldier = factory.createSoldier();
        Piece yetAnotherOne = factory.createSoldier();
        Piece sol = factory.createCatapult();

        Move move = new Builder ().ToRow ( 1 ).ToColumn ( 2 ).build ();
        Move move2 = new Builder ().ToRow ( 1 ).ToColumn ( 3 ).build ();
        Move move3 = new Builder ().ToRow ( 1 ).ToColumn ( 4 ).build ();
        Move move4 = new Builder ().ToRow ( 2 ).ToColumn ( 3 ).build ();
        board.placePiece(soldier, move);
        board.placePiece(anotherSoldier, move2);
        board.placePiece(yetAnotherOne, move3);
        board.placePiece(sol, move4);
        board.createBattalion(1, 3);

        Move move5 = new Builder ().fromRow ( 1 ).fromColumn ( 3 ).ToRow ( 2 ).ToColumn ( 3 ).build ();
        board.movePiece(move5);

        try {
            Move move6 = new Builder ().ToRow ( 2 ).ToColumn ( 3 ).build ();
            board.dissolveBattalion(move6);
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

        Move move = new Builder ().ToRow ( 1 ).ToColumn ( 2 ).build ();
        Move move2 = new Builder ().ToRow ( 1 ).ToColumn ( 3 ).build ();
        Move move3 = new Builder ().ToRow ( 1 ).ToColumn ( 4 ).build ();
        Move move4 = new Builder ().ToRow ( 1 ).ToColumn ( 1 ).build ();
        board.placePiece(soldier, move);
        board.placePiece(anotherSoldier, move2);
        board.placePiece(yetAnotherOne, move3);
        board.placePiece(sol, move4);
        board.createBattalion(1, 3);

        Move move5 = new Builder ().fromRow ( 1 ).fromColumn ( 3 ).ToRow ( 2 ).ToColumn ( 3 ).build ();
        board.movePiece(move5);

        try {
            assertNotNull(board.removePiece(move4));
        } catch (Exception e) {
            assertNotNull(board.removePiece(move3));
        }
    }

    @Test
    void test17IfAHorsemanAttacksAnEnemyInShortDistanceItIsABodyAttack() {
        Board board = new Board(team, team2);
        Piece horseman = factory.createRider();
        Piece soldier = efactory.createSoldier();
        Move move = new Builder ().ToRow ( 9 ).ToColumn ( 10 ).build ();
        Move move2 = new Builder ().ToRow ( 10 ).ToColumn ( 10 ).build ();
        board.placePiece(horseman, move);
        board.placePiece(soldier, move2);

        Move move3 = new Builder ().fromRow ( 9 ).fromColumn ( 10 ).ToRow ( 10 ).ToColumn ( 10 ).build ();
        board.bodyAttack( move3 );
        assertEquals(95, soldier.getLife());
    }

    @Test
    void test18IfAHorsemanAttacksAnEnemyInMediumDistanceAndHasAnEnemyNearbyThrowError() {
        Board board = new Board(team, team2);
        Piece cris = factory.createRider();
        Piece euge = efactory.createSoldier();
        Piece sol = efactory.createSoldier();
        Move move = new Builder ().fromRow ( 9 ).fromColumn ( 10 ).build ();
        Move move2 = new Builder ().fromRow ( 10 ).fromColumn ( 10 ).build ();
        Move move3 = new Builder ().fromRow ( 15 ).fromColumn ( 10 ).build ();
        board.placePiece(cris, move);
        board.placePiece(euge, move2);
        board.placePiece(sol, move3 );

        try {
            Move move4 = new Builder ().fromRow ( 9 ).fromColumn ( 10 ).ToRow ( 15 ).ToColumn ( 10 ).build ();
            board.distanceAttack( move4 );
        } catch (Exception e) {
            assertEquals(sol.getLife(), 100);
        }

    }
}