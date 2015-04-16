package com.jack.dicewars.dice_wars;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.jack.dicewars.dice_wars.game.board.AbstractBoard;
import com.jack.dicewars.dice_wars.game.board.TerritoryBorder;

import java.util.Map;

/**
 *
 * This class is the superclass for a game Board, which defines basic responsibilities like generating random versions
 * of itself within Configuration parameters and responding to user input. It also holds a ties the {@link com.jack
 * .dicewars.dice_wars.TerritoryView} to the actual Android View that represents it through the Activity.
 */
public abstract class AbstractBoardView {

    /**
     * This is the {@link com.jack.dicewars.dice_wars.MainGameActivity} that the board is being used on.
     */
    protected Context context;

    protected AbstractBoard board;

    protected Map<AbstractTerritoryView, View> territoryNativeViewMap;
    protected Map<TerritoryBorder, AbstractTerritoryView> modelTerritoryMap;

    /**
     * Puts programmatically generated views into the supplied viewPort based on the {@link #territoryNativeViewMap} and
     * any other Views that are defined by the subclass implementation.
     * @param viewPort The entire container allocated to viewing the board.
     * @return A modified version of the viewPort that has a representation of territoryNativeViewMap as its child
     * nodes.
     */
    public abstract View apply(ViewGroup viewPort);

    /**
     * Updates all Views in modelTerritoryMap that are also in territoryNativeViewMap.
     */
    public void updateViews() {
        for (Map.Entry<TerritoryBorder, AbstractTerritoryView> entry : modelTerritoryMap.entrySet()) {
            updateView(entry.getKey());
        }
    }

    public void updateUserPrimaryAction() {
        final View primaryButton = ((Activity) context).findViewById(R.id.phaseEnd);
        ((Button) primaryButton).setText(context.getResources().getString(board.getPrimaryUserActionId()));
    }

    /**
     *
     * @param modelKey The model data used to find View to update the properties of.
     */
    protected abstract void updateView(TerritoryBorder modelKey);

}
