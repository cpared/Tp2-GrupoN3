package move;

public class Builder {
    Move move;

    private int fromRow = 0;
    private int toRow = 0;
    private int fromColumn = 0;
    private int toColumn = 0;

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
        //Here we create the actual bank account object, which is always in a fully initialised state when it's returned.
        Move move = new Move();  //Since the builder is in the BankAccount class, we can invoke its private constructor.
        move.fromRow = this.fromRow;
        move.fromColumn = this.fromColumn;
        move.toRow = this.toRow;
        move.toColumn = this.toColumn;
        return move;
    }
}
