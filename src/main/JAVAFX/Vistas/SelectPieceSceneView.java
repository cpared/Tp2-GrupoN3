package Vistas;

import Controlers.CatapultSelectStatsHandler;
import Controlers.HealerSelectStatsHandler;
import Controlers.RiderSelectStatsHandler;
import Controlers.SoldierSelectStatsHandler;
import boardFx.*;
import game.Game;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import piece.Piece;
import player.Player;

public class SelectPieceSceneView {
    private ButtonPiece lastClicked = null;
    private ButtonPiece lastChoosed = null;
    private Label playerOneTextCoin;
    private Label playerTwoTextCoin;
    private Background background = new AlgoChessBackground ( "Image/scene00background.jpg" ).createBackground ();
    private Game game;


    public Scene scene02SelectPieces( Stage stage , String namePlayerOne, String namePlayerTwo, int playerOneCois, int playerTwoCois,Game game) throws InterruptedException {
        this.game = game;
        BorderPane borderPane = new BorderPane();
        GridPane board = makeGridPane();
        //buttons
        Button start = new Button("Start");
        start.getStyleClass().add("buttonStart");
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                changeScene(board, borderPane);
            }
        });
        start.setMinWidth(150);
        start.setMinHeight(40);


        Button choosePieceButton = new Button("Choose Piece");
        choosePieceButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lastChoosed = lastClicked;

            }
        });
        choosePieceButton.getStyleClass().add("button-choose");
        choosePieceButton.setAlignment(Pos.CENTER);

        ButtonPiece soldierButton = new ButtonPieceSoldier();
        soldierButton.getStyleClass().add("buttonSoldier");
        soldierButton.setMinWidth(60);
        soldierButton.setMinHeight(60);
        lastClicked = soldierButton;
        ButtonPiece riderButton = new ButtonPieceRider();
        riderButton.getStyleClass().add("buttonRider");
        riderButton.setMinWidth(60);
        riderButton.setMinHeight(60);

        ButtonPiece healerButton = new ButtonPieceRider();
        healerButton.getStyleClass().add("buttonHealer");
        healerButton.setMinWidth(60);
        healerButton.setMinHeight(60);

        ButtonPiece catapultButton = new ButtonPieceCatapult();
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

        Label playerOneText = new Label("Player One: " + namePlayerOne);
        Label playerTwoText = new Label("Player Two: " + namePlayerTwo);
        playerOneTextCoin = new Label("Coins: " + Integer.toString(playerOneCois));
        playerTwoTextCoin = new Label("Coins: " + Integer.toString(playerTwoCois));
        playerTwoTextCoin.getText();
        playerOneText.getStyleClass().add("textStyle");
        playerTwoText.getStyleClass().add("textStyle");
        playerOneTextCoin.getStyleClass().add("textStyle");
        playerTwoTextCoin.getStyleClass().add("textStyle");


        //Set action on buttons
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

        piecesGrid.add(choosePieceButton, 0,0);
        piecesGrid.add(soldierButton, 0,1);
        piecesGrid.add(riderButton, 0,2);
        piecesGrid.add(healerButton,1,1);
        piecesGrid.add(catapultButton,1,2);

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
        board.getStyleClass().add("board");
        setBoardCellAction(board);

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

    private void setBoardCellAction(GridPane board) {
        for (Node node: board.getChildren()){
            setButtonCellAction((ButtonCell) node);
        }
    }

    private void setButtonCellAction(ButtonCell button) {
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!(lastChoosed == null)){
                    Player player = game.getAvailablePlayer();
                    try{
                        Piece piece = lastChoosed.choosePiece(game,player);
                        Pair <Integer,Integer> pair = button.getPosition();
                        game.playerPlacesPieceOnBoard(player,piece,pair.getKey(),pair.getValue());
                        button.getStyleClass().add("buttonHealer");
                        lastChoosed = null;
                        playerOneTextCoin.setText(getCoins(game,game.getPlayer1()));
                        playerTwoTextCoin.setText(getCoins(game,game.getPlayer2()));
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                }
            }
        });
    }

    private GridPane makeGridPane() {
        String red = "-fx-background-color: #D53032; -fx-opacity: 0.7;";
        String blue = "-fx-background-color: #2D3C68; -fx-opacity: 0.9;";
        String actual = red;
        AlgoGrid gridPane = new AlgoGrid();
        for (int i = 0 ; i< 20;i++) {
            if (i == 10){
                actual = blue;
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
    private String getCoins(Game game, Player player){
        return "Coins" + Integer.toString(game.getPoints(player));
    }

    public Pair<RadioButton, RadioButton> setGameStage(GridPane grid) {
        final ToggleGroup group = new ToggleGroup();
        RadioButton movePiece = new RadioButton("nope");
        movePiece.setText("Mover");
        movePiece.setToggleGroup(group);
        movePiece.setStyle(" -fx-opacity: 1.5;-fx-font-size:30;-fx-text-fill: white;");
        movePiece.setSelected(true);
        movePiece.setPadding(new Insets(0,0,0,1100));

        RadioButton attackPiece = new RadioButton("sip");
        attackPiece.setText("Atacar");
        attackPiece.setToggleGroup(group);
        attackPiece.setStyle("-fx-opacity: 1.5;-fx-font-size:30;-fx-text-fill: white;");
        attackPiece.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            attackPiece.setStyle("-fx-opacity: 1.5;-fx-font-size:30; -fx-text-fill: green;");
                                            movePiece.setStyle("-fx-opacity: 1.5;-fx-font-size:30;-fx-text-fill: white;");
                                        }
                                    }
        );

        movePiece.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            movePiece.setStyle("-fx-opacity: 1.5;-fx-font-size:30; -fx-text-fill: green;");
                                            attackPiece.setStyle("-fx-opacity: 1.5;-fx-font-size:30;-fx-text-fill: white;");
                                        }
                                    }
        );


        SceneToAttack(this.game, grid, movePiece, attackPiece);
        //nope.setOnAction ( new ButtonsThatChangeScenesEventHandler ( stage, this.scene03Game ( stage ) ) );



        return new Pair<RadioButton, RadioButton>(movePiece, attackPiece);
    }
    private Pair<Integer,Integer> pair = null;
    private ButtonCell lastButton = null;


    public void SceneToAttack(Game game, GridPane board, RadioButton moveButton, RadioButton attackButton){
        VBox a = new VBox ( moveButton, attackButton);
        for (Node each:  board.getChildren()){
            each.setOnMouseClicked(new EventHandler<MouseEvent>() {
                   @Override
                   public void handle(MouseEvent mouseEvent) {
                       ButtonCell button = (ButtonCell) each;
                       if (pair == null && !(button.emptyImage())) {
                           pair = button.getPosition();
                           lastButton = button;
                       }
                       else if(pair != null) {
                           Pair<Integer,Integer> newPair = button.getPosition();
                           try {
                               privateMethod(newPair, game.getAvailablePlayer(),button);
                               lastButton = null;
                               pair = null;
                           }
                           catch(Exception i){
                               System.out.println(i);
                               lastButton = null;
                               pair = null;
                           }
                       }
                   }

                   private void privateMethod(Pair<Integer, Integer> newPair, Player player,ButtonCell button) {
                       if (moveButton.isSelected()) {
                           game.playerMovesPieceOnBoard(player,pair.getKey(),pair.getValue(),newPair.getKey(),newPair.getValue());
                           lastButton.getStyleClass().removeAll();
                           button.getStyleClass().add("buttonHealer");
                       }
                       if (attackButton.isSelected()){
                           game.playerAttacks(player,pair.getKey(),pair.getValue(),newPair.getKey(),newPair.getValue());
                       }



                   }
               }
            );
        }
    }


    private void changeScene (GridPane board, BorderPane borderPane){
       // borderPane.setLeft(null);
        borderPane.setTop(null);
        borderPane.setBottom(null);
        Pair<RadioButton, RadioButton> pair = setGameStage(board);
        //borderPane.setTop(new VBox(pair.getKey(),pair.getValue()));


        //top
        GridPane gridPane = new GridPane();
        gridPane.add(pair.getValue(), 0,0);
        gridPane.add(pair.getKey(), 1,0);
        gridPane.getStyleClass().add("hbox");
        borderPane.setTop(gridPane);

        //bottom

        Label playerOneText = new Label("Player One: " + this.game.getPlayer1().name());
        Label playerTwoText = new Label("Player Two: " + this.game.getPlayer2().name());
        playerOneText.getStyleClass().add("textStyle");
        playerTwoText.getStyleClass().add("textStyle");

        if(game.getAvailablePlayer().name().equals(playerOneText.getText())){
            playerOneText.setStyle("-fx-font-size:30; -fx-text-fill: green;");
            playerOneText.setStyle("-fx-font-size:30; -fx-text-fill: white;");
        } else {
            playerTwoText.setStyle("-fx-font-size:30; -fx-text-fill: green;");
            playerTwoText.setStyle("-fx-font-size:30; -fx-text-fill: white;");
        }

        VBox vbox = new VBox(playerOneText);
        vbox.setAlignment(Pos.CENTER_RIGHT);

        VBox vbox1 = new VBox(playerTwoText);
        vbox1.setAlignment(Pos.CENTER_RIGHT);

        Region regionLeft = new Region();
        HBox.setHgrow(regionLeft, Priority.ALWAYS);

        Region regionRigth = new Region();
        HBox.setHgrow(regionRigth, Priority.ALWAYS);

        //Horizontal box
        HBox hbox = new HBox(vbox, regionLeft, vbox1, regionRigth);
        hbox.setMinHeight(150);
        hbox.getStyleClass().add("hbox");
        borderPane.setBottom(hbox);



/*        HBox hbox = (HBox) borderPane.getBottom();

        VBox vBox1 = (VBox) hbox.getChildren().get(0);
        VBox vBox2 = (VBox) hbox.getChildren().get(2);

        HBox newHBox = new HBox(vBox1.getChildren().get(0), vBox2.getChildren().get(0));
        hbox.setMinHeight(150);
        hbox.getStyleClass().add("hbox");
        borderPane.setBottom(newHBox);*/


        //borderPane.setLeft(new VBox(pair.getKey(),pair.getValue()));

//        GridPane gridPane = (GridPane) borderPane.getLeft();
//
//        gridPane.getColumnConstraints().removeAll();
//        gridPane.getRowConstraints().removeAll();
    }


}
