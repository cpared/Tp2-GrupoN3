package Vistas;

import Controlers.StartButtonEventHandler;
import game.Game;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;
import player.Player;


public class ChoosingPiecesBorderPane extends BorderPane {
    private String background = "-fx-background-color: B22222;";


    public ChoosingPiecesBorderPane ( Game game, Player player, Stage stage, Scene scene ) {

        // Top pane
        PlayerInfoHorizontalBox pointBox = new PlayerInfoHorizontalBox ( game, player, this.background );
        pointBox.prefWidthProperty ().bind ( this.prefHeightProperty ().divide ( 2 ) );
        this.setTop ( pointBox );

        // Right Pane
        PiecesFlowPane right = new PiecesFlowPane ( this.background,this );
        right.prefWidthProperty ().bind ( this.widthProperty ().divide ( 5 ) );
        this.setRight ( right );

        // Left Pane
        PieceInformationDisplay left = new PieceInformationDisplay ( this, this.background,"","","","" );
        this.setLeft ( left );

        // Bottom pane
        Button ready = new Button ( "Ready to play" );
        ready.setOnAction ( new StartButtonEventHandler ( stage, scene ) );

        HBox bottom = new HBox ( ready );
        bottom.setAlignment ( Pos.CENTER );
        bottom.setStyle ( this.background );
        bottom.prefWidthProperty ().bind ( this.prefWidthProperty ().divide ( 4 ) );
        this.setBottom ( bottom );

    }
}
