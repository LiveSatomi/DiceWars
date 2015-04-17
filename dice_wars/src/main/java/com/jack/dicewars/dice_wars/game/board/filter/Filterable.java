package com.jack.dicewars.dice_wars.game.board.filter;

import com.jack.dicewars.dice_wars.TerritoryColor;

import java.util.List;

/**
 * A Filterable implements some or all of the below functions so that users of a Filterable may query Filterables by
 * these functions that have direct relation to properties in the Filterable. If an implmentation of Filterable does
 * not have a property that relates to a function, it may throw a UnsupportedOperationException.
 */
public interface Filterable {

    /**
     *
     * @return A list of all the Filterables that this Filterable has a reference to.
     */
    List<Filterable> adjacent();

    //TODO change this to a FilterColor
    /**
     *
     * @return A color that can match or not match other colors.
     */
    TerritoryColor color();

    /**
     *
     * @return This Filterable's self reported value as an integer.
     */
    int value();

}
