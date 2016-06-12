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
	 * 
	 * NB: In reality, pawns can optionally move two spaces forward at the start of a game, but for the purposes 
	 * of this exercise this has not been implemented.
	 */
	@Override
	public boolean move(MovementType movementType, int newX, int newY) {
		int currentX = this.getXCoordinate();
		int currentY = this.getYCoordinate();
		PieceColor color = this.getPieceColor();

		int xIndexModifier = (color == PieceColor.WHITE) ? 1 : -1;
		
		if (movementType == MovementType.MOVE) {
			int validX = currentX + xIndexModifier;
			int validY = currentY;
			
			return checkMoveIsLegalAndPerform(validX, validY, newX, newY);
		} else if (movementType == MovementType.CAPTURE) {
			// TODO: implement capture logic (outside test scope)
		}
		
		return false;
	}

}
