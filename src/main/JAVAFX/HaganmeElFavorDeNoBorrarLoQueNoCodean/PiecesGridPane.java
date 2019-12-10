package HaganmeElFavorDeNoBorrarLoQueNoCodean;

import Controlers.PieceStatHandlers.CatapultSelectStatsHandler;
import Controlers.PieceStatHandlers.HealerSelectStatsHandler;
import Controlers.PieceStatHandlers.RiderSelectStatsHandler;
import Controlers.PieceStatHandlers.SoldierSelectStatsHandler;
import Vistas.SelectPieceSceneView;
import boardFx.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PiecesGridPane extends GridPane {
    private ImageView attackView;
    private ImageView healthView;
    private ImageView priceView;
    private ImageView behaviorView;

    public PiecesGridPane (Button choosePieceButton, SelectPieceSceneView view, Turn turn ) {

        ButtonPiece soldierButton = new ButtonPieceSoldier ( "leftButtonSoldier" );
        ButtonPiece riderButton = new ButtonPieceRider ( "leftButtonRider" );
        ButtonPiece healerButton = new ButtonPieceHealer ( "leftButtonHealer" );
        ButtonPiece catapultButton = new ButtonPieceCatapult ( "leftButtonCatapult" );

         turn.applyStyleToButtons ( soldierButton, riderButton, healerButton, catapultButton );
        //Images
        Image attackImage = new Image ( "Image/broadsword.png" );
        ImageView attackView = new ImageView ( attackImage );
        attackView.setFitHeight ( 30 );
        attackView.setFitWidth ( 30 );

        Image healthImage = new Image ( "Image/heart-plus.png" );
        ImageView healthView = new ImageView ( healthImage );
        healthView.setFitHeight ( 30 );
        healthView.setFitWidth ( 30 );

        Image coinImage = new Image ( "Image/crown-coin.png" );
        ImageView priceView = new ImageView ( coinImage );
        priceView.setFitHeight ( 30 );
        priceView.setFitWidth ( 30 );

        Image behaviorImage = new Image ( "Image/guards.png" );
        ImageView behaviorView = new ImageView ( behaviorImage );
        behaviorView.setFitHeight ( 30 );
        behaviorView.setFitWidth ( 30 );

        //Text
        Label attackInformation = new Label ( "-" );
        Label healthInformation = new Label ( "-" );
        Label priceInformation = new Label ( "-" );
        Label information = new Label ( "-" );


        //Set action on buttons
        SoldierSelectStatsHandler soldierSelectStatsHandler = new SoldierSelectStatsHandler ( attackInformation, healthInformation, priceInformation, information, attackView, view, soldierButton );
        soldierButton.setOnMouseClicked ( soldierSelectStatsHandler );

        RiderSelectStatsHandler riderSelectStatsHandler = new RiderSelectStatsHandler ( attackInformation, healthInformation, priceInformation, information, attackView, view, riderButton );
        riderButton.setOnMouseClicked ( riderSelectStatsHandler );

        HealerSelectStatsHandler healerSelectStatsHandler = new HealerSelectStatsHandler ( attackInformation, healthInformation, priceInformation, information, attackView, view, healerButton );
        healerButton.setOnMouseClicked ( healerSelectStatsHandler );

        CatapultSelectStatsHandler catapultSelectStatsHandler = new CatapultSelectStatsHandler ( attackInformation, healthInformation, priceInformation, information, attackView, view, catapultButton );
        catapultButton.setOnMouseClicked ( catapultSelectStatsHandler );


        this.setMinWidth(250);
        this.setMaxWidth(100);
        this.setPadding(new Insets (10, 10, 10, 10));
        this.setVgap(15);
        this.setHgap(15);

        this.add(choosePieceButton, 0,0);
        this.add(soldierButton, 0,1);
        this.add(riderButton, 0,2);
        this.add(healerButton,1,1);
        this.add(catapultButton,1,2);

        this.add(attackView,0,4);
        this.add(attackInformation,1,4);
        this.add(healthView,0,5);
        this.add(healthInformation,1,5);
        this.add(priceView,0,6);
        this.add(priceInformation,1,6);
        this.add(behaviorView,0,7);
        this.add(information,1,7);
        this.setAlignment( Pos.CENTER);
        //this.getStyleClass().add("piecesGrid");
    }

}
