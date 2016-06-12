package com.logicnow.hiring.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.logicnow.hiring.constants.MovementType;
import com.logicnow.hiring.constants.PieceColour;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;
    private Pawn testSubject2;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColour.BLACK);
        this.testSubject2 = new Pawn(PieceColour.WHITE);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        chessBoard.add(testSubject, 6, 3, PieceColour.BLACK);
        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        chessBoard.add(testSubject, 6, 3, PieceColour.BLACK);
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_ReturnsFalse() {
        chessBoard.add(testSubject, 6, 3, PieceColour.BLACK);
        boolean success = testSubject.move(MovementType.MOVE, 6, 2);
        assertFalse(success);
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.add(testSubject, 6, 3, PieceColour.BLACK);
        testSubject.move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.add(testSubject, 6, 3, PieceColour.BLACK);
        testSubject.move(MovementType.MOVE, 6, 4);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_ReturnsTrue() {
        chessBoard.add(testSubject, 6, 3, PieceColour.BLACK);
        boolean success = testSubject.move(MovementType.MOVE, 5, 3);
        assertTrue(success);
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.add(testSubject, 6, 3, PieceColour.BLACK);
        testSubject.move(MovementType.MOVE, 5, 3);
        assertEquals(5, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testWhite_Pawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.add(testSubject2, 1, 3, PieceColour.WHITE);
        testSubject2.move(MovementType.MOVE, 2, 3);
        assertEquals(2, testSubject2.getXCoordinate());
        assertEquals(3, testSubject2.getYCoordinate());
    }

    // Note: this test is based on the exercise assumption that a pawn can only
    // ever move one space forward
    @Test
    public void testPawn_Move_IllegalCoordinates_Forward_DoesNotMove() {
        chessBoard.add(testSubject, 6, 3, PieceColour.BLACK);
        testSubject.move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }
    
    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesBoardPosition() {
        chessBoard.add(testSubject, 6, 3, PieceColour.BLACK);
        testSubject.move(MovementType.MOVE, 5, 3);
        
        ChessPiece[][] pieces = chessBoard.getPieces();

        assertEquals(testSubject, pieces[5][3]);
        assertNull(pieces[6][3]);
    }

    @Test
    public void testGetValidMoves_Black() {
        // In this exercise, a pawn only has one valid move - one space forward
        chessBoard.add(testSubject, 6, 3, PieceColour.BLACK);
        List<Position> moves = testSubject.getValidMoves(MovementType.MOVE);

        assertEquals(1, moves.size());

        Position move = moves.get(0);

        assertEquals(5, move.getXCoordinate());
        assertEquals(3, move.getYCoordinate());
    }

    @Test
    public void testGetValidMoves_White() {
        // In this exercise, a pawn only has one valid move - one space forward
        chessBoard.add(testSubject2, 1, 3, PieceColour.WHITE);
        List<Position> moves = testSubject2.getValidMoves(MovementType.MOVE);

        assertEquals(1, moves.size());

        Position move = moves.get(0);

        assertEquals(2, move.getXCoordinate());
        assertEquals(3, move.getYCoordinate());
    }

    @Test
    public void testGetValidMoves_No_Moves_Edge_Of_Board() {
        chessBoard.add(testSubject, 0, 3, PieceColour.BLACK);
        List<Position> moves = testSubject.getValidMoves(MovementType.MOVE);

        assertEquals(0, moves.size());
    }

    @Test
    public void testToString() {
        chessBoard.add(testSubject, 6, 3, PieceColour.BLACK);

        String output = testSubject.toString();
        String eol = System.lineSeparator();
        String expected = "Current X: 6" + eol + "Current Y: 3" + eol + "Piece Colour: BLACK" + eol + "Piece Type: PAWN";

        assertEquals(expected, output);
    }

}