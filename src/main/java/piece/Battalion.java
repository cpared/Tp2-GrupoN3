package piece;

import board.Board;
import move.Move;
import piece.Piece;
import piece.RealBattalion;
import team.Team;

import java.util.*;

public interface Battalion extends Piece {

    RealBattalion createBattalion ();

    @Override
    void move( Board board , Move move);
}