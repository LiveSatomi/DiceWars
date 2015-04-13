package com.jack.dicewars.dice_wars.game;

import com.jack.dicewars.dice_wars.Color;

/**
 *
 * Acts as the owner to a colorless Territory. Will always lose battles, have the colorless territory color, and
 * never has an active turn.
 */
public class NullPlayer extends Player {
    public static final String STATUS_COLORLESS = "COLORLESS";

    /**
     * A typical NullPlayer has a 0 length name, a special status, and uses the Colorless Color enum.
     */
    public NullPlayer() {
        super("", STATUS_COLORLESS, Color.colorless);
    }
}
