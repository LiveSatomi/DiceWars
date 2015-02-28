package com.jack.dicewars.dice_wars.game;

/**
 * Created by Jack Mueller on 2/25/15.
 */
public class NullPlayer extends Player{
    public static final String STATUS_COLORLESS = "COLORLESS";
    public static final int COLOR_NULL = -1;

    public NullPlayer() {
        super("", STATUS_COLORLESS, COLOR_NULL);
    }
}
