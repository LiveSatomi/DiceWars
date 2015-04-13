package com.jack.dicewars.dice_wars.game.progression;

import com.jack.dicewars.dice_wars.game.board.TerritoryBorder;

import java.util.Map;

/**
 * Created by Jack Mueller on 3/8/15.
 */
public interface Phase {

    //TODO pass in R.string.phaseName to constructor for localization
    /**
     *
     * @return A friendly description of what happens during this phase.
     */
    @Override
    public String toString();

    public Map filters(); // TODO implement

    /**
     * Appends territories to the end of a list for well ordered consumption by Phase implementation.
     * @param territory Territory that will be consumed later
     */
    void pushTerritory(TerritoryBorder territory);
}
