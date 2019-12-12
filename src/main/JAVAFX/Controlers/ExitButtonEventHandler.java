package Controlers;

import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.security.Key;

public class ExitButtonEventHandler implements EventHandler<InputEvent> {
    private Button button;
    private Stage stage;

    public ExitButtonEventHandler( Button exit, Stage stage) {
       this.button = exit;
       this.stage = stage;
    }

    @Override
    public void handle ( InputEvent event ) {
        if (event.getClass ().equals ( KeyEvent.class ))this.handleKey ( (KeyEvent) event );
        else if (event.getClass ().equals ( MouseEvent.class )) this.handleMouse ( (MouseEvent) event );

    }

    public void handleKey ( KeyEvent event ) {
        if (event.getCode () == KeyCode.ENTER || event.getCode () == KeyCode.ESCAPE) {
            this.stage.close ();
        }
    }

    public void handleMouse(MouseEvent event) {
        if (event.getEventType ().equals ( MouseEvent.MOUSE_CLICKED ) ) {
            this.stage.close ();
        }
    }
}


