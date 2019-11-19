package criteria;

import piece.Piece;

import java.util.ArrayList;

public class BattalionCriteria implements Criteria{
    private SoldierCriteria soldierCriteria = new SoldierCriteria ( );
    private ArrayList< Piece > noBattalion = new ArrayList<Piece> ( );

    @Override
    public ArrayList<Piece> criteria ( ArrayList< Piece > pieces ) {
        ArrayList< Piece > soldierPieces = soldierCriteria.criteria ( pieces );
        if (soldierPieces.size () == 3) {
            return soldierPieces;
        }
        while (soldierPieces.size () > 3) {
            soldierPieces.remove ( 3 );
            return soldierPieces;
        }
        return noBattalion;
    }
}
