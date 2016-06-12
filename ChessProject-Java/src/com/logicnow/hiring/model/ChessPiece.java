package com.logicnow.hiring.model;

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
     * A method that checks whether a proposed move is legal and then performs the move. This should be called from concrete implementations of
     * the move() method.
     * 
     * The "valid" coordinates represent a valid position that the piece can move to based upon the piece's movement rules.
     * The "new" coordinates represent that position that the piece is intended to be moved to.
     * 
     * @param validX
     * @param validY
     * @param newX
     * @param newY
     * @return A boolean indicating if the move was successful or not.
     */
    protected boolean checkMoveIsLegalAndPerform(int validX, int validY, int newX, int newY) {
    	// ensure that the new position is valid on the board and matches the proposed position
		if (chessBoard.isLegalBoardPosition(validX, validY) && (validX == newX) && (validY == newY)) {
			// update piece coordinates
			this.setXCoordinate(newX);
			this.setYCoordinate(newY);
			
			return true;
		}
		
		return false;
    }

    /**
     * Abstract movement method. Each type of chess piece will have its own movement mechanism, requiring its own movement method definition.
     * 
     * @param movementType
     * @param newX
     * @param newY
     * @return A boolean indicating if the move was successful or not.
     */
    public abstract boolean move(MovementType movementType, int newX, int newY);
}
