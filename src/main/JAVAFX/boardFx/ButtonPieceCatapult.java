package boardFx;

import game.Game;
import piece.Piece;
import player.Player;

public class ButtonPieceCatapult extends ButtonPiece {

    public ButtonPieceCatapult(){
        super();
    }
    @Override
    public Piece choosePiece(Game game, Player player) {
        return game.playerChoosesCatapult(player);
    }

    @Override
    public String getString(Game game, Player player){
        return "buttonCatapult";
        }
}
