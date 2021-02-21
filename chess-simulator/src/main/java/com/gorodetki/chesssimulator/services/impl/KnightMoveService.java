package com.gorodetki.chesssimulator.services.impl;

import com.gorodetki.chesssimulator.models.board.abstraction.GameBoard;
import com.gorodetki.chesssimulator.models.board.impl.ChessBoard;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.models.pieces.PieceColor;
import com.gorodetki.chesssimulator.models.pieces.abstraction.Chessman;
import com.gorodetki.chesssimulator.services.abstraction.BoardStateHandlingService;
import com.gorodetki.chesssimulator.services.abstraction.ChessmanMoveService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KnightMoveService implements ChessmanMoveService {
    private final BoardStateHandlingService boardStateHandlingService;

    @Override
    public List<ChessmanCoordinate> getAllPossibleDestinationOfPiece(Chessman chessman, ChessBoard board) {
        List<ChessmanCoordinate> possibleDestinations = new ArrayList<>();
        val currentCoordinate = chessman.getPieceCoordinate();
        val pieceColor = chessman.getPieceColor();

        collectAllDestinationsFromTopLeft(currentCoordinate, possibleDestinations, board, pieceColor);
        collectAllDestinationsFromBottomLeft(currentCoordinate, possibleDestinations, board, pieceColor);
        collectAllDestinationsFromTopRight(currentCoordinate, possibleDestinations, board, pieceColor);
        collectAllDestinationsFromBottomRight(currentCoordinate, possibleDestinations, board, pieceColor);
        return possibleDestinations;
    }

    private void collectAllDestinationsFromBottomRight(ChessmanCoordinate currentCoordinate, List<ChessmanCoordinate> possibleDestinations, GameBoard board, PieceColor pieceColor) {
        val corner = Arrays.asList(
                ChessmanCoordinate.at(currentCoordinate.getNumericCoordinate() + 2, currentCoordinate.getLiteralCoordinate() + 1),
                ChessmanCoordinate.at(currentCoordinate.getNumericCoordinate() + 1, currentCoordinate.getLiteralCoordinate() + 2));
        collectDestinationsForCorner(corner, possibleDestinations, pieceColor, board);
    }

    private void collectAllDestinationsFromTopRight(ChessmanCoordinate currentCoordinate, List<ChessmanCoordinate> possibleDestinations, GameBoard board, PieceColor pieceColor) {
        val corner = Arrays.asList(
                ChessmanCoordinate.at(currentCoordinate.getNumericCoordinate() - 2, currentCoordinate.getLiteralCoordinate() + 1),
                ChessmanCoordinate.at(currentCoordinate.getNumericCoordinate() - 1, currentCoordinate.getLiteralCoordinate() + 2));
        collectDestinationsForCorner(corner, possibleDestinations, pieceColor, board);
    }

    private void collectAllDestinationsFromBottomLeft(ChessmanCoordinate currentCoordinate, List<ChessmanCoordinate> possibleDestinations, GameBoard board, PieceColor pieceColor) {
        val corner = Arrays.asList(
                ChessmanCoordinate.at(currentCoordinate.getNumericCoordinate() + 2, currentCoordinate.getLiteralCoordinate() - 1),
                ChessmanCoordinate.at(currentCoordinate.getNumericCoordinate() + 1, currentCoordinate.getLiteralCoordinate() - 2));
        collectDestinationsForCorner(corner, possibleDestinations, pieceColor, board);
    }

    private void collectAllDestinationsFromTopLeft(ChessmanCoordinate currentCoordinate, List<ChessmanCoordinate> possibleDestinations, GameBoard board, PieceColor pieceColor) {
        val corner = Arrays.asList(
                ChessmanCoordinate.at(currentCoordinate.getNumericCoordinate() - 2, currentCoordinate.getLiteralCoordinate() - 1),
                ChessmanCoordinate.at(currentCoordinate.getNumericCoordinate() - 1, currentCoordinate.getLiteralCoordinate() - 2));
        collectDestinationsForCorner(corner, possibleDestinations, pieceColor, board);
    }

    private void collectDestinationsForCorner(List<ChessmanCoordinate> corner, List<ChessmanCoordinate> possibleDestinations, PieceColor pieceColor, GameBoard board) {
        for (ChessmanCoordinate chessmanCoordinate : corner) {
            if (isValidIndex(chessmanCoordinate.getNumericCoordinate()) && isValidIndex(chessmanCoordinate.getLiteralCoordinate())) {
                val piece = boardStateHandlingService.getPieceAt(chessmanCoordinate, board);
                if (piece == null || !piece.getPieceColor().equals(pieceColor)) {
                    possibleDestinations.add(chessmanCoordinate);
                }
            }
        }
    }

}
