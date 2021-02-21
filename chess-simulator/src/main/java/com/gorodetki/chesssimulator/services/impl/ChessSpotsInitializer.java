package com.gorodetki.chesssimulator.services.impl;

import com.gorodetki.chesssimulator.models.board.Spot;
import com.gorodetki.chesssimulator.models.board.impl.ChessBoard;
import com.gorodetki.chesssimulator.models.pieces.ChessmanType;
import com.gorodetki.chesssimulator.models.pieces.PieceColor;
import com.gorodetki.chesssimulator.models.pieces.abstraction.Chessman;
import com.gorodetki.chesssimulator.services.abstraction.BoardStateHandlingService;
import com.gorodetki.chesssimulator.services.abstraction.ChessmanMoveService;
import com.gorodetki.chesssimulator.services.abstraction.SpotsInitializer;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate.at;
import static com.gorodetki.chesssimulator.models.pieces.ChessmanType.*;
import static com.gorodetki.chesssimulator.models.pieces.PieceColor.BLACK;
import static com.gorodetki.chesssimulator.models.pieces.PieceColor.WHITE;

@Component
@RequiredArgsConstructor
public class ChessSpotsInitializer implements SpotsInitializer {

    private final BishopMoveService bishopChessPieceMoveService;
    private final KnightMoveService knightChessPieceMoveService;
    private final RookMoveService rookChessPieceMoveService;
    private final QueenMoveService queenMoveService;
    private final KingMoveService kingMoveService;
    private final PawnMoveService pawnMoveService;
    private final BoardStateHandlingService boardStateHandlingService;
    private final PieceInitializer pieceInitializer;
    private final ChessBoard chessBoard;
    private final BoardPrinter boardPrinter;

    @PostConstruct
    @Override
    public void resetBoard() {
        Spot[][] spots = new Spot[8][8];
        populateSpotAt(spots, 2, 0, B, WHITE, bishopChessPieceMoveService);
        populateSpotAt(spots, 5, 0, B, WHITE, bishopChessPieceMoveService);
        populateSpotAt(spots, 2, 7, B, BLACK, bishopChessPieceMoveService);
        populateSpotAt(spots, 5, 7, B, BLACK, bishopChessPieceMoveService);
        populateSpotAt(spots, 0, 1, P, WHITE, pawnMoveService);
        populateSpotAt(spots, 1, 1, P, WHITE, pawnMoveService);
        populateSpotAt(spots, 2, 1, P, WHITE, pawnMoveService);
        populateSpotAt(spots, 3, 1, P, WHITE, pawnMoveService);
        populateSpotAt(spots, 4, 1, P, WHITE, pawnMoveService);
        populateSpotAt(spots, 5, 1, P, WHITE, pawnMoveService);
        populateSpotAt(spots, 6, 1, P, WHITE, pawnMoveService);
        populateSpotAt(spots, 7, 1, P, WHITE, pawnMoveService);
        populateSpotAt(spots, 0, 6, P, BLACK, pawnMoveService);
        populateSpotAt(spots, 1, 6, P, BLACK, pawnMoveService);
        populateSpotAt(spots, 2, 6, P, BLACK, pawnMoveService);
        populateSpotAt(spots, 3, 6, P, BLACK, pawnMoveService);
        populateSpotAt(spots, 4, 6, P, BLACK, pawnMoveService);
        populateSpotAt(spots, 5, 6, P, BLACK, pawnMoveService);
        populateSpotAt(spots, 6, 6, P, BLACK, pawnMoveService);
        populateSpotAt(spots, 7, 6, P, BLACK, pawnMoveService);
        populateSpotAt(spots, 1, 0, KN, WHITE, knightChessPieceMoveService);
        populateSpotAt(spots, 6, 0, KN, WHITE, knightChessPieceMoveService);
        populateSpotAt(spots, 1, 7, KN, BLACK, knightChessPieceMoveService);
        populateSpotAt(spots, 6, 7, KN, BLACK, knightChessPieceMoveService);
        populateSpotAt(spots, 0, 0, R, WHITE, rookChessPieceMoveService);
        populateSpotAt(spots, 7, 0, R, WHITE, rookChessPieceMoveService);
        populateSpotAt(spots, 0, 7, R, BLACK, rookChessPieceMoveService);
        populateSpotAt(spots, 7, 7, R, BLACK, rookChessPieceMoveService);
        populateSpotAt(spots, 3, 0, Q, WHITE, queenMoveService);
        populateSpotAt(spots, 4, 0, K, WHITE, kingMoveService);
        populateSpotAt(spots, 3, 7, Q, BLACK, queenMoveService);
        populateSpotAt(spots, 4, 7, K, BLACK, kingMoveService);
        populateEmptySpots(spots);

        chessBoard.setSpots(spots);
        boardPrinter.printBoard(chessBoard);
    }

    private void populateEmptySpots(Spot[][] spots) {
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                spots[i][j] = emptySpot(i, j);
            }
        }
    }

    private Spot emptySpot(int i, int j) {
        return Spot.builder().coordinate(at(i, j)).build();
    }

    private void populateSpotAt(Spot[][] spots, int literalCoordinate, int numericCoordinate,
                                ChessmanType chessmanType, PieceColor pieceColor, ChessmanMoveService chessmanMoveService) {

        val coordinate = at(numericCoordinate, literalCoordinate);
        spots[numericCoordinate][literalCoordinate] =
                Spot.builder()
                        .coordinate(coordinate)
                        .piece((Chessman) pieceInitializer.initPieceForType(coordinate, pieceColor, chessmanType,
                                chessmanMoveService, boardStateHandlingService, chessBoard))
                        .build();
    }
}
