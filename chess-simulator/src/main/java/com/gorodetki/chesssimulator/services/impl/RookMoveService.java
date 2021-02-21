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
public class RookMoveService extends LongRunningChessmanMoveService {

    private final BoardStateHandlingService boardStateHandlingService;

    @Override
    public List<ChessmanCoordinate> getAllPossibleDestinationOfPiece(Chessman chessman, ChessBoard board) {
        List<ChessmanCoordinate> possibleDestinations = new ArrayList<>();
        val currentCoordinate = chessman.getPieceCoordinate();
        val pieceColor = ((Chessman) chessman).getPieceColor();
        collectAllDestinationsFromTop(currentCoordinate, possibleDestinations, board, pieceColor);
        collectAllDestinationsFromBottom(currentCoordinate, possibleDestinations, board, pieceColor);
        collectAllDestinationsFromRight(currentCoordinate, possibleDestinations, board, pieceColor);
        collectAllDestinationsFromLeft(currentCoordinate, possibleDestinations, board, pieceColor);
        return possibleDestinations;
    }

    private void collectAllDestinationsFromTop(ChessmanCoordinate currentCoordinate,
                                               List<ChessmanCoordinate> possibleDestinations,
                                               GameBoard board, PieceColor pieceColor) {
        int stepLiteral = 0;
        int stepNumeric = -1;
        collectAllDestinationByStep(stepLiteral, stepNumeric, currentCoordinate, possibleDestinations, board, pieceColor, boardStateHandlingService);
    }

    private void collectAllDestinationsFromBottom(ChessmanCoordinate currentCoordinate,
                                                  List<ChessmanCoordinate> possibleDestinations,
                                                  GameBoard board, PieceColor pieceColor) {
        int stepLiteral = 0;
        int stepNumeric = 1;
        collectAllDestinationByStep(stepLiteral, stepNumeric, currentCoordinate, possibleDestinations, board, pieceColor, boardStateHandlingService);
    }

    private void collectAllDestinationsFromRight(ChessmanCoordinate currentCoordinate,
                                                 List<ChessmanCoordinate> possibleDestinations,
                                                 GameBoard board, PieceColor pieceColor) {
        int stepLiteral = 1;
        int stepNumeric = 0;
        collectAllDestinationByStep(stepLiteral, stepNumeric, currentCoordinate, possibleDestinations, board, pieceColor, boardStateHandlingService);
    }

    private void collectAllDestinationsFromLeft(ChessmanCoordinate currentCoordinate,
                                                List<ChessmanCoordinate> possibleDestinations,
                                                GameBoard board, PieceColor pieceColor) {
        int stepLiteral = -1;
        int stepNumeric = 0;
        collectAllDestinationByStep(stepLiteral, stepNumeric, currentCoordinate, possibleDestinations, board, pieceColor, boardStateHandlingService);
    }

}
