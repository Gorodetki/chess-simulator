package com.gorodetki.chesssimulator.services.abstraction;

import com.gorodetki.chesssimulator.models.board.abstraction.GameBoard;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.models.pieces.PieceColor;
import lombok.val;

import java.util.List;


public abstract class LongRunningChessmanMoveService implements ChessmanMoveService {

    public void updateNextSpotCoordinate(ChessmanCoordinate previousCoordinate, int stepLiteral, int stepNumeric) {
        previousCoordinate.setNumericCoordinate(previousCoordinate.getNumericCoordinate() + stepNumeric);
        previousCoordinate.setLiteralCoordinate(previousCoordinate.getLiteralCoordinate() + stepLiteral);
    }

    protected void collectAllDestinationByStep(int stepLiteral, int stepNumeric, ChessmanCoordinate currentCoordinate,
                                               List<ChessmanCoordinate> possibleDestinations, GameBoard board,
                                               PieceColor pieceColor, BoardStateHandlingService boardStateHandlingService) {

        val nextSpot = ChessmanCoordinate.at(currentCoordinate.getNumericCoordinate(),
                currentCoordinate.getLiteralCoordinate());

        updateNextSpotCoordinate(nextSpot, stepLiteral, stepNumeric);
        while (shouldCheckNextSpot(nextSpot)) {
            val pieceAtNextSpot = boardStateHandlingService.getPieceAt(nextSpot, board);
            if (pieceAtNextSpot == null) {
                possibleDestinations.add(ChessmanCoordinate.at(nextSpot.getNumericCoordinate(), nextSpot.getLiteralCoordinate()));
                updateNextSpotCoordinate(nextSpot, stepLiteral, stepNumeric);
                continue;
            }
            val nextSpotPieceColor = pieceAtNextSpot.getPieceColor();
            if (!nextSpotPieceColor.equals(pieceColor)) {
                possibleDestinations.add(nextSpot);
            }
            break;
        }
    }

    private boolean shouldCheckNextSpot(ChessmanCoordinate nextSpotCoordinate) {
        int numericIndex = nextSpotCoordinate.getNumericCoordinate();
        int literalIndex = nextSpotCoordinate.getLiteralCoordinate();
        return isValidIndex(numericIndex) && isValidIndex(literalIndex);
    }
}
