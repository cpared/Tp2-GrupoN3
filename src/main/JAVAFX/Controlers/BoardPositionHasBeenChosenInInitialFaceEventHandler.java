package Controlers;

import Vistas.ChoosingPiecesBorderPane;
import boardFx.ButtonCell;
import game.Game;
import game.ItIsNotYourTurnException;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import piece.Piece;
import player.Player;

public class BoardPositionHasBeenChosenInInitialFaceEventHandler implements EventHandler<InputEvent> {
    private Image image;
    private ChoosingPiecesBorderPane pane;
    private  ButtonCell button;
    private Game game;
    public BoardPositionHasBeenChosenInInitialFaceEventHandler (Image image, ChoosingPiecesBorderPane pane, Game game, ButtonCell button) {
        this.pane = pane;
        this.image = image;
        this.button = button;
        this.game = game;
    }

    @Override

    public void handle ( InputEvent event ) {
        if (event.getClass ().equals ( KeyEvent.class )) this.handleKey ( (KeyEvent) event );
        else if (event.getClass ().equals ( MouseEvent.class )) this.handleMouse ( (MouseEvent) event );

    }

    public void handleKey ( KeyEvent event ) {
        if (event.getCode () == KeyCode.ENTER) {
            ImageView view = new ImageView ( this.image );
            view.setFitWidth ( 10 );
            view.setFitHeight ( 10 );
            this.button.setGraphic ( view );
            this.pane.setCursor ( Cursor.DEFAULT );
        }
    }

    public void handleMouse ( MouseEvent event ) {
        if (event.getEventType ().equals ( MouseEvent.MOUSE_CLICKED )) {
            try{
                Piece piece = game.playerChoosesSoldier(game.getPlayer1());
                Pair<Integer,Integer> pair = button.getPosition();
                Player player = game.getPlayer1();
                innerMethod(piece, pair,player);
            }
            catch(ItIsNotYourTurnException e){
                Piece piece = game.playerChoosesSoldier(game.getPlayer2());
                Pair<Integer,Integer> pair = button.getPosition();
                Player player = game.getPlayer2();
                innerMethod(piece, pair, player);
            }
            catch(Exception i){
                System.out.println(i);
            }
        }
    }

    private void innerMethod(Piece piece, Pair<Integer, Integer> pair, Player player) {
        game.playerPlacesPieceOnBoard(player,piece,pair.getKey(),pair.getValue());
        ImageView view = new ImageView ( this.image );
        view.setFitWidth ( 15 );
        view.setFitHeight ( 15 );
        this.button.setGraphic ( view );
        this.pane.setCursor ( Cursor.DEFAULT );
    }
}