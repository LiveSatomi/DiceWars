package com.jack.dicewars.dice_wars.game.progression;

import com.jack.dicewars.dice_wars.game.Player;
import com.jack.dicewars.dice_wars.game.board.TerritoryBorder;

import java.util.LinkedList;
import java.util.List;

/**
 * A Phase that can keep track of Territories to process at a given limit that is controlled by a specific Player.
 */
public abstract class AbstractPhase implements Phase {

    protected Player player;

    protected final List<TerritoryBorder> selected = new LinkedList<>();
    protected int territoryLimit;

    @Override
    public void pushTerritory(TerritoryBorder territory) {
        selected.add(territory);
        // Greedily process Territories as soon as enough are selected
        if (selected.size() == territoryLimit) {
            consume();
        }
    }

    /**
     * Uses territories that have pushed, usually by selection, in such a way that is defined by the implementation.
     */
    protected abstract void consume();
}
