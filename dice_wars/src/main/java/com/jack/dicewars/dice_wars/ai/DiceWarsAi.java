package com.jack.dicewars.dice_wars.ai;

import com.jack.dicewars.dice_wars.game.board.filter.Selectable;

/**
 * A DiceWarsAi can decide if it wants to continue selecting Selectables, and then request that they be selected.
 */
public interface DiceWarsAi {

    /**
     * This method will request a selection by the Game of the returned Selectable. This method will return null if
     * the AI does not deem any Selectable worthy. To ensure a non-null return value, only call this method once after
     * each time desiredSelection returns true.
     * @return The Selectable that the AI deemed worthy of being selected.
     */
    Selectable makeSelection();

    /**
     * After desiredSelection is called, the next call to makeSelection is guaranteed to be non-null.
     * @return True if this AI deems a Selectable in the Game to be worthy of Selection, false otherwise.
     */
    boolean desiredSelection();
}
