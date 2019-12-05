package boardFx;

import game.Game;
import piece.Piece;
import player.Player;

public class ButtonPieceHealer extends ButtonPiece {

    public ButtonPieceHealer(){
        super();
    }

    @Override
    public Piece choosePiece(Game game, Player player) {
        return game.playerChoosesHealer(player);
    }

    @Override
    public String getString(){
        return "Image/Piece/healer2.png";
    }
}
