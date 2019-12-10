package Controlers;

import Vistas.SelectPieceSceneView;
import game.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;


public class AcceptButtonEventHandler implements EventHandler<ActionEvent> {
    private TextField textField;
    private Game game;
    private int count;
    private Stage stage;
    private SelectPieceSceneView scene;
    private Label errorText;

    public AcceptButtonEventHandler (TextField textField, Game game, Stage stage, Label errorText, SelectPieceSceneView scene) {
        this.textField = textField;
        this.game = game;
        this.count = 0;
        this.scene = scene;
        this.stage = stage;
        this.errorText = errorText;
    }


    @Override
    public void handle ( ActionEvent event ) {

        if (this.textField.getText ().trim ().equals ( "" )) {
            this.textField.setPromptText ( "Player must choose a name." );
            this.errorText.setText("Oops, no name has been entered. Please try again");
            this.errorText.setStyle("-fx-text-fill: #fffb03; -fx-font-size:20");
        } else {
            this.game.newPlayer ( this.textField.getText () );
            this.count++;
            if (count == 2) {

                try {
                    String path = "src/main/JAVAFX/SoundEffects/readytoplay.mp3";
                    Media media = new Media ( new File ( path ).toURI ().toString () );
                    MediaPlayer mp = new MediaPlayer ( media );
                    mp.play ();
                    this.stage.setScene(this.scene.scene02SelectPieces(stage,game));
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
            this.textField.setText ( "" );
            this.textField.requestFocus ();
        }
    }

}


