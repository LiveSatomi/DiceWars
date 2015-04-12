package com.jack.dicewars.dice_wars.game;

import java.util.Map;

/**
 * Created by Jack Mueller on 3/8/15.
 */
public class ReinforcePhase extends AbstractPhase {

    public ReinforcePhase(Player player) {
        territoryLimit = 1;
    }

    public String toString() {
        return "Reinforce Phase";
    }

    @Override
    public Map filters() {
        return null;
    }

    @Override
    protected void consume() {

    }
}
