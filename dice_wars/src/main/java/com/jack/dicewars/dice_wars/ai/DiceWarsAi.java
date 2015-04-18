package com.jack.dicewars.dice_wars.ai;

import com.jack.dicewars.dice_wars.game.board.filter.Filterable;

/**
 * Created by Jack Mueller on 4/17/15.
 */
public interface DiceWarsAi {
    public abstract Filterable makeSelection();

    public boolean desiredSelection();
}
