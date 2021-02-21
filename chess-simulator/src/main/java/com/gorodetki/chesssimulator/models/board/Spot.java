package com.gorodetki.chesssimulator.models.board;

import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.models.pieces.abstraction.Chessman;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Spot {
    private Chessman piece;
    @NonNull
    private final ChessmanCoordinate coordinate;

    @Override
    public String toString(){
        return piece == null ? "." : piece.toString();
    }
}
