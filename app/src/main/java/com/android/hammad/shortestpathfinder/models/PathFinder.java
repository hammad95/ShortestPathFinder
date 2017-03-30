package com.android.hammad.shortestpathfinder.models;

import java.util.ArrayList;
import java.util.Arrays;

/*
    This class processes the grid passed in reverse.
    It creates a new grid from the existing gird where the cells in each position
    have a cost that is the least possible cost for moving from that cell to an
    adjacent cell in the next column. The cell with the lowest cost in each column
    in the new grid is part of the solution path.
 */
public class PathFinder {
    // The maximum cost a path is allowed to have
    private final int MAX_COST_ALLOWED = 49;

    // This is the user created grid
    private Cell[][] mGrid;
    // This is the final constructed grid which will be used
    // to find the path with the lowest cost
    private Cell[][] mFinalGrid;

    private int mNumRows;
    private int mNumColumns;

    // To hold the cells in the solution path in order
    private ArrayList<Cell> mSolutionPath = new ArrayList<>();

    // Hold strings representing the solutions for parts 1, 2 and 3
    private String[] mSolutionString = new String[3];

    /*
        Constructor takes a grid of cells, number of rows, and number of columns in the grid
     */
    public PathFinder(Cell[][] grid, int rows, int columns) {
        super();

        mGrid = grid;

        mNumRows = rows;
        mNumColumns = columns;

        // Create a new numRows x numColumns grid
        mFinalGrid = new Cell[mNumRows][mNumColumns];
    }

    /*
        This method will first create a grid to be traversed to find the solution path
        And then compute the solution path from the constructed grid
        Returns an ArrayList containing the cells in the solution path in order
     */
    public String[] compute() {
        createFinalGrid();
        computePath();
        createSolutionArray();

        return mSolutionString;
    }

    /*
        Creates the array of strings representing the solutions to return
     */
    private void createSolutionArray() {
        boolean isPathFound;
        int totalCost;
        int[] rowOrder;

        if (mSolutionPath.size() > 0) {
            Cell c = mSolutionPath.get(0);
            int row = c.getRow();
            int col = c.getColumn();

            // If size of solution is atleast the same as number of rows, a solution was found
            isPathFound = mSolutionPath.size() >= mNumColumns;

            // Set the answer to part 1 in the solution array
            mSolutionString[0] = isPathFound ? "Yes" : "No";

            // Set the total cost
            totalCost = calculateTotalCost();

            // Set the answer to part 2 in the solution array
            mSolutionString[1] = String.valueOf(totalCost);

            // Set the row order of the rows traversed
            rowOrder = calculateRowsTraversed();

            // Set the answer to part 3 in the solution array
            mSolutionString[2] = Arrays.toString(rowOrder);
        } else {
            // If no cells traversed, set default values to the solution array
            mSolutionString[0] = "No";
            mSolutionString[1] = "0";
            mSolutionString[2] = "[]";
        }
    }

    /*
        Calculates and returns an array representing the rows
        traversed in order in the solution path
     */
    private int[] calculateRowsTraversed() {
        int[] rowsTraversed = new int[mSolutionPath.size()];

        for (int i = 0; i < mSolutionPath.size(); i++) {
            rowsTraversed[i] = mSolutionPath.get(i).getRow() + 1;
        }

        return rowsTraversed;
    }

    /*
        Calculates and returns the total cost of the cells traversed in the solution path
     */
    private int calculateTotalCost() {
        int totalCost = 0;
        for (int i = 0; i < mSolutionPath.size(); i++) {
            totalCost += mSolutionPath.get(i).getCost();
        }
        return totalCost;
    }

    /*
        This method creates a final grid from the user's created grid by
        Adding to each position in the grid a cell whose value is the lowest
        cost required to move from the cell to an adjacent cell in the next
        column (wrapping to the first or last row as needed)
     */
    private void createFinalGrid() {
        // The last column of the final grid is the same as the
        // last column of the original grid
        int column = mNumColumns - 1;
        for (int row = 0; row < mNumRows; row++) {
            Cell c = mGrid[row][column];
            mFinalGrid[row][column] = new Cell(c.getRow(), c.getColumn(), c.getCost());
        }

        // For all other columns, each cell in the final grid must have the minimum
        // cost required to go from that cell in the original grid to the next adjacent
        // cell in the next column in the final grid
        column--;   // Since we have already processed the last column
        while (column >= 0) {
            for (int row = 0; row < mNumRows; row++) {
                Cell current = mGrid[row][column];
                // Add a new cell with the min possible cost to the final grid
                addNewCellToFinalGrid(current);
            }
            column--;
        }
    }

    // Compute the solution path from the final grid by traveling
    // from the first column to the last column and each time moving
    // to the adjacent cell with the lowest cost
    public void computePath() {
        // Add the cell in the final grid with the min cost in the first column
        // to the solution path since this is the starting point. The cost of this
        // cell is the total cost it takes to travel through the grid using the
        // minimum cost possible
        int minCostCellRow = 0;
        int minCost = mFinalGrid[minCostCellRow][0].getCost(); // Initially first cell's cost
        for (int row = 0; row < mNumRows; row++) {
            int cost = mFinalGrid[row][0].getCost();
            if (cost < minCost) {
                minCost = cost;
                minCostCellRow = row;
            }
        }

        // Add cell with lowest cost in the first column to the solution path
        Cell current = mGrid[minCostCellRow][0];
        if (current.getCost() > MAX_COST_ALLOWED)
            return;
        mSolutionPath.add(current);

        while (true) {
            current = getLowestCostNeighbor(current);
            // Stop if current is null or cost would exceed max cost allowed
            if (current == null || !isCostAllowed(current))
                break;
            // Add current to solution path
            mSolutionPath.add(current);
        }
    }

    /*
        Sums up the total cost of the cells present in the solution path and the cell
        passed as argument to this method to check if adding the passed cell to the path
        will exceed the max allowed cost for the path. It does it by simpy taking the row
        and column value of each cell in the path iteratively and checks the corresponding
        cell in the original grid.
     */
    public boolean isCostAllowed(Cell current) {
        int totalCost = 0;

        for (int i = 0; i < mSolutionPath.size(); i++) {
            totalCost += mSolutionPath.get(i).getCost();
        }

        return totalCost + current.getCost() <= MAX_COST_ALLOWED;
    }

    // Check the neighbors of the current cell and return the one with the lowest cost
    private Cell getLowestCostNeighbor(Cell cell) {
        if (cell.getColumn() + 1 == mNumColumns)
            return null;

        // Get the corresponding cell in the final grid
        Cell current = mFinalGrid[cell.getRow()][cell.getColumn()];

        Cell minCostCell;
        int minCost;

        // Get the first neighbor
        int neighbor1_column = current.getColumn() + 1;
        int neighbor1_row = current.getRow() - 1;

        // Grid wraps
        if (neighbor1_row < 0) {
            neighbor1_row = mNumRows - 1;
        }

        Cell neighbor1 = mFinalGrid[neighbor1_row][neighbor1_column];
        minCost = neighbor1.getCost();
        minCostCell = neighbor1;

        // Get the second neighbor
        int neighbor2_column = current.getColumn() + 1;
        int neighbor2_row = current.getRow();

        // Get the neighboring cell in the final grid to the current cell position
        // in the original grid
        Cell neighbor2 = mFinalGrid[neighbor2_row][neighbor2_column];
        // Update min cost
        if (neighbor2.getCost() < minCost) {
            minCost = neighbor2.getCost();
            minCostCell = neighbor2;
        }

        // Get the third neighbor
        int neighbor3_column = current.getColumn() + 1;
        int neighbor3_row = current.getRow() + 1;

        // Grid wraps
        if (neighbor3_row == mNumRows) {
            neighbor3_row = 0;
        }

        Cell neighbor3 = mFinalGrid[neighbor3_row][neighbor3_column];
        // Update min cost
        if (neighbor3.getCost() < minCost) {
            minCost = neighbor3.getCost();
            minCostCell = neighbor3;
        }

        // Return the cell in the original grid corresponding to the final grid
        return mGrid[minCostCell.getRow()][minCostCell.getColumn()];
    }

    // Look at the neighbors of the cell in the original gird passed to this method and
    // select the min possible cost create a new cell with and add it to the final grid
    private void addNewCellToFinalGrid(Cell current) {
        int cost;
        int minCost;

        int new_cell_row = current.getRow();
        int new_cell_column = current.getColumn();

        // Get the first neighbor's coordinates
        int neighbor1_column = current.getColumn() + 1;
        int neighbor1_row = current.getRow() - 1;

        // Grid wraps
        if (neighbor1_row < 0) {
            neighbor1_row = mNumRows - 1;
        }

        // Get the neighboring cell in the final grid to the current cell position
        // in the original grid
        Cell neighbor1 = mFinalGrid[neighbor1_row][neighbor1_column];
        // The first neighbors cost + the cost of the current cell is the min cost for now
        minCost = current.getCost() + neighbor1.getCost();

        // Get the secind neighbor's coordinates
        int neighbor2_column = current.getColumn() + 1;
        int neighbor2_row = current.getRow();

        // Get the neighboring cell in the final grid to the current cell position
        // in the original grid
        Cell neighbor2 = mFinalGrid[neighbor2_row][neighbor2_column];
        // Update min cost
        cost = current.getCost() + neighbor2.getCost();
        if (cost < minCost)
            minCost = cost;

        // Get the third neighbor's coordinates
        int neighbor3_column = current.getColumn() + 1;
        int neighbor3_row = current.getRow() + 1;

        // Grid wraps
        if (neighbor3_row == mNumRows) {
            neighbor3_row = 0;
        }

        // Get the neighboring cell in the final grid to the current cell position
        // in the original grid
        Cell neighbor3 = mFinalGrid[neighbor3_row][neighbor3_column];
        // Update min cost
        cost = current.getCost() + neighbor3.getCost();
        if (cost < minCost)
            minCost = cost;

        // Create a new cell with the min cost
        Cell newCell = new Cell(new_cell_row, new_cell_column, minCost);
        // Add the new cell to the final grid
        mFinalGrid[new_cell_row][new_cell_column] = newCell;
    }
}
