package Vistas;

import Controlers.ExitButtonEventHandler;
import Controlers.MusicButtonEventHandler;
import HaganmeElFavorDeNoBorrarLoQueNoCodean.NewGameButtonEventHandler;
import HaganmeElFavorDeNoBorrarLoQueNoCodean.PiecesGridPane;
import HaganmeElFavorDeNoBorrarLoQueNoCodean.Turn;

import Vistas.PieceInformationDuringGame.DefaultPieceView;
import Vistas.PieceInformationDuringGame.InformationDuringGameController;
import boardFx.ButtonCell;
import boardFx.ButtonPiece;
import game.Game;
import game.GameHasEndedException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Pair;
import piece.Piece;
import player.Player;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

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
    private Background background = new AlgoChessBackground ( "Image/scene00background.jpg" ).createBackground ();
    private MediaPlayer mediaPlayer;
    private MediaPlayer soundEffects;
    private Label label1 = new Label ();
    private Label label2 = new Label ();

    public SelectPieceSceneView ( Application game, MediaPlayer mediaPlayer ) {
        this.app = game;
        this.mediaPlayer = mediaPlayer;
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
                if (!game.getAvailablePlayer().isNumberOfPiecesOnTeam(0)) {
                    changeScene(board, borderPane);
                    setChooseButton();
                    game.playerIsReadyToPlay(game.getPlayer1());
                    game.playerIsReadyToPlay(game.getPlayer2());
                    String path = "src/main/JAVAFX/SoundEffects/readytoplay.mp3";
                    Media media = new Media(new File(path).toURI().toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                }
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

        //Button stop
        Button stopButton = new Button ();
        stopButton.getStyleClass ().add ( "buttonStop" );
        stopButton.setOnAction ( new MusicButtonEventHandler ( this.mediaPlayer, stopButton, "buttonStop", "buttonStopMuted" ) );

        HBox topContainer = new HBox ( new Label(),start,stopButton );
        stopButton.prefWidthProperty ().bind ( topContainer.widthProperty ().divide ( 50 ) );
        stopButton.prefHeightProperty ().bind ( topContainer.widthProperty ().divide ( 50 ) );
        topContainer.setAlignment ( Pos.CENTER );
        topContainer.setSpacing ( 450 );


        topContainer.setMinHeight ( 50 );
        topContainer.getStyleClass ().add ( "hbox" );

        choosePieceButton = new Button ( "Choose Piece" );
        choosePieceButton.setOnMouseClicked ( new EventHandler<MouseEvent> () {
            @Override
            public void handle ( MouseEvent mouseEvent ) {
                lastChosen = lastClicked;
                String path = "src/main/JAVAFX/SoundEffects/choosePiece.mp3";
                Media media = new Media ( new File ( path ).toURI ().toString () );
                mediaPlayer = new MediaPlayer ( media );
                mediaPlayer.play ();
            }
        } );
        choosePieceButton.getStyleClass ().add ( "button-choose" );
        choosePieceButton.setAlignment ( Pos.CENTER );

        //Board
        board.getStyleClass ().add ( "board" );
        setBoardCellAction ( board, this );

        //Left toolbar
        VBox vertical = new VBox ( this.turn.getCurrentPlayersName (), this.turn.getCurrentPlayersPoints (), new PiecesGridPane ( choosePieceButton, this, turn ) );
        vertical.setAlignment ( Pos.TOP_CENTER );
        vertical.setSpacing ( 40 );
        vertical.setPrefWidth ( 300 );
        vertical.getStyleClass ().add ( "piecesGrid" );

        borderPane.setLeft ( vertical );
        borderPane.setTop ( topContainer );
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
                    game.playerChoosesBattalion ( pair.getKey (), pair.getValue () );
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

    private void setButtonCellAction ( ButtonCell button, SelectPieceSceneView view ) {
        button.setOnMouseClicked ( new EventHandler<MouseEvent> () {
            @Override
            public void handle ( MouseEvent event ) {
                if (!(lastChosen == null)) {
                    try {
                        Piece piece = lastChosen.choosePiece ( game);
                        Pair<Integer, Integer> pair = button.getPosition ();
                        game.playerPlacesPieceOnBoard ( piece, pair.getKey (), pair.getValue () );
                        button.getStyleClass ().add ( lastChosen.getString ( game ));
                        lastChosen = null;
                        playerPoints = turn.getCurrentPlayersPoints ();
                        HBox playerInfo = turn.getCurrentPlayersName ();
                        turn.changeTurn ();

                        String path = "src/main/JAVAFX/SoundEffects/placePiece.mp3";
                        Media media = new Media ( new File ( path ).toURI ().toString () );
                        mediaPlayer = new MediaPlayer ( media );
                        mediaPlayer.play ();


                        VBox vertical = new VBox ( playerInfo, playerPoints, new PiecesGridPane ( choosePieceButton, view, turn ) );
                        vertical.setAlignment ( Pos.TOP_CENTER );
                        vertical.setSpacing ( 40 );
                        vertical.getStyleClass ().add ( "piecesGrid" );
                        vertical.setPrefWidth ( 300 );
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

    private GridPane makeGridPane () {
        String left = "-fx-background-color: #1a7749; -fx-border-color: black; -fx-border-width: 1px;";
        String right = "-fx-background-color: #8f9779; -fx-border-color: black; -fx-border-width: 1px;";


        String actual = left;
        //Algogrid
        GridPane gridPane = new GridPane ();
        for (int i = 0; i < 20; i++) {
            if (i == 10) {
                actual = right;
            }
            for (int j = 0; j < 20; j++) {
                ButtonCell button = new ButtonCell ( null, actual, i, j );
                button.setMinSize ( 41, 41 );
                button.setMaxSize ( 41, 41 );
                gridPane.add ( button, i, j );
            }
        }
        gridPane.setHgap ( 1 );
        gridPane.setVgap ( 1 );
        gridPane.setPadding ( new Insets ( 20, 20, 20, 20 ) );
        gridPane.setAlignment ( Pos.CENTER );
        return gridPane;
    }


    public Pair<ToggleButton, ToggleButton> setGameStage ( GridPane grid, BorderPane borderPane ) {
        final ToggleGroup group = new ToggleGroup ();
        ToggleButton moveButton = new ToggleButton ( "Move" );
        moveButton.setToggleGroup ( group );
        moveButton.getStyleClass ().add ( "button-choose" );
        moveButton.setPadding ( new Insets ( 0, 0, 0, 1100 ) );

        ToggleButton attackButton = new ToggleButton ( "Attack" );
        attackButton.setToggleGroup ( group );
        attackButton.getStyleClass ().add ( "button-choose" );
        attackButton.setOnMouseClicked ( new EventHandler<MouseEvent> () {
                                             @Override
                                             public void handle ( MouseEvent mouseEvent ) {
                                                 attackButton.setStyle ( "-fx-opacity: 1.5;-fx-font-size:30; -fx-text-fill: green;" );
                                                 moveButton.setStyle ( "-fx-opacity: 1.5;-fx-font-size:30;-fx-text-fill: white;" );
                                                 moveButton.prefWidthProperty ().bind ( borderPane.widthProperty ().divide ( 12 ) );
                                                 attackButton.prefWidthProperty ().bind ( borderPane.widthProperty ().divide ( 12 ) );

                                             }
                                         }
        );

        moveButton.setOnMouseClicked ( new EventHandler<MouseEvent> () {
                                           @Override
                                           public void handle ( MouseEvent mouseEvent ) {
                                               moveButton.setStyle ( "-fx-opacity: 1.5;-fx-font-size:30; -fx-text-fill: green;" );
                                               attackButton.setStyle ( "-fx-opacity: 1.5;-fx-font-size:30;-fx-text-fill: white;" );
                                               moveButton.prefWidthProperty ().bind ( borderPane.widthProperty ().divide ( 12 ) );
                                               attackButton.prefWidthProperty ().bind ( borderPane.widthProperty ().divide ( 12 ) );

                                           }
                                       }
        );

        SceneToAttack ( this.game, grid, moveButton, attackButton, borderPane );

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


                                                 InformationDuringGameController control = new InformationDuringGameController ( button, turn, moveButton, attackButton, game, pair, label1, label2,board );
                                                 VBox vertical = control.createPieceView ();
                                                 borderpane.setLeft ( vertical );
                                                 BorderPane.setMargin ( vertical, new Insets ( 12, 12, 12, 12 ) );

                                                 lastButton = button;
                                             } else if (pair != null) {
                                                 Pair<Integer, Integer> newPair = button.getPosition ();
                                                 try {

                                                     privateMethod ( newPair, button, label1, label2 );

                                                     lastButton = null;
                                                     pair = null;
                                                 } catch (GameHasEndedException e) {
                                                     String path = "src/main/JAVAFX/SoundEffects/readytoplay.mp3";
                                                     Media media = new Media ( new File ( path ).toURI ().toString () );
                                                     MediaPlayer mp = new MediaPlayer ( media );
                                                     mp.play ();
                                                     sceneFinal ( game.getAvailablePlayer (), border );
                                                 } catch (Exception i) {
                                                     System.out.println ( i );
                                                     lastButton = null;
                                                     pair = null;
                                                 }
                                             }
                                         }
                                         private void attackSound () {
                                             String path = "src/main/JAVAFX/SoundEffects/punch.mp3";
                                             Media media = new Media ( new File ( path ).toURI ().toString () );
                                             soundEffects = new MediaPlayer ( media );
                                             soundEffects.play ();
                                         }

                                         private void moveSound () {
                                             String path = "src/main/JAVAFX/SoundEffects/walking.mp3";
                                             Media media = new Media ( new File ( path ).toURI ().toString () );
                                             soundEffects = new MediaPlayer ( media );
                                             soundEffects.play ();
                                         }


                                         private void privateMethod ( Pair<Integer, Integer> newPair, ButtonCell button, Label first, Label second) {

                                             if (moveButton.isSelected ()) {
                                                 game.playerMovesPieceOnBoard ( pair.getKey (), pair.getValue (), newPair.getKey (), newPair.getValue () );
                                                 turn.changeTurn ();
                                                 this.moveSound ();
                                                 button.getStyleClass ().add ( lastButton.getStyleClass ().remove ( 1 ) );
                                                 changeLabelMove ( first, second, pair, newPair );
                                             }
                                             if (attackButton.isSelected ()) {
                                                 game.playerAttacks (pair.getKey (), pair.getValue (), newPair.getKey (), newPair.getValue () );
                                                 turn.changeTurn ();
                                                 this.attackSound ();
                                                 changeLabelAttack ( first,second,pair,newPair );

                                                 if (game.cellIsEmpty ( newPair.getKey (), newPair.getValue () )) {
                                                     changeLabelPieceDied ( first, second, newPair );
                                                     button.getStyleClass ().remove ( 1 );
                                                 }

                                             }
                                             VBox vertical = new DefaultPieceView ( turn, first, second );
                                             vertical.setPrefWidth ( 300 );

                                             BorderPane.setMargin ( vertical, new Insets ( 12, 12, 12, 12 ) );
                                             borderpane.setLeft ( vertical );
                                         }
                                     }
            );


            }
    }


    private void changeScene ( GridPane board, BorderPane borderPane ) {
        borderPane.setTop ( null );
        borderPane.setBottom ( null );
        Pair<ToggleButton, ToggleButton> pair = setGameStage ( board, borderPane );

        //Adding the components to the bar
        VBox vertical = new DefaultPieceView ( turn, new Label(), new Label ( ) );
        vertical.setPrefWidth ( 300 );
        borderPane.setLeft ( vertical );
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

        Label name = new Label ( "Player: " + player.name () + " has lost." );
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
        playAgain.setOnAction ( new NewGameButtonEventHandler ( this.app, exit ) );

        // Horizontal box containing exit & playAgain
        HBox horizontalEndButtons = new HBox ( exit, playAgain );
        horizontalEndButtons.setAlignment ( Pos.CENTER );
        horizontalEndButtons.setSpacing ( 60 );

        exit.prefWidthProperty ().bind ( horizontalEndButtons.widthProperty ().divide ( 8 ) );
        playAgain.prefWidthProperty ().bind ( horizontalEndButtons.widthProperty ().divide ( 8 ) );

        VBox contrast = new VBox ( name, horizontalEndButtons );
        contrast.setSpacing ( 100 );
        contrast.setAlignment ( Pos.CENTER );
        contrast.getStylesheets ().add ( "AlgoStyle.css" );
        contrast.prefWidthProperty ().bind ( contrast.widthProperty ().divide ( 6 ) );
        contrast.prefHeightProperty ().bind ( contrast.widthProperty ().divide ( 6 ) );


        // Vertical Box
        VBox vertical = new VBox ( gameOverView, contrast );
        vertical.setSpacing ( 100 );
        vertical.prefWidthProperty ().bind ( vertical.widthProperty ().divide ( 6 ) );
        vertical.prefHeightProperty ().bind ( vertical.widthProperty ().divide ( 6 ) );


        vertical.setAlignment ( Pos.CENTER );
        borderPane.setCenter ( vertical );
        borderPane.setBackground ( this.background );
    }

    public static class CloseListener extends WindowAdapter {
        public void windowClosing ( WindowEvent e ) {
            e.getWindow ().setVisible ( false );
        }
    }

    public void changeLabelMove ( Label firstLabel, Label secondLabel, Pair<Integer, Integer> firstPair, Pair<Integer, Integer> secondPair ) {
        firstLabel.setText ( secondLabel.getText () );
        secondLabel.setText ( "The piece from (" + firstPair.getKey () + "," + firstPair.getValue () + ") has moved to (" + secondPair.getKey () + "," + secondPair.getValue () + ")" );
    }

    public void changeLabelAttack ( Label firstLabel, Label secondLabel, Pair<Integer, Integer> firstPair, Pair<Integer, Integer> secondPair ) {
        firstLabel.setText ( secondLabel.getText () );
        secondLabel.setText ( "The piece from (" + firstPair.getKey () + "," + firstPair.getValue () + ") has attacked to (" + secondPair.getKey () + "," + secondPair.getValue () + ")" );
    }

    public void changeLabelPieceDied ( Label firstLabel, Label secondLabel, Pair<Integer, Integer> ripPiece ) {
        firstLabel.setText ( secondLabel.getText () );
        secondLabel.setText ( "The piece from (" + ripPiece.getKey () + "," + ripPiece.getValue () + ") has died" );
    }
}