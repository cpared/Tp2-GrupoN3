package HaganmeElFavorDeNoBorrarLoQueNoCodean;

import boardFx.ButtonPiece;
import game.Game;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import player.Player;

public class Turn {
    private String soldierButtonStyle;
    private String healerButtonStyle;
    private String riderButtonStyle;
    private String catapultButtonStyle;
    private Game game;

    public Turn ( Game game ) {
        this.game = game;
        this.leftPlayerStyle ();
    }

    private void leftPlayerStyle () {
        this.soldierButtonStyle = "leftButtonSoldier";
        this.healerButtonStyle = "leftButtonHealer";
        this.riderButtonStyle = "leftButtonRider";
        this.catapultButtonStyle = "leftButtonCatapult";
    }

    private void rightPlayerStyle () {
        this.soldierButtonStyle = "rightButtonSoldier";
        this.healerButtonStyle = "rightButtonHealer";
        this.riderButtonStyle = "rightButtonRider";
        this.catapultButtonStyle = "rightButtonCatapult";
    }

    public Label getCurrentPlayersName () {
        Label name = new Label ( "Player: " + this.game.getAvailablePlayer ().name () );
        name.setStyle ( "-fx-text-fill: white;-fx-font-size: 20" );
        return name;
    }

    public  Label getCurrentPlayersPoints() {
        Label points = new Label ( "Points: " + Integer.toString ( this.game.getPoints ( this.game.getAvailablePlayer () ) ) );
        points.setStyle ( "-fx-text-fill: white;-fx-font-size: 20" );
        return points;
    }

    public void applyStyleToButtons ( ButtonPiece soldierButton, ButtonPiece riderButton, ButtonPiece healerButton, ButtonPiece catapultButton ) {
        soldierButton.setString ( this.soldierButtonStyle );
        riderButton.setString ( this.riderButtonStyle );
        healerButton.setString ( this.healerButtonStyle );
        catapultButton.setString ( this.catapultButtonStyle );
    }

    public void changeTurn(){
        if (this.game.getAvailablePlayer ().equals ( this.game.getPlayer1 () )) {
            this.leftPlayerStyle ();
        } else {
            this.rightPlayerStyle ();
        }
    }
}
