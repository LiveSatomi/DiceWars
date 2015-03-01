package com.jack.dicewars.dice_wars;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Space;
import com.jack.dicewars.dice_wars.game.Board;
import com.jack.dicewars.dice_wars.game.GridTextBoard;
import com.jack.dicewars.dice_wars.game.Territory;
import com.jack.dicewars.dice_wars.game.TerritoryBorder;

import java.util.*;

/**
 * Created by Jack Mueller on 2/28/15.
 */
public class GridTextBoardView extends BoardView {

    private Context context;

    private int rows;
    private int cols;

    public GridTextBoardView(Board b, Context context) {
        GridTextBoard board = (GridTextBoard) b;

        this.context = context;
        rows = board.getRows();
        cols = board.getCols();

        final List<TerritoryBorder> grid = board.getBoard();

        // Sorted in order of addition
        territoryViewMap = new HashMap<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                TerritoryView territoryView = new GridTextTerritoryView(i, j);
                Button gridTextButton = (Button) territoryView.defaultView(context);

                final Territory internal = grid.get(board.coordinatesToIndex(i, j, cols)).getInternal();
                final Color color = internal.getColor();
                gridTextButton.setText(color.getCode() + internal.getValue());

                territoryViewMap.put(territoryView, gridTextButton);
            }
        }
    }

    public View apply(ViewGroup viewPort) {

        // set up rows and spaces to put the buttons between
        ViewGroup container = createSkeleton(viewPort);
        ((GridLayout) container).setRowCount(rows);
        // Only one LinearLayout per row, but the same number of rows as the model. Columns are defined by the number
        // of Space views.
        ((GridLayout) container).setColumnCount(1);

        // loop through all territories and places the corresponding View in the skeleton
        for (Map.Entry<TerritoryView, View> territoryMapping : territoryViewMap.entrySet()) {
            // get a reference to the model's row and column
            final GridTextTerritoryView territory = (GridTextTerritoryView) territoryMapping.getKey();
            final int modelRow = territory.getRow();
            final int modelCol = territory.getCol();
            // The correct LinearLayout of the viewport for this territory
            final ViewGroup row = (ViewGroup) container.getChildAt(modelRow);
            // Place the button at the correct index, accounting for spacers and already placed buttons.
            // Keeps track of all elements
            int adjustedCol= 0;
            // Keeps track of spaces, break after the nth spacer where n is the model column of the territory.
            int spaces = 0;
            while (spaces <= modelCol) {
                if(row.getChildAt(adjustedCol).getId() == R.id.gridTextTerritorySpace) {
                    spaces++;
                }
                adjustedCol++;
            }

            // Add to row with the adjusted column
            row.addView(territoryMapping.getValue(), adjustedCol);

        }
        return container;
    }

    private ViewGroup createSkeleton(ViewGroup container) {

        for (int i = 0; i < rows; i++) {
            final ViewGroup row = createRow();
            // Add 1 more space than columns because there should be a space on the beginning, end, and between all
            // buttons
            for (int j = 0; j < cols + 1; j++) {
                row.addView(createSpace());
            }
            container.addView(row);
        }
        return container;
    }

    
    private ViewGroup createRow() {
        final LinearLayout row = new LinearLayout(context);
        //add properties
        final int width = LinearLayout.LayoutParams.MATCH_PARENT;
        final int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        row.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        row.setOrientation(LinearLayout.HORIZONTAL);

        return row;
    }

    private Space createSpace() {
        final Space space = new Space(context);
        //add properties
        space.setId(R.id.gridTextTerritorySpace);
        final int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        final int height = LinearLayout.LayoutParams.MATCH_PARENT;
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.weight = 1;
        space.setLayoutParams(params);

        return space;
    }
}
