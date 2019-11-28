package Controlers;

import Vistas.ChoosingPiecesBorderPane;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;

import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ChoosePieceButtonEventHandler implements EventHandler<InputEvent> {
    private Image image;
    private  ChoosingPiecesBorderPane pane;

    public ChoosePieceButtonEventHandler ( Image image , ChoosingPiecesBorderPane pane){
        this.image = image;
        this.pane = pane;
    }

    @Override

    public void handle ( InputEvent event ) {
        if (event.getClass ().equals ( KeyEvent.class ))this.handleKey ( (KeyEvent) event );
        else if (event.getClass ().equals ( MouseEvent.class )) this.handleMouse ( (MouseEvent) event );

    }

    public void handleKey ( KeyEvent event ) {
        if (event.getCode () == KeyCode.ENTER ) {
            this.pane.setCursor ( new ImageCursor ( this.image ) );
        }
    }

    public void handleMouse(MouseEvent event) {
        if (event.getEventType ().equals ( MouseEvent.MOUSE_CLICKED ) ) {
           this.pane.setCursor ( new ImageCursor ( this.image ) );
        }
    }
}
