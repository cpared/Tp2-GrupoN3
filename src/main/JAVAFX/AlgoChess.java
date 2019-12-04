import Controlers.*;
import Vistas.*;
import boardFx.ButtonCell;
import game.Game;
import game.ItIsNotYourTurnException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Pair;
import player.Player;

import java.io.File;


public class AlgoChess extends Application {
    private Background background = new AlgoChessBackground ( "Image/scene00background.jpg" ).createBackground ();
    private ButtonView view = new ButtonView ();
    private Game game = new Game ();
    private Scene scene1;
    private GridPane board;
    private SelectPieceSceneView scene2 = new SelectPieceSceneView();

    public AlgoChess () {
        this.board = makeGridPane();
    }

    public static void main ( String[] args ) {
        launch ( args );
    }

    @Override
    public void start ( Stage stage ) throws Exception {

        stage.setTitle ( " AlgoChess " );

        this.scene00InitialStage ( stage );
        //this.scene2.scene02SelectPieces(stage, this.game.getPlayer1().name(), this.game.getPlayer2().name());


        //this.scene02SelectPieces( stage );

        //sceneMainGame( stage );
        //this.sceneFinal ( stage );
    }

    public void sceneMainGame( Stage stage ){


    }

    public void scene00InitialStage ( Stage stage ) throws InterruptedException {

        //Set game music
        String path = "src/main/JAVAFX/Image/Metallica-Master_Of_Puppets.mp3";

        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setAutoPlay(true);

        //Button stop
        Button stopButton = new Button();
        stopButton.getStyleClass().add("buttonStop");
        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    mediaPlayer.stop();
                    stopButton.setStyle("-fx-background-image: url('Image/speaker-off.png')");
            }
        });

        BorderPane borderPane = new BorderPane ();

        // AlgoChess image.
        Image logo = new Image ( "Image/algochess.png" );
        ImageView logoView = new ImageView ( logo );

        Label name = new Label ();
        name.setGraphic ( logoView );
        name.setContentDisplay ( ContentDisplay.CENTER );

        // Texts.
        Label welcome = new Label ( "Welcome to the game!" );
        welcome.getStyleClass().add("welcome");
        welcome.setTextAlignment ( TextAlignment.CENTER );
        welcome.setTextFill ( Color.WHITE );

        Label instruction = new Label ( "Instructions" );
        instruction.setTextAlignment ( TextAlignment.CENTER );
        instruction.setTextFill ( Color.WHITE );

        Instructions instructions = new Instructions ( borderPane );

        // Start button.
        //Button buttonStart = this.view.createButton ( "Start" );
        Button buttonStart = new Button("Start");
        Scene playerChoosesName = this.scene01PlayerChoosesName ( stage );
        this.scene1 = playerChoosesName;
        ButtonsThatChangeScenesEventHandler buttonsThatChangeScenesEventHandler = new ButtonsThatChangeScenesEventHandler ( stage, playerChoosesName );
        buttonStart.setOnAction ( buttonsThatChangeScenesEventHandler );

        // Creating the vertical box containing welcoming, instruction and instructions.
        VBox verticalB = new VBox ( welcome, instruction, instructions, buttonStart );
        verticalB.setSpacing ( 3 );
        verticalB.setAlignment ( Pos.CENTER );
        verticalB.setMaxSize(600,300);

        // Setting panes.
        borderPane.setTop ( name );
        BorderPane.setAlignment ( name, Pos.BOTTOM_CENTER );
        borderPane.setCenter ( verticalB );
        BorderPane.setAlignment ( name, Pos.TOP_CENTER );
        borderPane.setBottom(stopButton);
        BorderPane.setAlignment(stopButton, Pos.BOTTOM_CENTER);

        //Adding background.
        borderPane.setBackground ( this.background );

        // Final layout.
        Scene scene = new Scene ( borderPane );
        stage.setScene ( scene );
        scene.getStylesheets().add("AlgoStyle.css");
        //stage.setFullScreen ( true );
        stage.setWidth ( 1550 );
        stage.setHeight ( 1000 );
        stage.show ();
    }


    public Scene scene01PlayerChoosesName (Stage stage) throws InterruptedException {

        // Text
        TextField text = new TextField ();

        //Buttons
        Button acceptButton = new Button ("Accept");

        //Label
        Label label = new Label("Select your name");
        Label errorText = new Label();

        //Grid position on scene
        GridPane grid = new GridPane();

        //Set position on scene
        grid.setAlignment(Pos.CENTER);
        //grid.setStyle("-fx-background-color: black");
        grid.setMaxSize(270, 100);

        //Set separation
        grid.setVgap(10);
        grid.setHgap(10);

        //Add elements
        grid.add(label, 0,1);
        grid.add(text,0,2);
        grid.add(acceptButton,0,3);
        grid.add(errorText, 0, 4);

        //BorderPane
        BorderPane borderpane = new BorderPane();
        borderpane.setBackground(this.background);
        borderpane.setCenter(grid);

        acceptButton.setOnAction ( new AcceptButtonEventHandler ( text, this.game, stage , errorText, this.scene2));
        text.setOnKeyPressed ( new NameEventHandler ( acceptButton ) );

        Scene scene = new Scene ( borderpane );
        scene.getStylesheets().add("AlgoStyle.css");
        return scene;
    }

    public Pair<RadioButton, RadioButton> setGameStage(GridPane grid){
        final ToggleGroup group = new ToggleGroup();
        RadioButton movePiece = new RadioButton("nope");
        movePiece.setText("Este boton va a ser para mover");
        movePiece.setToggleGroup(group);
        movePiece.setStyle("-fx-background-color: #008f39; -fx-opacity: 1.5;");
        movePiece.setSelected(true);
        RadioButton attackPiece = new RadioButton("sip");
        attackPiece.setText("Este boton va a ser para atacar");
        attackPiece.setToggleGroup(group);
        attackPiece.setStyle("-fx-background-color: #ED8181; -fx-opacity: 1.5;");
        SceneToAttack(this.game, grid,movePiece,attackPiece);
        //nope.setOnAction ( new ButtonsThatChangeScenesEventHandler ( stage, this.scene03Game ( stage ) ) );

        return new Pair <RadioButton,RadioButton> (movePiece,attackPiece);
    }


    public Scene sceneFinal ( Stage stage ) {

        // AlgoChess image.
        Image gameover = new Image ( "Image/gameover.png" );
        ImageView gameOverView = new ImageView ( gameover );

        // Exit button
        //Button exit = new Button ("Exit");
        Button exit = this.view.createButton ( "Exit" );
        ExitButtonEventHandler exitButtonEventHandler = new ExitButtonEventHandler ( exit, stage );
        exit.setOnKeyPressed ( exitButtonEventHandler );
        exit.setOnMouseClicked ( exitButtonEventHandler );

        // Play again button.
        Button playAgain = this.view.createButton ( "New Game" );
        playAgain.setOnAction ( new ButtonsThatChangeScenesEventHandler ( stage, this.scene1)  );

        // Horizontal box containing exit & playAgain
        HBox horizontal = new HBox ( exit, playAgain );
        horizontal.setAlignment ( Pos.CENTER );
        horizontal.setSpacing ( 60 );

        exit.prefWidthProperty ().bind ( horizontal.widthProperty ().divide ( 8 ) );
        playAgain.prefWidthProperty ().bind ( horizontal.widthProperty ().divide ( 8 ) );


        // Vertical Box
        VBox vertical = new VBox ( gameOverView, horizontal );
        vertical.setSpacing ( 40 );
        vertical.prefWidthProperty ().bind ( vertical.widthProperty ().divide ( 6 ) );
        vertical.prefHeightProperty ().bind ( vertical.widthProperty ().divide ( 6 ) );

        vertical.setAlignment ( Pos.CENTER );
        vertical.setBackground ( this.background );

        // Final layout.
        Scene scene = new Scene ( vertical );
        return scene;
        //stage.setScene ( scene );
        //stage.show ();
    }

    private GridPane makeGridPane() {
        String green = "-fx-background-color: #0000FF; -fx-opacity: 0.8;";
        String red = "-fx-background-color: #FFFA00; -fx-opacity: 0.6;";
        String actual = green;
        AlgoGrid gridPane = new AlgoGrid();
        for (int i = 0 ; i< 20;i++) {
            if (i == 10){
                actual = red;
            }
            for (int j = 0; j < 20; j++) {
                ButtonCell button = new ButtonCell(null,actual,i,j);
                button.setPrefSize(30, 30);
                //button.setOnKeyPressed ( new BoardPositionHasBeenChosenInInitialFaceEventHandler ( null, this, button) );
                //button.setOnMouseClicked ( new BoardPositionHasBeenChosenInInitialFaceEventHandler ( null, this, button) );
                gridPane.add(button,i,j);
            }
        }
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }
    private Pair<Integer,Integer> pair = null;
    public void SceneToAttack(Game game, GridPane board, RadioButton moveButton, RadioButton attackButton){
        VBox a = new VBox ( moveButton, attackButton);
        for (Node each:  board.getChildren()){
            each.setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent mouseEvent) {
                   ButtonCell button = (ButtonCell) each;
                   if (pair == null && !(button.emptyImage())) {
                       pair = button.getPosition();
                   }
                   else if(pair != null) {
                       Pair<Integer,Integer> newPair = button.getPosition();
                       try {
                           privateMethod(newPair, game.getPlayer1());
                       }
                       catch(ItIsNotYourTurnException e){
                           privateMethod(newPair, game.getPlayer2());
                       }
                       catch(Exception i){
                           System.out.println(i);
                       }
                   }
               }

               private void privateMethod(Pair<Integer, Integer> newPair, Player player) {
                   if (moveButton.isSelected()) {
                       game.playerMovesPieceOnBoard(player,pair.getKey(),pair.getValue(),newPair.getKey(),newPair.getValue());
                   }
                   if (attackButton.isSelected()){
                       game.playerAttacks(player,pair.getKey(),pair.getValue(),newPair.getKey(),newPair.getValue());
                   }
               }
           }
            );
        }
    }
}
