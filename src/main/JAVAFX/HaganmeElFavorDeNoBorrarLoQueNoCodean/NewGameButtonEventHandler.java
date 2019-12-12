package HaganmeElFavorDeNoBorrarLoQueNoCodean;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class NewGameButtonEventHandler implements EventHandler<ActionEvent> {
    private Application app;
    private Button exitButton;

    public  NewGameButtonEventHandler ( Application game, Button exit  ){
        this.app = game;
        this.exitButton = exit;
    }

    @Override
    public void handle ( ActionEvent actionEvent ) {

        try {
           Event.fireEvent ( this.exitButton, new MouseEvent ( MouseEvent.MOUSE_CLICKED,
                   this.exitButton.getLayoutX(), this.exitButton.getLayoutY(),  this.exitButton.getLayoutX(), this.exitButton.getLayoutY(), MouseButton.PRIMARY, 1,
                   true, true, true, true, true, true, true, true, true, true, null) );
           this.app.start ( new Stage () );
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

}
