package move;

public class Builder {
    Move move;

    private int fromRow = 0;
    private int toRow = 0;
    private int fromColumn = 0;
    private int toColumn = 0;
    private int parameters = 0;

    public Builder () {

    }

    public Builder fromRow( int row ) {
        this.fromRow = row;
        this.parameters ++;
        return this;
    }

    public Builder fromColumn( int column ) {
        this.fromColumn = column;
        this.parameters ++;
        return this;
    }

    public Builder ToRow ( int row ) {
        this.toRow = row;
        this.parameters ++;
        return this;
    }

    public Builder ToColumn ( int column ) {
        this.toColumn = column;
        this.parameters ++;
        return this;
    }

    public Move build() {
        Move move = new Move();
        move.fromRow = this.fromRow;
        move.fromColumn = this.fromColumn;
        move.toRow = this.toRow;
        move.toColumn = this.toColumn ;

        return move;
    }
}
