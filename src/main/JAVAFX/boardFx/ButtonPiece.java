package boardFx;

import game.Game;
import javafx.scene.control.Button;
import piece.Piece;
import player.Player;

public abstract class ButtonPiece extends Button {
    private String style;

    ButtonPiece(){
        super();
    }
    public abstract Piece choosePiece(Game game);

    public abstract String getString(Game game);

    public abstract void setString (String style);

}
