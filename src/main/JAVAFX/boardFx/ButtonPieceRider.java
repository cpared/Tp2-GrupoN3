package boardFx;

import game.Game;
import piece.Piece;
import player.Player;

public class ButtonPieceRider extends ButtonPiece {
    private String style;

    public ButtonPieceRider(String style){
        super();
        this.style = style;
        this.getStyleClass().add(style);
        this.setMinWidth(60);
        this.setMinHeight(60);
    }

    @Override
    public Piece choosePiece(Game game, Player player) {
        return game.playerChoosesRider(player);
    }


    @Override
    public String getString(Game game, Player player){
        return this.style;
    }

    @Override
    public void setString (String style) {
        this.style = style;
        this.getStyleClass().add(style);
    }


}
