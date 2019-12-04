package boardFx;

import game.Game;
import piece.Piece;
import player.Player;

public class ButtonPieceRider extends ButtonPiece {

    public ButtonPieceRider(){
        super();
    }

    @Override
    public Piece choosePiece(Game game, Player player) {
        return game.playerChoosesRider(player);
    }
}
