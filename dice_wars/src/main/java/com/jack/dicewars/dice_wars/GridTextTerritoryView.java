package com.jack.dicewars.dice_wars;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 *
 * This implementation uses Buttons with two characters to represent its data to the users. This territory is mostly
 * for debugging purposes and for developing the model before the custom Views are finished. Buttons were chosen
 * because they are interactive and easy to change the text of out of the box.
 */
public class GridTextTerritoryView extends AbstractTerritoryView {

    /**
     * The y coordinate of this View in the grid.
     */
    private int row;
    /**
     * The x coordinate of this View in the grid.
     */
    private int col;

    /**
     * Create a GridTextTerritoryView that should be placed at [row, col] in a grid.
     * @param row this TerritoryView's row
     * @param col this TerritoryView's column
     */
    public GridTextTerritoryView(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public Button defaultView(Context context) {
        Button gridTextButton = new Button(context);
        //add properties
        final int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        final int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        gridTextButton.setLayoutParams(new LinearLayout.LayoutParams(width, height));

        gridTextButton.setId(R.id.gridTextTerritory);
        gridTextButton.setText("N0");
        return gridTextButton;
    }


    /**
     *
     * @return the row that this TerritoryView should be located in the grid.
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @return the column that this TerritoryView should be located in the grid.
     */
    public int getCol() {
        return col;
    }
}
