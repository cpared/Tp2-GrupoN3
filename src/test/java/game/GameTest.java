package game;

import Face.ThereAreOnlyTwoPlayersPerGameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import piece.Piece;
import piece.Soldier;
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
        Assertions.assertEquals ( "Mike", game.getPlayer1 ().getName () );
    }

    @Test
    void test02NewGameWithPlayerMikeAndWithPlayerRickHasBothPlayers () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException {
        //Assemble
        Game game = new Game ();
        //Act
        game.newPlayer ( "Mike" );
        game.newPlayer ( "Rick" );
        //Assert
        Assertions.assertEquals ( "Mike", game.getPlayer1 ().getName () );
        Assertions.assertEquals ( "Rick", game.getPlayer2 ().getName () );
    }

    @Test
    void test03GameCanOnlyHaveTwoPlayers () throws ThereAreOnlyTwoPlayersPerGameException {
        //Assemble
        Game game = new Game ();
        //Act
        try {
            game.newPlayer ( "Rose" );
            game.newPlayer ( "Patty" );
            game.newPlayer ( "Alfred" );
            fail ();
            //Assert
        } catch (ThereAreOnlyTwoPlayersPerGameException e) {
            e.printStackTrace ();
        } catch (ThereCantBeTwoPlayersOnTheSameTeamException e) {
            Assertions.assertEquals ( "Rose", game.getPlayer1 ().getName () );
            Assertions.assertEquals ( "Patty", game.getPlayer2 ().getName () );
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

        } catch (ThereAreOnlyTwoPlayersPerGameException e) {
            e.printStackTrace ();
        } catch (ThereCantBeTwoPlayersOnTheSameTeamException e) {
            e.printStackTrace ();
        }
        //Assert
        Assertions.assertEquals ( Gold.class, game.getPlayer1 ().getTeam ().getClass () );
        Assertions.assertEquals ( Blue.class, game.getPlayer2 ().getTeam ().getClass () );
    }

    // Tests related to moves.

    @Test
    void test05PlayerInGameCanChooseAPiece () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Rose" );
        //Act
        Piece piece= game.playerChoosesPiece ( game.getPlayer1 (), "SOLDIER" );
        //Assert
        Assertions.assertEquals ( 19, game.getPlayer1 ().obtainPoints () );
    }

    @Test
    void test06PlayerInGameCanPlaceAPieceOnTheBoard () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Rose" );
        Piece piece= game.playerChoosesPiece ( game.getPlayer1 (), "SOLDIER" );
        //Act
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), piece, 2,0 );
        //Assert
        Assertions.assertEquals ( piece, game.getBoard ().removePiece ( 2,0 ) );
    }

    @Test
    void test07APieceOnTheBoardCanBeRemoved () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException, GameHasEndedException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Rose" );
        game.newPlayer ( "Doyle" );
        Piece piece= game.playerChoosesPiece ( game.getPlayer1 (), "SOLDIER" );
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), piece, 2,0 );
        Piece piece2= game.playerChoosesPiece ( game.getPlayer1 (), "SOLDIER" );
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), piece2, 3,0 );
        Piece piece3= game.playerChoosesPiece ( game.getPlayer2 (), "SOLDIER" );
        game.playerPlacesPieceOnBoard ( game.getPlayer2 (), piece3, 11,0 );

        //Act
        Piece removed = game.removePieceFromBoard ( game.getPlayer1 (), 2,0 );
        //Assert
        Assertions.assertEquals ( piece, removed );
    }

    @Test
    void test08PlayerCanMoveAPieceOnTheBoard () throws ThereAreOnlyTwoPlayersPerGameException, ThereCantBeTwoPlayersOnTheSameTeamException, PlayerHas20PointsOnlyException, GameHasEndedException {
        //Assemble
        Game game = new Game ();
        game.newPlayer ( "Rose" );
        game.newPlayer ( "Doyle" );
        Piece piece= game.playerChoosesPiece ( game.getPlayer1 (), "SOLDIER" );
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), piece, 2,0 );
        Piece piece2= game.playerChoosesPiece ( game.getPlayer1 (), "SOLDIER" );
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), piece2, 3,0 );
        Piece piece3= game.playerChoosesPiece ( game.getPlayer2 (), "SOLDIER" );
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
        Piece piece= game.playerChoosesPiece ( game.getPlayer1 (), "SOLDIER" );
        game.playerPlacesPieceOnBoard ( game.getPlayer1 (), piece, 2,0 );
        Piece piece3= game.playerChoosesPiece ( game.getPlayer2 (), "SOLDIER" );
        game.playerPlacesPieceOnBoard ( game.getPlayer2 (), piece3, 11,0 );

        //Act
        try{
            game.playerAttacks ( game.getPlayer1 (),2,0 );
        } catch (GameHasEndedException e) {
            e.getMessage ();
        }
        //Assert
        Assertions.assertEquals ( 0, game.getPlayer1 ().getTeam ().numberOfMembersStillOnTeam () );
    }
}