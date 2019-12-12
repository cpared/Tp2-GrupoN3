package game;

import move.Builder;
import move.Move;
import player.Player;
import player.ThereAreOnlyTwoPlayersPerGameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import piece.Piece;
import player.PlayerHas20PointsOnlyException;
import team.*;

import static org.junit.jupiter.api.Assertions.fail;

class GameTest {

    // Tests related to the players.
    @Test
    void test01NewGameWithPlayerMikeHasMikeAsAPlayer () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException {
        //Assemble
        Game game = new Game ();
        //Act
        game.newPlayer ( "Mike" );
        //Assert
        Assertions.assertNotNull ( game.getPlayer1 () );
    }

    @Test
    void test02NewGameWithPlayerMikeAndWithPlayerRickHasBothPlayers () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException {
        //Assemble
        Game game = new Game ();
        //Act
        game.newPlayer ( "Mike" );
        game.newPlayer ( "Rick" );
        //Assert
        //TODO check it equals override.
        Assertions.assertFalse ( game.getPlayer1 ().equals ( game.getPlayer2 () ) );

    }

    @Test
    void test03GameCanOnlyHaveTwoPlayers () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException {
        //Assemble
        Game game = new Game ();
        //Act

        game.newPlayer ( "Rose" );
        game.newPlayer ( "Patty" );
        Player player1 = game.getPlayer1 ();
        Player player2 = game.getPlayer2 ();

        try {
            game.newPlayer ( "Alfred" );

            //Assert
        } catch (ThereAreOnlyTwoPlayersPerGameException | ThereCantBeTwoPlayersOnTheSameTeamException e) {
            Assertions.assertTrue ( player1.equals ( game.getPlayer1 () ) );
            Assertions.assertTrue ( player2.equals ( game.getPlayer2 () ) );
        }

    }

    // Tests related to teams.
    @Test
    void test04GameAssignsTwoDifferentTeams () throws ThereAreOnlyTwoPlayersPerGameException {
        //Assemble
        Game game = new Game ();
        //Act
        try {
            game.newPlayer ( "Rose" );
            game.newPlayer ( "Patty" );
            Piece piece = game.playerChoosesCatapult ( );
            game.playerPlacesPieceOnBoard ( piece, 0,0);
            Piece piece2 = game.playerChoosesCatapult (  );
            game.playerPlacesPieceOnBoard (  piece2, 13,13);
            game.playerIsReadyToPlay (  game.getPlayer1 () );
            game.playerChoosesHealer ( );



        } catch (ThereAreOnlyTwoPlayersPerGameException | PlayerHas20PointsOnlyException | ThereCantBeTwoPlayersOnTheSameTeamException e) {
            e.printStackTrace ();
        }
        //Assert
        Assertions.assertTrue ( game.getPlayer2 ().isNumberOfPiecesOnTeam ( 2 ) );
        Assertions.assertTrue ( game.getPlayer1 ().isNumberOfPiecesOnTeam ( 1 ) );
    }

    // Tests related to moves.

    @Test
    void test05PlayerInGameCanChooseAPiece () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Rose" );
        //Act
        Piece piece = game.playerChoosesSoldier ( );
        //Assert
        Assertions.assertNotNull ( piece );
    }

    @Test
    void test06PlayerInGameCanPlaceAPieceOnTheBoard () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException, GameHasEndedException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Rose" );
        game.newPlayer ( "Mike" );
        Piece piece = game.playerChoosesSoldier ( );
        //Act
        game.playerPlacesPieceOnBoard ( piece, 2, 0 );
        //Assert
        Move move = new Builder ().fromRow ( 2 ).fromColumn ( 0 ).build ();
        Assertions.assertEquals ( piece, game.getPiece ( move ) );
    }

    @Test
    void test07APieceOnTheBoardCanBeRemoved () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException, GameHasEndedException, NoMembersLeftException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Rose" );
        game.newPlayer ( "Doyle" );
        Piece piece = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard (  piece, 2, 0 );

        Piece piece3 = game.playerChoosesSoldier (  );
        game.playerPlacesPieceOnBoard (  piece3, 11, 0 );
        game.playerIsReadyToPlay ( game.getPlayer2 ( ));

        Piece piece2 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard (piece2, 3, 0 );

        game.playerIsReadyToPlay ( game.getPlayer1 ( ));

        Move move1 = new Builder ().fromRow ( 2 ).fromColumn ( 0 ).build ();
        //Act
        Piece removed = game.getPiece ( move1);
        //Assert
        Assertions.assertEquals ( piece, removed );
    }

    @Test
    void test08PlayerCanMoveAPieceOnTheBoard () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException, GameHasEndedException, NoMembersLeftException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Rose" );
        game.newPlayer ( "Doyle" );
        Piece piece = game.playerChoosesSoldier (  );
        game.playerPlacesPieceOnBoard (  piece, 2, 0 );

        Piece piece3 = game.playerChoosesSoldier (  );
        game.playerPlacesPieceOnBoard (  piece3, 11, 0 );

        Piece piece2 = game.playerChoosesSoldier ( );
        game.playerPlacesPieceOnBoard (  piece2, 3, 0 );
        game.playerIsReadyToPlay ( game.getPlayer1 ( ));
        game.playerIsReadyToPlay ( game.getPlayer2 ( ));
        Move move = new Builder ().fromRow ( 2 ).fromColumn ( 0 ).build ();
        //Act
        game.playerMovesPieceOnBoard (  2, 0, 2, 1 );
        game.playerMovesPieceOnBoard (   11, 0, 12, 1 );
        game.playerMovesPieceOnBoard (  2, 1, 2, 0 );
        Piece removed = game.getPiece ( move );
        //Assert
        Assertions.assertEquals ( piece, removed );
    }
}