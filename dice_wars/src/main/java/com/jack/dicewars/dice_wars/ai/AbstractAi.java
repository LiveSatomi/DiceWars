package com.jack.dicewars.dice_wars.ai;

import com.jack.dicewars.dice_wars.game.Game;
import com.jack.dicewars.dice_wars.game.board.AbstractBoard;
import com.jack.dicewars.dice_wars.game.board.filter.Selectable;

/**
 * This superclass of DiceWars AI takes a Game to analyze and request selections from.
 */
public abstract class AbstractAi implements DiceWarsAi {
    protected Game game;
    protected AbstractBoard board;

    /**
     *
     * @param game The Game this AI will analyze and make decisions on. The AI has access to make selections on
     * Territories in the Game.
     */
    public AbstractAi(Game game) {
        this.game = game;
        this.board = game.getBoard();
    }

    @Override
    public abstract Selectable makeSelection();

    @Override
    public abstract boolean desiredSelection();
}
