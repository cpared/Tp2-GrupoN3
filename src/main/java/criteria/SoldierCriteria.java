package criteria;

import piece.Piece;

import java.util.ArrayList;

public class SoldierCriteria implements Criteria {

    @Override
    public ArrayList<Piece> criteria ( ArrayList< Piece > pieces ) {
        ArrayList< Piece > soldierPieces = new ArrayList<Piece> ( );
        for ( Piece piece : pieces ) {
            if ( piece.isCost ( 1 )) {
                soldierPieces.add ( piece );
            }
        }
        return soldierPieces;
    }
}
