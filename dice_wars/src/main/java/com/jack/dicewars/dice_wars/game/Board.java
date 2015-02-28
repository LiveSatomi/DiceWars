package com.jack.dicewars.dice_wars.game;

import java.util.*;

/**
 * Created by Jack Mueller on 2/23/15.
 *
 * A board defines connections between Territories, it has different constraints based on its size.
 */
public abstract class Board {

    public static final int BOARD_SIZE_SMALL = 0;
    public static final int BOARD_SIZE_MEDIUM = 1;
    public static final int BOARD_SIZE_LARGE = 2;

    /**
     * A access to the same Configuration that the Game has. TODO This will be used repeatedly?
     */
    private Configuration config;

    /**
     * A list of the existing borders (one for each Territory)
     */
    private List<TerritoryBorder> board;

    public Board(Configuration config) {
        this.config = config;

    }

    /**
     * Puts the board in an appropriate state to start the game. Assigns Players some Territories, and values to those
     * Territories. Amount of territories that are assigned are based on the amount of players and the value of
     * {@Configuration#colorlessTerritories}.
     *
     * @param config the same Configuration that was used to create this board.
     */
    public void startState(Configuration config) {
        // Supply the board with isolated Territories
        board = generateLayout();
        // Connect the Territories through their TerritoryBorders
        board = generateGridConnections(board);
    }

    protected abstract List<TerritoryBorder> generateLayout();

    /**
     * This helper method finishes the instantiation of the TerritoryBorders in board by connecting
     * adding to {@link TerritoryBorder#neighbors} in a way defined by the subclass' implementation.
     * @param board
     */
    protected abstract List<TerritoryBorder> generateGridConnections(List<TerritoryBorder> board);
}