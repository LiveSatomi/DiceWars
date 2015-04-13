package com.jack.dicewars.dice_wars;

import android.content.Context;
import android.view.View;

/**
 *
 * This class is the superclass for a game Territory, which is updated with data from the model to display Territory
 * * information to the users. It programmatically generates the Android View it needs to represent itself, rather
 * than having it defined through XML.
 */
public abstract class AbstractTerritoryView {

    /**
     *
     * @param context The MainGameActivity this territory will be used in, needed for creating Views.
     * @return A View that can display color and value information, and that will combine nicely with other Views to
     * represent a BoardView.
     */
    public abstract View defaultView(Context context);
}
