package com.android.hammad.shortestpathfinder.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.hammad.shortestpathfinder.R;
import com.android.hammad.shortestpathfinder.models.Cell;
import com.android.hammad.shortestpathfinder.models.PathFinder;
import com.android.hammad.shortestpathfinder.utils.SampleUtils;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private GridLayout mGrid;
    private GridLayout mSampleGrid;
    private int mNumRows;
    private int mNumColumns;
    private LinearLayout mMainContentLayout;
    private EditText mEditTextRows;
    private EditText mEditTextCols;
    private Button mBtnRun;

    private TextView mTextViewPathFound;
    private TextView mTextViewPathLength;
    private TextView mTextViewRowOrder;

    private TextView mSampleTextViewPathFound;
    private TextView mSampleTextViewPathLength;
    private TextView mSampleTextViewRowOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the views
        mMainContentLayout = (LinearLayout) findViewById(R.id.linear_layout_main);
        mEditTextRows = (EditText) findViewById(R.id.et_rows);
        mEditTextCols = (EditText) findViewById(R.id.et_columns);
        mBtnRun = (Button) findViewById(R.id.button_run);
        mTextViewPathFound = (TextView) findViewById(R.id.tv_pathAvailable);
        mTextViewPathLength = (TextView) findViewById(R.id.tv_pathLength);
        mTextViewRowOrder = (TextView) findViewById(R.id.tv_rowOrder);

        // Set the text size of the result text views for custom grid to the default size
        mTextViewPathFound.setTextSize(getResources().getDimension(R.dimen.text_size_default));
        mTextViewPathLength.setTextSize(getResources().getDimension(R.dimen.text_size_default));
        mTextViewRowOrder.setTextSize(getResources().getDimension(R.dimen.text_size_default));
    }

    // Generates the grid of user specified rows X user specified column
    public void generateGrid(View v) {

        try {
            mNumRows = Integer.parseInt(mEditTextRows.getText().toString());
            mNumColumns = Integer.parseInt(mEditTextCols.getText().toString());
            if ((mNumRows > 10 || mNumRows < 1) || (mNumColumns > 100 || mNumColumns < 1)) {
                deleteDataAndShowError();
            } else {
                addGridToUI(mNumRows, mNumColumns);
            }
        } catch (NumberFormatException e) {
            mNumRows = 0;
            mNumColumns = 0;
        }
    }

    /*
        Adds the user specified num rows x num columns grid to the UI
     */
    public void addGridToUI(int numRows, int numColumns) {
        // remove old grid
        if(mGrid != null)
            mMainContentLayout.removeView(mGrid);

        // Set visibility of result TextView to gone
        mTextViewPathFound.setVisibility(View.GONE);
        mTextViewPathLength.setVisibility(View.GONE);
        mTextViewRowOrder.setVisibility(View.GONE);

        mGrid = new GridLayout(this);
        mGrid.setRowCount(numRows);
        mGrid.setColumnCount(numColumns);

        mMainContentLayout.addView(mGrid, 2);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                EditText editText = new EditText(this);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER |
                        InputType.TYPE_NUMBER_FLAG_SIGNED);
                editText.setTextSize(getResources().getDimension(R.dimen.text_size_default));
                editText.setHint("0");
                mGrid.addView(editText);
            }
        }

        mBtnRun.setVisibility(View.VISIBLE);
    }

    /*
        Extracts data from the grid, run the path finding alogorithm and displays
        the result on the UI
     */
    public void solveShortestPath(View v) {
        Cell[][] grid = new Cell[mNumRows][mNumColumns];

        // Get the data entered by the user
        for (int row = 0; row < mNumRows; row++) {
            for (int col = 0; col < mNumColumns; col++) {
                EditText editText = (EditText) mGrid.getChildAt(mNumColumns * row + col);
                String text = editText.getText().toString();
                try {
                    if (!text.equals("")) {
                        int cost = Integer.parseInt(editText.getText().toString());
                        grid[row][col] = new Cell(row, col, cost);
                    } else {
                        Toast.makeText(this, getString(R.string.text_empty_cell_toast), Toast.LENGTH_LONG).show();
                        return;
                    }
                } catch (Exception e) {
                    deleteDataAndShowError();
                    return;
                }
            }
        }

        // Run the algorithm
        PathFinder pathFinder = new PathFinder(grid, mNumRows, mNumColumns);
        String[] solution = pathFinder.compute();

        // Set the solution strings to these TextViews
        mTextViewPathFound.setText(solution[0]);
        mTextViewPathLength.setText(solution[1]);
        mTextViewRowOrder.setText(solution[2]);

        // Make the TextViews visible
        mTextViewPathFound.setVisibility(View.VISIBLE);
        mTextViewPathLength.setVisibility(View.VISIBLE);
        mTextViewRowOrder.setVisibility(View.VISIBLE);
    }

    /*
        Deletes the row, column and the grid and shows an error message
     */
    public void deleteDataAndShowError() {
        mNumRows = mNumColumns = 0;
        mEditTextRows.setText("");
        mEditTextCols.setText("");
        if (mMainContentLayout.indexOfChild(mGrid) != -1)
            mMainContentLayout.removeView(mGrid);
        Toast.makeText(this, getString(R.string.text_error_toast),
                Toast.LENGTH_SHORT).show();

        mBtnRun.setVisibility(View.GONE);
    }

    /*
        Checks user input to pick the correct sample grid to run
        The correct input to pick is determined by the button that
        the user pressed
     */
    public void runSampleGrid(View v) {
        Cell[][] sampleInput;
        int sampleRows, sampleCols;

        if (v.getId() == R.id.btn_sample1) {
            // Get the sample grid 1
            sampleInput = SampleUtils.SAMPLE_GRID_1;
            sampleRows = SampleUtils.SAMPLE_GRID_1_ROWS;
            sampleCols = SampleUtils.SAMPLE_GRID_1_COLS;
        } else if (v.getId() == R.id.btn_sample2) {
            // Get the sample grid 2
            sampleInput = SampleUtils.SAMPLE_GRID_2;
            sampleRows = SampleUtils.SAMPLE_GRID_2_ROWS;
            sampleCols = SampleUtils.SAMPLE_GRID_2_COLS;
        } else {
            // Get the sample grid 3
            sampleInput = SampleUtils.SAMPLE_GRID_3;
            sampleRows = SampleUtils.SAMPLE_GRID_3_ROWS;
            sampleCols = SampleUtils.SAMPLE_GRID_3_COLS;
        }

        // Fill the GridLayout in the UI with the data inside the sample input grid
        populateAndRunSampleGrid(sampleInput, sampleRows, sampleCols);
    }

    /*
        Displays the sample grid to the user, runs the sample input and displays the result on the UI
     */
    public void populateAndRunSampleGrid(Cell[][] inputGrid, int numRows, int numCols) {
        // Remove views if any related to the sample inputs (grid and results TextViews)
        removeViews();

        // Create new views
        mSampleTextViewPathFound = new TextView(this);
        mSampleTextViewPathLength = new TextView(this);
        mSampleTextViewRowOrder = new TextView(this);
        mSampleGrid = new GridLayout(this);

        // Set the number of rows and column inside the GridLayout
        mSampleGrid.setRowCount(numRows);
        mSampleGrid.setColumnCount(numCols);

        // Add data to the sample grid from the passed grid
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                TextView textView = new TextView(this);
                // Cell padding to each TextView
                textView.setPadding((int) getResources().getDimension(R.dimen.cell_padding),
                        (int) getResources().getDimension(R.dimen.cell_padding),
                        (int) getResources().getDimension(R.dimen.cell_padding),
                        (int) getResources().getDimension(R.dimen.cell_padding));
                // Set the text size
                textView.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
                // Set the text to the cost of the cell in the grid
                textView.setText(String.valueOf(inputGrid[row][col].getCost()));
                // Caculate the position in the GridLayout to add the view to from
                // the positon in the grid
                int pos = numCols * row + col;
                // Add the TextView to the grid at the right position
                mSampleGrid.addView(textView, pos);
            }
        }

        // Run the algorithm
        PathFinder pathFinder = new PathFinder(inputGrid, numRows, numCols);
        String[] solution = pathFinder.compute();

        // Display the results
        mSampleTextViewPathFound.setText(solution[0]);
        mSampleTextViewPathLength.setText(solution[1]);
        mSampleTextViewRowOrder.setText(solution[2]);

        // Set the text size
        mSampleTextViewPathFound.setTextSize(getResources().getDimension(R.dimen.text_size_default));
        mSampleTextViewPathLength.setTextSize(getResources().getDimension(R.dimen.text_size_default));
        mSampleTextViewRowOrder.setTextSize(getResources().getDimension(R.dimen.text_size_default));

        // Add the GridLayout to the UI
        mMainContentLayout.addView(mSampleGrid);

        // Add TextViews to the UI
        mMainContentLayout.addView(mSampleTextViewPathFound);
        mMainContentLayout.addView(mSampleTextViewPathLength);
        mMainContentLayout.addView(mSampleTextViewRowOrder);
    }

    /*
        Removes all views related to the sample input
        including the sample grid and the result TextViews
     */
    public void removeViews() {
        if (mSampleGrid != null)
            mSampleGrid.removeAllViews();
        mMainContentLayout.removeView(mSampleGrid);
        mMainContentLayout.removeView(mSampleTextViewPathFound);
        mMainContentLayout.removeView(mSampleTextViewPathLength);
        mMainContentLayout.removeView(mSampleTextViewRowOrder);
    }
}