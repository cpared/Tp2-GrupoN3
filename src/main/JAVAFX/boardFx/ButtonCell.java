package boardFx;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class ButtonCell extends Button {
    Pair<Integer,Integer> positionCell;

    public ButtonCell(String name, String color, int row, int column) {
        super(name);
        this.setStyle(color);
        positionCell = new Pair<>(row,column);
    }

    public Pair<Integer, Integer> getPosition(){
        return positionCell;
    }
    public boolean emptyImage(){
        return this.getStyleClass().isEmpty();
    }
    public void placeImage(ImageView piece){
        this.setGraphic(new ImageView(String.valueOf(piece)));
    }
    public void removeImage(Image piece){
        this.setGraphic(null);
    }


}
