package Controlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartButtonEventHandler implements EventHandler<ActionEvent> {
    private Stage stage;
    private Scene scene;

    public StartButtonEventHandler ( Stage initialStage, Scene nextScene ) {
        this.stage = initialStage;
        this.scene = nextScene;
    }

    @Override
    public void handle ( ActionEvent event ) {
        this.stage.setScene(this.scene);
    }

}
