package boardFx;

import javafx.scene.control.Button;
import javafx.util.Pair;

public class ButtonCell extends Button {
    Pair<Integer,Integer> positionCell;
    public ButtonCell(String name, String color, int row, int column) {
        super(name);
        this.setStyle(color);
        positionCell = new Pair<>(row,column);
    }
}
