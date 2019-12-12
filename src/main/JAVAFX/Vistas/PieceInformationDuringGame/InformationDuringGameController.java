package Vistas.PieceInformationDuringGame;

import HaganmeElFavorDeNoBorrarLoQueNoCodean.LastMovesView;
import HaganmeElFavorDeNoBorrarLoQueNoCodean.Turn;
import board.EmptyCellException;
import boardFx.ButtonCell;
import game.Game;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
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
    private Pair<Integer, Integer> pair;
    private ButtonCell button;
    private GridPane board;
    ;


    public InformationDuringGameController ( ButtonCell button, Turn turn, ToggleButton moveButton, ToggleButton attackButton, Game game, Pair<Integer, Integer> pair, Label first, Label second, GridPane board ) {
        this.turn = turn;
        this.game = game;
        this.moveButton = moveButton;
        this.attackButton = attackButton;
        this.first = first;
        this.second = second;
        this.pair = pair;
        this.button = button;
        this.board = board;
    }

    public VBox createPieceView () {

        int row = this.pair.getKey ();
        int column = this.pair.getValue ();
        try {
            this.piece = game.getPieceOnCell ( row, column );
            this.life = piece.getLife ();
        } catch (EmptyCellException e) {
            return new DefaultPieceView ( this.turn, first, second );
        }

        VBox moves = new LastMovesView ( first, second );
        if (piece.getClass ().equals ( Soldier.class )) {
            return checkAdjacents ( moves );
        } else if (piece.getClass ().equals ( Healer.class )) {
            return new HealerInformationDuringGame ( life, turn, moveButton, attackButton, moves );
        } else if (piece.getClass ().equals ( Rider.class )) {
            return new RiderInformationDuringGame ( life, turn, moveButton, attackButton, moves );
        } else {
            return new CatapultInformationDuringGame ( life, turn, moveButton, attackButton, moves );
        }
    }

    private Pair<ButtonCell, ButtonCell> getAdjacents () {
        ButtonCell prev=button;
        ButtonCell next=button;
        int row = this.pair.getKey ();
        int column = this.pair.getValue ();

        for (Node each : board.getChildren ()) {
            ButtonCell buttons = (ButtonCell) each;
            Pair<Integer, Integer> pos = buttons.getPosition ();
            if (pos.getKey () == row) {
                if (pos.getValue () == column + 1) {
                    next = buttons;
                } else if (pos.getValue () == column - 1) {
                    prev = buttons;
                }
            }
        }
        return new Pair<ButtonCell,ButtonCell>(prev,next);
    }


    private VBox checkAdjacents ( VBox moves ) {

        int row = this.pair.getKey ();
        int column = this.pair.getValue ();

        System.out.println ( row );
        System.out.println ( column );

        int column2 = column + 1;
        int column3 = column - 1;

        Piece top;
        Piece bottom;

        try {
            top = game.getPieceOnCell ( row, column2 );
            bottom = game.getPieceOnCell ( row, column3 );
            if (!top.getClass ().equals ( Soldier.class ) || !bottom.getClass ().equals ( Soldier.class ))
                throw new Exception ();
        } catch (Exception e) {
            return new SoldierInformationDuringGame ( life, turn, moveButton, attackButton, moves );
        }

        Pair<ButtonCell, ButtonCell> adj = this.getAdjacents ();
        return new BattalionDuringGame ( this.life, turn, moveButton, attackButton, moves, game, row, column, button, adj );
    }


}
