package com.logicnow.hiring.constants;

/**
 * Enum representing the type of a chess piece and the maximum number allowed
 * per colour.
 * 
 * @author IMJ84
 *
 */
public enum PieceType {

    PAWN(8), KNIGHT(2), BISHOP(2), ROOK(2), KING(1), QUEEN(1);

    private int maxNumber;

    PieceType(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

}
