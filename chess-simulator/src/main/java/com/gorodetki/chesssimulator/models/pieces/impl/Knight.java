package com.gorodetki.chesssimulator.models.pieces.impl;

import com.gorodetki.chesssimulator.models.board.impl.ChessBoard;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.models.pieces.PieceColor;
import com.gorodetki.chesssimulator.models.pieces.ChessmanType;
import com.gorodetki.chesssimulator.models.pieces.abstraction.Chessman;
import com.gorodetki.chesssimulator.services.abstraction.BoardStateHandlingService;
import com.gorodetki.chesssimulator.services.abstraction.ChessmanMoveService;

public class Knight extends Chessman {

    public Knight(ChessmanCoordinate pieceCoordinate, PieceColor pieceColor, ChessmanType chessmanType,
                  ChessmanMoveService bishopMoveService, BoardStateHandlingService boardStateHandlingService,
                  ChessBoard chessBoard) {
        super(pieceCoordinate, pieceColor, chessmanType, bishopMoveService, boardStateHandlingService, chessBoard);
    }
}