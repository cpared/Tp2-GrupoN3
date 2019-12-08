package Vistas;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.paint.Color;

public class Instructions extends TextFlow {
    private Color textColor = Color.WHITE;
    private TextAlignment textAlignment = TextAlignment.CENTER;

    public Instructions ( BorderPane pane ) {
        this.addText ( createText ( "Each player must choose the pieces with which they wish to play the game." ) );
        this.addText ( createText ( "Each player has 20 points available for use. The pieces in this game have a certain value so that when a player chooses a piece, the value of the piece is subtracted from the points the player currently has. The players must choose their pieces to start the game and place them on their side of the board." ) );
        this.addText ( createText ( "The game is won when the opponent has no pieces left on the board." ) );
        this.setLineSpacing ( 5 );
        this.setTextAlignment ( this.textAlignment );
        this.prefWidthProperty ().bind ( pane.widthProperty ().divide ( 5 ) );
    }

    private void addText ( Text newText ) {
        this.getChildren ().add ( newText );
    }

    private Text createText ( String text ) {
        Text newText = new Text ( text );
        newText.setTextAlignment ( this.textAlignment );
        newText.setFill ( this.textColor );
        newText.setStyle("-fx-font-size:25;");
        return newText;
    }

}
