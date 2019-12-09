package HaganmeElFavorDeNoBorrarLoQueNoCodean;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PiecesGridPane extends GridPane {
    private ImageView attackView;
    private ImageView healthView;
    private ImageView priceView;
    private ImageView behaviorView;

    public PiecesGridPane ( Button choosePieceButton ,Button soldierButton, Button riderButton, Button healerButton, Button catapultButton,ImageView attackView, ImageView healthView, ImageView priceView, ImageView behaviorView, Label attackInformation, Label healthInformation, Label priceInformation, Label information) {

        this.setMinWidth(250);
        this.setMaxWidth(100);
        this.setPadding(new Insets (10, 10, 10, 10));
        this.setVgap(15);
        this.setHgap(15);

        this.add(choosePieceButton, 0,0);
        this.add(soldierButton, 0,1);
        this.add(riderButton, 0,2);
        this.add(healerButton,1,1);
        this.add(catapultButton,1,2);

        this.add(attackView,0,4);
        this.add(attackInformation,1,4);
        this.add(healthView,0,5);
        this.add(healthInformation,1,5);
        this.add(priceView,0,6);
        this.add(priceInformation,1,6);
        this.add(behaviorView,0,7);
        this.add(information,1,7);
        this.setAlignment( Pos.CENTER);
        this.getStyleClass().add("piecesGrid");
    }

}
