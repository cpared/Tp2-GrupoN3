package criteria;

import piece.Piece;

import java.util.ArrayList;

public class HealerCriteria implements Criteria {

    @Override
    public ArrayList<Piece> criteria ( ArrayList< Piece > pieces ) {
        ArrayList< Piece > healerPieces = new ArrayList<Piece> ( );
        for ( Piece piece : pieces ) {
            if ( piece.isCost ( 2 )) {
                healerPieces.add ( piece );
            }
        }
        return healerPieces;
    }
}
