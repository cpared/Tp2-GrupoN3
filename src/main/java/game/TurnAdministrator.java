package game;

import player.Player;

public class TurnAdministrator {
    private Player previous;
    private Player actual;
    private Player next;
    private Player player1;
    private Player player2;
    private int count = 0;


    public TurnAdministrator ( Player player1, Player player2 ) {
        this.actual = player1;
        this.previous = player2;
        this.next = player2;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player changeAvailablePlayer () {
        this.previous = this.actual;
        this.actual = this.next;
        this.next = this.previous;
        return this.actual;
    }

    public Player playerIsReadyToPlay ( Player player ) {
        count++;
        if (count==2) {
            this.actual = this.player1;
            this.previous = this.player2;
            this.next = this.player2;
        } else if (player.equals ( this.actual )) {
            this.actual = this.next;
        }
        else if (player.equals ( this.previous ) || player.equals ( this.next )) {
            this.previous = this.actual;
            this.next = this.actual;
        }

        return this.actual;
    }
}
