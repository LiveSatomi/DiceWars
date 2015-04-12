package com.jack.dicewars.dice_wars.game;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jack Mueller on 4/12/15.
 */
public abstract class AbstractPhase implements Phase {

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

    protected abstract void consume();
}
