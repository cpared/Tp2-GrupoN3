package game;

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
        Assertions.assertNotNull ( game.getPlayer1( ) );
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
        Assertions.assertFalse (  game.getPlayer1 ().equals ( game.getPlayer2 () ) );

    }

    @Test
    void test03GameCanOnlyHaveTwoPlayers () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException {
        //Assemble
        Game game = new Game ();
        //Act

        game.newPlayer ( "Rose" );
        game.newPlayer ( "Patty" );
        Player player1 = game.getPlayer1 ( );
        Player player2 = game.getPlayer2 ( );

        try {
            game.newPlayer ( "Alfred" );
            fail ();

            //Assert
        } catch (ThereAreOnlyTwoPlayersPerGameException e) {
            e.printStackTrace ();
        } catch (ThereCantBeTwoPlayersOnTheSameTeamException e) {
            Assertions.assertTrue ( player1.equals (  game.getPlayer1 () ) );
            Assertions.assertTrue ( player2.equals (  game.getPlayer2 () ) );
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
            game.playerChoosesCatapult ( game.getPlayer1 () );
            game.playerChoosesCatapult ( game.getPlayer2 () );
            game.playerChoosesHealer ( game.getPlayer2 () );


        } catch (ThereAreOnlyTwoPlayersPerGameException e) {
            e.printStackTrace ();
        } catch (ThereCantBeTwoPlayersOnTheSameTeamException e) {
            e.printStackTrace ();
        } catch (PlayerHas20PointsOnlyException e) {
            e.printStackTrace ();
        }
        //Assert
        Assertions.assertNotEquals (  game.getPlayer2 ().numberOfPiecesOnTeam () , game.getPlayer1 ().numberOfPiecesOnTeam () );

    }

    // Tests related to moves.

    @Test
    void test05PlayerInGameCanChooseAPiece () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Rose" );
        //Act
        Piece piece= game.playerChoosesSoldier ( game.getPlayer1 () );
        //Assert
        Assertions.assertNotNull ( piece );
    }

    @Test
    void test06PlayerInGameCanPlaceAPieceOnTheBoard () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Rose" );
        Piece piece= game.playerChoosesSoldier ( game.getPlayer1 () );
        //Act
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), piece, 2,0 );
        //Assert
        Assertions.assertEquals ( piece, game.getBoard ().removePiece ( 2,0 ) );
    }

    @Test
    void test07APieceOnTheBoardCanBeRemoved () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException, GameHasEndedException, NoMembersLeftException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Rose" );
        game.newPlayer ( "Doyle" );
        Piece piece= game.playerChoosesSoldier ( game.getPlayer1 () );
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), piece, 2,0 );
        Piece piece2= game.playerChoosesSoldier ( game.getPlayer1 () );
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), piece2, 3,0 );
        Piece piece3= game.playerChoosesSoldier ( game.getPlayer2 () );
        game.playerPlacesPieceOnBoard ( game.getPlayer2 (), piece3, 11,0 );

        //Act
        Piece removed = game.removePieceFromBoard ( game.getPlayer1 (), 2,0 );
        //Assert
        Assertions.assertEquals ( piece, removed );
    }

    @Test
    void test08PlayerCanMoveAPieceOnTheBoard () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException, GameHasEndedException, NoMembersLeftException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Rose" );
        game.newPlayer ( "Doyle" );
        Piece piece= game.playerChoosesSoldier ( game.getPlayer1 () );
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), piece, 2,0 );
        Piece piece2= game.playerChoosesSoldier ( game.getPlayer1 () );
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), piece2, 3,0 );
        Piece piece3= game.playerChoosesSoldier ( game.getPlayer2 () );
        game.playerPlacesPieceOnBoard ( game.getPlayer2 (), piece3, 11,0 );

        //Act
        game.playerMovesPieceOnBoard ( game.getPlayer1 (),2, 0,2,1 );
        Piece removed = game.removePieceFromBoard ( game.getPlayer1 (), 2,1 );
        //Assert
        Assertions.assertEquals ( piece, removed );
    }

    @Test
    void test09GameIsWonWhenAPlayerHasNoPiecesLeft () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException, GameHasEndedException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Rose" );
        game.newPlayer ( "Doyle" );
        Player player1 = game.getPlayer1 ();
        Player player2 = game.getPlayer2 ();
        Piece piece= game.playerChoosesSoldier ( player1 );
        game.playerPlacesPieceOnBoard ( player1, piece, 2,0 );
        Piece piece3= game.playerChoosesSoldier ( player2 );
        game.playerPlacesPieceOnBoard ( player2, piece3, 11,0 );

        //Act
        try{
            game.playerAttacks ( player1,2,0 );
        } catch (GameHasEndedException e) {
            e.getMessage ();
        } catch (NoMembersLeftException e) {
            e.printStackTrace ();
        }
        //Assert
        Assertions.assertTrue (game.isNumberOfMembersOnTeam ( player1 , 0 ) );
    }
}