package piece;

import board.Battalion;
import org.junit.jupiter.api.Test;
import team.Team;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        BattalionE battalion = new BattalionE ( soldiers );

        PieceDecorator soldierD = new SoldierDecorator ( soldier1 , battalion);
        PieceDecorator soldierDe = new SoldierDecorator ( soldier2 , battalion);
        PieceDecorator soldierDec = new SoldierDecorator ( soldier2 , battalion);

        soldier1.getAttacked ( 15 );
        System.out.println ( soldier1.getLife () );
        System.out.println ( soldier2.getLife () );
        System.out.println ( soldier3.getLife () );

        battalion.getAttacked ( 15 );
        System.out.println ( soldier1.getLife () );
        System.out.println ( soldier2.getLife () );
        System.out.println ( soldier3.getLife () );

        soldierD.getAttacked (15);
        System.out.println ( soldier1.getLife () );
        System.out.println ( soldier2.getLife () );
        System.out.println ( soldier3.getLife () );
        soldierDe.getAttacked (5);
        System.out.println ( soldier1.getLife () );
        System.out.println ( soldier2.getLife () );
        System.out.println ( soldier3.getLife () );
        soldierDec.getAttacked (5);
        System.out.println ( soldier1.getLife () );
        System.out.println ( soldier2.getLife () );
        System.out.println ( soldier3.getLife () );
    }
}