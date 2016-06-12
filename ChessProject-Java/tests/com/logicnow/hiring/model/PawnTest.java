package com.logicnow.hiring.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.logicnow.hiring.constants.MovementType;
import com.logicnow.hiring.constants.PieceColor;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;
    private Pawn testSubject2;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
        this.testSubject2 = new Pawn(PieceColor.WHITE);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_ReturnsFalse() {
        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        boolean success = testSubject.move(MovementType.MOVE, 6, 2);
        assertFalse(success);
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 6, 4);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }
    
    @Test
    public void testPawn_Move_LegalCoordinates_ReturnsTrue() {
        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        boolean success = testSubject.move(MovementType.MOVE, 5, 3);
        assertTrue(success);
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 5, 3);
        assertEquals(5, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }
    
    @Test
    public void testWhite_Pawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.add(testSubject2, 1, 3, PieceColor.WHITE);
        testSubject2.move(MovementType.MOVE, 2, 3);
        assertEquals(2, testSubject2.getXCoordinate());
        assertEquals(3, testSubject2.getYCoordinate());
    }
    
    // Note: this test is based on the exercise assumption that a pawn can only ever move one space forward
    @Test
    public void testPawn_Move_IllegalCoordinates_Forward_DoesNotMove() {
        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }
    
    @Test
    public void testToString() {
    	chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
    	
    	String output = testSubject.toString();
    	String eol = System.lineSeparator();
    	String expected = "Current X: 6" + eol + "Current Y: 3" + eol + "Piece Color: BLACK" + eol + "Piece Type: PAWN";
    	
    	assertEquals(expected, output);
    }

}