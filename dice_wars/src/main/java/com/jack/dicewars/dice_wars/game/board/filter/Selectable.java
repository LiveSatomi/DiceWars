package com.jack.dicewars.dice_wars.game.board.filter;

/**
 * This interface defines a Filterable that can also be selected. It follows the idea that something must be found
 * before it can be selected.
 */
public interface Selectable extends Filterable {

    /**
     *
     * @return The intra-phase progression state "Territory selected"
     */
    boolean isSelected();

    /**
     *
     * @param state The new selectivity of this Territory.
     */
    void setSelected(boolean state);

    /**
     *
     * @return Information on this Territory's selectivity.
     */
    boolean isSelectable();

    /**
     *
     * @param state The new selectivity of this Territory.
     */
    void setSelectable(boolean state);
}
