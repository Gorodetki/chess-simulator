package com.gorodetki.chesssimulator.services.abstraction;

import com.gorodetki.chesssimulator.models.board.impl.ChessBoard;
import com.gorodetki.chesssimulator.models.move.impl.ChessMove;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.models.pieces.abstraction.Chessman;

import java.util.List;

public interface ChessmanMoveService {

    List<ChessmanCoordinate> getAllPossibleDestinationOfPiece(Chessman chessman, ChessBoard board);

    default List<ChessmanCoordinate> getAllPossibleAttackingDestinations(Chessman chessman, ChessBoard board) {
        return getAllPossibleDestinationOfPiece(chessman, board);
    }

    default boolean validateMove(ChessMove move, Chessman chessman, ChessBoard board) {
        return getAllPossibleDestinationOfPiece(chessman, board).contains(move.getTo());
    }

    default boolean isValidIndex(int index) {
        return index >= 0 && index <= 7;
    }

}
