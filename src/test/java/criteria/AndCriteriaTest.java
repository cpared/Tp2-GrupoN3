package criteria;

import org.junit.Test;
import piece.Piece;
import piece.PieceFactory;
import piece.Soldier;
import team.Team;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AndCriteriaTest {
    // Assemble
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private PieceFactory factory = new PieceFactory ( new Team (1) );
    private Piece soldier1 = factory.createSoldier ();
    private Piece soldier2 = factory.createSoldier ();
    private Piece soldier3 = factory.createSoldier ();
    private Piece rider = factory.createRider ();
    private Piece catapult1 = factory.createCatapult ();
    private Piece catapult2 = factory.createCatapult ();
    private Piece healer1 = factory.createHealer ();
    private Piece healer2 = factory.createHealer ();


    @Test
    public void test01FilteringBySoldierCriteriaAndBattalionCriteriaLeaves3Soldiers () {
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( rider );
        pieces.add ( catapult1 );
        pieces.add ( catapult2 );

        SoldierCriteria soldierPieces = new SoldierCriteria ();
        BattalionCriteria battalionPieces = new BattalionCriteria ();

        //Act
        Criteria soldierBattalion = new AndCriteria ( soldierPieces , battalionPieces );
        ArrayList<Piece> battalion = soldierBattalion.criteria ( pieces );

        //Assert
        assertEquals ( 3, battalion.size() );
        for (Piece soldier: battalion) {
            assertEquals ( Soldier.class, soldier.getClass () );
        }
    }

    @Test
    public void test02FilteringBySoldierCriteriaAndRiderCriteriaLeavesNoPieces () {
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( rider );

        SoldierCriteria soldierPieces = new SoldierCriteria ();
        RiderCriteria riderPieces = new RiderCriteria ();

        //Act
        Criteria soldierRider = new AndCriteria ( soldierPieces , riderPieces );
        ArrayList<Piece> filteredPieces = soldierRider.criteria ( pieces );

        //Assert
        assertEquals ( 0, filteredPieces.size() );
    }

    @Test
    public void test03FilteringBySoldierCriteriaAndHealerCriteriaLeavesNoPieces () {
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );

        SoldierCriteria soldierPieces = new SoldierCriteria ();
        HealerCriteria healerPieces = new HealerCriteria ();

        //Act
        Criteria soldierHealer = new AndCriteria ( soldierPieces , healerPieces );
        ArrayList<Piece> filteredPieces = soldierHealer.criteria ( pieces );

        //Assert
        assertEquals ( 0, filteredPieces.size() );
    }

    @Test
    public void test04FilteringBySoldierCriteriaAndCatapultCriteriaLeavesNoPieces () {
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( catapult1 );
        pieces.add ( catapult2 );

        SoldierCriteria soldierPieces = new SoldierCriteria ();
        CatapultCriteria catapultPieces = new CatapultCriteria ();

        //Act
        Criteria soldierCatapult = new AndCriteria ( soldierPieces , catapultPieces );
        ArrayList<Piece> filteredPieces = soldierCatapult.criteria ( pieces );

        //Assert
        assertEquals ( 0, filteredPieces.size() );
    }

    @Test
    public void test05FilteringByRiderCriteriaAndHealerCriteriaLeavesNoPieces () {
        //Assemble
        pieces.add ( rider );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );

        RiderCriteria riderPieces = new RiderCriteria ();
        HealerCriteria healerPieces = new HealerCriteria ();

        //Act
        Criteria riderHealer = new AndCriteria ( riderPieces , healerPieces );
        ArrayList<Piece> filteredPieces = riderHealer.criteria ( pieces );

        //Assert
        assertEquals ( 0, filteredPieces.size() );
    }

    @Test
    public void test06FilteringByRiderCriteriaAndCatapultCriteriaLeavesNoPieces () {
        //Assemble
        pieces.add ( rider );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );

        RiderCriteria riderPieces = new RiderCriteria ();
        CatapultCriteria catapultPieces = new CatapultCriteria ();

        //Act
        Criteria riderCatapult = new AndCriteria ( riderPieces , catapultPieces );
        ArrayList<Piece> filteredPieces = riderCatapult.criteria ( pieces );

        //Assert
        assertEquals ( 0, filteredPieces.size() );
    }

    @Test
    public void test07FilteringByHealerCriteriaAndCatapultCriteriaLeavesNoPieces () {
        //Assemble
        pieces.add ( rider );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );

        HealerCriteria healerPieces = new HealerCriteria ();
        CatapultCriteria catapultPieces = new CatapultCriteria ();

        //Act
        Criteria healerCatapult = new AndCriteria ( healerPieces , catapultPieces );
        ArrayList<Piece> filteredPieces = healerCatapult.criteria ( pieces );

        //Assert
        assertEquals ( 0, filteredPieces.size() );
    }

    @Test
    public void test08FilteringBySoldierCriteriaAndSoldierCriteriaLeaves3Pieces () {
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );
        pieces.add ( catapult1 );
        pieces.add ( catapult2 );

        SoldierCriteria soldierPieces = new SoldierCriteria ();

        //Act
        Criteria soldiers = new AndCriteria ( soldierPieces , soldierPieces );
        ArrayList<Piece> filteredPieces = soldiers.criteria ( pieces );

        //Assert
        assertEquals ( 3, filteredPieces.size() );
    }

    @Test
    public void test09FilteringByHealerCriteriaAndHealerCriteriaLeaves2Pieces () {
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );
        pieces.add ( catapult1 );
        pieces.add ( catapult2 );

        HealerCriteria healerPieces = new HealerCriteria ();

        //Act
        Criteria healers = new AndCriteria ( healerPieces , healerPieces );
        ArrayList<Piece> filteredPieces = healers.criteria ( pieces );

        //Assert
        assertEquals ( 2, filteredPieces.size() );
    }

    @Test
    public void test10FilteringByRiderCriteriaAndRiderCriteriaLeaves1Pieces () {
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );
        pieces.add ( catapult1 );
        pieces.add ( catapult2 );
        pieces.add ( rider );

        RiderCriteria riderPieces = new RiderCriteria ();

        //Act
        Criteria riders = new AndCriteria ( riderPieces , riderPieces );
        ArrayList<Piece> filteredPieces = riders.criteria ( pieces );

        //Assert
        assertEquals ( 1, filteredPieces.size() );
    }

    @Test
    public void test10FilteringByCatapultCriteriaAndCatapultCriteriaLeaves2Pieces () {
        //Assemble
        pieces.add ( soldier1 );
        pieces.add ( soldier2 );
        pieces.add ( soldier3 );
        pieces.add ( healer1 );
        pieces.add ( healer2 );
        pieces.add ( catapult1 );
        pieces.add ( catapult2 );
        pieces.add ( rider );

        CatapultCriteria catapultPieces = new CatapultCriteria ();

        //Act
        Criteria catapults = new AndCriteria ( catapultPieces , catapultPieces );
        ArrayList<Piece> filteredPieces = catapults.criteria ( pieces );

        //Assert
        assertEquals ( 2, filteredPieces.size() );
    }
}
