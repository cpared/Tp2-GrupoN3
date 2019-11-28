package Vistas;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class PieceInformationDisplay  extends VBox {

    public  PieceInformationDisplay(ChoosingPiecesBorderPane borderpane, String background, String cost, String life, String damage, String behaviour){
        Text text = new Text();
        text.setFill ( Color.BLACK  );
        TextFlow label = new TextFlow (text);

        label.setLineSpacing ( 5 );
        label.setMaxHeight ( 250 );
        label.setMaxWidth ( 180 );
        label.setTextAlignment ( TextAlignment.CENTER );

        PieceInformationHBox pieceInformationHBox = new PieceInformationHBox ( label, cost, life, damage, behaviour );
        pieceInformationHBox.prefWidthProperty ().bind ( borderpane.prefWidthProperty ().divide ( 1 ) );

        Label name = new Label ( "Piece information:" );

        // Stack creation
        Rectangle rectangle = new Rectangle (200, 500);

        rectangle.setStroke ( Color.BLACK );
        rectangle.setStrokeType ( StrokeType.CENTERED );
        rectangle.setFill(Color.WHITE);

        StackPane pane = new StackPane ( rectangle, label );
        pane.setMaxSize ( 200, 500 );
        pane.setAlignment ( Pos.CENTER );

        this.getChildren().addAll ( name, pieceInformationHBox, pane );
        this.setAlignment ( Pos.TOP_CENTER );
        this.setSpacing ( 50 );
        this.setMaxWidth ( 300 );
        this.prefHeightProperty ().bind ( borderpane.prefWidthProperty ().divide ( 2 ) );
        this.setStyle ( background );
    }
}
