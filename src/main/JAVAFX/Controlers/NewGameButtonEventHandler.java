package Controlers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class NewGameButtonEventHandler {
}

class BotonLimpiarEventHandler implements EventHandler<ActionEvent> {
    private TextField textField;

    public BotonLimpiarEventHandler(TextField textField) {
        this.textField = textField;
    }

    public void handle(ActionEvent actionEvent) {
        this.textField.setText("");
        this.textField.requestFocus();
    }
}

class TextoEventHandler implements EventHandler<KeyEvent> {
    private Button botonEnviar;

    public TextoEventHandler ( Button botonEnviar ) {
        this.botonEnviar = botonEnviar;
    }

    public void handle ( KeyEvent event ) {
        if (event.getCode () == KeyCode.ENTER) {
            Event.fireEvent ( this.botonEnviar, new ActionEvent () );
        }

    }
}