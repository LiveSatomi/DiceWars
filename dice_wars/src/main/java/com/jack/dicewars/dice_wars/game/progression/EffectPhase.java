package com.jack.dicewars.dice_wars.game.progression;

import com.jack.dicewars.dice_wars.game.Player;
import com.jack.dicewars.dice_wars.game.progression.AbstractPhase;

import java.util.Map;

/**
 * Created by Jack Mueller on 3/8/15.
 */
public class EffectPhase extends AbstractPhase {

    public EffectPhase(Player player) {
        territoryLimit = -1;
    }

    public String toString() {
        return "Effect Phase";
    }

    @Override
    public Map filters() {
        return null;
    }

    @Override
    protected void consume() {

    }
}
