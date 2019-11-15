package criteria;

import org.junit.jupiter.api.Test;
import piece.Healer;
import piece.Piece;
import piece.PieceFactory;
import piece.Soldier;
import team.Team;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BattalionCriteriaTest {
    // Assemble
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private PieceFactory factory = new PieceFactory ( new Team () );
    private Piece soldier1 = factory.createSoldier ();
    private Piece soldier2 = factory.createSoldier ();
    private Piece soldier3 = factory.createSoldier ();
    private Piece soldier4 = factory.createSoldier ();
    private Piece rider1 = factory.createRider ();
    private Piece rider2 = factory.createRider ();
    private Piece healer1 = factory.createHealer ();
    private Piece healer2 = factory.createHealer ();

    @Test
    void test01FilteringByBattalionCriteriaLeavesSoldiers (){
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( rider1 );
        pieces.add ( rider2 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );

        //Act
        BattalionCriteria battalionPieces = new BattalionCriteria ();
        ArrayList<Piece> battalion = battalionPieces.criteria ( pieces );

        //Assert
        for (Piece soldier: battalion) {
            assertEquals ( Soldier.class, soldier.getClass () );
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
        BattalionCriteria battalionPieces = new BattalionCriteria ();
        ArrayList<Piece> battalion = battalionPieces.criteria ( pieces );

        //Assert
        assertEquals ( 3, battalion.size() );
    }

    @Test
    void test03TryingToMakeABattalionWith4SoldiersWillOnlyAllow3Soldiers (){
        //Assemble
        pieces.add ( rider1 );
        pieces.add ( rider2 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( soldier4 );

        //Act
        BattalionCriteria battalionPieces = new BattalionCriteria ();
        ArrayList<Piece> battalion = battalionPieces.criteria ( pieces );

        //Assert
        assertEquals ( 3, battalion.size() );
    }

    @Test
    void test04TryingToMakeABattalionWith2SoldiersWillNotCreateBattalion (){
        //Assemble
        pieces.add ( rider1 );
        pieces.add ( rider2 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );

        //Act
        BattalionCriteria battalionPieces = new BattalionCriteria ();
        ArrayList<Piece> battalion = battalionPieces.criteria ( pieces );

        //Assert
        assertEquals ( 0, battalion.size() );
    }

    @Test
    void test04TryingToMakeABattalionWith1SoldierWillNotCreateBattalion (){
        //Assemble
        pieces.add ( rider1 );
        pieces.add ( rider2 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );
        pieces.add ( soldier1 );

        //Act
        BattalionCriteria battalionPieces = new BattalionCriteria ();
        ArrayList<Piece> battalion = battalionPieces.criteria ( pieces );

        //Assert
        assertEquals ( 0, battalion.size() );
    }

}
