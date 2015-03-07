package com.jack.dicewars.dice_wars.game;

import java.util.List;

/**
 * Created by Jack Mueller on 2/23/15.
 *
 * A board defines connections between Territories, it has different constraints based on its size, amount of
 * Players, and other factors in Configuration. It has many initial states for each set of constraints.
 */
public abstract class AbstractBoard {

    public static final int BOARD_SIZE_SMALL = 0;
    public static final int BOARD_SIZE_MEDIUM = 1;
    public static final int BOARD_SIZE_LARGE = 2;

    private Configuration config;

    /**
     * A well ordered list of TerritoryBorders, which also define well ordered connections between themselves. The
     * specific order is determined by the subclass implementation.
     */
    protected List<TerritoryBorder> board;

    /**
     * Saves the configuration used for making this game because it is important for balancing the number of Players
     * that start with Territories.
     * @param config the configuration which defines constraints for the start states of this board.
     */
    public AbstractBoard(Configuration config) {
        this.config = config;

    }

    /**
     * Puts the board in an appropriate state to start the game. Assigns Players some Territories, and values to those
     * Territories. Amount of territories that are assigned are based on the amount of players and the value of
     * {@link com.jack.dicewars.dice_wars.game.Configuration#colorlessTerritory}.
     *
     * @param config the same Configuration that was used to create this board.
     */
    public void startState(Configuration config) {
        // Supply the board with isolated Territories
        board = generateLayout();
        // Connect the Territories through their TerritoryBorders
        board = generateGridConnections();
        //TODO assign players and values
        board = assignFairly();
    }


    /**
     *
     * @return A well ordered list of TerritoryBorders.
     */
    protected abstract List<TerritoryBorder> generateLayout();

    /**
     * This helper method finishes the instantiation of the TerritoryBorders in this Board by connecting to {@link
     * TerritoryBorder#neighbors} in a way defined by the subclass' implementation.
     *
     * @return The updated List of TerritoryBorders, which now have data on their neighbors in a well defined structure.
     */
    protected abstract List<TerritoryBorder> generateGridConnections();

    /**
     * Attempts to distribute territories to all available Players fairly and gives all Players an equal number of
     * dice to begin the game. Some territories will be assigned to {@link com.jack.dicewars.dice_wars.game
     * .NullPlayer} if and only if {@link Configuration#colorlessTerritory} is true for this Board's config.
     * @return A list of TerritoryBorders where all Players control an approximately equal number of Territories and an
     * equal number of dice.
     */
    protected List<TerritoryBorder> assignFairly() {
        assignTerritories();
        
        assignDice();

        return board;
    }

    protected abstract void assignTerritories();

    protected abstract void assignDice();

    /**
     * A list of the existing borders (one for each Territory).
     *
     * @return The list of TerritoryBorder objects in an order defined by a subclass implementation.
     */
    public List<TerritoryBorder> getBoard() {
        return board;
    }

    /**
     * A access to the same Configuration that the Game has. TODO This will be used repeatedly?
     */
    protected Configuration getConfig() {
        return config;
    }
}
