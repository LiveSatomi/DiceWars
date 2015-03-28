package com.jack.dicewars.dice_wars.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jack Mueller on 2/28/15.
 */
public class GridTextBoard extends AbstractBoard {

    private static final int[] BOARD_SIZE_SMALL_GRID = {7, 3};
    private static final int[] BOARD_SIZE_MEDIUM_GRID = {8, 4};
    private static final int[] BOARD_SIZE_LARGE_GRID = {9, 5};
    /**
     * Defines grid sizes based on {@link #BOARD_SIZE_SMALL} variables.
     */
    public static final int[][] BOARD_SIZE_GRID = {BOARD_SIZE_SMALL_GRID, BOARD_SIZE_MEDIUM_GRID,
            BOARD_SIZE_LARGE_GRID};

    /**
     * Number of dice each Player at the start of the game in addition to their 1 per Territory.
     */
    private static final int DICE_PER_PLAYER = 6;

    private int rows;
    private int cols;

    /**
     * @param config the configuration with information about the player count and board size.
     */
    public GridTextBoard(Configuration config) {
        super(config);

        int[] dims = BOARD_SIZE_GRID[config.getBoardSize()];
        rows = dims[0];
        cols = dims[1];
    }

    /**
     * @return A 1D list of TerritoryBorders that can be translated into a grid with row major order.
     */
    protected List<TerritoryBorder> generateLayout() {

        List<TerritoryBorder> board = new LinkedList<>();

        // Create territories (isolated, how they come) and add them to the major board.

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if ((i == 0 || i == getRows() - 1) && (j == 0 || j == getCols() - 1)) {
                    // corner case
                    board.add(new TerritoryBorder(TerritoryBorder.EDGE_CORNER_COUNT));
                } else if (i == 0 || i == getRows() - 1 || j == 0 || j == getCols() - 1) {
                    // edge but not corner
                    board.add(new TerritoryBorder((TerritoryBorder.EDGE_EDGE_COUNT)));
                } else {
                    // interior territories
                    board.add(new TerritoryBorder((TerritoryBorder.EDGE_MID_COUNT)));
                }
            }
        }

        return board;
    }

    /**
     * Helper method to ensure correct connections with naive (row-major grid) Territory generation.
     *
     * @return The passed list with each TerritoryBorder now having references to the TerritoryBorders to the left,
     * top, right, and bottom of itself if applicable.
     */
    protected List<TerritoryBorder> generateGridConnections() {
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
            for (int j = 0, addedDirections = 0; j < TerritoryBorder.EDGE_MAX_COUNT && addedDirections < board.get(i)
                    .getNeighbors().length; j++) {
                if (neighborsMask[j]) {
                    board.get(i).getNeighbors()[addedDirections] = board.get(neighbors[j]).getInternal();
                }
            }
        }
        return board;
    }

    /**
     * Assigns Territories to Players by linking the internal Territory to a Color and registering the Territory with
     * the Player (for caching). Each player is giving exactly the same amount of Territories (when possible).
     * Leftovers after distributing evenly are given to random players, unless colorless territories is enabled in
     * the {@link #config}.
     */
    @Override
    protected void assignTerritories() {
        // A shallow copy of the member board that we can remove from without affecting the instance.
        List<TerritoryBorder> boardCopy = new ArrayList<>(board);

        List<Player> activePlayers = getConfig().activePlayers();
        int perPlayer = board.size() / activePlayers.size();

        final boolean colorlessTerritory = getConfig().isColorlessTerritory();
        if (colorlessTerritory) {
            perPlayer--;
        }

        Random rand = new Random();
        while (perPlayer > 0) {
            // Assign 1 territory to each player per while loop iteration
            for (int i = 0; i < activePlayers.size(); i++) {

                int randomLoc = rand.nextInt(boardCopy.size());
                TerritoryBorder current = boardCopy.remove(randomLoc);
                Player player = activePlayers.get(i);
                current.setOwnerOfInternal(player);
            }
            perPlayer--;
        }

        // Give the left over territories to some players, this will not affect the number of dice they begin with.
        // Territories that were truncated by "board.size() / activePlayers.size()"  will be assigned randomly
        int playerForExtraTerritory;
        while (!boardCopy.isEmpty() && !colorlessTerritory) {
            playerForExtraTerritory = rand.nextInt(activePlayers.size());
            boardCopy.remove(0).setOwnerOfInternal(activePlayers.get(playerForExtraTerritory));
        }
    }

    /**
     * Randomly assigns a preset number of dice based on {@link #DICE_PER_PLAYER}. Each owned territory is guaranteed
     * to have at least 1 die.
     */
    protected void assignDice() {
        List<Player> activePlayers = getConfig().activePlayers();

        // Guarantee each territory 1 die. TODO decide if territory constructor sets value to 1 (what about colorless?)
        for (Player p : activePlayers) {
            final List<Territory> territories = p.getTerritories();
            for (Territory t : territories) {
                t.setValue(1);
            }
        }

        // For each Player pick random Territories for each dice
        Random rand = new Random();
        for (Player p : activePlayers) {
            final List<Territory> territories = p.getTerritories();
            // Reset dice count for each player
            for (int diceLeft = DICE_PER_PLAYER; diceLeft > 0; diceLeft--) {
                // Pick a random territory and increment its value
                final Territory pickedTerritory = territories.get(rand.nextInt(territories.size()));
                //TODO set a guard for max value
                pickedTerritory.setValue(pickedTerritory.getValue() + 1);
            }
        }
    }

    /**
     * Helper method to do the 2D to 1D conversion of a row major grid to a list.
     *
     * @param row  the grid row
     * @param col  the grid column
     * @param cols the number of columns in the grid
     * @return the index of a 1d list representation of the element at the row and column specified.
     */
    public int coordinatesToIndex(int row, int col, int cols) {
        return row * cols + col;
    }

    /**
     * @return The number of rows of Territories this board has.
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return The number of columns of Territories this board has.
     */
    public int getCols() {
        return cols;
    }
}
