package com.gorodetki.chesssimulator.services.impl;

import com.gorodetki.chesssimulator.exceptions.MalformedMoveException;
import com.gorodetki.chesssimulator.models.move.impl.ChessMove;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.services.abstraction.MoveParsingService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChessMoveParsingService implements MoveParsingService {
    private final MovePrinter movePrinter;
    private static final String ERROR_MESSAGE = "Malformed move found in file: %s";

    @Override
    public ChessMove parseMove(String move) {
        validateMoveFormat(move);
        val chessMove = ChessMove.builder()
                .from(ChessmanCoordinate.at(move.charAt(1) - 49, move.charAt(0) - 97))
                .to(ChessmanCoordinate.at(move.charAt(3) - 49, move.charAt(2) - 97))
                .displayMove(move)
                .build();
        movePrinter.printMove(chessMove);
        return chessMove;
    }


    private void validateMoveFormat(String move) {
        if (move.length() < 4) {
            throw new MalformedMoveException(String.format(ERROR_MESSAGE, move));
        }
        if (isInvalidLiteralCoordinate(move.charAt(0)) || isInvalidLiteralCoordinate(move.charAt(2))) {
            throw new MalformedMoveException(String.format(ERROR_MESSAGE, move));
        }
        if (isInvalidNumericCoordinate(move.charAt(1)) || isInvalidNumericCoordinate(move.charAt(3))) {
            throw new MalformedMoveException(String.format(ERROR_MESSAGE, move));
        }
    }

    private boolean isInvalidLiteralCoordinate(char literalCoordinate) {
        return literalCoordinate < 97 || literalCoordinate > 104;
    }

    private boolean isInvalidNumericCoordinate(char numericCoordinate) {
        return numericCoordinate < 48 || numericCoordinate > 56;
    }
}
