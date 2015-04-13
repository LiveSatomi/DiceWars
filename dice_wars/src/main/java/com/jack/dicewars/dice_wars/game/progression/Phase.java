package com.jack.dicewars.dice_wars.game.progression;

import com.jack.dicewars.dice_wars.game.board.TerritoryBorder;

import java.util.Map;

/**
 * A phase that knows about what is occurring during it, how to keep track of territories, and what kinds of
 * territories it wants to keep track of.
 */
public interface Phase {

    //TODO pass in R.string.phaseName to constructor for localization
    /**
     *
     * @return A friendly description of what happens during this phase.
     */
    @Override
    String toString();

    /**
     * Returns a set of filters on data that can be queried from a Board and Territories. The possible criteria and the
     * form of their value is defined by TODO define this thing.*
     * TODO implement
     * @return A set of criteria
     */
    Map filters();

    /**
     * Appends territories to the end of a list for well ordered consumption by Phase implementation.
     * @param territory Territory that will be consumed later
     */
    void pushTerritory(TerritoryBorder territory);
}
