package Controlers;

import Vistas.ChoosingPiecesBorderPane;
import boardFx.ButtonCell;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class BoardPositionHasBeenChosenInInitialFaceEventHandler implements EventHandler<InputEvent> {
    private Image image;
    private ChoosingPiecesBorderPane pane;
    private  ButtonCell button;

    public BoardPositionHasBeenChosenInInitialFaceEventHandler ( Image image, ChoosingPiecesBorderPane pane, ButtonCell button) {
        this.pane = pane;
        this.image = image;
        this.button = button;
    }

    @Override

    public void handle ( InputEvent event ) {
        if (event.getClass ().equals ( KeyEvent.class )) this.handleKey ( (KeyEvent) event );
        else if (event.getClass ().equals ( MouseEvent.class )) this.handleMouse ( (MouseEvent) event );

    }

    public void handleKey ( KeyEvent event ) {
        if (event.getCode () == KeyCode.ENTER) {
            this.button.setGraphic ( new ImageView ( this.image ) );
            this.pane.setCursor ( Cursor.DEFAULT );
        }
    }

    public void handleMouse ( MouseEvent event ) {
        if (event.getEventType ().equals ( MouseEvent.MOUSE_CLICKED )) {
            this.button.setGraphic ( new ImageView ( this.image ) );
            this.pane.setCursor ( Cursor.DEFAULT );
        }
    }
}