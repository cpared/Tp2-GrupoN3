package boardFx;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class ButtonCell extends Button {
    Pair<Integer,Integer> positionCell;
    public ButtonCell(String name, ImageView imageView, int row, int column) {
        super(name,imageView);
        positionCell = new Pair<>(row,column);
    }
}
