package com.jack.dicewars.dice_wars.game.board.filter;

/**
 * Implementation of Filter that accepts all Filterables near a target Filterable.
 */
public class AdjacentFilter implements Filter {

    /**
     * This Filterable is reachable by all accepted Filterables.
     */
    private Filterable target;

    /**
     *
     * @param target The Filterable that must be reachable by accepted Filterables.
     */
    public AdjacentFilter(Filterable target) {
        this.target = target;

    }

    /**
     * Uses Filterable.adjacent to match a target to another Filterable.
     * @param filterable The filterable that may be adjacent to target
     * @return True if any of the target's adjacent Filterables are the passed filterable, false otherwise.
     */
    @Override
    public boolean accepts(Filterable filterable) {
        for (Filterable other : target.adjacent()) {
            if (other == filterable) {
                return true;
            }
        }
        return false;
    }
}
