package com.jack.dicewars.dice_wars.game;

import com.jack.dicewars.dice_wars.Color;

/**
 * Created by Jack Mueller on 2/25/15.
 */
public class NullPlayer extends Player {
    public static final String STATUS_COLORLESS = "COLORLESS";
    public static final int COLOR_NULL = -1;

    /**
     * Acts as the owner to a colorless Territory. Will always lose battles, have the colorless territory color, and
     * never have an active turn.
     */
    public NullPlayer() {
        super("", STATUS_COLORLESS, Color.colorless);
    }
}
