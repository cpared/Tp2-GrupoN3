package HaganmeElFavorDeNoBorrarLoQueNoCodean;

import Controlers.PieceStatHandlers.*;
import Vistas.SelectPieceSceneView;
import boardFx.*;
import game.Game;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import player.Player;

public class PlayerSelectsPieceVerticalBox extends VBox {
    private Player availablePlayer;
    private Scene scene;

    public PlayerSelectsPieceVerticalBox ( Game game , Scene scene) {

        this.availablePlayer = game.getAvailablePlayer ();
        // PLAYER NAME:

        Text name = new Text ("Player: " + this.availablePlayer.name () );
        name.setFill ( Color.WHITE );

        Text points = new Text ("Points: " + game.getPoints ( this.availablePlayer ) );
        name.setFill ( Color.WHITE );

        TextFlow playerInfo = new TextFlow ( name, points );
        playerInfo.setLineSpacing ( 7 );
        playerInfo.setTextAlignment ( TextAlignment.CENTER );
        playerInfo.setMinSize ( 30,30 );
        //name.setMaxHeight ( 250 );
        //name.setMaxWidth ( 180 );

        playerInfo.getStyleClass().add("selectedtextStyle");
        playerInfo.getStyleClass().add("textStyle");


        // BUTTON DISPLAYS

        Button choosePieceButton = new Button ("Choose Piece");
        choosePieceButton.getStyleClass().add("button-choose");
        choosePieceButton.setAlignment( Pos.CENTER);

        ButtonPiece soldierButton = new ButtonPieceSoldier();
        soldierButton.getStyleClass().add("leftButtonSoldier");
        soldierButton.setMinWidth(60);
        soldierButton.setMinHeight(60);
        ButtonPiece riderButton = new ButtonPieceRider();
        riderButton.getStyleClass().add("leftButtonRider");
        riderButton.setMinWidth(60);
        riderButton.setMinHeight(60);

        ButtonPiece healerButton = new ButtonPieceHealer();
        healerButton.getStyleClass().add("leftButtonHealer");
        healerButton.setMinWidth(60);
        healerButton.setMinHeight(60);

        ButtonPiece catapultButton = new ButtonPieceCatapult();
        catapultButton.getStyleClass().add("leftButtonCatapult");
        catapultButton.setMinWidth(60);
        catapultButton.setMinHeight(60);

        //Images
        Image attackImage = new Image("Image/broadsword.png");
        ImageView attackView = new ImageView(attackImage);
        attackView.setFitHeight(20);
        attackView.setFitWidth(20);

        Image healthImage = new Image("Image/heart-plus.png");
        ImageView healthView = new ImageView(healthImage);
        healthView.setFitHeight(20);
        healthView.setFitWidth(20);

        Image coinImage = new Image("Image/crown-coin.png");
        ImageView coinView = new ImageView(coinImage);
        coinView.setFitHeight(20);
        coinView.setFitWidth(20);

        Image behaviorImage = new Image("Image/guards.png");
        ImageView behaviorView = new ImageView(behaviorImage);
        behaviorView.setFitHeight(20);
        behaviorView.setFitWidth(20);

        //Text
        Label attackInformation = new Label("-");
        Label healthInformation = new Label("-");
        Label priceInformation = new Label("-");
        Label information = new Label("-");


        //Set action on buttons
        SoldierSelectStatsHandler soldierSelectStatsHandler = new SoldierSelectStatsHandler(attackInformation,healthInformation, priceInformation, information, attackView, new SelectPieceSceneView (), soldierButton);
        soldierButton.setOnMouseClicked( soldierSelectStatsHandler );

        RiderSelectStatsHandler riderSelectStatsHandler = new RiderSelectStatsHandler(attackInformation,healthInformation, priceInformation, information, attackView, new SelectPieceSceneView (), riderButton);
        riderButton.setOnMouseClicked( riderSelectStatsHandler );

        HealerSelectStatsHandler healerSelectStatsHandler = new HealerSelectStatsHandler(attackInformation,healthInformation, priceInformation, information, attackView, new SelectPieceSceneView (), healerButton);
        healerButton.setOnMouseClicked( healerSelectStatsHandler );

        CatapultSelectStatsHandler catapultSelectStatsHandler = new CatapultSelectStatsHandler(attackInformation,healthInformation, priceInformation, information, attackView, new SelectPieceSceneView (), catapultButton);
        catapultButton.setOnMouseClicked( catapultSelectStatsHandler );

        //Pieces grid.
        GridPane piecesGrid = new PiecesGridPane ( choosePieceButton,soldierButton, riderButton,healerButton,catapultButton, attackView, healthView, coinView, behaviorView, attackInformation,healthInformation, priceInformation, information);
    }


}
