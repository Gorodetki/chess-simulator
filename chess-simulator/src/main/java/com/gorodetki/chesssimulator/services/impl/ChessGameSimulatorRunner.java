package com.gorodetki.chesssimulator.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!dev")
@Component
@RequiredArgsConstructor
public class ChessGameSimulatorRunner implements CommandLineRunner {
    private final ChessGameSimulatorProcessor chessGameSimulatorProcessor;

    @Override
    public void run(String... args) {
        chessGameSimulatorProcessor.startGameSimulation(args[0]);
    }
}
