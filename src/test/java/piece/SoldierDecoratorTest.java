package piece;

import org.junit.jupiter.api.Test;
import team.Team;

import java.util.ArrayList;

class SoldierDecoratorTest {
    private PieceFactory factory = new PieceFactory ( new Team (1) );
    private Piece soldier1 = factory.createSoldier ();
    private Piece soldier2 = factory.createSoldier ();
    private Piece soldier3 = factory.createSoldier ();
    private ArrayList<Piece> soldiers = new ArrayList<Piece> (  );


    @Test
    void test00 () {
        // Assemble
        soldiers.add ( soldier1 );
        soldiers.add ( soldier2 );
        soldiers.add ( soldier3 );
        RealBattalion battalion = new RealBattalion ( soldiers );

        PieceDecorator soldierD = new SoldierDecorator ( battalion);

        soldier1.receiveAttacked ( 15 );
        System.out.println ( soldier1.getLife () );
        System.out.println ( soldier2.getLife () );
        System.out.println ( soldier3.getLife () );

        battalion.receiveAttacked ( 15 );
        System.out.println ( soldier1.getLife () );
        System.out.println ( soldier2.getLife () );
        System.out.println ( soldier3.getLife () );

        soldierD.receiveAttacked (15);
        System.out.println ( soldier1.getLife () );
        System.out.println ( soldier2.getLife () );
        System.out.println ( soldier3.getLife () );


    }
}