package move;

public class Builder {

    private int fromRow = -1 ;
    private int toRow = -1;
    private int fromColumn = -1;
    private int toColumn = -1;

    public Builder () {

    }

    public Builder fromRow( int row ) {
        this.fromRow = row;
        return this;
    }

    public Builder fromColumn( int column ) {
        this.fromColumn = column;
        return this;
    }

    public Builder ToRow ( int row ) {
        this.toRow = row;
        return this;
    }

    public Builder ToColumn ( int column ) {
        this.toColumn = column;
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
