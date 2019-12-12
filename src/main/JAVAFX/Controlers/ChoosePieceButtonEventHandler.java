package Controlers;

import Vistas.ChoosingPiecesBorderPane;
import boardFx.ButtonCell;
import game.Game;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ChoosePieceButtonEventHandler implements EventHandler<InputEvent> {
    private Image image;
    private  ChoosingPiecesBorderPane pane;
    private GridPane grid;
    private Game game;

    public ChoosePieceButtonEventHandler (Image image , ChoosingPiecesBorderPane pane, GridPane grid, Game game){
        this.image = image;
        this.pane = pane;
        this.grid = grid;
        this.game = game;
    }

    @Override
    public void handle ( InputEvent event ) {
        if (event.getClass ().equals ( KeyEvent.class ))this.handleKey ( (KeyEvent) event );
        else if (event.getClass ().equals ( MouseEvent.class )) this.handleMouse ( (MouseEvent) event );
    }

    public void handleKey ( KeyEvent event ) {
        if (event.getCode () == KeyCode.ENTER ) {
            this.pane.setCursor ( new ImageCursor ( this.image ) );
            ObservableList<Node> children = this.grid.getChildren();
            for (Node node : children) {
                node.setOnKeyPressed ( new BoardPositionHasBeenChosenInInitialFaceEventHandler ( this.image, pane,this.game, (ButtonCell) node ) );
                node.setOnMouseClicked ( new BoardPositionHasBeenChosenInInitialFaceEventHandler ( this.image, pane,this.game, (ButtonCell) node ) );
            }
        }
    }

    public void handleMouse(MouseEvent event) {
        if (event.getEventType ().equals ( MouseEvent.MOUSE_CLICKED ) ) {
            this.pane.setCursor ( new ImageCursor ( this.image ) );
            ObservableList<Node> children = this.grid.getChildren();
            for (Node node : children) {
                node.setOnKeyPressed ( new BoardPositionHasBeenChosenInInitialFaceEventHandler ( this.image, pane,this.game, (ButtonCell) node ) );
                node.setOnMouseClicked ( new BoardPositionHasBeenChosenInInitialFaceEventHandler ( this.image, pane, this.game,(ButtonCell) node ) );
            }
        }
    }
}
