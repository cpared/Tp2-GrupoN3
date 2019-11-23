package game;
import org.junit.jupiter.api.Test;

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