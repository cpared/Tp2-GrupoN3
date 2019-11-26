package Controlers.GeneralButtonEvents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

public class MouseIsOnTheButtonEventHandler implements EventHandler<MouseEvent> {
    private Button button;

    public MouseIsOnTheButtonEventHandler (Button button) {
        this.button = button;
    }

    @Override
    public void handle ( MouseEvent actionEvent ) {
        DropShadow shadow = new DropShadow();
        this.button.setEffect(shadow);
    }
}
