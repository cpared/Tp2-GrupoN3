package Vistas;

import Controlers.StartButtonEventHandler;
import boardFx.ButtonCell;
import game.Game;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;
import player.Player;


public class ChoosingPiecesBorderPane extends BorderPane {
    //private String background = "-fx-background-color: B22222;";
    private Background background = new AlgoChessBackground ( "Image/scene00background.jpg" ).createBackground ();

    public ChoosingPiecesBorderPane ( Game game, Player player, Stage stage, Scene scene ) {

        this.setBackground(background);
        // Top pane
        //PlayerInfoHorizontalBox pointBox = new PlayerInfoHorizontalBox ( game, player, this.background );
        PlayerInfoHorizontalBox pointBox = new PlayerInfoHorizontalBox ( game, player);
        pointBox.prefWidthProperty ().bind ( this.prefHeightProperty ().divide ( 2 ) );
        this.setTop ( pointBox );

        // Right Pane
        //PiecesFlowPane right = new PiecesFlowPane ( this.background,this );
        PiecesFlowPane right = new PiecesFlowPane (this );
        right.prefWidthProperty ().bind ( this.widthProperty ().divide ( 5 ) );
        this.setRight ( right );

        // Left Pane
        //PieceInformationDisplay left = new PieceInformationDisplay ( this, this.background,"","","","" );
        PieceInformationDisplay left = new PieceInformationDisplay ( this,"","","","" );
        this.setLeft ( left );

        // Bottom pane
        Button ready = new Button ( "Ready to play" );
        ready.setOnAction ( new StartButtonEventHandler ( stage, scene ) );

        HBox bottom = new HBox ( ready );
        bottom.setAlignment ( Pos.CENTER );
        //bottom.setStyle ( this.background );
        bottom.setBackground(background);
        bottom.prefWidthProperty ().bind ( this.prefWidthProperty ().divide ( 4 ) );
        this.setBottom ( bottom );

        //Center Pane
        GridPane board = this.makeGridPane();
        VBox centralContainer = new VBox (board);
        //centralContainer.setBackground ( this.background );
        centralContainer.setAlignment ( Pos.CENTER );
        centralContainer.setPadding(new Insets(0,0,0,220));
        this.setCenter(centralContainer);
    }

    private GridPane makeGridPane() {
        String green = "-fx-background-color: #008f39;";
        String red = "-fx-background-color: #ff0000;";
        String actual = green;
        GridPane gridPane = new GridPane();
        for (int i = 0 ; i< 20;i++) {
            if (i == 10){
                actual = red;
            }
            for (int j = 0; j < 20; j++) {
                gridPane.add(new ButtonCell(null,actual,i,j),i,j);
            }
        }
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        return gridPane;
    }


}
