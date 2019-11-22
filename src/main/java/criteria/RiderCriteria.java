package criteria;

import piece.Piece;

import java.util.ArrayList;

public class RiderCriteria implements Criteria{

    @Override
    public ArrayList<Piece> criteria ( ArrayList< Piece > pieces ) {
        ArrayList< Piece > riderPieces = new ArrayList<Piece> ( );
        for ( Piece piece : pieces ) {
            if ( piece.isCost ( 3 )) {
                riderPieces.add ( piece );
            }
        }
        return riderPieces;
    }
}