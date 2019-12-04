import Controlers.*;
import Vistas.*;
import boardFx.ButtonCell;
import game.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


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
        //this.scene02SelectPieces( stage );
        //sceneMainGame( stage );
        //this.sceneFinal ( stage );
    }

    public void sceneMainGame( Stage stage ){


    }

    public void scene00InitialStage ( Stage stage ) {

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
        acceptButton.setOnAction ( new AcceptButtonEventHandler ( text, this.game, stage , this.scene02SelectPieces ( stage ), errorText));
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

    public Scene scene02SelectPieces( Stage stage ){

        BorderPane borderPane = new BorderPane();

        //buttons
        Button start = new Button("Start");
        start.getStyleClass().add("buttonStart");
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                borderPane.setLeft(null);
            }
        });
        start.setMinWidth(150);
        start.setMinHeight(40);

        Button soldierButton = new Button();
        soldierButton.getStyleClass().add("buttonSoldier");
        soldierButton.setMinWidth(60);
        soldierButton.setMinHeight(60);

        Button riderButton = new Button();
        riderButton.getStyleClass().add("buttonRider");
        riderButton.setMinWidth(60);
        riderButton.setMinHeight(60);

        Button healerButton = new Button();
        healerButton.getStyleClass().add("buttonHealer");
        healerButton.setMinWidth(60);
        healerButton.setMinHeight(60);

        Button catapultButton = new Button();
        catapultButton.getStyleClass().add("buttonCatapult");
        catapultButton.setMinWidth(60);
        catapultButton.setMinHeight(60);

        //Images
        Image attackImage = new Image("Image/broadsword.png");
        ImageView attackView = new ImageView(attackImage);
        attackView.setFitHeight(20);
        attackView.setFitWidth(20);

        Image healthImage = new Image("Image/heart-plus.png");
        ImageView healthView = new ImageView(healthImage);
        healthView.setFitHeight(20);
        healthView.setFitWidth(20);

        Image coinImage = new Image("Image/crown-coin.png");
        ImageView coinView = new ImageView(coinImage);
        coinView.setFitHeight(20);
        coinView.setFitWidth(20);

        Image behaviorImage = new Image("Image/guards.png");
        ImageView behaviorView = new ImageView(behaviorImage);
        behaviorView.setFitHeight(20);
        behaviorView.setFitWidth(20);

        //Text
        Label attackInformation = new Label("-");
        Label healthInformation = new Label("-");
        Label priceInformation = new Label("-");
        Label information = new Label("-");

        /*
        Label playerOneText = new Label("Player One: ");
        Label playerTwoText = new Label("Player Two: ");
        Label playerOneTextCoin = new Label("Coins: ");
        Label playerTwoTextCoin = new Label("Coins: ");
        playerOneText.getStyleClass().add("textStyle");
        playerTwoText.getStyleClass().add("textStyle");
        playerOneTextCoin.getStyleClass().add("textStyle");
        playerTwoTextCoin.getStyleClass().add("textStyle");
*/
        Label playerOneText = new Label("Player One: ");
        Label playerTwoText = new Label("Player Two: ");
        Label playerOneTextCoin = new Label("Coins: ");
        Label playerTwoTextCoin = new Label("Coins: ");
        playerOneText.getStyleClass().add("textStyle");
        playerTwoText.getStyleClass().add("textStyle");
        playerOneTextCoin.getStyleClass().add("textStyle");
        playerTwoTextCoin.getStyleClass().add("textStyle");

        //Set action on buttons
        //ESTOY DUPLICANDO CÓDIGO EN LOS EVENTHANDLER
        SoldierSelectStatsHandler soldierSelectStatsHandler = new SoldierSelectStatsHandler(attackInformation,healthInformation, priceInformation, information, attackView);
        soldierButton.setOnMouseClicked( soldierSelectStatsHandler );

        RiderSelectStatsHandler riderSelectStatsHandler = new RiderSelectStatsHandler(attackInformation,healthInformation, priceInformation, information, attackView);
        riderButton.setOnMouseClicked( riderSelectStatsHandler );

        HealerSelectStatsHandler healerSelectStatsHandler = new HealerSelectStatsHandler(attackInformation,healthInformation, priceInformation, information, attackView);
        healerButton.setOnMouseClicked( healerSelectStatsHandler );

        CatapultSelectStatsHandler catapultSelectStatsHandler = new CatapultSelectStatsHandler(attackInformation,healthInformation, priceInformation, information, attackView);
        catapultButton.setOnMouseClicked( catapultSelectStatsHandler );

        //Vertical box
        VBox vbox = new VBox(playerOneText, playerOneTextCoin);
        vbox.setAlignment(Pos.CENTER_RIGHT);

        VBox vbox1 = new VBox(playerTwoText, playerTwoTextCoin);
        vbox1.setAlignment(Pos.CENTER_RIGHT);

        Region regionLeft = new Region();
        HBox.setHgrow(regionLeft, Priority.ALWAYS);

        Region regionRigth = new Region();
        HBox.setHgrow(regionRigth, Priority.ALWAYS);

        //Horizontal box
        HBox hbox = new HBox(vbox, regionLeft, vbox1, regionRigth);
        hbox.setMinHeight(150);
        hbox.getStyleClass().add("hbox");

        //Pieces grid
        GridPane piecesGrid = new GridPane();
        piecesGrid.setMinWidth(250);
        piecesGrid.setMaxWidth(100);
        piecesGrid.setPadding(new Insets(10, 10, 10, 10));
        //piecesGrid.setMargin(soldierButton, new Insets(12,12,12,12));77
        piecesGrid.setVgap(15);
        piecesGrid.setHgap(15);

        piecesGrid.add(soldierButton, 0,0);
        piecesGrid.add(riderButton, 0,1);
        piecesGrid.add(healerButton,1,0);
        piecesGrid.add(catapultButton,1,1);

        piecesGrid.add(attackView,0,4);
        piecesGrid.add(attackInformation,1,4);
        piecesGrid.add(healthView,0,5);
        piecesGrid.add(healthInformation,1,5);
        piecesGrid.add(coinView,0,6);
        piecesGrid.add(priceInformation,1,6);
        piecesGrid.add(behaviorView,0,7);
        piecesGrid.add(information,1,7);
        piecesGrid.setAlignment(Pos.CENTER);
        piecesGrid.getStyleClass().add("piecesGrid");


        //Board
        GridPane board = makeGridPane();
        board.getStyleClass().add("board");

        //Left toolbar

        //borderPane.setMaxSize(600,400);
        borderPane.setLeft(piecesGrid);
        BorderPane.setAlignment(piecesGrid,Pos.CENTER_LEFT);
        borderPane.setTop(start);
        BorderPane.setAlignment(start, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(start, new Insets(12,12,12,12));
        borderPane.setBackground(this.background);
        borderPane.setBottom(hbox);
        borderPane.setCenter(board);
        BorderPane.setAlignment(board, Pos.CENTER);

        Scene scene = new Scene ( borderPane );
        stage.setScene(scene);
        scene.getStylesheets().add("SelectStyle.css");
/*        stage.setWidth ( 1550 );
        stage.setHeight ( 830 );
        stage.show();*/
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
        String green = "-fx-background-color: #008f39; -fx-opacity: 0.8;";
        String red = "-fx-background-color: #ED8181; -fx-opacity: 0.5;";
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
}
