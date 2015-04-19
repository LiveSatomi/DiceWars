package com.jack.dicewars.dice_wars.game.board.filter;

/**
 * Only accepts Filterables that have an adjacent Filterable with a different color than its own.
 */
public class HostilityFilter implements Filter {

    @Override
    public boolean accepts(Filterable filterable) {
        for (Filterable neighbor : filterable.adjacent()) {
            if (neighbor.color() != filterable.color()) {
                // Found a hostile neighbor
                return true;
            }
        }
        return false;
    }
}
