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
    Game game = new Game();
    Team team = new Team ( 1 ,game);
    Team team2 = new Team ( 2 ,game);
    PieceFactory factory = new PieceFactory ( team );
    PieceFactory efactory = new PieceFactory ( team2 );

    @Test
    void test00CanMoveAPieceFromRow3AndColumn3InAllPossibleWays () throws PlayerHas20PointsOnlyException {
        //Assemble
        Move place = new Builder ().ToRow ( 3 ).ToColumn ( 3 ).build ();

        Board board = new Board ( team, team2 );
        Piece piece = factory.createSoldier ();
        //Act
        board.placePiece ( piece, place );
        for (int i = 2; i < 5; i++) {
            for (int j = 2; j < 5; j++) {
                if (i == 3 && j == 3) {
                    continue;
                }
                Move move = new Builder ().fromRow ( 3 ).fromColumn ( 3 ).ToRow ( i ).ToColumn ( j ).build ();
                Move move2 = new Builder ().fromRow ( i ).fromColumn ( j ).ToRow ( 3 ).ToColumn ( 3 ).build ();
                board.movePiece ( move );
                board.movePiece ( move2 );
            }
        }
    }

    @Test
    void test01APieceCanNotMoveToAnOccupiedCell () throws PlayerHas20PointsOnlyException {

        //Assemble
        Move move = new Builder ().ToRow ( 3 ).ToColumn ( 3 ).build ();
        Move move2 = new Builder ().ToRow ( 3 ).ToColumn ( 4 ).build ();
        Board board = new Board ( team, team2 );
        Piece piece = factory.createSoldier ();

        board.placePiece ( piece, move );
        board.placePiece ( piece, move2 );

        try {
            Move both = new Builder ().fromRow ( 3 ).fromColumn ( 3 ).ToRow ( 3 ).ToColumn ( 4 ).build ();
            board.movePiece ( both );
            fail ();
        } catch (CanNotMakeThatMoveException e) {
            assert true;
        }
    }

    @Test
    void test06CanPlaceAnAllyPieceInAnEmptyAllyCell () throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move = new Builder ().ToRow ( 3 ).ToColumn ( 3 ).build ();
        Board board = new Board ( team, team2 );
        Piece piece = factory.createSoldier ();

        board.placePiece ( piece, move );

    }

    @Test
    void Test02AnAlliedInfantrySoldierAttacksAnEnemyPieceAndVerifiesThatTheCorrespondingLifeIsSubtracted () throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move = new Builder ().ToRow ( 9 ).ToColumn ( 0 ).build ();
        Move move2 = new Builder ().ToRow ( 10 ).ToColumn ( 0 ).build ();
        Board board = new Board ( team, team2 );
        Piece soldier = factory.createSoldier ();
        Piece healer = efactory.createHealer ();

        //Act
        board.placePiece ( soldier, move );
        board.placePiece ( healer, move2 );

        //Assert
        Move move3 = new Builder ().fromRow ( 9 ).fromColumn ( 0 ).ToRow ( 10 ).ToColumn ( 0 ).build ();
        assertEquals ( 100, soldier.getLife () );
        assertEquals ( 75, healer.getLife () );
        board.attack ( move3 );
        assertEquals ( 65, healer.getLife () );

    }

    @Test
    void Test03AnAlliedRiderAttacksAnEnemyPieceAndVerifiesThatTheCorrespondingLifeIsSubtracted () throws PlayerHas20PointsOnlyException {
        //Assemble
        Board board = new Board ( team, team2 );
        Piece soldier = efactory.createSoldier ();
        Piece rider = factory.createRider ();
        Move move = new Builder ().ToRow ( 9 ).ToColumn ( 0 ).build ();
        Move move2 = new Builder ().ToRow ( 10 ).ToColumn ( 0 ).build ();

        //Act
        board.placePiece ( rider, move );
        board.placePiece ( soldier, move2 );

        //Assert
        assertEquals ( 100, rider.getLife () );
        assertEquals ( 100, soldier.getLife () );
        Move move3 = new Builder ().fromRow ( 9 ).fromColumn ( 0 ).ToRow ( 10 ).ToColumn ( 0 ).build ();
        board.attack ( move3 );
        assertEquals ( 95, soldier.getLife () );

        assertEquals ( 100, rider.getLife () );
        assertEquals ( 95, soldier.getLife () );
        Move move4 = new Builder ().fromRow ( 9 ).fromColumn ( 0 ).ToRow ( 10 ).ToColumn ( 0 ).build ();
        board.attack ( move4 );
        assertEquals ( 90, soldier.getLife () );

    }

    @Test
    void Test04AnAlliedCatapultAttacksAnEnemyPieceAndItIsVerifiedThatTheCorrespondingLifeIsSubtracted () throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move = new Builder ().ToRow ( 0 ).ToColumn ( 0 ).build ();
        Move move2 = new Builder ().ToRow ( 10 ).ToColumn ( 2 ).build ();
        Board board = new Board ( team, team2 );
        Piece soldier = efactory.createSoldier ();
        Piece catapult = factory.createCatapult ();

        //Act
        board.placePiece ( catapult, move );
        board.placePiece ( soldier, move2 );

        //Assert
        assertEquals ( 50, catapult.getLife () );
        assertEquals ( 100, soldier.getLife () );
        Move move3 = new Builder ().fromRow ( 0 ).fromColumn ( 0 ).ToRow ( 10 ).ToColumn ( 2 ).build ();
        board.attack ( move3 );
        assertEquals ( 80, soldier.getLife () );
    }

    @Test
    void Test05AnAlliedHealerHealsAnAlliedPieceAndVerifiesThatTheCorrespondingLifeIsAdded () throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move = new Builder ().ToRow ( 9 ).ToColumn ( 0 ).build ();
        Move move2 = new Builder ().ToRow ( 9 ).ToColumn ( 1 ).build ();
        Move move3 = new Builder ().ToRow ( 10 ).ToColumn ( 1 ).build ();
        Board board = new Board ( team, team2 );
        Piece soldier = factory.createSoldier ();
        Piece healer = factory.createHealer ();
        Piece rider = efactory.createRider ();

        //Act
        board.placePiece ( healer, move );
        board.placePiece ( soldier, move2 );
        board.placePiece ( rider, move3 );


        //Assert
        assertEquals ( 75, healer.getLife () );
        assertEquals ( 100, soldier.getLife () );
        assertEquals ( 100, rider.getLife () );

        Move move4 = new Builder ().fromRow ( 10 ).fromColumn ( 1 ).ToRow ( 9 ).ToColumn ( 1 ).build ();
        board.attack ( move4 );
        assertEquals ( 75, healer.getLife () );
        assertEquals ( 95, soldier.getLife () );
        assertEquals ( 100, rider.getLife () );

        Move move5 = new Builder ().fromRow ( 9 ).fromColumn ( 0 ).ToRow ( 9 ).ToColumn ( 1 ).build ();
        board.attack ( move5 );
        assertEquals ( 75, healer.getLife () );
        assertEquals ( 100, soldier.getLife () );
        assertEquals ( 100, rider.getLife () );
    }

    @Test
    void test06CanPlaceAnAllyPieceToAnEmptyAllyCell () throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move1 = new Builder ().ToRow ( 3 ).ToColumn ( 3 ).build ();
        Board board = new Board ( team, team2 );
        Piece piece = factory.createSoldier ();
        //Act
        board.placePiece ( piece, move1 );
        //Assert
        Move move = new Builder ().fromRow ( 3 ).fromColumn ( 3 ).build ();
        assertEquals ( piece, board.removePiece ( move ) );
    }

    @Test
    void test07CannotPlaceAnAllyPieceInAnOccupiedAllyCell () throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move = new Builder ().ToRow ( 3 ).ToColumn ( 3 ).build ();
        Board board = new Board ( team, team2 );
        Piece piece = factory.createSoldier ();
        Piece pieceThatOccupiesCell = factory.createRider ();
        //Act
        board.placePiece ( pieceThatOccupiesCell, move );

        try {
            board.placePiece ( piece, move );
            //Assemble
        } catch (OccupiedCellException e) {
            assert true;
        }
    }

    @Test
    void test08cannotPlaceAnAllyPieceInAnEnemyCell () throws PlayerHas20PointsOnlyException {
        //Assemble
        Move move = new Builder ().ToRow ( 3 ).ToColumn ( 3 ).build ();
        Board board = new Board ( team, team2 );
        Piece piece = factory.createSoldier ();
        //Act
        try {
            board.placePiece ( piece, move );
            //Assert
        } catch (EnemyCellException e) {
            assert true;
        }
    }

    @Test
    void test09BoardIsProperlyCreated () {
        //Assemble & Act
        Board board = new Board ( team, team2 );
        //Assert
        assertNotNull ( board );
    }

    @Test
    void test11PlayerCantChooseMorePiecesThanWhatHisPointsAllow () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Player0005667" );
        Player player1 = game.getPlayer1 ();

        //Act
        Piece soldier = game.playerChoosesSoldier (  );
        Piece rider = game.playerChoosesRider (  );
        Piece catapult = game.playerChoosesCatapult (  );
        Piece rider2 = game.playerChoosesRider (  );
        Piece catapult2 = game.playerChoosesCatapult (  );
        Piece rider3 = game.playerChoosesRider (  );
        try {
            Piece catapult3 = game.playerChoosesCatapult (  );
        } catch (PlayerHas20PointsOnlyException e) {
            //assertEquals ( 20, game.getPlayer1 ().obtainPoints () );
            assertTrue ( true );
        }
    }

    @Test
    void test12PlayerThatHasNoPiecesLostTheGame () {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Player0005667" );
        Player player1 = game.getPlayer1 ();
        game.newPlayer ( "Alan12" );
        Player player2 = game.getPlayer2 ();

        Piece catapult1 = game.playerChoosesCatapult ( );
        game.playerPlacesPieceOnBoard (  catapult1, 9, 0 );

        Piece soldier = game.playerChoosesSoldier (  );
        game.playerPlacesPieceOnBoard ( soldier, 11, 0 );

        Piece catapult2 = game.playerChoosesSoldier (  );
        game.playerPlacesPieceOnBoard ( catapult2, 9, 1 );

        Piece soldier2 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard (soldier2, 11, 3 );

        Piece catapult3 = game.playerChoosesCatapult ();
        game.playerPlacesPieceOnBoard (  catapult3, 9, 2 );

        Piece soldier3 = game.playerChoosesSoldier (  );
        game.playerPlacesPieceOnBoard (  soldier3, 11, 4 );

        Piece catapult4 = game.playerChoosesCatapult ();
        game.playerPlacesPieceOnBoard ( catapult4, 9, 3 );

        game.playerIsReadyToPlay ( player1 );
        game.playerIsReadyToPlay ( player2 );

        //Act
        game.playerAttacks ( 9, 0, 11, 0 );
        game.playerMovesPieceOnBoard (  11,0 ,11,1 );
        game.playerAttacks (9, 0, 11, 1 );
        game.playerMovesPieceOnBoard (  11,1 ,11,0 );
        game.playerAttacks ( 9, 2, 11, 0 );
        game.playerMovesPieceOnBoard (  11,0 ,11,1 );
        game.playerAttacks ( 9, 3, 11, 1 );

        //Assert
        try {
            game.playerMovesPieceOnBoard (  11,1,11,2 );
            game.playerMovesPieceOnBoard ( 9, 1, 10, 1 );
        } catch (GameHasEndedException e) {
            assertEquals ( 0, game.removePieceFromBoard ( 9, 1 ) );
        }
    }

    //ENTREGA 2
    @Test
    void test13CanMoveAGroupOfSoldiersAsABattalion () throws ThereCantBeTwoPlayersOnTheSameTeamException, GameHasEndedException, PlayerHas20PointsOnlyException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Pepe" );
        game.newPlayer ( "Juan" );
        Piece cat1 = game.playerChoosesRider (  );
        game.playerPlacesPieceOnBoard ( cat1,  9, 1 );

        Piece sol1 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard (  sol1, 12, 1 );

        Piece cat2 = game.playerChoosesRider ( );
        game.playerPlacesPieceOnBoard ( cat2, 9, 2 );

        Piece sol2 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard (  sol2, 12, 2 );

        Piece cat3 = game.playerChoosesRider ( );
        game.playerPlacesPieceOnBoard ( cat3, 9, 3 );

        Piece sol3 = game.playerChoosesSoldier (  );
        game.playerPlacesPieceOnBoard ( sol3, 12, 3 );

        Piece cat4 = game.playerChoosesRider (  );
        game.playerPlacesPieceOnBoard ( cat4, 9, 4 );

        Piece sol4 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard (  sol4, 14, 1 );

        game.playerIsReadyToPlay ( game.getPlayer1 () );
        game.playerIsReadyToPlay ( game.getPlayer2 () );

        game.playerMovesPieceOnBoard (  9,4, 9,5 );
        game.playerChoosesBattalion (  12, 2 );
        // Act
        game.playerMovesPieceOnBoard (  12, 2, 13, 2 );
        game.playerMovesPieceOnBoard (  9,5, 9,4 );
        // Assert

        assertEquals ( sol1, game.getPiece (new Builder().fromRow(13).fromColumn(1).build() ) );
        assertEquals ( sol2, game.getPiece (new Builder().fromRow(13).fromColumn(2).build() ) );
        assertEquals ( sol3, game.getPiece (new Builder().fromRow(13).fromColumn(3).build() ) );
    }

    @Test
    void test14IfThereIsAnObstacleInFrontOfTheBattalionItMovesAndTheSoldierBlockedStaysBehind () throws ThereCantBeTwoPlayersOnTheSameTeamException, GameHasEndedException, PlayerHas20PointsOnlyException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Pepe" );
        game.newPlayer ( "Juan" );
        Piece cat1 = game.playerChoosesRider (  );
        game.playerPlacesPieceOnBoard ( cat1,  9, 1 );

        Piece sol1 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard (  sol1, 12, 1 );

        Piece cat2 = game.playerChoosesRider ( );
        game.playerPlacesPieceOnBoard ( cat2, 9, 2 );

        Piece sol2 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard (  sol2, 12, 2 );

        Piece cat3 = game.playerChoosesRider ( );
        game.playerPlacesPieceOnBoard ( cat3, 9, 3 );

        Piece sol3 = game.playerChoosesSoldier (  );
        game.playerPlacesPieceOnBoard ( sol3, 12, 3 );

        Piece cat4 = game.playerChoosesRider (  );
        game.playerPlacesPieceOnBoard (cat4, 9, 4 );

        Piece sol4 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard ( sol4, 13, 1 );

        game.playerIsReadyToPlay ( game.getPlayer1 () );
        game.playerIsReadyToPlay ( game.getPlayer2 () );

        game.playerMovesPieceOnBoard ( 9,4, 9,5 );
        game.playerChoosesBattalion (  12, 2 );

        // Act
        game.playerMovesPieceOnBoard ( 12, 2, 13, 2 );

        // Assert
        assertEquals ( sol1, game.getPiece (new Builder().fromRow(12).fromColumn(1).build() ) );
        assertEquals ( sol2, game.getPiece (new Builder().fromRow(13).fromColumn(2).build() ) );
        assertEquals ( sol3, game.getPiece (new Builder().fromRow(13).fromColumn(3).build() ) );
        assertEquals ( sol4, game.getPiece (new Builder().fromRow(13).fromColumn(1).build() ) );
    }

    @Test
    void test15WhenTheBattalionMovesAndIsAnObstacleInFrontTheBattalionGetsDissolved () throws GameHasEndedException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Pepe" );
        game.newPlayer ( "Juan" );
        Piece cat1 = game.playerChoosesRider (  );
        game.playerPlacesPieceOnBoard ( cat1,  9, 1 );

        Piece sol1 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard (  sol1, 12, 1 );

        Piece cat2 = game.playerChoosesRider ( );
        game.playerPlacesPieceOnBoard ( cat2, 9, 2 );

        Piece sol2 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard (  sol2, 12, 2 );

        Piece cat3 = game.playerChoosesRider ( );
        game.playerPlacesPieceOnBoard ( cat3, 9, 3 );

        Piece sol3 = game.playerChoosesSoldier (  );
        game.playerPlacesPieceOnBoard ( sol3, 12, 3 );

        Piece cat4 = game.playerChoosesRider (  );
        game.playerPlacesPieceOnBoard ( cat4, 9, 4 );

        Piece sol4 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard ( sol4, 13, 1 );

        game.playerIsReadyToPlay ( game.getPlayer1 () );
        game.playerIsReadyToPlay ( game.getPlayer2 () );

        game.playerMovesPieceOnBoard (  9,4, 9,5 );
        game.playerChoosesBattalion (  12, 2 );
        // Act
        game.playerMovesPieceOnBoard ( 12, 2, 13, 2 );

        // Assert
        game.playerMovesPieceOnBoard (   9,5, 9,4 );
        game.playerMovesPieceOnBoard ( 12, 1, 11, 1 );
        game.playerMovesPieceOnBoard (  9,4, 9,5 );
        game.playerMovesPieceOnBoard (  13, 2, 14, 2 );
        game.playerMovesPieceOnBoard (   9,5, 9,4 );
        game.playerMovesPieceOnBoard (  13, 3, 12, 3 );

        assertEquals ( sol1, game.getPiece (new Builder().fromRow(11).fromColumn(1 ).build() ));
        assertEquals ( sol2, game.getPiece (new Builder().fromRow(14).fromColumn(2 ).build() ));
        assertEquals ( sol3, game.getPiece (new Builder().fromRow(12).fromColumn(3).build() ) );

    }

    @Test
    void test16WhenThereAre4SoldiersTogetherOnly3AreInTheBattalionAndOnlyTheyMove () throws GameHasEndedException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Pepe" );
        game.newPlayer ( "Juan" );
        Piece cat1 = game.playerChoosesRider (  );
        game.playerPlacesPieceOnBoard ( cat1,  9, 1 );

        Piece sol1 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard (  sol1, 12, 1 );

        Piece cat2 = game.playerChoosesRider ( );
        game.playerPlacesPieceOnBoard ( cat2, 9, 2 );

        Piece sol2 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard (  sol2, 12, 2 );

        Piece cat3 = game.playerChoosesRider ( );
        game.playerPlacesPieceOnBoard ( cat3, 9, 3 );

        Piece sol3 = game.playerChoosesSoldier (  );
        game.playerPlacesPieceOnBoard ( sol3, 12, 3 );

        Piece cat4 = game.playerChoosesRider (  );
        game.playerPlacesPieceOnBoard ( cat4, 9, 4 );

        Piece sol4 = game.playerChoosesSoldier (  );
        game.playerPlacesPieceOnBoard (  sol4, 13, 1 );
        game.playerIsReadyToPlay ( game.getPlayer1 () );
        game.playerIsReadyToPlay ( game.getPlayer2 () );

        game.playerMovesPieceOnBoard (  9,4, 9,5 );
        game.playerChoosesBattalion (  12, 2 );
        // Act
        game.playerMovesPieceOnBoard ( 12, 2, 13, 2 );
        // Assert

        assertEquals ( sol4, game.getPiece (new Builder().fromRow(13).fromColumn(1).build() ) );
        assertEquals ( sol2, game.getPiece (new Builder().fromRow(13).fromColumn(2).build() ) );
        assertEquals ( sol3, game.getPiece (new Builder().fromRow(13).fromColumn(3).build() ) );
        assertEquals ( sol1, game.getPiece (new Builder().fromRow(12).fromColumn( 1).build() ) );
    }

    @Test
    void test17IfAHorsemanAttacksAnEnemyInShortDistanceItIsABodyAttack () throws PlayerHas20PointsOnlyException {
        Board board = new Board ( team, team2 );
        Piece horseman = factory.createRider ();
        Piece soldier = efactory.createSoldier ();
        Move move = new Builder ().ToRow ( 9 ).ToColumn ( 10 ).build ();
        Move move2 = new Builder ().ToRow ( 10 ).ToColumn ( 10 ).build ();
        board.placePiece ( horseman, move );
        board.placePiece ( soldier, move2 );

        Move move3 = new Builder ().fromRow ( 9 ).fromColumn ( 10 ).ToRow ( 10 ).ToColumn ( 10 ).build ();
        board.attack ( move3 );
        assertEquals ( 95, soldier.getLife () );
    }

    @Test
    void test18IfAHorsemanAttacksAnEnemyInMediumDistanceAndHasAnEnemyNearbyThrowError () throws PlayerHas20PointsOnlyException {
        Board board = new Board ( team, team2 );
        Piece cris = factory.createRider ();
        Piece euge = efactory.createSoldier ();
        Piece sol = efactory.createSoldier ();
        Move move = new Builder ().ToRow ( 9 ).ToColumn ( 10 ).build ();
        Move move2 = new Builder ().ToRow ( 10 ).ToColumn ( 10 ).build ();
        Move move3 = new Builder ().ToRow ( 15 ).ToColumn ( 10 ).build ();
        board.placePiece ( cris, move );
        board.placePiece ( euge, move2 );
        board.placePiece ( sol, move3 );

        try {
            Move move4 = new Builder ().fromRow ( 9 ).fromColumn ( 10 ).ToRow ( 15 ).ToColumn ( 10 ).build ();
            board.attack ( move4 );
        } catch (Exception e) {
            assertEquals ( sol.getLife (), 100 );
        }

    }
    @Test
    void test19WhenACatapultAttacksIfTheAttackedPiecesHaveOtherPiecesAroundTheyGetAttacked(){
        Game game = new Game ();
        game.newPlayer ( "Pepe" );
        game.newPlayer ( "Juan" );
        Piece cris = game.playerChoosesSoldier (  );
        game.playerPlacesPieceOnBoard (  cris, 9, 1 );

        Piece sol = game.playerChoosesRider (  );
        game.playerPlacesPieceOnBoard (  sol, 10, 1 );

        Piece nacho = game.playerChoosesCatapult (  );
        game.playerPlacesPieceOnBoard (  nacho, 0, 3 );

        Piece euge = game.playerChoosesHealer (  );
        game.playerPlacesPieceOnBoard (euge, 10, 2 );

        game.playerIsReadyToPlay ( game.getPlayer1 () );
        game.playerIsReadyToPlay ( game.getPlayer2 () );
        game.playerAttacks(0,3,10,1);
        assertEquals ( cris.getLife(), 80 );
        assertEquals ( sol.getLife(), 80 );
        assertEquals ( euge.getLife(),55);
        assertEquals ( nacho.getLife(), 50 );


    }
}