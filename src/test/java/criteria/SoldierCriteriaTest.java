package criteria;

import org.junit.jupiter.api.Test;
import piece.Piece;
import piece.PieceFactory;
import piece.Soldier;
import team.Team;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SoldierCriteriaTest {
    // Assemble
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private PieceFactory factory = new PieceFactory ( new Team (45) );
    private Piece soldier1 = factory.createSoldier ();
    private Piece soldier2 = factory.createSoldier ();
    private Piece soldier3 = factory.createSoldier ();
    private Piece healer1 = factory.createHealer ();
    private Piece healer2 = factory.createHealer ();

    @Test
    void test01FilteringBySoldierCriteriaLeavesSoldiers (){
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );

        //Act
        SoldierCriteria soldierPieces = new SoldierCriteria ();
        ArrayList<Piece> soldiers = soldierPieces.criteria ( pieces );

        //Assert
        for (Piece soldier: soldiers) {
            assertEquals ( Soldier.class, soldier.getClass () );
        }
    }

    @Test
    void test02FilteringBySoldierCriteriaLeaves3Soldiers (){
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );

        //Act
        SoldierCriteria soldierPieces = new SoldierCriteria ();
        ArrayList<Piece> soldiers = soldierPieces.criteria ( pieces );

        //Assert
        assertEquals ( 3, soldiers.size() );
    }
}
