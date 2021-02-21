package com.gorodetki.chesssimulator.services.impl;

import com.gorodetki.chesssimulator.models.board.abstraction.GameBoard;
import org.springframework.stereotype.Component;

@Component
public class BoardPrinter {

    public void printBoard(GameBoard board) {
        System.out.println(board);
    }
}
