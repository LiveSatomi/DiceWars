package com.jack.dicewars.dice_wars.game.board;

import com.jack.dicewars.dice_wars.TerritoryColor;
import com.jack.dicewars.dice_wars.game.NullPlayer;
import com.jack.dicewars.dice_wars.game.Player;
import com.jack.dicewars.dice_wars.game.board.filter.Filterable;
import com.jack.dicewars.dice_wars.game.board.filter.Selectable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * A Territory Border is a model fabrication that encapsulates internal {@link Territory} data when it is not needed.
 * A TerritoryBorders responsibilities revolve around how many other Territories (which actually means
 * TerritoryBorders, it is connected to.
 */
public class TerritoryBorder implements Selectable {

    /**
     * The exact number of Territories the internal Territory touches.
     */
    public static final int EDGE_MAX_COUNT = 4;
    public static final int MID_EDGE_COUNT = 4;
    public static final int SIDE_EDGE_COUNT = 3;
    public static final int CORNER_EDGE_COUNT = 2;

    private TerritoryBorder[] neighbors;

    private Territory internal;
    private boolean selected;
    private boolean selectable;

    /**
     * Creates a TerritoryBorder with an internal Territory that is owned by
     * {@link com.jack.dicewars.dice_wars.game.NullPlayer} and has no dice on it. This constructor does not connect this
     * object to any other TerritoryBorders.
     *
     * @param edgeCount How many TerritoryBorders can a Player get to from this TerritoryBorder.
     */
    public TerritoryBorder(int edgeCount) {
        internal = new Territory(new NullPlayer(), 0);
        neighbors = new TerritoryBorder[edgeCount];
        selected = false;
        selectable = false;
    }

    @Override
    public List<Filterable> adjacent() {
        return new LinkedList<Filterable>(Arrays.asList(neighbors));
    }

    @Override
    public TerritoryColor color() {
        return internal.getColor();
    }

    @Override
    public int value() {
        return internal.getValue();
    }

    /**
     *
     * @return The number of reachable TerritoryBorders there are from this TerritoryBorder.
     */
    public int numberOfNeighbors() {
        return neighbors.length;
    }

    /**
     *
     * @param neighbors Sets the TerritoryBorders that are reachable from this object.
     */
    public void setNeighbors(TerritoryBorder[] neighbors) {
        this.neighbors = neighbors;
    }

    /**
     *
     * @return The Territory that is encapsulated by this TerritoryBorder.
     */
    public Territory getInternal() {
        return internal;
    }

    /**
     *
     * @param player The Player that now owns the Territory inside of this Border
     */
    public void setOwnerOfInternal(Player player) {
        player.claimOwnership(getInternal());
    }

    /**
     *
     * @param index The index of the neighbor to set.
     * @param territory The TerritoryBorder reachable from this one.
     */
    void setNeighborAt(int index, TerritoryBorder territory) {
        neighbors[index] = territory;
    }

    /**
     * Increments the internal Territory's value by one. Useful for ReinforcePhases.
     */
    public void incrementValue() {
        internal.setValue(internal.getValue() + 1);
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean state) {
        this.selected = state;
    }

    @Override
    public boolean isSelectable() {
        return selectable;
    }

    @Override
    public void setSelectable(boolean state) {
        this.selectable = state;
    }

}
