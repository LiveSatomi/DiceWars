package com.jack.dicewars.dice_wars.game;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jack Mueller on 2/28/15.
 *
 */
public class GridTextBoard extends Board {


    private int cols = 3;
    private int rows = 7;


    public GridTextBoard(Configuration config) {
        super(config);
    }

    protected List<TerritoryBorder> generateLayout() {

        List<TerritoryBorder> board = new LinkedList<>();

        // Create territories (isolated, how they come) and add them to the major board.

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if((i == 0 || i == getRows() - 1) && (j == 0 || j == getCols() - 1)) {
                    // corner case
                    board.add(new TerritoryBorder(TerritoryBorder.EDGE_CORNER_COUNT));
                } else if (i == 0 || i == getRows() - 1 || j == 0 || j == getCols() - 1) {
                    // edge but not corner
                    board.add(new TerritoryBorder((TerritoryBorder.EDGE_EDGE_COUNT)));
                } else {
                    board.add(new TerritoryBorder((TerritoryBorder.EDGE_MID_COUNT)));
                }
            }
        }

        return board;
    }

    /**
     * Helper method to ensure correct connections with naive (row-major grid) Territory generation.
     * @param board A board with incomplete instantiation of its TerritoryBorder objects; the neighbors array is empty.
     */
    protected List<TerritoryBorder> generateGridConnections(List<TerritoryBorder> board) {
        for (int i = 0; i < board.size(); i++) {
            // linear index to XY index
            int rowIndex = i / cols;
            int colIndex = i % cols;

            // 4 possible neighbors in right, down, left, up order
            int rightIndex = coordinatesToIndex(rowIndex, colIndex + 1, cols);
            int downIndex = coordinatesToIndex(rowIndex + 1, colIndex, cols);
            int leftIndex = coordinatesToIndex(rowIndex, colIndex - 1, cols);
            int upIndex = coordinatesToIndex(rowIndex - 1, colIndex, cols);
            int[] neighbors = {rightIndex, downIndex, leftIndex, upIndex};

            // mask out impossible neighbors
            boolean rightPossible = colIndex + 1 < cols;
            boolean downPossible = rowIndex + 1 < rows;
            boolean leftPossible = colIndex - 1 > 0;
            boolean upPossible = rowIndex - 1 > 0;
            boolean[] neighborsMask = {rightPossible, downPossible, leftPossible, upPossible};

            // Add neighbors that exist to this territory's neighbor list
            for (int j = 0, addedDirections = 0; j < TerritoryBorder.EDGE_MAX_COUNT &&
                    addedDirections < board.get(i).getNeighbors().length; j++) {
                if (neighborsMask[j]) {
                    board.get(i).getNeighbors()[addedDirections] = board.get(neighbors[j]).getInternal();
                }
            }
        }
        return board;
    }

    /**
     * Helper method to do the 2D to 1D conversion of a row major grid to a list.
     * @param row the grid row
     * @param col the grid column
     * @param cols the number of columns in the grid
     * @return the index of a 1d list representation of the element at the row and column specified.
     */
    public int coordinatesToIndex(int row, int col, int cols) {
        return row * cols + col;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    /**
     * Debug. Called by bespoke options only.
     * @param cols
     */
    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
