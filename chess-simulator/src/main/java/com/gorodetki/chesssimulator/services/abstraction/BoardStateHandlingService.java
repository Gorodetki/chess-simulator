package com.gorodetki.chesssimulator.services.abstraction;

import com.gorodetki.chesssimulator.models.board.abstraction.GameBoard;
import com.gorodetki.chesssimulator.models.move.impl.ChessMove;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.models.pieces.abstraction.Piece;

public interface BoardStateHandlingService {

    Piece getPieceAt(ChessmanCoordinate coordinate, GameBoard gameBoard);

    void handleMove(ChessMove chessMove);
}
