package Vistas;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class AlgoChessBackground {
    private Image image;
    private  BackgroundSize size;

    public AlgoChessBackground (String imageURL) {
        this.image = new Image ( imageURL );
        this.size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
    }

    public Background createBackground(){
        BackgroundImage backgroundImage = new BackgroundImage (this.image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                this.size);

        return new Background( backgroundImage);
    }

}
