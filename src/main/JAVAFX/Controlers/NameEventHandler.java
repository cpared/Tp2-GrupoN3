package Controlers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class NameEventHandler implements EventHandler<KeyEvent> {
    private Button acceptButton;

    public NameEventHandler ( Button acceptButton ) {
        this.acceptButton = acceptButton;
    }

    public void handle ( KeyEvent event ) {
        if (event.getCode () == KeyCode.ENTER) {
            Event.fireEvent ( this.acceptButton, new ActionEvent () );
        }
    }
}