package criteria;

import game.Game;
import org.junit.jupiter.api.Test;
import piece.Healer;
import piece.Piece;
import piece.PieceFactory;
import team.Team;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealerCriteriaTest {
    // Assemble
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private PieceFactory factory = new PieceFactory ( new Team (1, new Game ()) );
    private Piece soldier1 = factory.createSoldier ();
    private Piece soldier2 = factory.createSoldier ();
    private Piece soldier3 = factory.createSoldier ();
    private Piece rider1 = factory.createRider ();
    private Piece rider2 = factory.createRider ();
    private Piece healer1 = factory.createHealer ();
    private Piece healer2 = factory.createHealer ();

    @Test
    void test01FilteringByHealerCriteriaLeavesHealers (){
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( rider1 );
        pieces.add ( rider2 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );

        //Act
        HealerCriteria healerPieces = new HealerCriteria ();
        ArrayList<Piece> healers = healerPieces.criteria ( pieces );

        //Assert
        for (Piece healer: healers) {
            assertEquals ( Healer.class, healer.getClass () );
        }
    }

    @Test
    void test02FilteringByHealerCriteriaLeaves2Healers (){
        //Assemble
        pieces.add ( rider1 );
        pieces.add ( rider2 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );

        //Act
        HealerCriteria healerPieces = new HealerCriteria ();
        ArrayList<Piece> healers = healerPieces.criteria ( pieces );

        //Assert
        assertEquals ( 2, healers.size() );
    }
}
