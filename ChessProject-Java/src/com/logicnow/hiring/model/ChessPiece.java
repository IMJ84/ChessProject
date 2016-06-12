package com.logicnow.hiring.model;

import java.util.List;

import com.logicnow.hiring.constants.MovementType;
import com.logicnow.hiring.constants.PieceColor;
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
    private PieceColor pieceColor;
    private PieceType pieceType;

    public ChessPiece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }
    
    public ChessPiece(PieceColor pieceColor, PieceType pieceType) {
        this.pieceColor = pieceColor;
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

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    public void setPieceColor(PieceColor value) {
        pieceColor = value;
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
        return String.format("Current X: %d%sCurrent Y: %d%sPiece Color: %s%sPiece Type: %s", xCoordinate, eol, yCoordinate, eol, pieceColor, eol, pieceType);
    }

    /**
     * Main movement method. Calls the concrete implementation of the getValidMoves() method and then checks if
     * the proposed move coordinates match any of those moves. If so, updates the piece coordinates and returns
     * true to indicate a successful move.
     * 
     * @param movementType
     * @param newX
     * @param newY
     * @return A boolean indicating if the move was successful or not.
     */
    public boolean move(MovementType movementType, int newX, int newY) {
    	List<Position> availableMoves = getValidMoves(movementType);
    	
    	// loop through the available moves and check if any match the proposed move
    	for (Position potentialMove: availableMoves) {
    		if (newX == potentialMove.getXCoordinate() && newY == potentialMove.getYCoordinate()) {
    			// update piece coordinates
    			this.setXCoordinate(newX);
    			this.setYCoordinate(newY);
    			
    			return true;
    		}
    	}
    	
    	// if no matching moves, return false
    	return false;
    }
    
    /**
     * A method that returns a list of Position objects, representing all available moves that the piece can make. Each piece type will have
     * its own rules determining the set of potential moves.
     * 
     * @param movementType
     * @return
     */
    public abstract List<Position> getValidMoves(MovementType movementType);
}
