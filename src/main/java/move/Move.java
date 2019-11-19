package move;

public class Move {
    public int fromRow;
    public int toRow;
    public int fromColumn;
    public int toColumn;
    public Builder builder;

    public Move () {

    }

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
