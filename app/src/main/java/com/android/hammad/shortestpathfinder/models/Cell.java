package com.android.hammad.shortestpathfinder.models;

/**
 * This class represents a cell in the grid
 * Cell has a row, column and a cost
 */

public class Cell {
    private int cost;
    private int row, column;

    public Cell(int row, int column, int cost){
        this.row = row;
        this.column = column;
        this.cost = cost;
    }

    // Return the row of this cell
    public int getRow() {
        return row;
    }

    // Return the column of this cell
    public int getColumn() {
        return column;
    }

    // Return the cost of this cell
    public int getCost() {
        return cost;
    }

    // Set the row of this cell
    public void setRow(int row) {
        this.row = row;
    }

    // Set the column of this cell
    public void setColumn(int column) {
        this.column = column;
    }

    // Set the cost of this cell
    public void setCost(int cost) {
        this.cost = cost;
    }

    // Return the cost of this cell
    @Override
    public String toString() {
        return "" + cost;
    }
}