package criteria;
import piece.Piece;

import java.util.ArrayList;

public class AndCriteria implements Criteria {

    private Criteria firstCriteria;
    private Criteria secondCriteria;

    public AndCriteria ( Criteria firstCriteria, Criteria secondCriteria ) {
        this.firstCriteria = firstCriteria;
        this.secondCriteria = secondCriteria;
    }

    @Override
    public ArrayList< Piece > criteria ( ArrayList< Piece > pieces ) {
        ArrayList< Piece > firstCriteriaPieces = firstCriteria.criteria ( pieces );
        return secondCriteria.criteria ( firstCriteriaPieces );
    }
}