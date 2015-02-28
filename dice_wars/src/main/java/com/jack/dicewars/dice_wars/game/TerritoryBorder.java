package com.jack.dicewars.dice_wars.game;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jack Mueller on 2/25/15.
 */
public class TerritoryBorder {

    /**
     * The exact number of Territories the internal Territory touches.
     */
    public static final int EDGE_MAX_COUNT = 4;
    public static final int EDGE_MID_COUNT = 4;
    public static final int EDGE_EDGE_COUNT = 3;
    public static final int EDGE_CORNER_COUNT = 2;

    private Territory[] neighbors;

    private Territory internal;

    public TerritoryBorder(int edgeCount) {
        internal = new Territory(new NullPlayer(), 0);
        neighbors = new Territory[edgeCount];
    }


    public Territory[] getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Territory[] neighbors) {
        this.neighbors = neighbors;
    }

    public Territory getInternal() {
        return internal;
    }
}
