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

    @Test
    void test01FilteringByBattalionCriteriaLeavesSoldiers (){
        //Assemble
        Piece rider1 = factory.createRider ();
        Piece rider2 = factory.createRider ();

        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( rider1 );
        pieces.add ( rider2 );

        //Act
        BattalionCriteria battalionPieces = new BattalionCriteria ();
        ArrayList<Piece> battalion = battalionPieces.criteria ( pieces );

        //Assert
        for (Piece soldier: battalion) {
            assertEquals ( Soldier.class, soldier.getClass () );
        }
    }

    @Test
    void test02FilteringByBattalionCriteriaLeaves3oldiers (){
        //Assemble
        Piece rider1 = factory.createRider ();
        Piece rider2 = factory.createRider ();

        pieces.add ( rider1 );
        pieces.add ( rider2 );
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

        Piece rider1 = factory.createRider ();
        Piece rider2 = factory.createRider ();

        pieces.add ( rider1 );
        pieces.add ( rider2 );
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
        Piece rider1 = factory.createRider ();
        Piece rider2 = factory.createRider ();

        pieces.add ( rider1 );
        pieces.add ( rider2 );
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );

        //Act
        BattalionCriteria battalionPieces = new BattalionCriteria ();
        ArrayList<Piece> battalion = battalionPieces.criteria ( pieces );

        //Assert
        assertEquals ( 0, battalion.size() );
    }

    @Test
    void test05TryingToMakeABattalionWith1SoldierWillNotCreateBattalion (){
        //Assemble
        Piece rider1 = factory.createRider ();
        Piece rider2 = factory.createRider ();

        pieces.add ( rider1 );
        pieces.add ( rider2 );
        pieces.add ( soldier1 );

        //Act
        BattalionCriteria battalionPieces = new BattalionCriteria ();
        ArrayList<Piece> battalion = battalionPieces.criteria ( pieces );

        //Assert
        assertEquals ( 0, battalion.size() );
    }

    @Test
    void test06TryingToMakeABattalionWith3RidersWillNotCreateBattalion (){
        //Assemble
        Piece rider1 = factory.createRider ();
        Piece rider2 = factory.createRider ();
        Piece rider3 = factory.createRider ();

        pieces.add ( rider1 );
        pieces.add ( rider2 );
        pieces.add ( rider3 );
        pieces.add ( soldier1 );

        //Act
        BattalionCriteria battalionPieces = new BattalionCriteria ();
        ArrayList<Piece> battalion = battalionPieces.criteria ( pieces );

        //Assert
        assertEquals ( 0, battalion.size() );
    }

    @Test
    void test08TryingToMakeABattalionWith3HealersWillNotCreateBattalion (){
        //Assemble
        Piece healer3 = factory.createHealer ();
        Piece healer1 = factory.createHealer ();
        Piece healer2 = factory.createHealer ();

        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );
        pieces.add ( healer3 );

        //Act
        BattalionCriteria battalionPieces = new BattalionCriteria ();
        ArrayList<Piece> battalion = battalionPieces.criteria ( pieces );

        //Assert
        assertEquals ( 0, battalion.size() );
    }

    @Test
    void test09TryingToMakeABattalionWith3CatapultsWillNotCreateBattalion (){
        //Assemble
        Piece catapult3 = factory.createCatapult ();
        Piece catapult2 = factory.createCatapult ();
        Piece catapult1 = factory.createCatapult ();

        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( catapult1 );
        pieces.add ( catapult2 );
        pieces.add ( catapult3 );

        //Act
        BattalionCriteria battalionPieces = new BattalionCriteria ();
        ArrayList<Piece> battalion = battalionPieces.criteria ( pieces );

        //Assert
        assertEquals ( 0, battalion.size() );
    }

    @Test
    void test10TryingToMakeABattalionWith3CatapultsAnd3SoldiersWillCreateBattalionWithSoldiers (){
        //Assemble
        Piece catapult3 = factory.createCatapult ();
        Piece catapult2 = factory.createCatapult ();
        Piece catapult1 = factory.createCatapult ();

        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( catapult1 );
        pieces.add ( catapult2 );
        pieces.add ( catapult3 );
        pieces.add ( soldier3 );

        //Act
        BattalionCriteria battalionPieces = new BattalionCriteria ();
        ArrayList<Piece> battalion = battalionPieces.criteria ( pieces );

        //Assert
        assertEquals ( 3, battalion.size() );
    }

}
