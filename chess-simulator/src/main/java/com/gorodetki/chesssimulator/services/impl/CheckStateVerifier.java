package com.gorodetki.chesssimulator.services.impl;

import com.gorodetki.chesssimulator.models.board.Spot;
import com.gorodetki.chesssimulator.models.board.abstraction.GameBoard;
import com.gorodetki.chesssimulator.models.board.impl.ChessBoard;
import com.gorodetki.chesssimulator.models.pieces.ChessmanCoordinate;
import com.gorodetki.chesssimulator.models.pieces.ChessmanType;
import com.gorodetki.chesssimulator.models.pieces.PieceColor;
import com.gorodetki.chesssimulator.models.pieces.abstraction.Piece;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckStateVerifier {


    public void isKingUnderAttack(ChessBoard gameBoard, PieceColor moveOwner) {
        val oppositeKingCoordinate = getOppositeKingCoordinate(gameBoard, moveOwner);
        val allPossibleDestinations = Arrays.stream(gameBoard.getSpots())
                .flatMap(Arrays::stream)
                .map(Spot::getPiece)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getPieceColor().equals(moveOwner))
                .flatMap(piece -> piece.getAllAttackingCoordinates().stream())
                .collect(Collectors.toSet());
        if (allPossibleDestinations.contains(oppositeKingCoordinate)) {
            System.out.println("        Check!");
        }
    }

    private ChessmanCoordinate getOppositeKingCoordinate(GameBoard gameBoard, PieceColor moveOwner) {
        return Arrays.stream(((Spot[][]) gameBoard.getSpots()))
                .flatMap(Arrays::stream)
                .map(Spot::getPiece)
                .filter(Objects::nonNull)
                .filter(chessman -> !chessman.getPieceColor().equals(moveOwner))
                .filter(piece -> piece.getChessmanType().equals(ChessmanType.K))
                .map(Piece::getPieceCoordinate)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("The game has been already terminated"));
    }

}
