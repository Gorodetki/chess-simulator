package com.gorodetki.chesssimulator.services.impl;

import com.gorodetki.chesssimulator.models.board.impl.ChessBoard;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.models.pieces.PieceColor;
import com.gorodetki.chesssimulator.models.pieces.ChessmanType;
import com.gorodetki.chesssimulator.models.pieces.abstraction.Piece;
import com.gorodetki.chesssimulator.services.abstraction.BoardStateHandlingService;
import com.gorodetki.chesssimulator.services.abstraction.ChessmanMoveService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class PieceInitializer {

    @SneakyThrows
    public Piece initPieceForType(ChessmanCoordinate pieceCoordinate, PieceColor pieceColor,
                                  ChessmanType chessmanType, ChessmanMoveService chessmanMoveService,
                                  BoardStateHandlingService boardStateHandlingService, ChessBoard chessBoard) {
        return chessmanType
                .getPieceClass()
                .getConstructor(ChessmanCoordinate.class, PieceColor.class, ChessmanType.class, ChessmanMoveService.class, BoardStateHandlingService.class, ChessBoard.class)
                .newInstance(pieceCoordinate, pieceColor, chessmanType, chessmanMoveService, boardStateHandlingService, chessBoard);
    }
}
