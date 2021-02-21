package com.gorodetki.chesssimulator.services.impl;

import com.gorodetki.chesssimulator.models.board.impl.ChessBoard;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.models.pieces.abstraction.Chessman;
import com.gorodetki.chesssimulator.services.abstraction.BoardStateHandlingService;
import com.gorodetki.chesssimulator.services.abstraction.ChessmanMoveService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gorodetki.chesssimulator.models.pieces.PieceColor.WHITE;

@Service
@RequiredArgsConstructor
public class PawnMoveService implements ChessmanMoveService {
    private final BoardStateHandlingService boardStateHandlingService;

    @Override
    public List<ChessmanCoordinate> getAllPossibleDestinationOfPiece(Chessman chessman, ChessBoard board) {
        List<ChessmanCoordinate> possibleCoordinates = new ArrayList<>();
        int pawnMoves = chessman.getMoveCounter();
        int step = pawnMoves > 1 ? 1 : 2;
        int direction = chessman.getPieceColor().equals(WHITE) ? 1 : -1;
        calculate(chessman, board, possibleCoordinates, step, direction);
        return possibleCoordinates;
    }

    @Override
    public List<ChessmanCoordinate> getAllPossibleAttackingDestinations(Chessman chessman, ChessBoard board) {
        List<ChessmanCoordinate> possibleCoordinates = new ArrayList<>();
        int direction = chessman.getPieceColor().equals(WHITE) ? 1 : -1;
        calculateAttackingDestinations(chessman, board, possibleCoordinates, direction);
        return possibleCoordinates;
    }

    private void calculateAttackingDestinations(Chessman chessman, ChessBoard board, List<ChessmanCoordinate> possibleCoordinates, int direction) {
        val numericCoordinate = chessman.getPieceCoordinate().getNumericCoordinate() + direction;
        val literalCoordinates = Arrays.asList(chessman.getPieceCoordinate().getLiteralCoordinate() + 1,
                chessman.getPieceCoordinate().getLiteralCoordinate() - 1);
        for (int literalCoordinate : literalCoordinates) {
            if (isValidIndex(numericCoordinate) && isValidIndex(literalCoordinate)) {
                collectCoordinateIfApplicable(numericCoordinate, literalCoordinate, board, chessman, possibleCoordinates);
            }
        }

    }

    private void calculate(Chessman chessman, ChessBoard board, List<ChessmanCoordinate> possibleCoordinates, int step, int direction) {
        for (int i = 1; i <= step; i++) {
            val numericCoordinate = chessman.getPieceCoordinate().getNumericCoordinate() + i * direction;
            val literalCoordinate = chessman.getPieceCoordinate().getLiteralCoordinate();
            if (isValidIndex(numericCoordinate)) {
                collectCoordinateIfApplicable(numericCoordinate, literalCoordinate, board, chessman, possibleCoordinates);
            }
        }
    }

    private void collectCoordinateIfApplicable(int numericCoordinate, int literalCoordinate, ChessBoard board,
                                               Chessman chessman, List<ChessmanCoordinate> possibleCoordinates) {
        val destinationCoordinate = ChessmanCoordinate.at(numericCoordinate, literalCoordinate);
        val chessmanAtDestinationSpot = boardStateHandlingService.getPieceAt(destinationCoordinate, board);
        if (chessmanAtDestinationSpot == null || !chessmanAtDestinationSpot.getPieceColor().equals(chessman.getPieceColor())) {
            possibleCoordinates.add(destinationCoordinate);
        }
    }
}

