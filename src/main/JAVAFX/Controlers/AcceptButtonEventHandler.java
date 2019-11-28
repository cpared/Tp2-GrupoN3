package Controlers;

import game.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;


public class AcceptButtonEventHandler implements EventHandler<ActionEvent> {
    private TextField textField;
    private Game game;
    private int count;
    private Stage stage;
    private Scene scene;

    public AcceptButtonEventHandler ( TextField textField, Game game, Stage stage, Scene scene ) {
        this.textField = textField;
        this.game = game;
        this.count = 0;
        this.stage = stage;
        this.scene = scene;
    }


    @Override
    public void handle ( ActionEvent event ) {

        if (this.textField.getText ().trim ().equals ( "" )) {
            this.textField.setPromptText ( "Debe ingresar un nombre" );
        } else {
            this.game.newPlayer ( this.textField.getText () );
            this.count++;
            this.textField.setText ( "" );
            this.textField.requestFocus ();
            if (count == 2) this.stage.setScene ( this.scene );


        }

    }

}


