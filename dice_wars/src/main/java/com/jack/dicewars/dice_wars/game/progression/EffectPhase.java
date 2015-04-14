package com.jack.dicewars.dice_wars.game.progression;

import com.jack.dicewars.dice_wars.game.Player;
import com.jack.dicewars.dice_wars.game.board.Filter;

import java.util.HashSet;
import java.util.Map;

/**
 * An effect phase. Can have many effects.
 */
public class EffectPhase extends AbstractPhase {

    /**
     * TODO implement.
     * @param player The player controlling the effects
     */
    public EffectPhase(Player player) {
        territoryLimit = -1;
    }

    @Override
    public String toString() {
        return "Effect Phase";
    }

    @Override
    public HashSet<Filter> filters() {
        return null;
    }

    @Override
    protected void consume() {

    }
}
