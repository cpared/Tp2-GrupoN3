package Vistas.PieceInformationDuringGame;

import HaganmeElFavorDeNoBorrarLoQueNoCodean.LastMovesView;
import HaganmeElFavorDeNoBorrarLoQueNoCodean.Turn;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DefaultPieceView extends VBox {

    public DefaultPieceView ( Turn turn , Label first, Label second) {
        HBox name = turn.getCurrentPlayersName ();
        name.getStyleClass ().add ( "textStyle" );
        Label piece = new Label ( "Choose a Piece to view options" );
        piece.getStyleClass ().add ( "textStyle" );

        VBox moves = new LastMovesView ( first,second );

        this.getChildren ().addAll ( name, piece, moves );
        this.getStyleClass ().add ( "piecesGrid" );
        this.setAlignment ( Pos.TOP_CENTER );
        this.setSpacing ( 40 );
    }

}
