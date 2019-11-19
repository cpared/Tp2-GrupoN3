package move;

public class Move {
    public int fromRow = 0;
    public int toRow = 0;
    public int fromColumn = 0;
    public int toColumn = 0;


    public boolean isValidMove () {
        return this.isForward () || this.isBackwards () || this.isLeft () || this.isRight () || this.isLeftDiagonal () || this.isRightDiagonal ();
    }

    private boolean isForward () {
        return this.toRow - this.fromRow == 1;
    }

    private boolean isBackwards () {
        return this.toRow - this.fromRow == -1;
    }

    private boolean isLeft () {
        return this.toColumn - fromColumn == 1;
    }

    private boolean isRight () {
        return this.toColumn - fromColumn == -1;
    }

    private boolean isRightDiagonal () {
        return ( this.isRight () && ( this.isForward () || this.isBackwards () ) );
    }

    private boolean isLeftDiagonal () {
        return ( this.isLeft () && ( this.isForward () || this.isBackwards () ) );
    }

}
