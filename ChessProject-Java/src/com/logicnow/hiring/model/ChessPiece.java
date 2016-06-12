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
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }

    /**
     * Abstract movement method. Each type of chess piece will have its own movement mechanism, requiring its own movement method definition.
     * 
     * @param movementType
     * @param newX
     * @param newY
     */
    public abstract void move(MovementType movementType, int newX, int newY);
}
