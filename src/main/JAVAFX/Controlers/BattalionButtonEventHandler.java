package Controlers;

import boardFx.ButtonCell;
import game.Game;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Pair;


public class BattalionButtonEventHandler implements EventHandler<MouseEvent> {
    private ToggleButton battalionButton;
    private ToggleButton moveButton;
    private ToggleButton attackButton;
    private int count = 0;
    private Game game;
    private int row;
    private int column;
    private ButtonCell buttonCell;
    private Pair<ButtonCell, ButtonCell> adjacents;

    public BattalionButtonEventHandler ( ToggleButton battalionButton, ToggleButton moveButton, ToggleButton attackButton, Game game, int row, int column, ButtonCell button , Pair<ButtonCell, ButtonCell> adjacents) {
        this.moveButton = moveButton;
        this.battalionButton = battalionButton;
        this.attackButton = attackButton;
        this.game = game;
        this.row = row;
        this.column = column;
        this.buttonCell = button;
        this.adjacents = adjacents;
    }

    @Override
    public void handle ( MouseEvent mouseEvent ) {
        count++;
        if (count>1) battalionButton.setStyle ( "-fx-opacity: 1.5;-fx-font-size:30;-fx-text-fill: white;" );
        battalionButton.setStyle ( "-fx-opacity: 1.5;-fx-font-size:30; -fx-text-fill: green;" );
        moveButton.setStyle ( "-fx-opacity: 1.5;-fx-font-size:30;-fx-text-fill: white;" );
        attackButton.setStyle ( "-fx-opacity: 1.5;-fx-font-size:30;-fx-text-fill: white;" );
        this.decorateButtonCells ();
        this.game.playerChoosesBattalion (row,column);
    }


    private void decorateButtonCells(){
        ButtonCell prev = adjacents.getKey ();
        prev.getStyleClass ().remove ( 1 ) ;
        prev.getStyleClass ().add ( "leftButtonBattalion" );
        ButtonCell next = adjacents.getValue ();
        next.getStyleClass ().remove ( 1 ) ;
        next.getStyleClass ().add ( "leftButtonBattalion" );
        buttonCell.getStyleClass ().remove ( 1 ) ;
        buttonCell.getStyleClass ().add ( "leftButtonBattalion" );
    }
}
