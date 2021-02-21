package com.gorodetki.chesssimulator.services.impl;

import com.gorodetki.chesssimulator.models.board.abstraction.GameBoard;
import com.gorodetki.chesssimulator.services.abstraction.FileLinesReaderService;
import com.gorodetki.chesssimulator.services.abstraction.GameSimulatorProcessor;
import com.gorodetki.chesssimulator.services.abstraction.MoveProcessorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChessGameSimulatorProcessor implements GameSimulatorProcessor {

    private final FileLinesReaderService movesFileReaderService;
    private final MoveProcessorService moveProcessorService;
    @Getter
    private final GameBoard chessBoard;

    @Override
    public void startGameSimulation(String filePath) {
        val moves = movesFileReaderService.readAllLines(filePath);
        moves.forEach(moveProcessorService::processMove);
    }
}
