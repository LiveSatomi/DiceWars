package com.jack.dicewars.dice_wars.game.progression;

import com.jack.dicewars.dice_wars.game.Player;
import com.jack.dicewars.dice_wars.game.board.TerritoryBorder;
import com.jack.dicewars.dice_wars.game.board.filter.ColorFilter;
import com.jack.dicewars.dice_wars.game.board.filter.Filter;
import com.jack.dicewars.dice_wars.game.board.filter.ValueFilter;

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
        this.player = player;
    }

    @Override
    public String toString() {
        return "Reinforce Phase";
    }

    @Override
    public HashSet<Filter> filters() {
        HashSet<Filter> filters = new HashSet<>();

        // Only reinforce your own Territories
        filters.add(new ColorFilter(player.getTerritoryColor(), true));
        // Cannot Reinforce Territories at max value
        filters.add(new ValueFilter(player.getMaxValue(), false));
        return filters;
    }

    @Override
    protected void consume() {
        TerritoryBorder buffed = selected.remove(0);
        buffed.setSelected(false);
        buffed.incrementValue();
    }
}
