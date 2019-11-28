package Controlers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ChangeInformationOnLabelEventHandler implements EventHandler<ActionEvent> {
    private TextFlow label;
    private Button button;
    private String string;

    public ChangeInformationOnLabelEventHandler ( TextFlow label, Button pressed , String string) {
        this.label = label;
        this.button = pressed;
        this.string= string;
    }

    @Override
    public void handle ( ActionEvent actionEvent ) {
        ObservableList<Node> remove = this.label.getChildren ();
        //this.label.getChildren ().remove ( 0 ,1);
        this.label.getChildren ().removeAll ( remove);
        this.label.getChildren ().add ( new Text (this.string) );
        //this.label.setText ( this.string);
    }
}
