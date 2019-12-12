package Vistas.PieceInformationDuringGame;

import Controlers.BattalionButtonEventHandler;
import HaganmeElFavorDeNoBorrarLoQueNoCodean.Turn;
import game.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SoldierInformationDuringGame extends VBox {

    public SoldierInformationDuringGame ( int life, Turn turn, ToggleButton moveButton, ToggleButton attackButton, VBox moves ) {

        Label piece = new Label ( "Chosen piece: Soldier" );
        piece.getStyleClass ().add ( "textStyle" );

        Label pieceLife = new Label ( "Life:" + Integer.toString ( life ) );
        pieceLife.getStyleClass ().add ( "textStyle" );

        moveButton.getStyleClass ().add ( "button-choose" );
        attackButton.setText ( "Attack" );
        attackButton.getStyleClass ().add ( "button-choose" );

        HBox player = turn.getCurrentPlayersName ();

        VBox pieceInfo = new VBox ( player, piece, pieceLife );
        pieceInfo.setSpacing ( 20 );
        pieceInfo.setAlignment ( Pos.TOP_CENTER );


        VBox buttons = new VBox ( moveButton, attackButton );
        moveButton.prefWidthProperty ().bind ( buttons.widthProperty ().divide ( 2 ) );
        attackButton.prefWidthProperty ().bind ( buttons.widthProperty ().divide ( 2 ) );
        buttons.setSpacing ( 40 );
        buttons.setAlignment ( Pos.TOP_CENTER );

        this.getChildren ().addAll ( pieceInfo, buttons , moves );
        this.setAlignment ( Pos.CENTER );
        this.setSpacing ( 100 );
        this.getStyleClass ().add ( "piecesGrid" );
    }

}
