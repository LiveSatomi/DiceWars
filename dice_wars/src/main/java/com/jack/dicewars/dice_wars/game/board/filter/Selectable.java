package com.jack.dicewars.dice_wars.game.board.filter;

import com.jack.dicewars.dice_wars.game.board.filter.Filterable;

/**
 * Created by Jack Mueller on 4/18/15.
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
