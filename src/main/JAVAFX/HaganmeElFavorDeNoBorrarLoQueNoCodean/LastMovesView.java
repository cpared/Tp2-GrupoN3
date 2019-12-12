package HaganmeElFavorDeNoBorrarLoQueNoCodean;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LastMovesView extends  VBox {

    public LastMovesView ( Label firstMove, Label secondMove ) {
        Label last = new Label ( "Last two moves in the game:" );
        last.getStyleClass ().add ( "lastStyle" );
        firstMove.getStyleClass ().add ( "movesStyle" );
        secondMove.getStyleClass ().add ( "movesStyle" );
        VBox lastVBox = new VBox ( last );
        lastVBox.setAlignment ( Pos.CENTER );
       this.getChildren() .addAll( lastVBox, firstMove, secondMove );
       this.setSpacing ( 10 );
       this.setAlignment ( Pos.CENTER );
       this.getStyleClass ().add ( "VBox" );
    }
}
