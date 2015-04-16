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

    protected boolean pendingAction;
    protected int territoryLimit;

    @Override
    public void pushTerritory(TerritoryBorder territory) {
        // There are now Territories saved, pending for action until consumed
        pendingAction = true;

        selected.add(territory);
        // Greedily process Territories as soon as enough are selected
        if (selected.size() == territoryLimit) {
            consume();
        }

        // If *everything* was consumed, no required action is pending.
        if (selected.isEmpty()) {
            pendingAction = false;
        }
    }

    @Override
    public void undoAction() {
        // Remove last in selected list and set its selected field to false.
        selected.remove(selected.size() - 1).setSelected(false);

        // If that was the only thing in the list, no required action is pending.
        if (selected.isEmpty()) {
            pendingAction = false;
        }
    }

    /**
     * Uses territories that have pushed, usually by selection, in such a way that is defined by the implementation.
     */
    protected abstract void consume();

    public boolean getPendingAction() {
        return pendingAction;
    }
}
