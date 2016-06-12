package com.logicnow.hiring.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.logicnow.hiring.constants.PieceColour;

import junit.framework.TestCase;

public class ChessBoardTest extends TestCase {

    private ChessBoard testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardWidth_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_COLUMNS);
    }

    @Test
    public void testHas_MaxBoardHeight_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_ROWS);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(0, 0);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(5, 5);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(11, 5);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(0, 9);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(11, 0);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_8_Y_equals_8() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(8, 8);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(5, -1);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_X_Values() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(-1, 5);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testAvoids_Duplicate_Positioning() {
        Pawn firstPawn = new Pawn(PieceColour.BLACK);
        Pawn secondPawn = new Pawn(PieceColour.BLACK);
        testSubject.add(firstPawn, 6, 3, PieceColour.BLACK);
        testSubject.add(secondPawn, 6, 3, PieceColour.BLACK);
        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());
        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns() {
        for (int i = 0; i < 10; i++) {
            Pawn pawn = new Pawn(PieceColour.BLACK);
            int row = i / ChessBoard.MAX_BOARD_ROWS;
            testSubject.add(pawn, 6 + row, i % ChessBoard.MAX_BOARD_COLUMNS, PieceColour.BLACK);
            if (row < 1) {
                assertEquals(6 + row, pawn.getXCoordinate());
                assertEquals(i % ChessBoard.MAX_BOARD_COLUMNS, pawn.getYCoordinate());
            } else {
                assertEquals(-1, pawn.getXCoordinate());
                assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }

    @Test
    public void testUpdatePiecePosition_Sets_Piece_In_Array() {
        Pawn pawn = new Pawn(PieceColour.WHITE);
        testSubject.add(pawn, 1, 1, PieceColour.WHITE);
        testSubject.updatePiecePosition(pawn, 1, 1, 2, 1);

        ChessPiece[][] pieces = testSubject.getPieces();

        assertEquals(pawn, pieces[2][1]);
    }

    @Test
    public void testUpdatePiecePosition_Nulls_Original_Position_In_Array() {
        Pawn pawn = new Pawn(PieceColour.WHITE);
        testSubject.add(pawn, 1, 1, PieceColour.WHITE);
        testSubject.updatePiecePosition(pawn, 1, 1, 2, 1);

        ChessPiece[][] pieces = testSubject.getPieces();

        assertNull(pieces[1][1]);
    }
}