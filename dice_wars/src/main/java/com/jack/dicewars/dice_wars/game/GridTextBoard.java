package com.jack.dicewars.dice_wars.game;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jack Mueller on 2/28/15.
 *
 */
public class GridTextBoard extends Board {


    private final int cols = 3;
    private final int rows = 7;


    public GridTextBoard(Configuration config) {
        super(config);
    }

    protected List<TerritoryBorder> generateLayout() {

        List<TerritoryBorder> board = new LinkedList<>();

        // Create territories (isolated, how they come) and add them to the major board.

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if((i == 0 || i == rows - 1) && (j == 0 || j == cols - 1)) {
                    // corner case
                    board.add(new TerritoryBorder(TerritoryBorder.EDGE_CORNER_COUNT));
                } else if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
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
            int rightIndex = (rowIndex) * cols + (colIndex + 1);
            int downIndex = (rowIndex + 1) * cols + (colIndex);
            int leftIndex = (rowIndex) * cols + (colIndex - 1);
            int upIndex = (rowIndex - 1) * cols + (colIndex);
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

}
