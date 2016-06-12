package com.logicnow.hiring.model;

import java.util.HashMap;
import java.util.Map;

import com.logicnow.hiring.constants.PieceColor;
import com.logicnow.hiring.constants.PieceType;

public class ChessBoard {

    public static final int MAX_BOARD_WIDTH = 8;
    public static final int MAX_BOARD_HEIGHT = 8;

    private ChessPiece[][] pieces;
    private Map<PieceColor, Map<PieceType, Integer>> pieceCounts;

    public ChessBoard() {
        pieces = new ChessPiece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

        pieceCounts = new HashMap<PieceColor, Map<PieceType, Integer>>();
        
        // initialise all of the piece counts to zero
        for (PieceColor color: PieceColor.values()) {
        	Map<PieceType, Integer> counts = new HashMap<PieceType, Integer>();
        	
        	for (PieceType type: PieceType.values()) {
        		counts.put(type, 0);
        	}
        	
        	pieceCounts.put(color, counts);
        }
    }
    
    public ChessPiece[][] getPieces() {
    	return pieces;
    }

    /**
     * Add a piece to the board. First needs to check that the position is legal, there is no piece already occupying the space,
     * and the limits of the given piece type per colour have not been reached.
     * 
     * @param piece
     * @param xCoordinate
     * @param yCoordinate
     * @param pieceColor
     */
    public void add(ChessPiece piece, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
    	
    	PieceType pieceType = piece.getPieceType();
    	
    	if (withinPieceLimits(piece.getPieceType(), pieceColor) && isLegalBoardPosition(xCoordinate, yCoordinate) 
    			&& (pieces[xCoordinate][yCoordinate] == null)) {
    		pieces[xCoordinate][yCoordinate] = piece;
    		
    		// update coordinates of the piece and set the board
    		piece.setXCoordinate(xCoordinate);
    		piece.setYCoordinate(yCoordinate);
    		piece.setChessBoard(this);
    		
    		// update existing piece counts
    		int existingCount = pieceCounts.get(pieceColor).get(pieceType);
    		pieceCounts.get(pieceColor).put(pieceType, existingCount + 1);
        } else {
        	// if the piece cannot be placed, set the coordinates to -1, -1 (i.e. not on the board)
        	piece.setXCoordinate(-1);
    		piece.setYCoordinate(-1);
        }
    }

    /**
     * A method to check if a given set of coordinates is a valid position on the board, i.e. it is within the width and height
     * and the coordinates are non-negative.
     * 
     * @param xCoordinate
     * @param yCoordinate
     * @return
     */
    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if ((xCoordinate >= 0) && (yCoordinate >= 0) && (xCoordinate <= MAX_BOARD_WIDTH) && (yCoordinate <= MAX_BOARD_HEIGHT)) {
        	return true;
        }
        
        return false;
    }
    
    /**
     * A method to update the position of a piece on the board. Sets the given piece in the correct position of the pieces array and
     * nulls the original position.
     * 
     * This method should be called from a controller class method upon a successful call to a piece's move method.
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
     * A method to check if the board already contains the maximum number of the given piece in the given colour.
     * 
     * @param pieceType
     * @param pieceColor
     * @return
     */
    private boolean withinPieceLimits(PieceType pieceType, PieceColor pieceColor) {
    	int numMatchingPieces = pieceCounts.get(pieceColor).get(pieceType);
    	
    	if (numMatchingPieces < pieceType.getMaxNumber()) {
    		return true;
    	}
    	
    	return false;
    }
}
