package Controlers;

import Vistas.SelectPieceSceneView;
import game.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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
            this.textField.setPromptText ( "Debe ingresar un nombre" );
            this.errorText.setText("Oops, empty name is not allow. Please try again");
            this.errorText.setStyle("-fx-text-fill: #FF0000");
        } else {
            this.game.newPlayer ( this.textField.getText () );
            this.count++;
            this.textField.setText ( "" );
            this.textField.requestFocus ();
            if (count == 2) {
                try {
                    this.stage.setScene(this.scene.scene02SelectPieces(stage, this.game.getPlayer1().name(), this.game.getPlayer2().name(), this.game.getPoints(this.game.getPlayer1()), this.game.getPoints(this.game.getPlayer2()),game));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


