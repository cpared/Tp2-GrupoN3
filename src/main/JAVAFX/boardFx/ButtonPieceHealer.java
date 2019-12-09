package boardFx;

import game.Game;
import piece.Piece;
import player.Player;

public class ButtonPieceHealer extends ButtonPiece {
    private String style;

    public ButtonPieceHealer(String style){
        super();
        this.style = style;
        this.getStyleClass().add(style);
        this.setMinWidth(60);
        this.setMinHeight(60);
    }

    @Override
    public Piece choosePiece(Game game, Player player) {
        return game.playerChoosesHealer(player);
    }

    @Override
    public String getString(Game game, Player player){
        return this.style;
        //return "buttonHealer";
    }
}
