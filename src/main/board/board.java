class Board {
    public void movePiece(Cell originCell, Cell destinationCell) {
        if (distance(originCell,destinationCell) < 1) {
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

    private int distance(Cell originCell, Cell destinationCell) {
    }
}