package HaganmeElFavorDeNoBorrarLoQueNoCodean;

import game.Game;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import player.Player;

public class Turn {
    private Player currentPlayer;
    private String soldierButtonStyle;
    private String healerButtonStyle;
    private String riderButtonStyle;
    private String catapultButtonStyle;
    private Game game;

    public Turn ( Game game ) {
        this.currentPlayer = game.getAvailablePlayer ();
        this.game = game;
        this.leftPlayerStyle ();
    }

    private void leftPlayerStyle () {
        this.soldierButtonStyle = "leftButtonSoldier";
        this.healerButtonStyle = "leftButtonHealer";
        this.riderButtonStyle = "leftButtonRider";
        this.riderButtonStyle = "leftButtonCatapult";
    }

    private void rightPlayerStyle () {
        this.soldierButtonStyle = "rightButtonSoldier";
        this.healerButtonStyle = "rightButtonHealer";
        this.riderButtonStyle = "rightButtonRider";
        this.riderButtonStyle = "rightButtonCatapult";
    }

    private VBox playerInformationDisplay () {
        Label name = new Label ( "Player: " + this.currentPlayer.name () );
        Label points = new Label ( "Points: " + Integer.toString ( game.getPoints ( this.currentPlayer ) ) );

        //Style
        name.setStyle ( "-fx-text-fill: white;-fx-font-size: 20" );
        points.setStyle ( "-fx-text-fill: white;-fx-font-size: 20" );

        VBox vertical = new VBox ( name, points );
        return vertical;
    }

    public void applyStyleToButtons ( Button soldierButton, Button riderButton, Button healerButton, Button catapultButton ) {
        soldierButton.getStyleClass().add(this.soldierButtonStyle);
        riderButton.getStyleClass().add(this.riderButtonStyle);
        healerButton.getStyleClass().add(this.healerButtonStyle);
        catapultButton.getStyleClass().add(this.catapultButtonStyle);
    }
}
