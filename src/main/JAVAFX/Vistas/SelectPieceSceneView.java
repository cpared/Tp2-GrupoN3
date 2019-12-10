package Vistas;

import Controlers.ExitButtonEventHandler;
import Controlers.PieceStatHandlers.*;
import HaganmeElFavorDeNoBorrarLoQueNoCodean.NewGameButtonEventHandler;
import HaganmeElFavorDeNoBorrarLoQueNoCodean.PiecesGridPane;
import HaganmeElFavorDeNoBorrarLoQueNoCodean.Turn;
import boardFx.*;
import game.Game;
import game.GameHasEndedException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import piece.Piece;
import player.Player;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SelectPieceSceneView {
    private Button choosePieceButton;
    private ButtonPiece lastClicked = null;
    private ButtonPiece lastChosen = null;
    private BorderPane borderpane;
    private Label player = new Label ();
    private Label playerPoints = new Label ();
    //private Background background = new AlgoChessBackground ( "Image/scene00background.jpg" ).createBackground ();
    private Game game;
    private Turn turn;
    private Stage stage;
    private Application app;

    public SelectPieceSceneView ( Application game ) {
        this.app = game;
    }

    public Scene scene02SelectPieces ( Stage stage, Game game ) throws InterruptedException {
        this.game = game;
        this.stage = stage;
        this.turn = new Turn ( this.game );

        //Main Panes
        BorderPane borderPane = new BorderPane ();
        this.borderpane = borderPane;
        GridPane board = makeGridPane ();

        //Buttons
        Button start = new Button ( "Ready To Play" );
        start.getStyleClass ().add ( "button-choose" );

        start.setOnMouseClicked ( new EventHandler<MouseEvent> () {
            @Override
            public void handle ( MouseEvent mouseEvent ) {
                changeScene ( board, borderPane );
                setChooseButton ();
                game.playerIsReadyToPlay ( game.getPlayer1 () );
                game.playerIsReadyToPlay ( game.getPlayer2 () );
            }
        } );
        start.setOnKeyPressed ( new EventHandler<KeyEvent> () {
            @Override
            public void handle ( KeyEvent event ) {
                if (event.getCode () == KeyCode.ENTER) {
                    changeScene ( board, borderPane );
                    setChooseButton ();
                    game.playerIsReadyToPlay ( game.getPlayer1 () );
                    game.playerIsReadyToPlay ( game.getPlayer2 () );
                }
            }
        } );
        start.setMinWidth ( 150 );
        start.setMinHeight ( 40 );

        choosePieceButton = new Button ( "Choose Piece" );
        choosePieceButton.setOnMouseClicked ( new EventHandler<MouseEvent> () {
            @Override
            public void handle ( MouseEvent mouseEvent ) {
                lastChosen = lastClicked;
            }
        } );
        choosePieceButton.getStyleClass ().add ( "button-choose" );
        choosePieceButton.setAlignment ( Pos.CENTER );

        //Board
        board.getStyleClass ().add ( "board" );
        setBoardCellAction ( board, this );

        //Left toolbar
        VBox vertical = new VBox ( this.turn.getCurrentPlayersName (), this.turn.getCurrentPlayersPoints (), new PiecesGridPane ( choosePieceButton, this, turn ) );
        vertical.setAlignment ( Pos.CENTER );
        vertical.setSpacing ( 40 );
        vertical.getStyleClass().add("piecesGrid");


        borderPane.setLeft ( vertical );
        BorderPane.setAlignment ( start, Pos.BOTTOM_CENTER );
        BorderPane.setMargin ( start, new Insets ( 12, 12, 12, 12 ) );
        borderPane.setTop ( start );
        borderPane.setCenter ( board );
        BorderPane.setAlignment ( board, Pos.CENTER );

        Scene scene = new Scene ( borderPane );
        stage.setScene ( scene );
        scene.getStylesheets ().add ( "SelectStyleLeft.css" );
        stage.show ();
        return scene;
    }

    private void setChooseButton () {
        choosePieceButton.setText ( "Choose Battalion" );
        choosePieceButton.setOnMouseClicked ( new EventHandler<MouseEvent> () {
            @Override
            public void handle ( MouseEvent mouseEvent ) {
                try {
                    game.playerChoosesBattalion ( game.getAvailablePlayer (), pair.getKey (), pair.getValue () );
                    lastButton.setBattalion ();
                } catch (Exception e) {
                    System.out.println ( e );
                }
            }
        } );

    }

    private void setBoardCellAction ( GridPane board, SelectPieceSceneView view ) {
        for (Node node : board.getChildren ()) {
            setButtonCellAction ( (ButtonCell) node, view );
        }
    }

    private void setButtonCellAction ( ButtonCell button , SelectPieceSceneView view) {
        button.setOnMouseClicked ( new EventHandler<MouseEvent> () {
            @Override
            public void handle ( MouseEvent event ) {
                if (!(lastChosen == null)) {
                    Player currentPlayer = game.getAvailablePlayer ();
                    try {
                        System.out.println ( "aca paso" );
                        Piece piece = lastChosen.choosePiece ( game, currentPlayer );
                        Pair<Integer, Integer> pair = button.getPosition ();
                        game.playerPlacesPieceOnBoard ( currentPlayer, piece, pair.getKey (), pair.getValue () );
                        button.getStyleClass ().add ( lastChosen.getString ( game, game.getAvailablePlayer () ) );
                        lastChosen = null;
                        playerPoints = turn.getCurrentPlayersPoints ();
                        player = turn.getCurrentPlayersName ();
                        turn.changeTurn ();
                        VBox vertical = new VBox (player, playerPoints, new PiecesGridPane ( choosePieceButton, view, turn ) );
                        vertical.setAlignment ( Pos.CENTER );
                        vertical.setSpacing ( 40 );
                        vertical.getStyleClass().add("piecesGrid");
                        borderpane.setLeft ( vertical );

                    } catch (GameHasEndedException i) {
                        throw i;
                    } catch (Exception e) {
                        System.out.println ( e );
                    }
                }
            }
        } );
    }

    private GridPane makeGridPane() {
        String left = "-fx-background-color: #1a7749; -fx-border-color: black; -fx-border-width: 1px;";
        String right = "-fx-background-color: #8f9779; -fx-border-color: black; -fx-border-width: 1px;";


        String actual = left;
        AlgoGrid gridPane = new AlgoGrid();
        for (int i = 0 ; i< 20;i++) {
            if (i == 10){
                actual = right;
            }
            for (int j = 0; j < 20; j++) {
                ButtonCell button = new ButtonCell ( null, actual, i, j );
                //button.setPrefSize ( 30, 30 );
                button.setMinSize(41,41);
                button.setMaxSize(41,41);
                //button.setOnKeyPressed ( new BoardPositionHasBeenChosenInInitialFaceEventHandler ( null, this, button) );
                //button.setOnMouseClicked ( new BoardPositionHasBeenChosenInInitialFaceEventHandler ( null, this, button) );
                gridPane.add ( button, i, j );
            }
        }
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }


    public Pair<ToggleButton, ToggleButton> setGameStage ( GridPane grid, BorderPane borderPane ) {
        final ToggleGroup group = new ToggleGroup ();
        ToggleButton moveButton = new ToggleButton ( "Move" );
        moveButton.setToggleGroup ( group );
        moveButton.getStyleClass().add("button-choose");
        //movePiece.setSelected ( true );
        moveButton.setPadding ( new Insets ( 0, 0, 0, 1100 ) );

        ToggleButton attackButton = new ToggleButton ( "Attack" );
        attackButton.setToggleGroup ( group );
        attackButton.getStyleClass().add("button-choose");
        attackButton.setOnMouseClicked ( new EventHandler<MouseEvent> () {
                @Override
                public void handle ( MouseEvent mouseEvent ) {
                    attackButton.setStyle ( "-fx-opacity: 1.5;-fx-font-size:30; -fx-text-fill: green;" );
                    moveButton.setStyle ( "-fx-opacity: 1.5;-fx-font-size:30;-fx-text-fill: white;" );

                }
            }
        );

        moveButton.setOnMouseClicked ( new EventHandler<MouseEvent> () {
              @Override
              public void handle ( MouseEvent mouseEvent ) {
                  moveButton.setStyle ( "-fx-opacity: 1.5;-fx-font-size:30; -fx-text-fill: green;" );
                  attackButton.setStyle ( "-fx-opacity: 1.5;-fx-font-size:30;-fx-text-fill: white;" );

              }
          }
        );


        SceneToAttack ( this.game, grid, moveButton, attackButton, borderPane );
        //nope.setOnAction ( new ButtonsThatChangeScenesEventHandler ( stage, this.scene03Game ( stage ) ) );

        return new Pair<ToggleButton, ToggleButton> ( moveButton, attackButton );
    }

    private Pair<Integer, Integer> pair = null;
    private ButtonCell lastButton = null;


    public void SceneToAttack ( Game game, GridPane board, ToggleButton moveButton, ToggleButton attackButton, BorderPane border ) {
        VBox a = new VBox ( moveButton, attackButton );
        for (Node each : board.getChildren ()) {
            each.setOnMouseClicked ( new EventHandler<MouseEvent> () {
                 @Override
                 public void handle ( MouseEvent mouseEvent ) {
                     ButtonCell button = (ButtonCell) each;
                     if (pair == null && !(button.emptyImage ())) {
                         pair = button.getPosition ();
                         lastButton = button;
                     } else if (pair != null) {
                         Pair<Integer, Integer> newPair = button.getPosition ();
                         try {
                             privateMethod ( newPair, game.getAvailablePlayer (), button );
                             lastButton = null;
                             pair = null;
                         } catch (GameHasEndedException e) {
                             sceneFinal ( game.getAvailablePlayer (), border );
                         } catch (Exception i) {
                             System.out.println ( i );
                             lastButton = null;
                             pair = null;
                         }
                     }
                 }

                 private void privateMethod ( Pair<Integer, Integer> newPair, Player currentPlayer, ButtonCell button ) {
                     if (moveButton.isSelected ()) {
                         game.playerMovesPieceOnBoard ( currentPlayer, pair.getKey (), pair.getValue (), newPair.getKey (), newPair.getValue () );
                         player = turn.getCurrentPlayersName ();
                         turn.changeTurn ();

                         moveButton.getStyleClass ().add ( "button-choose" );
                         attackButton.getStyleClass ().add ( "button-choose" );

                         VBox vertical = new VBox (player, moveButton, attackButton );
                         vertical.setAlignment ( Pos.CENTER );
                         vertical.setSpacing ( 40 );
                         vertical.getStyleClass().add("piecesGrid");
                         borderpane.setLeft ( vertical);
                         BorderPane.setMargin ( vertical, new Insets ( 12, 12, 12, 12 ) );

                         button.getStyleClass ().add ( lastButton.getStyleClass ().remove ( 1 ) );
                     }
                     if (attackButton.isSelected ()) {
                         game.playerAttacks ( currentPlayer, pair.getKey (), pair.getValue (), newPair.getKey (), newPair.getValue () );
                         player = turn.getCurrentPlayersName ();
                         turn.changeTurn ();
                         moveButton.getStyleClass ().add ( "button-choose" );
                         attackButton.getStyleClass ().add ( "button-choose" );

                         VBox vertical = new VBox (player, moveButton, attackButton );
                         vertical.setAlignment ( Pos.CENTER );
                         vertical.setSpacing ( 40 );
                         vertical.getStyleClass().add("piecesGrid");
                         borderpane.setLeft ( vertical);
                         BorderPane.setMargin ( vertical, new Insets ( 12, 12, 12, 12 ) );

                         if (game.cellIsEmpty ( newPair.getKey (), newPair.getValue () ))
                             button.getStyleClass ().remove ( 1 );

                     }
                 }
             }
            );
        }
    }


    private void changeScene ( GridPane board, BorderPane borderPane ) {
        // borderPane.setLeft(null);
        borderPane.setTop ( null );
        borderPane.setBottom ( null );
        Pair<ToggleButton, ToggleButton> pair = setGameStage ( board, borderPane );


        //Left
        Label name = turn.getCurrentPlayersName ();
        name.getStyleClass ().add ( "textStyle" );
        Label piece = new Label ( "Choose a Piece" );
        piece.getStyleClass ().add ( "textStyle" );

        ToggleButton move = pair.getValue ();
        move.getStyleClass ().add ( "button-choose" );
        ToggleButton attack =   pair.getKey ();attack.getStyleClass ().add ( "button-choose" );

        /*
        Scrollbar barraTemp = new Scrollbar(Scrollbar.HORIZONTAL, 0, 10, -50, 160);
        Frame frameTemp = new Frame("Error"); //creamos el marco
        Label
        frameTemp.add ( new Text ("Player must choose at least one piece to play.") )
        frameTemp.add("Center", barraTemp); //agregamos la barra
        frameTemp.setSize(300,100);
        frameTemp.setVisible(true);  //mostramos el marco
        frameTemp.addWindowListener(new CloseListener());
        */

        //Adding the components to the bar
        VBox vertical = new VBox (name, piece, move,attack);
        //VBox vertical = new VBox (name, piece);
        vertical.getStyleClass().add("piecesGrid");
        vertical.setAlignment ( Pos.TOP_CENTER );
        vertical.setSpacing ( 40 );
        BorderPane.setMargin ( vertical, new Insets ( 12, 12, 12, 12 ) );
        borderPane.setLeft ( vertical );
        //BorderPane.setAlignment ( vertical, Pos.CENTER );

    }


    public void setLastClicked ( ButtonPiece button ) {
        lastClicked = button;
    }

    public void sceneFinal ( Player player, BorderPane borderPane ) {
        borderPane.setBottom ( null );
        borderPane.setTop ( null );
        borderPane.setLeft ( null );
        borderPane.setRight ( null );
        borderPane.setCenter ( null );
        // AlgoChess image.
        Image gameover = new Image ( "Image/gameover.png" );
        ImageView gameOverView = new ImageView ( gameover );
        Label name = new Label ( "Player: " + player.name () + " has won." );
        name.getStyleClass ().add ( "textStyle" );


        // Exit button
        Button exit = new Button ( "Exit" );
        exit.getStyleClass ().add ( "button-choose" );
        ExitButtonEventHandler exitButtonEventHandler = new ExitButtonEventHandler ( exit, stage );
        exit.setOnKeyPressed ( exitButtonEventHandler );
        exit.setOnMouseClicked ( exitButtonEventHandler );

        // Play again button.
        Button playAgain = new Button ( "New Game" );
        playAgain.getStyleClass ().add ( "button-choose" );
        playAgain.setOnAction ( new NewGameButtonEventHandler (this.app, exit) );

        // Horizontal box containing exit & playAgain
        HBox horizontalEndButtons = new HBox ( exit, playAgain );
        horizontalEndButtons.setAlignment ( Pos.CENTER );
        horizontalEndButtons.setSpacing ( 60 );

        exit.prefWidthProperty ().bind ( horizontalEndButtons.widthProperty ().divide ( 8 ) );
        playAgain.prefWidthProperty ().bind ( horizontalEndButtons.widthProperty ().divide ( 8 ) );

        // Vertical Box
        VBox vertical = new VBox ( gameOverView, name, horizontalEndButtons );
        vertical.setSpacing ( 100 );
        vertical.prefWidthProperty ().bind ( vertical.widthProperty ().divide ( 6 ) );
        vertical.prefHeightProperty ().bind ( vertical.widthProperty ().divide ( 6 ) );

        vertical.setAlignment ( Pos.CENTER );
        //vertical.setBackground(this.background);
        borderPane.setCenter ( vertical );
    }

    public static class CloseListener extends WindowAdapter {
        public void windowClosing( WindowEvent e) {
            e.getWindow().setVisible(false);
        }
    }
}