package boardFx;

import javafx.scene.control.Button;
import javafx.util.Pair;

public class ButtonCell extends Button {
    Pair<Integer,Integer> positionCell;
    Boolean bool = false;

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

    public void setBattalion() {
        bool =  true;
    }
    public boolean getBattalion(){
        return bool;
    }
}
