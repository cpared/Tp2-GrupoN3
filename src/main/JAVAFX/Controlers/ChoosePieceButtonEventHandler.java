package Controlers;

import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ChoosePieceButtonEventHandler implements EventHandler<InputEvent> {
    @Override

    public void handle ( InputEvent event ) {
        if (event.getClass ().equals ( KeyEvent.class ))this.handleKey ( (KeyEvent) event );
        else if (event.getClass ().equals ( MouseEvent.class )) this.handleMouse ( (MouseEvent) event );

    }

    public void handleKey ( KeyEvent event ) {
        if (event.getCode () == KeyCode.ENTER ) {

        }
    }

    public void handleMouse(MouseEvent event) {
        if (event.getEventType ().equals ( MouseEvent.MOUSE_CLICKED ) ) {
           
        }
    }
}
