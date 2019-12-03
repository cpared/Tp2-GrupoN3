import Controlers.AcceptButtonEventHandler;
import Controlers.ButtonsThatChangeScenesEventHandler;
import Controlers.ExitButtonEventHandler;
import Controlers.NameEventHandler;
import Vistas.*;
import boardFx.ButtonCell;
import game.Game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class AlgoChess extends Application {
    private Background background = new AlgoChessBackground ( "Image/scene00background.jpg" ).createBackground ();
    private ButtonView view = new ButtonView ();
    private Game game = new Game ();
    private Scene scene1;
    private GridPane board;

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
        //sceneMainGame( stage );
        //this.sceneFinal ( stage );
    }

    public void sceneMainGame( Stage stage ){
        BorderPane borderPane = new BorderPane();

        Image board = new Image("Image/boardLast.png");
        ImageView boardView = new ImageView(board);

        Image sideBar = new Image("Image/sideBar.png");
        ImageView sideBarView = new ImageView(sideBar);

        Image soldier = new Image("Image/pieces/archer3.png");
        ImageView soldierView = new ImageView(soldier);

        StackPane stack = new StackPane ();
        stack.getChildren ().addAll ( boardView );
        stack.getChildren ().addAll ( soldierView );

        Scene scene = new Scene ( stack );
        stage.setScene ( scene );
        stage.show ();

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

        //Adding background.
        borderPane.setBackground ( this.background );

        // Final layout.
        Scene scene = new Scene ( borderPane );
        stage.setScene ( scene );
        scene.getStylesheets().add("AlgoStyle.css");
        //stage.setFullScreen ( true );
        stage.setWidth ( 1550 );
        stage.setHeight ( 830 );
        stage.show ();
    }


    public Scene scene01PlayerChoosesName (Stage stage){

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

        //Events
        acceptButton.setOnAction ( new AcceptButtonEventHandler ( text, this.game, stage , this.scene02PlayerPlacesPieces ( stage ), errorText));
        text.setOnKeyPressed ( new NameEventHandler ( acceptButton ) );

        Scene scene = new Scene ( borderpane, 300.0D, 250.0D );
        scene.getStylesheets().add("AlgoStyle.css");
        return scene;
    }

    public Scene scene02PlayerPlacesPieces (Stage stage){

        String background = "-fx-background-color: B22222;";
        ChoosingPiecesBorderPane pieces = new ChoosingPiecesBorderPane (this.game, game.getPlayer1 (), stage, this.scene03Game ( stage ), background,this.board);
        Scene scene = new Scene ( pieces );
        return scene;
    }

    public Scene scene03Game (Stage stage){
        final ToggleGroup group = new ToggleGroup();
        RadioButton movePiece = new RadioButton("nope");
        movePiece.setText("Este boton va a ser para mover");
        movePiece.setToggleGroup(group);
        movePiece.setSelected(true);
        RadioButton attackPiece = new RadioButton("sip");
        attackPiece.setText("Este boton va a ser para atacar");
        attackPiece.setToggleGroup(group);
        SceneToAttack cosito = new SceneToAttack(this.game, this.board,movePiece,attackPiece);
        //nope.setOnAction ( new ButtonsThatChangeScenesEventHandler ( stage, this.scene03Game ( stage ) ) );
        
        Scene scene = new Scene ( cosito );

        return scene;
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
        String green = "-fx-background-color: #008f39;";
        String red = "-fx-background-color: #ff0000;";
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
        return gridPane;
    }
}
