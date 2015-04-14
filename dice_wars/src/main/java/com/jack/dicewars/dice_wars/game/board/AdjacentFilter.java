package com.jack.dicewars.dice_wars.game.board;

/**
 * Created by Jack Mueller on 4/13/15.
 */
public class AdjacentFilter implements Filter {

    private Filterable target;

    public AdjacentFilter(Filterable t) {
        target = t;

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
