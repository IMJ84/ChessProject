package com.logicnow.hiring.model;

import java.util.List;

import com.logicnow.hiring.constants.MovementType;
import com.logicnow.hiring.constants.PieceColour;
import com.logicnow.hiring.constants.PieceType;

/**
 * A base class to represent a generic chess piece.
 * 
 * @author IMJ84
 *
 */
public abstract class ChessPiece {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColour pieceColour;
    private PieceType pieceType;

    public ChessPiece(PieceColour pieceColour, PieceType pieceType) {
        this.pieceColour = pieceColour;
        this.pieceType = pieceType;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColour getPieceColour() {
        return this.pieceColour;
    }

    public void setPieceColour(PieceColour value) {
        pieceColour = value;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    @Override
    public String toString() {
        String eol = System.lineSeparator();
        return String.format("Current X: %d%sCurrent Y: %d%sPiece Colour: %s%sPiece Type: %s", xCoordinate, eol, yCoordinate, eol, pieceColour, eol, pieceType);
    }

    /**
     * Main movement method. Calls the concrete implementation of the
     * getValidMoves() method and then checks if the proposed move coordinates
     * match any of those moves. If so, updates the piece coordinates and
     * returns true to indicate a successful move.
     * 
     * @param movementType
     * @param newX
     * @param newY
     * @return A boolean indicating if the move was successful or not.
     */
    public boolean move(MovementType movementType, int newX, int newY) {
        List<Position> availableMoves = getValidMoves(movementType);

        // loop through the available moves and check if any match the proposed move
        for (Position potentialMove : availableMoves) {
            if (newX == potentialMove.getXCoordinate() && newY == potentialMove.getYCoordinate()) {
                int originalX = getXCoordinate();
                int originalY = getYCoordinate();

                // Capture moves will have to issue updates to the board for enemy pieces
                if (movementType == MovementType.CAPTURE) {
                    // TODO: out of scope for this exercise
                }

                // update piece coordinates
                setXCoordinate(newX);
                setYCoordinate(newY);

                // update the piece position on the board
                chessBoard.updatePiecePosition(this, originalX, originalY, newX, newY);

                return true;
            }
        }

        // if no matching moves, return false
        return false;
    }

    /**
     * A method that returns a list of Position objects, representing all
     * available moves that the piece can make for the given movement type. Each
     * piece type will have its own rules determining the set of potential
     * moves.
     * 
     * @param movementType
     * @return
     */
    public abstract List<Position> getValidMoves(MovementType movementType);
}
