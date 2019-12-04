package Vistas;

import boardFx.ButtonCell;
import game.Game;
import game.ItIsNotYourTurnException;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import player.Player;

public class SceneToAttack extends BorderPane {
    private Pair<Integer,Integer> pair = null;
    public SceneToAttack(Game game, GridPane board, RadioButton moveButton, RadioButton attackButton){
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
