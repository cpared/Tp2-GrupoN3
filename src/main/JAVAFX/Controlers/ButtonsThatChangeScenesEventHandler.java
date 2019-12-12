package Controlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class ButtonsThatChangeScenesEventHandler implements EventHandler<InputEvent> {
    private Stage stage;
    private Scene scene;
    private MediaPlayer player;

    public ButtonsThatChangeScenesEventHandler ( Stage initialStage, Scene nextScene ) {
        this.stage = initialStage;
        this.scene = nextScene;
    }

    @Override
    public void handle ( InputEvent event ) {
        if (event.getClass ().equals ( KeyEvent.class )) this.handleKey ( (KeyEvent) event );
        else if (event.getClass ().equals ( MouseEvent.class )) this.handleMouse ( (MouseEvent) event );

    }

    public void handleKey ( KeyEvent event ) {
        if (event.getCode () == KeyCode.ENTER) {
            Double width = stage.getWidth ();
            Double height = stage.getHeight ();
            this.stage.setScene ( this.scene );
            this.stage.setForceIntegerRenderScale ( true );
            //this.stage.setFullScreen ( true );
            this.stage.setWidth ( width );
            this.stage.setHeight ( height );
            this.sound ();
        }
    }

    public void handleMouse(MouseEvent event) {
        if (event.getEventType ().equals ( MouseEvent.MOUSE_CLICKED )) {
            Double width = stage.getWidth ();
            Double height = stage.getHeight ();
            this.stage.setScene ( this.scene );
            this.stage.setForceIntegerRenderScale ( true );
            this.stage.setWidth ( width );
            this.stage.setHeight ( height );
            this.sound ();
        }
    }
    private void sound() {
        String path = "src/main/JAVAFX/SoundEffects/readytoplay.mp3";
        Media media = new Media ( new File ( path ).toURI ().toString () );
        MediaPlayer mp = new MediaPlayer ( media );
        mp.play ();
    }

}
