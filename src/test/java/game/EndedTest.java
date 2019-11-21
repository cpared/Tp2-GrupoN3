package game;

import move.Builder;
import move.Move;
import org.junit.jupiter.api.Test;
import piece.Piece;
import player.Player;
import player.PlayerHas20PointsOnlyException;

import static org.junit.jupiter.api.Assertions.*;

class EndedTest {

    @Test
    void test00AnStateCanBeCreated () {
        //Act
        GameState state = new Ended ();
        //Assert
        assertNotNull ( state );
    }
    

}