package com.gorodetki.chesssimulator.models.pieces;

import com.gorodetki.chesssimulator.models.pieces.abstraction.Piece;
import com.gorodetki.chesssimulator.models.pieces.impl.*;

public enum ChessmanType {
    K(King.class, "K"),
    Q(Queen.class, "Q"),
    B(Bishop.class, "B"),
    KN(Knight.class, "N"),
    R(Rook.class, "R"),
    P(Pawn.class, "P");

    private final Class<? extends Piece> pieceClass;
    private final String displayName;

    ChessmanType(Class<? extends Piece> piece, String displayName) {
        this.pieceClass = piece;
        this.displayName = displayName;
    }

    public Class<? extends Piece> getPieceClass(){
        return this.pieceClass;
    }

    public String getDisplayName(){
        return this.displayName;
    }
}
