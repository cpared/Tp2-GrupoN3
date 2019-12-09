package Controlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ButtonsThatChangeScenesEventHandler implements EventHandler<InputEvent> {
    private Stage stage;
    private Scene scene;

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
            //this.stage.show ();
        }
    }

    public void handleMouse(MouseEvent event) {
        if (event.getEventType ().equals ( MouseEvent.MOUSE_CLICKED ) ) {
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


}
