package boardFx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class BoardFx extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("AlgoChess");
        Pane root = new VBox(5);
        GridPane gridPane = makeGridPane();
        gridPane.setAlignment(Pos.CENTER);
        Pane format = new VBox(5);
        AnchorPane bottomRow = getPlayersNames("Cris se la re come", "Es verdad");
        Label label = new Label();
        label.setText("AlgoChess");
        label.setFont(new Font(20));
        label.setAlignment(Pos.TOP_CENTER);
        format.getChildren().addAll(label,gridPane);
        root.getChildren().addAll(format,bottomRow);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private AnchorPane getPlayersNames(String playerOne, String playerTwo) {
        AnchorPane bottomRow = new AnchorPane();
        Label firstPlayer = new Label(playerOne);
        Label secondPlayer = new Label(playerTwo);
        AnchorPane.setLeftAnchor(firstPlayer, 0.0);
        AnchorPane.setRightAnchor(secondPlayer, 0.0);
        bottomRow.getChildren().addAll(firstPlayer, secondPlayer);
        return bottomRow;
    }

    private GridPane makeGridPane() {
        Image blue = new Image(getClass().getResourceAsStream("blue.jpg"));
        Image red = new Image(getClass().getResourceAsStream("red.jpg"));
        Image actual = blue;
        GridPane gridPane = new GridPane();
        for (int i = 0 ; i< 20;i++) {
            if (i == 10){
                actual = red;
            }
            for (int j = 0; j < 20; j++) {
                gridPane.add(new ButtonCell(null,new ImageView(actual),i,j),i,j);
            }
        }
        return gridPane;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}