package com.android.hammad.shortestpathfinder.utils;

import com.android.hammad.shortestpathfinder.models.Cell;

/**
 * Created by Hassan on 3/30/2017.
 */

public class SampleUtils {
    // Sample grid 1
    public static final int SAMPLE_GRID_1_ROWS = 5;
    public static final int SAMPLE_GRID_1_COLS = 6;
    public static final Cell[][] SAMPLE_GRID_1 =
            {
                    {new Cell(0, 0, 3), new Cell(0, 1, 4), new Cell(0, 2, 1), new Cell(0, 3, 2), new Cell(0, 4, 8), new Cell(0, 5, 6)},
                    {new Cell(1, 0, 6), new Cell(1, 1, 1), new Cell(1, 2, 8), new Cell(1, 3, 2), new Cell(1, 4, 7), new Cell(1, 5, 4)},
                    {new Cell(2, 0, 5), new Cell(2, 1, 9), new Cell(2, 2, 3), new Cell(2, 3, 9), new Cell(2, 4, 9), new Cell(2, 5, 5)},
                    {new Cell(3, 0, 8), new Cell(3, 1, 4), new Cell(3, 2, 1), new Cell(3, 3, 3), new Cell(3, 4, 2), new Cell(3, 5, 6)},
                    {new Cell(4, 0, 3), new Cell(4, 1, 7), new Cell(4, 2, 2), new Cell(4, 3, 8), new Cell(4, 4, 6), new Cell(4, 5, 4)}
            };

    // Sample grid 2
    public static final int SAMPLE_GRID_2_ROWS = SAMPLE_GRID_1_ROWS;
    public static final int SAMPLE_GRID_2_COLS = SAMPLE_GRID_1_COLS;
    public static final Cell[][] SAMPLE_GRID_2 =
            {
                    {new Cell(0, 0, 3), new Cell(0, 1, 4), new Cell(0, 2, 1), new Cell(0, 3, 2), new Cell(0, 4, 8), new Cell(0, 5, 6)},
                    {new Cell(1, 0, 6), new Cell(1, 1, 1), new Cell(1, 2, 8), new Cell(1, 3, 2), new Cell(1, 4, 7), new Cell(1, 5, 4)},
                    {new Cell(2, 0, 5), new Cell(2, 1, 9), new Cell(2, 2, 3), new Cell(2, 3, 9), new Cell(2, 4, 9), new Cell(2, 5, 5)},
                    {new Cell(3, 0, 8), new Cell(3, 1, 4), new Cell(3, 2, 1), new Cell(3, 3, 3), new Cell(3, 4, 2), new Cell(3, 5, 6)},
                    {new Cell(4, 0, 3), new Cell(4, 1, 7), new Cell(4, 2, 2), new Cell(4, 3, 1), new Cell(4, 4, 2), new Cell(4, 5, 3)}
            };

    // Sample grid 3
    public static final int SAMPLE_GRID_3_ROWS = 3;
    public static final int SAMPLE_GRID_3_COLS = 5;
    public static final Cell[][] SAMPLE_GRID_3 =
            {
                    {new Cell(0, 0, 19), new Cell(0, 1, 10), new Cell(0, 2, 19), new Cell(0, 3, 10), new Cell(0, 4, 19)},
                    {new Cell(1, 0, 21), new Cell(1, 1, 23), new Cell(1, 2, 20), new Cell(1, 3, 19), new Cell(1, 4, 12)},
                    {new Cell(2, 0, 20), new Cell(2, 1, 12), new Cell(2, 2, 20), new Cell(2, 3, 11), new Cell(2, 4, 10)}
            };
}
