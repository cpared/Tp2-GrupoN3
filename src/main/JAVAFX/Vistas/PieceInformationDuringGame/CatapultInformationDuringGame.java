package Vistas.PieceInformationDuringGame;

import HaganmeElFavorDeNoBorrarLoQueNoCodean.Turn;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CatapultInformationDuringGame extends VBox {

    public CatapultInformationDuringGame ( int life, Turn turn, ToggleButton moveButton, ToggleButton attackButton, VBox moves ) {

        Label piece = new Label ( "Chosen piece: Catapult" );
        piece.getStyleClass ().add ( "textStyle" );

        Label pieceLife = new Label ( "Life:" + Integer.toString ( life ) );
        pieceLife.getStyleClass ().add ( "textStyle" );
        attackButton.setText ( "Attack" );
        attackButton.getStyleClass ().add ( "button-choose" );

        HBox player = turn.getCurrentPlayersName ();

        VBox pieceInfo = new VBox ( player, piece, pieceLife );
        pieceInfo.setSpacing ( 20 );
        pieceInfo.setAlignment ( Pos.TOP_CENTER );

        VBox buttons = new VBox ( attackButton );
        attackButton.prefWidthProperty ().bind ( buttons.widthProperty ().divide ( 2 ) );
        buttons.setSpacing ( 40 );
        buttons.setAlignment ( Pos.TOP_CENTER );

        this.getChildren ().addAll ( pieceInfo, buttons, moves );
        this.setAlignment ( Pos.TOP_CENTER );
        this.setSpacing ( 100 );
        this.getStyleClass ().add ( "piecesGrid" );
    }
}
