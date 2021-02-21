package com.gorodetki.chesssimulator.services.impl;

import com.gorodetki.chesssimulator.models.board.abstraction.GameBoard;
import com.gorodetki.chesssimulator.models.board.impl.ChessBoard;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.models.pieces.PieceColor;
import com.gorodetki.chesssimulator.models.pieces.abstraction.Chessman;
import com.gorodetki.chesssimulator.services.abstraction.BoardStateHandlingService;
import com.gorodetki.chesssimulator.services.abstraction.LongRunningChessmanMoveService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BishopMoveService extends LongRunningChessmanMoveService {

    private final BoardStateHandlingService boardStateHandlingService;

    @Override
    public List<ChessmanCoordinate> getAllPossibleDestinationOfPiece(Chessman chessman, ChessBoard board) {
        List<ChessmanCoordinate> possibleDestinations = new ArrayList<>();
        val currentCoordinate = chessman.getPieceCoordinate();
        val pieceColor = ((Chessman) chessman).getPieceColor();
        collectAllDestinationsFromLeftTop(currentCoordinate, possibleDestinations, board, pieceColor);
        collectAllDestinationsFromLeftBottom(currentCoordinate, possibleDestinations, board, pieceColor);
        collectAllDestinationsFromRightTop(currentCoordinate, possibleDestinations, board, pieceColor);
        collectAllDestinationsFromRightBottom(currentCoordinate, possibleDestinations, board, pieceColor);
        return possibleDestinations;
    }

    private void collectAllDestinationsFromRightBottom(ChessmanCoordinate currentCoordinate,
                                                       List<ChessmanCoordinate> possibleDestinations,
                                                       GameBoard board, PieceColor pieceColor) {
        int stepLiteral = 1;
        int stepNumeric = 1;
        collectAllDestinationByStep(stepLiteral, stepNumeric, currentCoordinate, possibleDestinations, board, pieceColor, boardStateHandlingService);
    }

    private void collectAllDestinationsFromRightTop(ChessmanCoordinate currentCoordinate,
                                                    List<ChessmanCoordinate> possibleDestinations,
                                                    GameBoard board, PieceColor pieceColor) {
        int stepLiteral = 1;
        int stepNumeric = -1;
        collectAllDestinationByStep(stepLiteral, stepNumeric, currentCoordinate, possibleDestinations, board, pieceColor, boardStateHandlingService);

    }

    private void collectAllDestinationsFromLeftBottom(ChessmanCoordinate currentCoordinate,
                                                      List<ChessmanCoordinate> possibleDestinations,
                                                      GameBoard board, PieceColor pieceColor) {
        int stepLiteral = -1;
        int stepNumeric = 1;
        collectAllDestinationByStep(stepLiteral, stepNumeric, currentCoordinate, possibleDestinations, board, pieceColor, boardStateHandlingService);
    }

    private void collectAllDestinationsFromLeftTop(ChessmanCoordinate currentCoordinate,
                                                   List<ChessmanCoordinate> possibleDestinations,
                                                   GameBoard board, PieceColor pieceColor) {
        int stepLiteral = -1;
        int stepNumeric = -1;
        collectAllDestinationByStep(stepLiteral, stepNumeric, currentCoordinate, possibleDestinations, board, pieceColor, boardStateHandlingService);
    }


}
