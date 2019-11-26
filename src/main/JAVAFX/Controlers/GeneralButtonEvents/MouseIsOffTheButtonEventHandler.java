package Controlers.GeneralButtonEvents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MouseIsOffTheButtonEventHandler implements EventHandler<MouseEvent> {
    private Button button;

    public MouseIsOffTheButtonEventHandler (Button button) {
        this.button = button;
    }

    public void handle ( MouseEvent event ) {
        this.button.setEffect(null);
    }
}
