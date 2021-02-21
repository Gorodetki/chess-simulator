package com.gorodetki.chesssimulator.models.pieces;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChessmanCoordinate {
    private int literalCoordinate;
    private int numericCoordinate;

    public static ChessmanCoordinate at(int numericCoordinate, int literalCoordinate){
        return new ChessmanCoordinate(literalCoordinate, numericCoordinate);
    }
}
