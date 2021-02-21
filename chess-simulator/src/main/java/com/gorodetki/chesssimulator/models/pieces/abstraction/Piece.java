package com.gorodetki.chesssimulator.models.pieces.abstraction;

import com.gorodetki.chesssimulator.models.move.abstraction.Move;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.models.pieces.PieceColor;

public interface Piece {

    ChessmanCoordinate getPieceCoordinate();


    void updatePieceCoordinate(ChessmanCoordinate toSpot);

    boolean canMove(Move move);

    PieceColor getPieceColor();
}
