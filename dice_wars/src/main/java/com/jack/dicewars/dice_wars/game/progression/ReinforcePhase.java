package com.jack.dicewars.dice_wars.game.progression;

import com.jack.dicewars.dice_wars.game.Player;
import com.jack.dicewars.dice_wars.game.board.filter.Filter;

import java.util.HashSet;

/**
 * A Phase that is meant to add value to Territories incrementally. It can also calculate how much value should be
 * allocated to the given Player on the current Phase.
 */
public class ReinforcePhase extends AbstractPhase {

    /**
     * Creates a Reinforcement phase controlled by the current player that will put 1 dice on each Territory selected
     * until the dice allocated run out.
     * @param player The player controlling the phase
     */
    public ReinforcePhase(Player player) {
        territoryLimit = 1;
    }

    @Override
    public String toString() {
        return "Reinforce Phase";
    }

    @Override
    public HashSet<Filter> filters() {
        return new HashSet<>();
    }

    @Override
    protected void consume() {

    }
}
