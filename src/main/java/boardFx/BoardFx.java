package boardFx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        label.setPadding(new Insets(0,200,0,870));
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
        firstPlayer.setPadding(new Insets(20,20,20,200));
        secondPlayer.setPadding(new Insets(20,200,20,20));
        bottomRow.getChildren().addAll(firstPlayer, secondPlayer);
        return bottomRow;
    }

    private GridPane makeGridPane() {
        String blue = "-fx-background-color: #0000ff;";
        String red = "-fx-background-color: #ff0000;";
        String actual = blue;
        GridPane gridPane = new GridPane();
        for (int i = 0 ; i< 20;i++) {
            if (i == 10){
                actual = red;
            }
            for (int j = 0; j < 20; j++) {
                gridPane.add(new ButtonCell(null,actual,i,j),i,j);
            }
        }
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        return gridPane;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}