package com.jack.dicewars.dice_wars;

import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

/**
 * Created by Jack Mueller on 2/28/15.
 */
public abstract class BoardView {

    protected Map<TerritoryView, View> territoryViewMap;

    /**
     * Applies the {@link #territoryViewMap} to the passed boardContainer which is the view port for the board in the
     * game.
     * @param boardContainer The entire container allocated to viewing the board.
     * @return A modified version of the boardContainer that has a representation of territoryViewMap as its child
     * nodes.
     */
    public abstract View apply(ViewGroup boardContainer);

}
