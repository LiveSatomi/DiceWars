package com.jack.dicewars.dice_wars.ai;

import com.jack.dicewars.dice_wars.game.Game;
import com.jack.dicewars.dice_wars.game.board.AbstractBoard;
import com.jack.dicewars.dice_wars.game.board.filter.Selectable;

/**
 * Created by Jack Mueller on 4/17/15.
 */
public abstract class AbstractAi implements DiceWarsAi {
    Game game;
    AbstractBoard board;

    public AbstractAi(Game game) {
        this.game = game;
        this.board = game.getBoard();
    }

    public abstract Selectable makeSelection();

    public abstract boolean desiredSelection();
}
