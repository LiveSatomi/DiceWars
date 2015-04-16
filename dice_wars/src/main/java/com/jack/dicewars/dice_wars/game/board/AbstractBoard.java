package com.jack.dicewars.dice_wars.game.board;

import android.util.Log;
import com.jack.dicewars.dice_wars.game.Configuration;
import com.jack.dicewars.dice_wars.game.Game;
import com.jack.dicewars.dice_wars.game.board.filter.Filter;
import com.jack.dicewars.dice_wars.game.board.filter.Filterable;

import java.util.HashSet;
import java.util.List;

/**
 *
 * A board defines connections between Territories, it has different constraints based on its size, amount of
 * Players, and other factors in Configuration. It has many initial states for each set of constraints.
 */
public abstract class AbstractBoard {

    public static final int BOARD_SIZE_SMALL = 0;
    public static final int BOARD_SIZE_MEDIUM = 1;
    public static final int BOARD_SIZE_LARGE = 2;
    /**
     * Number of dice each Player at the start of the game in addition to their 1 per Territory.
     */
    protected static final int DICE_PER_PLAYER = 6;

    private Game game;
    protected Configuration config;

    /**
     * A well ordered list of TerritoryBorders, which also define well ordered connections between themselves. The
     * specific order is determined by the subclass implementation.
     */
    protected List<TerritoryBorder> board;

    /**
     * Saves the configuration used for making this game because it is important for balancing the number of Players
     * that start with Territories.
     * @param game the game this board is contained in.
     * @param config the configuration which defines constraints for the start states of this board.
     */
    public AbstractBoard(Game game, Configuration config) {
        this.game = game;
        this.config = config;
    }

    /**
     * Puts the board in an appropriate state to start the game. Assigns Players some Territories, and values to those
     * Territories. Amount of territories that are assigned are based on the amount of players and the value of
     * {@link com.jack.dicewars.dice_wars.game.Configuration#colorlessTerritory}.
     *
     */
    public void startState() {
        // Supply the board with isolated Territories
        board = generateLayout();
        // Connect the Territories through their TerritoryBorders
        board = generateGridConnections();
        // Assign players and values
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

    /**
     * Assigns Territories to Players by linking the internal Territory to a Color and registering the Territory with
     * the Player (for caching). Each player is giving exactly the same amount of Territories (when possible).
     * Leftovers after distributing evenly are given to random players, unless colorless territories is enabled in
     * the {@link #config}.
     */
    protected abstract void assignTerritories();

    /**
     * Randomly assigns a preset number of dice based on {@link #DICE_PER_PLAYER}. Each owned territory is guaranteed
     * to have at least 1 die.
     */
    protected abstract void assignDice();

    /**
     * @param territory The territory requesting to be selected based on the click of it's TerritoryView
     */
    public void requestSelection(TerritoryBorder territory) {
        game.requestSelection(territory);
    }

    /**
     * A list of the existing borders (one for each Territory).
     *
     * @return The list of TerritoryBorder objects in an order defined by a subclass implementation.
     */
    public List<TerritoryBorder> getBoard() {
        return board;
    }

    /**
     * Checks territory for the criteria defined in Map. Possible types criteria and the form of their value is
     * defined by TODO define this thing.
     *
     * @param territory The territory being checked by filters
     * @param filters The criteria to check the territory by
     * @return Whether the territory meets the criteria in filters.
     */
    public boolean passesFilter(Filterable territory, HashSet<Filter> filters) {
        for (Filter filter : filters) {
            if (!filter.accepts(territory)) {
                return false;
            }
        }
        return true;
    }
}
