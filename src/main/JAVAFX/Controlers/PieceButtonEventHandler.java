package Controlers;

import Vistas.ChoosingPiecesBorderPane;
import Vistas.PieceInformationDisplay;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class PieceButtonEventHandler implements EventHandler<InputEvent> {
    private ChoosingPiecesBorderPane choosingPiecesBorderPane;
    private String cost;
    private String life;
    private String damage;
    private String behaviour;
    private String background;
    private Image image;


    public PieceButtonEventHandler ( ChoosingPiecesBorderPane borderPane, String background, String cost, String life, String damage, String behaviour,  Image image ) {
        this.choosingPiecesBorderPane = borderPane;
        this.cost = cost;
        this.life = life;
        this.damage = damage;
        this.behaviour = behaviour;
        this.background = background;
        this.image = image;
    }

    @Override
    public void handle ( InputEvent event ) {
        if (event.getClass ().equals ( KeyEvent.class )) this.handleKey ( (KeyEvent) event );
        else if (event.getClass ().equals ( MouseEvent.class )) this.handleMouse ( (MouseEvent) event );

    }

    public void handleKey ( KeyEvent event ) {
        if (event.getCode () == KeyCode.ENTER) {
            PieceInformationDisplay pieceInformationDisplay = new PieceInformationDisplay ( this.choosingPiecesBorderPane,  this.background, this.cost, this.life, this.damage, this.behaviour, this.image );
            this.choosingPiecesBorderPane.setLeft ( pieceInformationDisplay );
        }
    }

    public void handleMouse ( MouseEvent event ) {
        if (event.getEventType ().equals ( MouseEvent.MOUSE_CLICKED )) {
            PieceInformationDisplay pieceInformationDisplay = new PieceInformationDisplay ( this.choosingPiecesBorderPane,  this.background,this.cost, this.life, this.damage, this.behaviour, this.image );
            this.choosingPiecesBorderPane.setLeft ( pieceInformationDisplay );
        }
    }
}
