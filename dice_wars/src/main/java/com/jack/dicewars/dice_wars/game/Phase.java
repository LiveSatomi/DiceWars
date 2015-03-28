package com.jack.dicewars.dice_wars.game;

/**
 * Created by Jack Mueller on 3/8/15.
 */
public interface Phase {

    //TODO pass in R.string.phaseName to constructor for localization
    /**
     *
     * @return A friendly description of what this happens during this phase.
     */
    @Override
    public String toString();
}
