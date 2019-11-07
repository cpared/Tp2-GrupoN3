package piece;

import team.Team;

public class PieceFactory {
    public Piece getPiece(String pieceType, Team team){
        pieceType = fixingStringParameter ( pieceType );

        if ( pieceType == null ){
            return null;
        }
        if ( pieceType.equalsIgnoreCase ("SOLDIER" )){
            return new Soldier ( team );

        } else if ( pieceType.equalsIgnoreCase ("HEALER" )){
            return new Healer ( team );

        } else if ( pieceType.equalsIgnoreCase ("RIDER" )){
            return new Rider ( team );

        } else if ( pieceType.equalsIgnoreCase ("CATAPULT" )){
            return new Catapult ( team );
        }
        return null;
    }

    private String fixingStringParameter (String string) {
        if (string == null) {
            return null;
        }
        return string.toUpperCase ().trim ();
    }
}
