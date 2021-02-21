package com.gorodetki.chesssimulator.models.board.impl;

import com.gorodetki.chesssimulator.models.board.Spot;
import com.gorodetki.chesssimulator.models.board.abstraction.GameBoard;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Data
public class ChessBoard implements GameBoard {
    private static final String LITERALS = "   a b c d e f g h\n";
    private static final String DELIMITER = "   ----------------\n";
    private Spot[][] spots;

    public Spot getSpotAt(ChessmanCoordinate coordinate) {
        return getSpots()[coordinate.getNumericCoordinate()][coordinate.getLiteralCoordinate()];
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder(LITERALS);
        board.append(DELIMITER);
        int numericCounter = 1;
        for (Spot[] spots : getSpots()) {
            board.append(numericCounter).append("|").append(" ");
            board.append(Arrays.toString(spots)
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", ""));
            board.append(" ").append("|").append(numericCounter).append("\n");
            numericCounter++;
        }
        board.append(DELIMITER);
        board.append(LITERALS);
        return board.toString();
    }


}
