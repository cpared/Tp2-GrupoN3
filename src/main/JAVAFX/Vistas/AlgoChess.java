package Vistas;

import Controlers.ExitButtonEventHandler;
import Controlers.StartButtonEventHandler;
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
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class AlgoChess extends Application {
    private Background background = new AlgoChessBackground ( "Image/scene00background.jpg" ).createBackground ();
    private ButtonView view = new ButtonView ();

    public AlgoChess () {
    }

    public static void main ( String[] args ) {
        launch ( args );
    }

    @Override
    public void start ( Stage stage ) throws Exception {
        stage.setTitle ( " AlgoChess " );
        //this.scene00InitialStage ( stage );
        this.sceneFinal ( stage );
    }

    public void scene00InitialStage ( Stage stage ) {

        BorderPane borderPane = new BorderPane ();

        // AlgoChess image.
        Image logo = new Image ( "Image/algochess.png" );
        ImageView logoView = new ImageView ( logo );

        Label name = new Label ();
        name.setGraphic ( logoView );
        name.setContentDisplay ( ContentDisplay.CENTER );

        // Texts.
        Label welcome = new Label ( "Welcome to the game!" );
        welcome.setTextAlignment ( TextAlignment.CENTER );
        welcome.setTextFill ( Color.WHITE );

        Label instruction = new Label ( "Instructions" );
        instruction.setTextAlignment ( TextAlignment.CENTER );
        instruction.setTextFill ( Color.WHITE );

        Instructions instructions = new Instructions ( borderPane );

        // Start button.
        Button buttonStart = this.view.createButton ( "Start" );
        Scene playerChoosesName = this.scene01PlayerChoosesName ( stage );
        StartButtonEventHandler startButtonEventHandler = new StartButtonEventHandler ( stage, playerChoosesName );
        buttonStart.setOnAction ( startButtonEventHandler );

        // Creating the vertical box containing welcoming, instruction and instructions.
        VBox verticalB = new VBox ( welcome, instruction, instructions, buttonStart );
        verticalB.setSpacing ( 3 );
        verticalB.setAlignment ( Pos.CENTER );
        verticalB.setMaxWidth ( 600 );


        // Setting panes.
        borderPane.setTop ( name );
        BorderPane.setAlignment ( name, Pos.BOTTOM_CENTER );
        borderPane.setCenter ( verticalB );
        //borderPane.prefWidthProperty ().bind ( borderPane.widthProperty().divide(5 ));
        BorderPane.setAlignment ( name, Pos.TOP_CENTER );


        //All the components are ready to be placed.

        // Locate the background image behind the border pane.
        StackPane stack = new StackPane ();
        StackPane.setAlignment ( borderPane, Pos.CENTER );
        stack.setBackground ( this.background );
        stack.getChildren ().addAll ( borderPane );

        // Final layout.
        Scene scene = new Scene ( stack );
        stage.setScene ( scene );
        stage.setFullScreen ( true );
        stage.show ();
    }


    public Scene scene01PlayerChoosesName ( Stage stage ) {
        /*
        Each player chooses the name they will have in the game.
        */

        TextField text = new TextField ();
        text.setPromptText ( "Please enter your name" );

        Label label = new Label ();
        label.setText ( text.getText () );

        Button acceptButton = this.view.createButton ( "Accept"  );


        HBox horizontalContainer = new HBox ( acceptButton );
        horizontalContainer.setSpacing ( 10.0D );
        VBox mainContainer = new VBox ( text, horizontalContainer, label );
        mainContainer.setSpacing ( 10.0D );
        mainContainer.setPadding ( new Insets ( 20.0D ) );
        mainContainer.setBackground ( this.background );
        mainContainer.setAlignment ( Pos.CENTER );


        Scene scene2 = new Scene ( mainContainer, 300.0D, 250.0D );
        //stage.setScene ( scene2 );
        return scene2;
    }


    public void sceneFinal ( Stage stage ) {

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
        playAgain.setOnAction ( new StartButtonEventHandler ( stage, this.scene01PlayerChoosesName ( stage ) ) );

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
        stage.setScene ( scene );
        stage.show ();
    }
}
