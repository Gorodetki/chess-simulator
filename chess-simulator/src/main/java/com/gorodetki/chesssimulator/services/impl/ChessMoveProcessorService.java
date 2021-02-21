package com.gorodetki.chesssimulator.services.impl;

import com.gorodetki.chesssimulator.services.abstraction.BoardStateHandlingService;
import com.gorodetki.chesssimulator.services.abstraction.MoveParsingService;
import com.gorodetki.chesssimulator.services.abstraction.MoveProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChessMoveProcessorService implements MoveProcessorService {
    private final MoveParsingService moveParsingService;
    private final BoardStateHandlingService boardStateHandlingService;

    @Override
    public void processMove(String move) {
        boardStateHandlingService.handleMove(moveParsingService.parseMove(move));
    }
}
