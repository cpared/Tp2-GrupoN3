package move;

public class Move {
    public int fromRow = -1;
    public int toRow = -1;
    public int fromColumn = -1;
    public int toColumn = -1;


    public boolean isValidMove () {
        return this.isForward () || this.isBackwards () || this.isLeft () || this.isRight () ;
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

}
