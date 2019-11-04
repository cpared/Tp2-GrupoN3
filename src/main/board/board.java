class Board {
    public void movePiece(Cell originCell, Cell destinationCell) {
        if (originCell.distance(destinationCell) < 1) {
            throw Exception CanNotMakeThatMoveException;
        }
        Piece piece;
        piece = originCell.deletePieceFromCell();
        try {
            destinationCell.putPieceInCell(piece);
        } catch (Exception OccupiedCellException) {
            originCell.putPieceInCell(piece);
            throw CanNotMakeThatMoveException;
        }
    }
}