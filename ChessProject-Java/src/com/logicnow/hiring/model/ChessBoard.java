package com.logicnow.hiring.model;

import java.util.HashMap;
import java.util.Map;

import com.logicnow.hiring.constants.PieceColour;
import com.logicnow.hiring.constants.PieceType;

public class ChessBoard {

    // These have been renamed to avoid x/y confusion - in this exercise X is
    // the row, Y the column
    public static final int MAX_BOARD_COLUMNS = 8;
    public static final int MAX_BOARD_ROWS = 8;

    // Array representing the board and the pieces on it
    private ChessPiece[][] pieces;
    
    // Keeps track of how many pieces of each type there are on the board, of each colour
    private Map<PieceColour, Map<PieceType, Integer>> pieceCounts;

    public ChessBoard() {
        pieces = new ChessPiece[MAX_BOARD_ROWS][MAX_BOARD_COLUMNS];

        pieceCounts = new HashMap<PieceColour, Map<PieceType, Integer>>();

        // initialise all of the piece counts to zero
        for (PieceColour colour : PieceColour.values()) {
            Map<PieceType, Integer> counts = new HashMap<PieceType, Integer>();

            for (PieceType type : PieceType.values()) {
                counts.put(type, 0);
            }

            pieceCounts.put(colour, counts);
        }
    }

    public ChessPiece[][] getPieces() {
        return pieces;
    }

    /**
     * Add a piece to the board. First needs to check that the position is
     * legal, there is no piece already occupying the space, and the limits of
     * the given piece type per colour have not been reached.
     * 
     * @param piece
     * @param xCoordinate
     * @param yCoordinate
     * @param pieceColour
     */
    public void add(ChessPiece piece, int xCoordinate, int yCoordinate, PieceColour pieceColour) {

        PieceType pieceType = piece.getPieceType();

        if (withinPieceLimits(pieceType, pieceColour) && isLegalBoardPosition(xCoordinate, yCoordinate) && (pieces[xCoordinate][yCoordinate] == null)) {
            pieces[xCoordinate][yCoordinate] = piece;

            // update coordinates of the piece and set the board
            piece.setXCoordinate(xCoordinate);
            piece.setYCoordinate(yCoordinate);
            piece.setChessBoard(this);

            // update existing piece counts
            int existingCount = pieceCounts.get(pieceColour).get(pieceType);
            pieceCounts.get(pieceColour).put(pieceType, existingCount + 1);
        } else {
            // if the piece cannot be placed, set the coordinates to -1, -1 (i.e. not on the board)
            piece.setXCoordinate(-1);
            piece.setYCoordinate(-1);
        }
    }

    /**
     * A method to check if a given set of coordinates is a valid position on
     * the board, i.e. it is within the width and height and the coordinates are
     * non-negative.
     * 
     * @param xCoordinate
     * @param yCoordinate
     * @return
     */
    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if ((xCoordinate >= 0) && (yCoordinate >= 0) && (xCoordinate < MAX_BOARD_ROWS) && (yCoordinate < MAX_BOARD_COLUMNS)) {
            return true;
        }

        return false;
    }

    /**
     * A method to update the position of a piece on the board. Sets the given
     * piece in the correct position of the pieces array and nulls the original
     * position.
     * 
     * This method allows the piece objects on the board to issue updates to their position.
     * 
     * @param piece
     * @param originalXCoordinate
     * @param originalYCoordinate
     * @param newXCoordinate
     * @param newYCoordinate
     */
    public void updatePiecePosition(ChessPiece piece, int originalXCoordinate, int originalYCoordinate, int newXCoordinate, int newYCoordinate) {
        pieces[newXCoordinate][newYCoordinate] = piece;
        pieces[originalXCoordinate][originalYCoordinate] = null;
    }

    /**
     * A method to check if the board already contains the maximum number of the
     * given piece in the given colour.
     * 
     * @param pieceType
     * @param pieceColour
     * @return
     */
    private boolean withinPieceLimits(PieceType pieceType, PieceColour pieceColour) {
        int numMatchingPieces = pieceCounts.get(pieceColour).get(pieceType);

        if (numMatchingPieces < pieceType.getMaxNumber()) {
            return true;
        }

        return false;
    }
}
