package com.android.hammad.shortestpathfinder;

import com.android.hammad.shortestpathfinder.models.Cell;
import com.android.hammad.shortestpathfinder.models.PathFinder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
    Tests for sample inputs to PathFinder
 */
public class PathFinderUnitTest {
    private Cell[][] mGrid;
    private PathFinder mPathFinder;
    private String[] mSolutionString;

    int mNumRows,
            mNumColumns;

    /*
        Sample 1 test
     */
    @Test
    public void testSampleInput1() {
        mNumRows = 5;
        mNumColumns = 6;

        mGrid = new Cell[mNumRows][mNumColumns];

        mGrid[0][0] = new Cell(0,0,3);
        mGrid[0][1] = new Cell(0,1,4);
        mGrid[0][2] = new Cell(0,2,1);
        mGrid[0][3] = new Cell(0,3,2);
        mGrid[0][4] = new Cell(0,4,8);
        mGrid[0][5] = new Cell(0,5,6);
        mGrid[1][0] = new Cell(1,0,6);
        mGrid[1][1] = new Cell(1,1,1);
        mGrid[1][2] = new Cell(1,2,8);
        mGrid[1][3] = new Cell(1,3,2);
        mGrid[1][4] = new Cell(1,4,7);
        mGrid[1][5] = new Cell(1,5,4);
        mGrid[2][0] = new Cell(2,0,5);
        mGrid[2][1] = new Cell(2,1,9);
        mGrid[2][2] = new Cell(2,2,3);
        mGrid[2][3] = new Cell(2,3,9);
        mGrid[2][4] = new Cell(2,4,9);
        mGrid[2][5] = new Cell(2,5,5);
        mGrid[3][0] = new Cell(3,0,8);
        mGrid[3][1] = new Cell(3,1,4);
        mGrid[3][2] = new Cell(3,2,1);
        mGrid[3][3] = new Cell(3,3,3);
        mGrid[3][4] = new Cell(3,4,2);
        mGrid[3][5] = new Cell(3,5,6);
        mGrid[4][0] = new Cell(4,0,3);
        mGrid[4][1] = new Cell(4,1,7);
        mGrid[4][2] = new Cell(4,2,2);
        mGrid[4][3] = new Cell(4,3,8);
        mGrid[4][4] = new Cell(4,4,6);
        mGrid[4][5] = new Cell(4,5,4);

        mPathFinder = new PathFinder(mGrid, mNumRows, mNumColumns);

        mSolutionString = mPathFinder.compute();

        assertEquals("Yes", mSolutionString[0]);
        assertEquals("16", mSolutionString[1]);
        assertEquals("[1, 2, 3, 4, 4, 5]", mSolutionString[2]);
    }

    /*
        Sample 2 test
     */
    @Test
    public void testSampleInput2() {
        mNumRows = 5;
        mNumColumns = 6;

        mGrid = new Cell[mNumRows][mNumColumns];

        mGrid[0][0] = new Cell(0,0,3);
        mGrid[0][1] = new Cell(0,1,4);
        mGrid[0][2] = new Cell(0,2,1);
        mGrid[0][3] = new Cell(0,3,2);
        mGrid[0][4] = new Cell(0,4,8);
        mGrid[0][5] = new Cell(0,5,6);
        mGrid[1][0] = new Cell(1,0,6);
        mGrid[1][1] = new Cell(1,1,1);
        mGrid[1][2] = new Cell(1,2,8);
        mGrid[1][3] = new Cell(1,3,2);
        mGrid[1][4] = new Cell(1,4,7);
        mGrid[1][5] = new Cell(1,5,4);
        mGrid[2][0] = new Cell(2,0,5);
        mGrid[2][1] = new Cell(2,1,9);
        mGrid[2][2] = new Cell(2,2,3);
        mGrid[2][3] = new Cell(2,3,9);
        mGrid[2][4] = new Cell(2,4,9);
        mGrid[2][5] = new Cell(2,5,5);
        mGrid[3][0] = new Cell(3,0,8);
        mGrid[3][1] = new Cell(3,1,4);
        mGrid[3][2] = new Cell(3,2,1);
        mGrid[3][3] = new Cell(3,3,3);
        mGrid[3][4] = new Cell(3,4,2);
        mGrid[3][5] = new Cell(3,5,6);
        mGrid[4][0] = new Cell(4,0,3);
        mGrid[4][1] = new Cell(4,1,7);
        mGrid[4][2] = new Cell(4,2,2);
        mGrid[4][3] = new Cell(4,3,1);
        mGrid[4][4] = new Cell(4,4,2);
        mGrid[4][5] = new Cell(4,5,3);

        mPathFinder = new PathFinder(mGrid, mNumRows, mNumColumns);

        mSolutionString = mPathFinder.compute();

        assertEquals("Yes", mSolutionString[0]);
        assertEquals("11", mSolutionString[1]);
        assertEquals("[1, 2, 1, 5, 4, 5]", mSolutionString[2]);
    }

    /*
        Sample 3 test
     */
    @Test
    public void testSampleInput3() {
        mNumRows = 3;
        mNumColumns = 5;

        mGrid = new Cell[mNumRows][mNumColumns];

        mGrid[0][0] = new Cell(0,0,19);
        mGrid[0][1] = new Cell(0,1,10);
        mGrid[0][2] = new Cell(0,2,19);
        mGrid[0][3] = new Cell(0,3,10);
        mGrid[0][4] = new Cell(0,4,19);
        mGrid[1][0] = new Cell(1,0,21);
        mGrid[1][1] = new Cell(1,1,23);
        mGrid[1][2] = new Cell(1,2,20);
        mGrid[1][3] = new Cell(1,3,19);
        mGrid[1][4] = new Cell(1,4,12);
        mGrid[2][0] = new Cell(2,0,20);
        mGrid[2][1] = new Cell(2,1,12);
        mGrid[2][2] = new Cell(2,2,20);
        mGrid[2][3] = new Cell(2,3,11);
        mGrid[2][4] = new Cell(2,4,10);

        mPathFinder = new PathFinder(mGrid, mNumRows, mNumColumns);

        mSolutionString = mPathFinder.compute();

        assertEquals("No", mSolutionString[0]);
        assertEquals("48", mSolutionString[1]);
        assertEquals("[1, 1, 1]", mSolutionString[2]);
    }

    /*
        Sample 4 test
     */
    @Test
    public void testSampleInput4() {
        mNumRows = 1;
        mNumColumns = 5;

        mGrid = new Cell[mNumRows][mNumColumns];

        mGrid[0][0] = new Cell(0,0,5);
        mGrid[0][1] = new Cell(0,1,8);
        mGrid[0][2] = new Cell(0,2,5);
        mGrid[0][3] = new Cell(0,3,3);
        mGrid[0][4] = new Cell(0,4,5);

        mPathFinder = new PathFinder(mGrid, mNumRows, mNumColumns);

        mSolutionString = mPathFinder.compute();

        assertEquals("Yes", mSolutionString[0]);
        assertEquals("26", mSolutionString[1]);
        assertEquals("[1, 1, 1, 1, 1]", mSolutionString[2]);
    }

    /*
        Sample 5 test
     */
    @Test
    public void testSampleInput5() {
        mNumRows = 5;
        mNumColumns = 1;

        mGrid = new Cell[mNumRows][mNumColumns];

        mGrid[0][0] = new Cell(0,0,5);
        mGrid[1][0] = new Cell(1,0,8);
        mGrid[2][0] = new Cell(2,0,5);
        mGrid[3][0] = new Cell(3,0,3);
        mGrid[4][0] = new Cell(4,0,5);

        mPathFinder = new PathFinder(mGrid, mNumRows, mNumColumns);

        mSolutionString = mPathFinder.compute();

        assertEquals("Yes", mSolutionString[0]);
        assertEquals("3", mSolutionString[1]);
        assertEquals("[4]", mSolutionString[2]);
    }

    /*
        Sample 8 test
     */
    @Test
    public void testSampleInput8() {
        mNumRows = 3;
        mNumColumns = 5;

        mGrid = new Cell[mNumRows][mNumColumns];

        mGrid[0][0] = new Cell(0,0,69);
        mGrid[0][1] = new Cell(0,1,10);
        mGrid[0][2] = new Cell(0,2,19);
        mGrid[0][3] = new Cell(0,3,10);
        mGrid[0][4] = new Cell(0,4,19);
        mGrid[1][0] = new Cell(1,0,51);
        mGrid[1][1] = new Cell(1,1,23);
        mGrid[1][2] = new Cell(1,2,20);
        mGrid[1][3] = new Cell(1,3,19);
        mGrid[1][4] = new Cell(1,4,12);
        mGrid[2][0] = new Cell(2,0,60);
        mGrid[2][1] = new Cell(2,1,12);
        mGrid[2][2] = new Cell(2,2,20);
        mGrid[2][3] = new Cell(2,3,11);
        mGrid[2][4] = new Cell(2,4,10);

        mPathFinder = new PathFinder(mGrid, mNumRows, mNumColumns);

        mSolutionString = mPathFinder.compute();

        assertEquals("No", mSolutionString[0]);
        assertEquals("0", mSolutionString[1]);
        assertEquals("[]", mSolutionString[2]);
    }

    /*
        Sample 9 test
     */
    @Test
    public void testSampleInput9() {
        mNumRows = 3;
        mNumColumns = 4;

        mGrid = new Cell[mNumRows][mNumColumns];

        mGrid[0][0] = new Cell(0,0,60);
        mGrid[0][1] = new Cell(0,1,3);
        mGrid[0][2] = new Cell(0,2,3);
        mGrid[0][3] = new Cell(0,3,6);
        mGrid[1][0] = new Cell(1,0,6);
        mGrid[1][1] = new Cell(1,1,3);
        mGrid[1][2] = new Cell(1,2,7);
        mGrid[1][3] = new Cell(1,3,9);
        mGrid[2][0] = new Cell(2,0,5);
        mGrid[2][1] = new Cell(2,1,6);
        mGrid[2][2] = new Cell(2,2,8);
        mGrid[2][3] = new Cell(2,3,3);

        mPathFinder = new PathFinder(mGrid, mNumRows, mNumColumns);

        mSolutionString = mPathFinder.compute();

        assertEquals("Yes", mSolutionString[0]);
        assertEquals("14", mSolutionString[1]);
        assertEquals("[3, 2, 1, 3]", mSolutionString[2]);
    }

    /*
        Sample 10 test
     */
    @Test
    public void testSampleInput10() {
        mNumRows = 4;
        mNumColumns = 4;

        mGrid = new Cell[mNumRows][mNumColumns];

        mGrid[0][0] = new Cell(0,0,6);
        mGrid[0][1] = new Cell(0,1,3);
        mGrid[0][2] = new Cell(0,2,-5);
        mGrid[0][3] = new Cell(0,3,9);
        mGrid[1][0] = new Cell(1,0,-5);
        mGrid[1][1] = new Cell(1,1,2);
        mGrid[1][2] = new Cell(1,2,4);
        mGrid[1][3] = new Cell(1,3,10);
        mGrid[2][0] = new Cell(2,0,3);
        mGrid[2][1] = new Cell(2,1,-2);
        mGrid[2][2] = new Cell(2,2,6);
        mGrid[2][3] = new Cell(2,3,10);
        mGrid[3][0] = new Cell(3,0,6);
        mGrid[3][1] = new Cell(3,1,-1);
        mGrid[3][2] = new Cell(3,2,-2);
        mGrid[3][3] = new Cell(3,3,10);

        mPathFinder = new PathFinder(mGrid, mNumRows, mNumColumns);

        mSolutionString = mPathFinder.compute();

        assertEquals("Yes", mSolutionString[0]);
        assertEquals("0", mSolutionString[1]);
        assertEquals("[2, 3, 4, 1]", mSolutionString[2]);
    }

    /*
        Sample 11 test
     */
    @Test
    public void testSampleInput11() {
        mNumRows = 4;
        mNumColumns = 2;

        mGrid = new Cell[mNumRows][mNumColumns];

        mGrid[0][0] = new Cell(0,0,51);
        mGrid[0][1] = new Cell(0,1,51);
        mGrid[1][0] = new Cell(1,0,0);
        mGrid[1][1] = new Cell(1,1,51);
        mGrid[2][0] = new Cell(2,0,51);
        mGrid[2][1] = new Cell(2,1,51);
        mGrid[3][0] = new Cell(3,0,5);
        mGrid[3][1] = new Cell(3,1,5);

        mPathFinder = new PathFinder(mGrid, mNumRows, mNumColumns);

        mSolutionString = mPathFinder.compute();

        assertEquals("Yes", mSolutionString[0]);
        assertEquals("10", mSolutionString[1]);
        assertEquals("[4, 4]", mSolutionString[2]);
    }

    /*
        Sample 12 test
     */
    @Test
    public void testSampleInput12() {
        mNumRows = 4;
        mNumColumns = 3;

        mGrid = new Cell[mNumRows][mNumColumns];

        mGrid[0][0] = new Cell(0,0,51);
        mGrid[0][1] = new Cell(0,1,51);
        mGrid[0][2] = new Cell(0,2,51);
        mGrid[1][0] = new Cell(1,0,0);
        mGrid[1][1] = new Cell(1,1,51);
        mGrid[1][2] = new Cell(1,2,51);
        mGrid[2][0] = new Cell(2,0,51);
        mGrid[2][1] = new Cell(2,1,51);
        mGrid[2][2] = new Cell(2,2,51);
        mGrid[3][0] = new Cell(3,0,5);
        mGrid[3][1] = new Cell(3,1,5);
        mGrid[3][2] = new Cell(3,2,51);

        mPathFinder = new PathFinder(mGrid, mNumRows, mNumColumns);

        mSolutionString = mPathFinder.compute();

        assertEquals("No", mSolutionString[0]);
        assertEquals("10", mSolutionString[1]);
        assertEquals("[4, 4]", mSolutionString[2]);
    }


    /*
        Sample 13 test
     */
    @Test
    public void testSampleInput13() {
        mNumRows = 2;
        mNumColumns = 20;

        mGrid = new Cell[mNumRows][mNumColumns];

        mGrid[0][0] = new Cell(0,0,1);
        mGrid[0][1] = new Cell(0,1,1);
        mGrid[0][2] = new Cell(0,2,1);
        mGrid[0][3] = new Cell(0,3,1);
        mGrid[0][4] = new Cell(0,4,1);
        mGrid[0][5] = new Cell(0,5,1);
        mGrid[0][6] = new Cell(0,6,1);
        mGrid[0][7] = new Cell(0,7,1);
        mGrid[0][8] = new Cell(0,8,1);
        mGrid[0][9] = new Cell(0,9,1);
        mGrid[0][10] = new Cell(0,10,1);
        mGrid[0][11] = new Cell(0,11,1);
        mGrid[0][12] = new Cell(0,12,1);
        mGrid[0][13] = new Cell(0,13,1);
        mGrid[0][14] = new Cell(0,14,1);
        mGrid[0][15] = new Cell(0,15,1);
        mGrid[0][16] = new Cell(0,16,1);
        mGrid[0][17] = new Cell(0,17,1);
        mGrid[0][18] = new Cell(0,18,1);
        mGrid[0][19] = new Cell(0,19,1);
        mGrid[1][0] = new Cell(1,0,2);
        mGrid[1][1] = new Cell(1,1,2);
        mGrid[1][2] = new Cell(1,2,2);
        mGrid[1][3] = new Cell(1,3,2);
        mGrid[1][4] = new Cell(1,4,2);
        mGrid[1][5] = new Cell(1,5,2);
        mGrid[1][6] = new Cell(1,6,2);
        mGrid[1][7] = new Cell(1,7,2);
        mGrid[1][8] = new Cell(1,8,2);
        mGrid[1][9] = new Cell(1,9,2);
        mGrid[1][10] = new Cell(1,10,2);
        mGrid[1][11] = new Cell(1,11,2);
        mGrid[1][12] = new Cell(1,12,2);
        mGrid[1][13] = new Cell(1,13,2);
        mGrid[1][14] = new Cell(1,14,2);
        mGrid[1][15] = new Cell(1,15,2);
        mGrid[1][16] = new Cell(1,16,2);
        mGrid[1][17] = new Cell(1,17,2);
        mGrid[1][18] = new Cell(1,18,2);
        mGrid[1][19] = new Cell(1,19,2);

        mPathFinder = new PathFinder(mGrid, mNumRows, mNumColumns);

        mSolutionString = mPathFinder.compute();

        assertEquals("Yes", mSolutionString[0]);
        assertEquals("20", mSolutionString[1]);
        assertEquals("[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]",
                mSolutionString[2]);
    }
}
