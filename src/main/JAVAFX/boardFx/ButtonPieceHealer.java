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
}
