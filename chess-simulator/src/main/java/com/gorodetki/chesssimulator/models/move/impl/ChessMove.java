package com.gorodetki.chesssimulator.models.move.impl;

import com.gorodetki.chesssimulator.models.move.abstraction.Move;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class ChessMove implements Move {
    private final ChessmanCoordinate from;
    private final ChessmanCoordinate to;
    private final String displayMove;
}
