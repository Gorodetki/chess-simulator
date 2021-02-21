package com.gorodetki.chesssimulator.services.impl;

import com.gorodetki.chesssimulator.models.move.impl.ChessMove;
import com.gorodetki.chesssimulator.models.pieces.PieceColor;
import com.gorodetki.chesssimulator.models.pieces.abstraction.Chessman;
import org.springframework.stereotype.Component;

@Component
public class MovePrinter {

    public void printMove(ChessMove move) {
        System.out.println("         " + move.getDisplayMove());
    }

    public void printError(Chessman piece, PieceColor moveOwner, ChessMove chessMove) {
        if (piece.getPieceColor().equals(moveOwner)) {
            System.out.printf("Chessman -> %s can not make this move -> %s\n\n", piece, chessMove.getDisplayMove());
        } else {
            System.out.println("You can not move opponent's pieces");
        }
    }
}
