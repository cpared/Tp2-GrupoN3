package Vistas;

import game.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import player.Player;

public class PlayerInfoHorizontalBox extends HBox {

    public PlayerInfoHorizontalBox ( Game game, Player player, String background ) {
        Label points = new Label ( "Your points: " );
        points.setTextAlignment ( TextAlignment.CENTER );
        points.setTextFill ( Color.WHITE );
        Text p = new Text ( "Your points: " );
        p.setFill ( Color.WHITE );
        p.autosize ();

        //Points
        int point = game.getPoints ( player );
        String str = Integer.toString ( point );
        Label number = new Label ( str );
        number.setTextAlignment ( TextAlignment.CENTER );
        number.setTextFill ( Color.WHITE );
        this.getChildren ().addAll ( points, number );

        this.setAlignment ( Pos.CENTER_RIGHT );
        this.setStyle ( background );
    }
}
