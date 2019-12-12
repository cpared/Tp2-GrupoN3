package criteria;

import game.Game;
import org.junit.jupiter.api.Test;
import piece.Piece;
import piece.PieceFactory;
import piece.Rider;

import team.Team;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiderCriteriaTest {

    // Assemble
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private PieceFactory factory = new PieceFactory ( new Team (1,new Game ()) );
    private Piece soldier1 = factory.createSoldier ();
    private Piece soldier2 = factory.createSoldier ();
    private Piece soldier3 = factory.createSoldier ();
    private Piece rider1 = factory.createRider ();
    private Piece rider2 = factory.createRider ();
    private Piece rider3 = factory.createRider ();
    private Piece rider4 = factory.createRider ();

    @Test
    void test01FilteringByRiderCriteriaLeavesRiders (){
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( rider1 );
        pieces.add ( rider2 );
        pieces.add ( rider3 );
        pieces.add ( rider4 );

        //Act
        RiderCriteria riderPieces = new RiderCriteria ();
        ArrayList<Piece> riders = riderPieces.criteria ( pieces );

        //Assert
        for (Piece rider: riders) {
            assertEquals ( Rider.class, rider.getClass () );
        }
    }

    @Test
    void test02FilteringByRiderCriteriaLeaves4Riders (){
        //Assemble
        pieces.add ( rider1 );
        pieces.add ( rider2 );
        pieces.add ( rider3 );
        pieces.add ( rider4 );
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );

        //Act
        RiderCriteria riderPieces = new RiderCriteria ();
        ArrayList<Piece> riders = riderPieces.criteria ( pieces );

        //Assert
        assertEquals ( 4, riders.size() );
    }
}
