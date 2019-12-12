package Vistas.PieceInformationDuringGame;

import Controlers.BattalionButtonEventHandler;
import HaganmeElFavorDeNoBorrarLoQueNoCodean.Turn;
import boardFx.ButtonCell;
import game.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class BattalionDuringGame extends VBox {


    public BattalionDuringGame ( int life, Turn turn, ToggleButton moveButton, ToggleButton attackButton, VBox moves , Game game, int row, int column, ButtonCell button , Pair<ButtonCell, ButtonCell> adjacents ) {

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

        //BATTALION
        ToggleButton battalion = new ToggleButton ( "Create Battalion" );
        attackButton.getToggleGroup ().getToggles ().add ( battalion );
        battalion.setOnMouseClicked ( new BattalionButtonEventHandler ( battalion,moveButton,attackButton, game, row, column,button, adjacents ) );
        battalion.getStyleClass ().add ( "button-choose" );

        VBox buttons = new VBox ( moveButton, attackButton, battalion );
        moveButton.prefWidthProperty ().bind ( buttons.widthProperty ().divide ( 2 ) );
        attackButton.prefWidthProperty ().bind ( buttons.widthProperty ().divide ( 2 ) );
        battalion.prefWidthProperty ().bind ( buttons.widthProperty ().divide ( 2 ) );
        buttons.setSpacing ( 40 );
        buttons.setAlignment ( Pos.TOP_CENTER );

        this.getChildren ().addAll ( pieceInfo, buttons , moves );
        this.setAlignment ( Pos.TOP_CENTER );
        this.setSpacing ( 100 );
        this.getStyleClass ().add ( "piecesGrid" );
    }
}
