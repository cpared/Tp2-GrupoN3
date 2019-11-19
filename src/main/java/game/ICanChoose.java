package game;

import piece.Piece;
import player.Player;

public interface ICanChoose {

    Piece chooseSoldier ( Player player );

    Piece chooseHealer (Player player );

    Piece chooseRider (Player player );

    Piece chooseCatapult (Player player );
}

