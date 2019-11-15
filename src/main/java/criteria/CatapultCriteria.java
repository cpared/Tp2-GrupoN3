package criteria;

import piece.Piece;

import java.util.ArrayList;

public class CatapultCriteria implements Criteria {

    @Override
    public ArrayList<Piece> criteria ( ArrayList< Piece > pieces ) {
        ArrayList< Piece > catapultPieces = new ArrayList<Piece> ( );
        for ( Piece piece : pieces ) {
            if ( piece.isCost ( 5 )) {
                catapultPieces.add ( piece );
            }
        }
        return catapultPieces;
    }
}
