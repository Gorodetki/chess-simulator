package com.gorodetki.chesssimulator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ChessSimulatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChessSimulatorApplication.class, args);
    }
}
