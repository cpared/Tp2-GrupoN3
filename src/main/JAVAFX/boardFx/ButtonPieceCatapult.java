package boardFx;

import game.Game;
import piece.Piece;
import player.Player;

public class ButtonPieceCatapult extends ButtonPiece {
    private String style;

    public ButtonPieceCatapult(String style){
        super();
        this.style = style;
        this.getStyleClass().add(style);
        this.setMinWidth(60);
        this.setMinHeight(60);
    }

    @Override
    public Piece choosePiece(Game game) {
        return game.playerChoosesCatapult();
    }

    @Override
    public String getString(Game game){
        return this.style;
    }

    @Override
    public void setString (String style) {
        this.style = style;
        this.getStyleClass().add(style);
    }


}
