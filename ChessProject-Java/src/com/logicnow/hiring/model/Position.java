package com.logicnow.hiring.model;

/**
 * A class representing a position on a chess board, with x and y coordinates.
 * 
 * @author IMJ84
 *
 */
public class Position {

    private int xCoordinate;
    private int yCoordinate;

    public Position(int xCooordinate, int yCoordinate) {
        this.xCoordinate = xCooordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
