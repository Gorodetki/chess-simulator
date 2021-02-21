package com.gorodetki.chesssimulator.services.impl;

import com.gorodetki.chesssimulator.models.board.abstraction.GameBoard;
import com.gorodetki.chesssimulator.models.board.impl.ChessBoard;
import com.gorodetki.chesssimulator.models.move.impl.ChessMove;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.models.pieces.PieceColor;
import com.gorodetki.chesssimulator.models.pieces.abstraction.Chessman;
import com.gorodetki.chesssimulator.services.abstraction.BoardStateHandlingService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.val;
import org.springframework.stereotype.Service;

import static com.gorodetki.chesssimulator.models.pieces.PieceColor.BLACK;
import static com.gorodetki.chesssimulator.models.pieces.PieceColor.WHITE;

@Service
@RequiredArgsConstructor
public class ChessBoardStateHandlingService implements BoardStateHandlingService {
    @Setter
    private PieceColor moveOwner = WHITE;
    private final CheckStateVerifier checkStateVerifier;
    private final BoardPrinter boardPrinter;
    private final ChessBoard chessBoard;
    private final MovePrinter movePrinter;

    @Override
    public void handleMove(ChessMove chessMove) {
        val piece = getPieceAt(chessMove.getFrom(), chessBoard);
        val pieceColor = piece.getPieceColor();
        val canMove = pieceColor.equals(moveOwner) && piece.canMove(chessMove);

        if (canMove) {
            move(piece, chessMove.getFrom(), chessMove.getTo(), chessBoard)
                    .andCheckIfTheKingIsUnderAttack()
                    .thenUpdateMoveOwner()
                    .andPrintUpdatedBoard();
        } else {
            movePrinter.printError(piece, moveOwner, chessMove);
        }
    }

    @Override
    public Chessman getPieceAt(ChessmanCoordinate coordinate, GameBoard gameBoard) {
        int literal = coordinate.getLiteralCoordinate();
        int numeric = coordinate.getNumericCoordinate();
        return ((ChessBoard) gameBoard).getSpots()[numeric][literal].getPiece();
    }

    private ChessBoardStateHandlingService move(Chessman chessman, ChessmanCoordinate fromSpot,
                                                ChessmanCoordinate toSpot, ChessBoard board) {
        val spotTo = board.getSpotAt(toSpot);
        val spotFrom = board.getSpotAt(fromSpot);
        spotTo.setPiece(chessman);
        chessman.updatePieceCoordinate(spotTo.getCoordinate());
        spotFrom.setPiece(null);
        chessman.setMoveCounter(chessman.getMoveCounter() + 1);
        return this;
    }

    private ChessBoardStateHandlingService thenUpdateMoveOwner() {
        if (moveOwner.equals(WHITE)) {
            moveOwner = BLACK;
        } else {
            moveOwner = WHITE;
        }
        return this;
    }

    private ChessBoardStateHandlingService andCheckIfTheKingIsUnderAttack() {
        checkStateVerifier.isKingUnderAttack(chessBoard, moveOwner);
        return this;
    }

    private void andPrintUpdatedBoard() {
        boardPrinter.printBoard(chessBoard);
    }

}
