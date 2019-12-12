package HaganmeElFavorDeNoBorrarLoQueNoCodean;

import boardFx.ButtonPiece;
import game.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import player.Player;

public class Turn {
    private String soldierButtonStyle;
    private String healerButtonStyle;
    private String riderButtonStyle;
    private String catapultButtonStyle;
    private String battalionButtonStyle;
    private Game game;
    private Circle circle = new Circle(10);

    public Turn ( Game game ) {
        this.game = game;
        this.leftPlayerStyle ();
    }

    private void leftPlayerStyle () {
        this.circle.setStyle("-fx-fill: #1a7749;");
        this.soldierButtonStyle = "leftButtonSoldier";
        this.healerButtonStyle = "leftButtonHealer";
        this.riderButtonStyle = "leftButtonRider";
        this.catapultButtonStyle = "leftButtonCatapult";
        this.battalionButtonStyle = "leftButtonBattalion";
    }

    private void rightPlayerStyle () {
        this.circle.setStyle("-fx-fill: #8f9779;");
        this.soldierButtonStyle = "rightButtonSoldier";
        this.healerButtonStyle = "rightButtonHealer";
        this.riderButtonStyle = "rightButtonRider";
        this.catapultButtonStyle = "rightButtonCatapult";
        this.battalionButtonStyle = "rightButtonBattalion";
    }

    public HBox getCurrentPlayersName () {


        Label name = new Label ( "Player: " + this.game.getAvailablePlayer ().name () );
        name.getStyleClass().add("textStyle");

        HBox playerInfo = new HBox(name,this.circle);
        playerInfo.setSpacing ( 10 );
        playerInfo.setAlignment ( Pos.CENTER );

        return playerInfo;
    }

    public Label getCurrentPlayersPoints () {
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

    public void changeTurn () {
        if (this.game.getAvailablePlayer ().equals ( this.game.getPlayer1 () )) {
            this.leftPlayerStyle ();
        } else {
            this.rightPlayerStyle ();
        }
    }
}
