package HaganmeElFavorDeNoBorrarLoQueNoCodean;

import game.Game;
import player.Player;

public class Turn {
    private Player currentPlayer;
    private String soldierButtonStyle;
    private String healerButtonStyle;
    private String riderButtonStyle;
    private String catapultButtonStyle;

    public Turn ( Game game) {
        this.currentPlayer = game.getAvailablePlayer ();
    }

    private void leftPlayerStyle () {
        this.soldierButtonStyle = "leftButtonSoldier";
        this.healerButtonStyle = "leftButtonHealer";
        this.riderButtonStyle = "leftButtonRider";
        this.riderButtonStyle = "leftButtonCatapult";
    }

    private void rightPlayerStyle () {
        this.soldierButtonStyle = "rightButtonSoldier";
        this.healerButtonStyle = "rightButtonHealer";
        this.riderButtonStyle = "rightButtonRider";
        this.riderButtonStyle = "rightButtonCatapult";
    }
}
