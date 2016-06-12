package com.logicnow.hiring.model;

import com.logicnow.hiring.constants.MovementType;
import com.logicnow.hiring.constants.PieceColor;
import com.logicnow.hiring.constants.PieceType;

/**
 * Class representing a pawn.
 * 
 * @author IMJ84
 *
 */
public class Pawn extends ChessPiece {

	public Pawn(PieceColor pieceColor) {
		super(pieceColor, PieceType.PAWN);
	}

	/**
	 * A pawn can only move directly forward one space, or capture diagonally one space forward.
	 * A black piece will be moving to a lower row index (down the board) whereas a white piece will
	 * move to a higher row index (up the board).
	 * 
	 * This method checks the movement type and then determines if the passed coordinates would be valid for
	 * that move. If they are, the piece's coordinates are updated, otherwise they remain unchanged.
	 */
	@Override
	public void move(MovementType movementType, int newX, int newY) {
		int currentX = this.getXCoordinate();
		int currentY = this.getYCoordinate();
		PieceColor color = this.getPieceColor();
		ChessBoard board = this.getChessBoard();
		
		int xIndexModifier = (color == PieceColor.WHITE) ? 1 : -1;
		
		if (movementType == MovementType.MOVE) {
			int validX = currentX + xIndexModifier;
			int validY = currentY;
			
			// ensure that the new position is valid on the board and matches the proposed position
			if (board.isLegalBoardPosition(validX, validY) && (validX == newX) && (validY == newY)) {
				this.setXCoordinate(newX);
				this.setYCoordinate(newY);
			}
		} else if (movementType == MovementType.CAPTURE) {
			// TODO: implement capture logic (outside test scope)
		}
	}

}
