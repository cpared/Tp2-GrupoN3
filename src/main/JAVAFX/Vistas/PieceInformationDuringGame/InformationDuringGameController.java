package Vistas.PieceInformationDuringGame;

import HaganmeElFavorDeNoBorrarLoQueNoCodean.LastMovesView;
import HaganmeElFavorDeNoBorrarLoQueNoCodean.Turn;
import boardFx.ButtonCell;
import game.Game;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import piece.Healer;
import piece.Piece;
import piece.Rider;
import piece.Soldier;

public class InformationDuringGameController {
    private Piece piece;
    private int life;
    private Turn turn;
    private ToggleButton moveButton;
    private ToggleButton attackButton;
    private Game game;
    private Label first;
    private Label second;


    public InformationDuringGameController ( ButtonCell button, Turn turn, ToggleButton moveButton, ToggleButton attackButton, Game game, Pair<Integer, Integer> pair, Label first, Label second ) {
        int row = pair.getKey ();
        int column = pair.getValue ();
        this.piece = game.getPieceOnCell ( row, column );
        this.life = piece.getLife ();
        this.turn = turn;
        this.game = game;
        this.moveButton = moveButton;
        this.attackButton = attackButton;
        this.first = first;
        this.second = second;

    }

    public VBox createPieceView () {
        VBox moves = new LastMovesView ( first, second );

        if (piece.getClass ().equals ( Soldier.class )) {
            return new SoldierInformationDuringGame ( life, turn, moveButton, attackButton, moves );
        } else if (piece.getClass ().equals ( Healer.class )) {
            return new HealerInformationDuringGame ( life, turn, moveButton, attackButton, moves );
        } else if (piece.getClass ().equals ( Rider.class )) {
            return new RiderInformationDuringGame ( life, turn, moveButton, attackButton, moves );
        } else {
            return new CatapultInformationDuringGame ( life, turn, moveButton, attackButton, moves );
        }
    }
}
