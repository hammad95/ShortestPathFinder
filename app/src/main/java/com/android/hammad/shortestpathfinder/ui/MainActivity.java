package com.android.hammad.shortestpathfinder.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.hammad.shortestpathfinder.models.Cell;
import com.android.hammad.shortestpathfinder.models.PathFinder;
import com.android.hammad.shortestpathfinder.R;

public class MainActivity extends AppCompatActivity {

    private GridLayout mGrid;
    private int mNumRows;
    private int mNumColumns;
    private LinearLayout mMainContentLayout;
    private EditText mEditTextRows;
    private EditText mEditTextCols;
    private Button mBtnRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Get the views
        mMainContentLayout = (LinearLayout) findViewById(R.id.linear_layout_main);

        mEditTextRows = (EditText) findViewById(R.id.et_rows);
        mEditTextCols = (EditText) findViewById(R.id.et_columns);

        mBtnRun = (Button) findViewById(R.id.button_run);
    }

    // Generates the grid of user specified rows X user specified column
    public void generateGrid(View v) {

        try {
            mNumRows = Integer.parseInt(mEditTextRows.getText().toString());
            mNumColumns = Integer.parseInt(mEditTextCols.getText().toString());
            if ((mNumRows > 10 || mNumRows < 1) || (mNumColumns > 100 || mNumColumns < 5)) {
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

        if (mGrid != null) {
            mMainContentLayout.removeView(mGrid);
        }

        mGrid = new GridLayout(this);
        mGrid.setRowCount(numRows);
        mGrid.setColumnCount(numColumns);

        mMainContentLayout.addView(mGrid, 2);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                EditText editText = new EditText(this);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER |
                        InputType.TYPE_NUMBER_FLAG_DECIMAL |
                        InputType.TYPE_NUMBER_FLAG_SIGNED);
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
        TextView textViewPathAvailable = (TextView) findViewById(R.id.tv_pathAvailable);
        TextView textViewPathLength = (TextView) findViewById(R.id.tv_pathLength);
        TextView textViewRowOrder = (TextView) findViewById(R.id.tv_rowOrder);

        for (int row = 0; row < mNumRows; row++) {
            for (int col = 0; col < mNumColumns; col++) {
                EditText editText = (EditText) mGrid.getChildAt(mNumColumns * row + col);
                String text = editText.getText().toString();
                try {
                    if (!text.equals("")) {
                        int cost = Integer.parseInt(editText.getText().toString());
                        grid[row][col] = new Cell(row, col, cost);
                    } else {
                        deleteDataAndShowError();
                        return;
                    }
                } catch (Exception e) {
                    deleteDataAndShowError();
                    return;
                }
            }
        }

        PathFinder pathFinder = new PathFinder(grid, mNumRows, mNumColumns);
        String[] solution = pathFinder.compute();

        textViewPathAvailable.setText(solution[0]);
        textViewPathLength.setText(solution[1]);
        textViewRowOrder.setText(solution[2]);

        textViewPathAvailable.setVisibility(View.VISIBLE);
        textViewPathLength.setVisibility(View.VISIBLE);
        textViewRowOrder.setVisibility(View.VISIBLE);
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
}