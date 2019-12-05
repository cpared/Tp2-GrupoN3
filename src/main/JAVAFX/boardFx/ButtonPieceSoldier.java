package boardFx;

import game.Game;
import piece.Piece;
import player.Player;

public class ButtonPieceSoldier extends ButtonPiece {

    public ButtonPieceSoldier(){
        super();
    }

    @Override
    public Piece choosePiece(Game game, Player player) {
        return game.playerChoosesSoldier(player);
    }

    @Override
    public String getString(Game game, Player player){
        return "buttonSoldier";
        }
}
