package com.logicnow.hiring.model;

import java.util.ArrayList;
import java.util.List;

import com.logicnow.hiring.constants.MovementType;
import com.logicnow.hiring.constants.PieceColour;
import com.logicnow.hiring.constants.PieceType;

/**
 * Class representing a pawn.
 * 
 * @author IMJ84
 *
 */
public class Pawn extends ChessPiece {

    public Pawn(PieceColour pieceColour) {
        super(pieceColour, PieceType.PAWN);
    }

    /**
     * A pawn can only move directly forward one space, or capture diagonally
     * one space forward. A black piece will be moving to a lower row index
     * (down the board) whereas a white piece will move to a higher row index
     * (up the board).
     * 
     * This method checks the movement type and then generates a list of
     * available moves (ensuring that they are legal positions on the board).
     * 
     * NB: In reality, pawns can optionally move two spaces forward at the start
     * of a game, but for the purposes of this exercise this has not been
     * implemented.
     */
    @Override
    public List<Position> getValidMoves(MovementType movementType) {
        List<Position> moves = new ArrayList<Position>();

        int currentX = this.getXCoordinate();
        int currentY = this.getYCoordinate();
        PieceColour colour = this.getPieceColour();
        ChessBoard board = this.getChessBoard();

        int xIndexModifier = (colour == PieceColour.WHITE) ? 1 : -1;

        if (movementType == MovementType.MOVE) {
            int moveX = currentX + xIndexModifier;
            int moveY = currentY;

            // ensure it's a legal position on the board
            if (board.isLegalBoardPosition(moveX, moveY)) {
                // add move to list
                moves.add(new Position(moveX, moveY));
            }

        } else if (movementType == MovementType.CAPTURE) {
            // TODO: implement capture logic (outside test scope)
        }

        return moves;
    }

}
