package Vistas;

import Controlers.ButtonsThatChangeScenesEventHandler;
import boardFx.ButtonCell;
import game.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import player.Player;


public class ChoosingPiecesBorderPane extends BorderPane {
    private String background;

    public ChoosingPiecesBorderPane ( Game game, Player player, Stage stage, Scene scene, String background ) {
        this.background = background;


        // Top pane
        PlayerInfoHorizontalBox pointBox = new PlayerInfoHorizontalBox ( game, player, this.background );
        pointBox.prefWidthProperty ().bind ( this.prefHeightProperty ().divide ( 2 ) );
        this.setTop ( pointBox );

        // Bottom pane
        Button ready = new Button ( "Ready to play" );
        ready.setOnAction ( new ButtonsThatChangeScenesEventHandler ( stage, scene ) );

        HBox bottom = new HBox ( ready );
        bottom.setAlignment ( Pos.CENTER );
        bottom.setStyle ( this.background );
        bottom.prefWidthProperty ().bind ( this.prefWidthProperty ().divide ( 4 ) );
        this.setBottom ( bottom );

        //Center Pane
        GridPane board = this.makeGridPane();
        VBox centralContainer = new VBox (board);

        Background backgroundGrid = new AlgoChessBackground ( "Image/backggg (2).jpg" ).createBackground ();
        centralContainer.setBackground ( backgroundGrid);
        centralContainer.setAlignment ( Pos.CENTER );
        centralContainer.prefWidthProperty ().bind ( this.prefWidthProperty ().divide ( 3 ) );
        centralContainer.setPadding(new Insets(0,0,0,100));
        this.setCenter(centralContainer);

        // Left Pane
        PieceInformationDisplay left = new PieceInformationDisplay ( this, this.background,"","","","",  null, board,game);
        this.setLeft ( left );

        // Right Pane
        PiecesFlowPane right = new PiecesFlowPane ( this.background,this, board,game );
        right.prefWidthProperty ().bind ( this.widthProperty ().divide ( 5 ) );
        this.setRight ( right );

    }

    private GridPane makeGridPane() {
        String green = "-fx-background-color: #008f39;";
        String red = "-fx-background-color: #ff0000;";
        String actual = green;
        AlgoGrid gridPane = new AlgoGrid();
        for (int i = 0 ; i< 20;i++) {
            if (i == 10){
                actual = red;
            }
            for (int j = 0; j < 20; j++) {
                ButtonCell button = new ButtonCell(null,actual,i,j);
                button.setPrefSize(30, 30);
                //button.setOnKeyPressed ( new BoardPositionHasBeenChosenInInitialFaceEventHandler ( null, this, button) );
                //button.setOnMouseClicked ( new BoardPositionHasBeenChosenInInitialFaceEventHandler ( null, this, button) );
                gridPane.add(button,i,j);
            }
        }
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        return gridPane;
    }


}
