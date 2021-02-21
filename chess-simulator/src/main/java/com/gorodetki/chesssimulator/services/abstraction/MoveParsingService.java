package com.gorodetki.chesssimulator.services.abstraction;

import com.gorodetki.chesssimulator.models.move.impl.ChessMove;

public interface MoveParsingService {
    ChessMove parseMove(String move);
}
