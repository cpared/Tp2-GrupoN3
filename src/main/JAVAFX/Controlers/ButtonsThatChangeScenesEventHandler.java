package Controlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

public class ButtonsThatChangeScenesEventHandler implements EventHandler<ActionEvent> {
    private Stage stage;
    private Scene scene;

    public ButtonsThatChangeScenesEventHandler ( Stage initialStage, Scene nextScene ) {
        this.stage = initialStage;
        this.scene = nextScene;
    }

    @Override
    public void handle ( ActionEvent event ) {
        Double width = stage.getWidth ();
        Double height = stage.getHeight ();
        this.stage.setScene(this.scene);
        this.stage.setForceIntegerRenderScale ( true );
        //this.stage.setFullScreen ( true );
        this.stage.setWidth ( width );
        this.stage.setHeight ( height );
        //this.stage.show ();
    }

}
