import Controlers.*;
import Vistas.AlgoChessBackground;
import Vistas.ButtonView;
import Vistas.Instructions;
import Vistas.SelectPieceSceneView;
import game.Game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import player.Player;

import java.io.File;


public class AlgoChess extends Application {
    private Background background = new AlgoChessBackground ( "Image/scene00background.jpg" ).createBackground ();
    private ButtonView view = new ButtonView ();
    private Game game;
    private Scene scene1;
    private SelectPieceSceneView scene2;
    private MediaPlayer mediaPlayer;
    private MediaPlayer soundPlayer;
    private String path = "src/main/JAVAFX/SoundEffects/Metallica-Master_Of_Puppets.mp3";
    private Media media = new Media ( new File ( path ).toURI ().toString () );

    public AlgoChess () {
    }

    public static void main ( String[] args ) {
        launch ( args );
    }

    @Override
    public void start ( Stage stage ) throws Exception {
        //stage.setMaximized(true);
        stage.setTitle ( " AlgoChess " );
        this.game = new Game ();

        this.soundtrack ( path );
        this.scene2 = new SelectPieceSceneView ( this, this.mediaPlayer );

        this.scene00InitialStage ( stage );
    }

    public void soundtrack ( String path ) {

        Media media = new Media ( new File ( path ).toURI ().toString () );
        this.mediaPlayer = new MediaPlayer ( media );
        this.mediaPlayer.setCycleCount ( MediaPlayer.INDEFINITE );
        this.mediaPlayer.setAutoPlay ( true );
    }

    public void scene00InitialStage ( Stage stage ) throws InterruptedException {


        //Button stop
        Button stopButton = new Button ();
        stopButton.getStyleClass ().add ( "buttonStop" );
        stopButton.setOnAction ( new MusicButtonEventHandler ( this.mediaPlayer, stopButton, "buttonStop", "buttonStopMuted" ) );
        BorderPane borderPane = new BorderPane ();

        // AlgoChess image.
        Image logo = new Image ( "Image/algochess.png" );
        ImageView logoView = new ImageView ( logo );

        Label name = new Label ();
        name.setGraphic ( logoView );
        name.setContentDisplay ( ContentDisplay.CENTER );

        // Texts.
        Label welcome = new Label ( "Welcome to the game!" );
        welcome.getStyleClass ().add ( "welcome" );
        welcome.setTextAlignment ( TextAlignment.CENTER );
        welcome.setTextFill ( Color.WHITE );

        Label instruction = new Label ( "Instructions" );
        instruction.setTextAlignment ( TextAlignment.CENTER );
        instruction.setTextFill ( Color.WHITE );
        instruction.setStyle ( "-fx-font-size:40;" );

        Instructions instructions = new Instructions ( borderPane );

        // Start button.
        Button buttonStart = new Button ( "Start" );
        buttonStart.setMinWidth ( 150 );
        buttonStart.setMinHeight ( 40 );

        buttonStart.setAlignment ( Pos.BOTTOM_CENTER );
        Scene playerChoosesName = this.scene01PlayerChoosesName ( stage );
        this.scene1 = playerChoosesName;
        ButtonsThatChangeScenesEventHandler buttonsThatChangeScenesEventHandler = new ButtonsThatChangeScenesEventHandler ( stage, playerChoosesName );
        buttonStart.setOnKeyPressed ( buttonsThatChangeScenesEventHandler );
        buttonStart.setOnMouseClicked ( buttonsThatChangeScenesEventHandler );

        // Creating the vertical box containing welcoming, instruction and instructions.
        Region topRegion = new Region ();
        VBox.setVgrow ( topRegion, Priority.ALWAYS );

        Region centerRigion = new Region ();
        VBox.setVgrow ( centerRigion, Priority.ALWAYS );

        VBox verticalB = new VBox ( welcome, topRegion, instruction, instructions, centerRigion, buttonStart );
        VBox.setMargin ( welcome, new Insets ( 20 ) );
        VBox.setMargin ( buttonStart, new Insets ( 20 ) );
        verticalB.setSpacing ( 3 );
        verticalB.setAlignment ( Pos.CENTER );
        verticalB.setMaxSize ( 1000, 700 );

        Region regionLeft = new Region ();
        HBox.setHgrow ( regionLeft, Priority.ALWAYS );

        Region regionCenter = new Region ();
        HBox.setHgrow ( regionCenter, Priority.ALWAYS );

        Label emptyLabel = new Label ( "" );

        HBox hbox = new HBox ( emptyLabel, regionLeft, name, regionCenter );
        HBox.setMargin ( stopButton, new Insets ( 10 ) );


        HBox bottom = new HBox ( stopButton );
        //HBox.setMargin ( stopButton, new Insets ( 10 ) );
        BorderPane.setAlignment ( stopButton, Pos.CENTER_RIGHT );
        // Setting panes.
        borderPane.setTop ( hbox );
        BorderPane.setAlignment ( name, Pos.BOTTOM_CENTER );
        borderPane.setCenter ( verticalB );
        BorderPane.setAlignment ( name, Pos.TOP_CENTER );
        borderPane.setBottom ( bottom );
        BorderPane.setAlignment ( bottom, Pos.CENTER_RIGHT );

        //Adding background.
        borderPane.setBackground ( this.background );

        // Final layout.
        //Scene scene = new Scene ( borderPane, 1500,800 );
        Scene scene = new Scene ( borderPane );
        stage.setMaximized ( true );
        stage.setScene ( scene );
        scene.getStylesheets ().add ( "AlgoStyle.css" );
        stage.show ();
    }

    public Scene scene01PlayerChoosesName ( Stage stage ) throws InterruptedException {

        // Text
        TextField text = new TextField ();
        text.setPadding ( new Insets ( 10 ) );

        //Buttons
        Button acceptButton = new Button ( "Accept" );
        acceptButton.setPadding ( new Insets ( 10 ) );
        acceptButton.setMinWidth ( 150 );
        acceptButton.setMinHeight ( 40 );

        //Label
        Label label = new Label ( "Select your name" );
        label.setStyle ( "-fx-font-size:30;" );
        label.setPadding ( new Insets ( 10 ) );

        Label errorText = new Label ();
        errorText.setPadding ( new Insets ( 10 ) );

        //Grid position on scene
        GridPane grid = new GridPane ();

        //Set position on scene
        grid.setAlignment ( Pos.CENTER );
        grid.setMaxSize ( 800, 500 );

        //Set separation
        grid.setVgap ( 10 );
        grid.setHgap ( 10 );

        //Add elements
        grid.add ( label, 0, 1 );
        grid.add ( text, 0, 2 );
        grid.add ( acceptButton, 0, 3 );
        grid.add ( errorText, 0, 4 );

        //Bottom
        Button stopButton = new Button ();
        stopButton.getStyleClass ().add ( "buttonStop" );
        stopButton.setOnAction ( new MusicButtonEventHandler ( this.mediaPlayer, stopButton , "buttonStop", "buttonStopMuted" ) );

        HBox bottom = new HBox ( stopButton );
        HBox.setMargin ( stopButton, new Insets ( 10 ) );


        //BorderPane
        BorderPane borderpane = new BorderPane ();
        borderpane.setBackground ( this.background );
        borderpane.setCenter ( grid );
        borderpane.setBottom ( bottom );

        acceptButton.setOnAction ( new AcceptButtonEventHandler ( text, this.game, stage, errorText, this.scene2 ) );
        text.setOnKeyPressed ( new NameEventHandler ( acceptButton ) );

        Scene scene = new Scene ( borderpane );
        scene.getStylesheets ().add ( "AlgoStyle.css" );
        return scene;
    }

    public Scene sceneFinal ( Stage stage, Player player ) {

        // AlgoChess image.
        Image gameover = new Image ( "Image/gameover.png" );
        ImageView gameOverView = new ImageView ( gameover );
        Label name = new Label ( "Player: " + player.name () + " has won." );
        // Exit button
        //Button exit = new Button ("Exit");
        Button exit = this.view.createButton ( "Exit" );
        ExitButtonEventHandler exitButtonEventHandler = new ExitButtonEventHandler ( exit, stage );
        exit.setOnKeyPressed ( exitButtonEventHandler );
        exit.setOnMouseClicked ( exitButtonEventHandler );

        // Play again button.
        Button playAgain = this.view.createButton ( "New Game" );
        //playAgain.setOnAction ( new ButtonsThatChangeScenesEventHandler ( stage, this.scene1 ) );

        // Horizontal box containing exit & playAgain
        HBox horizontal = new HBox ( exit, playAgain );
        horizontal.setAlignment ( Pos.CENTER );
        horizontal.setSpacing ( 60 );

        exit.prefWidthProperty ().bind ( horizontal.widthProperty ().divide ( 8 ) );
        playAgain.prefWidthProperty ().bind ( horizontal.widthProperty ().divide ( 8 ) );


        // Vertical Box
        VBox vertical = new VBox ( gameOverView, horizontal, name );
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
}