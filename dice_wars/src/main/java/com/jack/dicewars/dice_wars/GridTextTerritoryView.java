package com.jack.dicewars.dice_wars;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Jack Mueller on 2/28/15.
 */
public class GridTextTerritoryView extends TerritoryView {

    private int row;
    private int col;

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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
