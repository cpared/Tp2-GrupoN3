package criteria;

import org.junit.jupiter.api.Test;
import piece.Catapult;
import piece.Piece;
import piece.PieceFactory;
import team.Team;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CatapultCriteriaTest {
    // Assemble
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private PieceFactory factory = new PieceFactory ( new Team (4) );
    private Piece soldier1 = factory.createSoldier ();
    private Piece soldier2 = factory.createSoldier ();
    private Piece soldier3 = factory.createSoldier ();
    private Piece catapult1 = factory.createCatapult ();
    private Piece catapult2 = factory.createCatapult ();
    private Piece catapult3 = factory.createCatapult ();


    @Test
    void test01FilteringByCatapultCriteriaLeavesCatapults (){
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( catapult1 );
        pieces.add ( catapult2 );
        pieces.add ( catapult3 );

        //Act
        CatapultCriteria catapultPieces = new CatapultCriteria ();
        ArrayList<Piece> catapults = catapultPieces.criteria ( pieces );

        //Assert
        for (Piece catapult: catapults) {
            assertEquals ( Catapult.class, catapult.getClass () );
        }
    }

    @Test
    void test02FilteringByCatapultCriteriaLeaves3Catapults (){
        //Assemble
        pieces.add ( catapult1 );
        pieces.add ( catapult2 );
        pieces.add ( catapult3 );
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );

        //Act
        CatapultCriteria catapultPieces = new CatapultCriteria ();
        ArrayList<Piece> catapults = catapultPieces.criteria ( pieces );

        //Assert
        assertEquals ( 3, catapults.size() );
    }
}
