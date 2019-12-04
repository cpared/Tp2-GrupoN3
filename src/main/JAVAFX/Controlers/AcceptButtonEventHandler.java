package Controlers;

import game.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;


public class AcceptButtonEventHandler implements EventHandler<ActionEvent> {
    private TextField textField;
    private Game game;
    private int count;
    private Stage stage;
    private Scene scene;
    private Label errorText;

    public AcceptButtonEventHandler ( TextField textField, Game game, Stage stage, Scene scene, Label errorText ) {
        this.textField = textField;
        this.game = game;
        this.count = 0;
        this.stage = stage;
        this.scene = scene;
        this.errorText = errorText;
    }


    @Override
    public void handle ( ActionEvent event ) {

        if (this.textField.getText ().trim ().equals ( "" )) {
            this.textField.setPromptText ( "Debe ingresar un nombre" );
            this.errorText.setText("Oops, empty name is not allow. Please try again");
            this.errorText.setStyle("-fx-text-fill: #FF0000");
        } else {
            this.game.newPlayer ( this.textField.getText () );
            this.count++;
            this.textField.setText ( "" );
            this.textField.requestFocus ();
            if (count == 2) this.stage.setScene ( this.scene );

        }
    }

}


