package com.gorodetki.chesssimulator.models.pieces.abstraction;

import com.gorodetki.chesssimulator.models.board.impl.ChessBoard;
import com.gorodetki.chesssimulator.models.move.abstraction.Move;
import com.gorodetki.chesssimulator.models.move.impl.ChessMove;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.models.pieces.ChessmanType;
import com.gorodetki.chesssimulator.models.pieces.PieceColor;
import com.gorodetki.chesssimulator.services.abstraction.BoardStateHandlingService;
import com.gorodetki.chesssimulator.services.abstraction.ChessmanMoveService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.List;

@Data
@RequiredArgsConstructor
public abstract class Chessman implements Piece {
    private final ChessmanCoordinate pieceCoordinate;
    private final PieceColor pieceColor;
    private final ChessmanType chessmanType;
    private int moveCounter;
    @EqualsAndHashCode.Exclude
    private final ChessmanMoveService chessmanMoveService;
    @EqualsAndHashCode.Exclude
    private final BoardStateHandlingService boardStateHandlingService;
    @EqualsAndHashCode.Exclude
    private final ChessBoard board;


    @Override
    public boolean canMove(Move move) {
        val coordinateTo = ((ChessMove) move).getTo();
        val pieceAtDestination = boardStateHandlingService.getPieceAt(coordinateTo, board);
        if (pieceAtDestination != null && isOwnPiece(pieceAtDestination)) {
            return false;
        }
        return chessmanMoveService.validateMove(((ChessMove) move), this, board);
    }

    @Override
    public void updatePieceCoordinate(ChessmanCoordinate toSpot) {
        this.getPieceCoordinate().setLiteralCoordinate(toSpot.getLiteralCoordinate());
        this.getPieceCoordinate().setNumericCoordinate(toSpot.getNumericCoordinate());
    }

    @Override
    public String toString() {
        return this.pieceColor.equals(PieceColor.BLACK)
                ? this.chessmanType.getDisplayName().toLowerCase()
                : this.chessmanType.getDisplayName();
    }

    public boolean isOwnPiece(Piece piece) {
        return this.pieceColor.equals(piece.getPieceColor());
    }

    public List<ChessmanCoordinate> getAllAttackingCoordinates() {
        return chessmanMoveService.getAllPossibleAttackingDestinations(this, board);
    }
}
