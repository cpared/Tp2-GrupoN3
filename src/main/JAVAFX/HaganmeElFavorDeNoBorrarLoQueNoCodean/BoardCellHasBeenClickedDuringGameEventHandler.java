package HaganmeElFavorDeNoBorrarLoQueNoCodean;

import boardFx.ButtonCell;
import boardFx.ButtonPiece;
import game.Game;
import game.GameHasEndedException;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Pair;
import piece.Piece;
import player.Player;


import java.io.File;


public class BoardCellHasBeenClickedDuringGameEventHandler implements EventHandler<MouseEvent> {
    private ButtonPiece lastChosen;
    private Game game;
    private  ButtonCell button;
    private  MediaPlayer mediaPlayer;
    private  Turn turn;
    private ButtonPiece lastClicked;
    private Pair<Integer, Integer> pair = null;
    private ButtonCell lastButton = null;

    public BoardCellHasBeenClickedDuringGameEventHandler ( ButtonPiece lastChosen, ButtonPiece lastClicked, Game game, ButtonCell button ,Turn turn ) {
        this.lastChosen = lastChosen;
        this.game = game;
        this.button = button;
        this.turn = turn;
        this.lastClicked = lastClicked;
    }

    @Override
    public void handle ( MouseEvent mouseEvent ) {
        /*
        if (pair == null && !(button.emptyImage ())) {
            pair = button.getPosition ();
            lastButton = button;
        } else if (pair != null) {
            Pair<Integer, Integer> newPair = button.getPosition ();
            try {
                privateMethod ( newPair, game.getAvailablePlayer (), button );
                lastButton = null;
                pair = null;
            } catch (GameHasEndedException e) {
                String path = "src/main/JAVAFX/SoundEffects/readytoplay.mp3";
                Media media = new Media ( new File ( path ).toURI ().toString () );
                MediaPlayer mp = new MediaPlayer ( media );
                mp.play ();
                sceneFinal ( game.getAvailablePlayer (), border );
            } catch (Exception i) {
                System.out.println ( i );
                lastButton = null;
                pair = null;
            }
        }
    }
    private void attackSound () {
        String path = "src/main/JAVAFX/SoundEffects/punch.mp3";
        Media media = new Media ( new File ( path ).toURI ().toString () );
        mediaPlayer = new MediaPlayer ( media );
        mediaPlayer.play ();
    }

    private void moveSound () {
        String path = "src/main/JAVAFX/SoundEffects/walking.mp3";
        Media media = new Media ( new File ( path ).toURI ().toString () );
        mediaPlayer = new MediaPlayer ( media );
        mediaPlayer.play ();
    }

    private void privateMethod ( Pair<Integer, Integer> newPair, Player currentPlayer, ButtonCell button ) {
        if (moveButton.isSelected ()) {
            game.playerMovesPieceOnBoard ( currentPlayer, pair.getKey (), pair.getValue (), newPair.getKey (), newPair.getValue () );
            player = turn.getCurrentPlayersName ();
            this.moveSound ();
            turn.changeTurn ();

            moveButton.getStyleClass ().add ( "button-choose" );
            attackButton.getStyleClass ().add ( "button-choose" );

            VBox vertical = new VBox (player, moveButton, attackButton );
            vertical.setAlignment ( Pos.CENTER );
            vertical.setSpacing ( 40 );
            vertical.getStyleClass().add("piecesGrid");
            borderpane.setLeft ( vertical);
            BorderPane.setMargin ( vertical, new Insets ( 12, 12, 12, 12 ) );

            button.getStyleClass ().add ( lastButton.getStyleClass ().remove ( 1 ) );
        }
        if (attackButton.isSelected ()) {
            game.playerAttacks ( currentPlayer, pair.getKey (), pair.getValue (), newPair.getKey (), newPair.getValue () );
            player = turn.getCurrentPlayersName ();
            this.attackSound ();
            turn.changeTurn ();
            moveButton.getStyleClass ().add ( "button-choose" );
            attackButton.getStyleClass ().add ( "button-choose" );

            VBox vertical = new VBox (player, moveButton, attackButton );
            vertical.setAlignment ( Pos.CENTER );
            vertical.setSpacing ( 40 );
            vertical.getStyleClass().add("piecesGrid");
            borderpane.setLeft ( vertical);
            BorderPane.setMargin ( vertical, new Insets ( 12, 12, 12, 12 ) );

            if (game.cellIsEmpty ( newPair.getKey (), newPair.getValue () ))
                button.getStyleClass ().remove ( 1 );

        }

         */
    }


}

