package boardFx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class BoardFx extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("AlgoChess");
        StackPane layout = new StackPane();
        Image blue = new Image(getClass().getResourceAsStream("blue.jpg"));
        Image red = new Image(getClass().getResourceAsStream("red.jpg"));
        Image actual = blue;
        Map <Integer,Button> buttons = new HashMap<Integer, Button>();
        GridPane gridPane = new GridPane();
        for (int i = 0; i < 400; i++){
            if (i == 200){
                actual = red;
            }
            buttons.put(i,new Button(null,new ImageView(actual)));
        }
        int button = 0;
        for (int i = 0 ; i< 20;i++) {
            for (int j = 0; j < 20; j++) {
                gridPane.add(buttons.get(button),i,j);
                button++;
            }
        }
        gridPane.setAlignment(Pos.CENTER);
        Label label = new Label();
        label.setText("AlgoChess");
        label.setAlignment(Pos.CENTER_LEFT);
        Scene scene = new Scene(gridPane, 600, 240);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}