package com.jack.dicewars.dice_wars.game.board.filter;

/**
 * Contract for all Filters to follow. A Filter can accept or reject any Filterable based on any criteria that can
 * define a Filterable.
 */
public interface Filter {
    /**
     * Checks the passed Filterable for properties based on the implementation.
     * @param filterable The Filterable to check properties of.
     * @return True if the passed Filterable passes the property checks, false otherwise.
     */
    boolean accepts(Filterable filterable);
}


























