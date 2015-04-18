package com.jack.dicewars.dice_wars.ai;

import com.jack.dicewars.dice_wars.game.Game;
import com.jack.dicewars.dice_wars.game.board.filter.Filterable;
import com.jack.dicewars.dice_wars.game.board.filter.Selectable;

import java.util.List;

/**
 * This SimpleAi will take select the first Selectable it can get to and only attack if it has a higher attack value
 * than it's opponent.
 */
public class SimpleAi extends AbstractAi {

    private Selectable desired;
    private final int thinkingTime = 500;

    /**
     *
     * @param game The Game this AI will analyze and make decisions on. The AI has access to make selections on
     * Territories in the Game.
     */
    public SimpleAi(Game game) {
        super(game);
        desired = null;
    }

    @Override
    public Selectable makeSelection() {
        // AI has not yet been asked to see if it desires a selection
        if (desired == null) {
            if (!desiredSelection()) {
                return null;
            }
        }
        game.requestSelection(desired);
        return desired;
    }

    @Override
    public boolean desiredSelection() {
        // Waiting time
        try {
            Thread.sleep(thinkingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TODO better phase equality
        if (!game.currentPhase().toString().equals("Attack Phase")) {
            return false;
        }

        final List<Selectable> selectables = game.allSelectable();
        if (selectables.isEmpty()) {
            return false;
        }
        Selectable potential = selectables.get(0);
        for (Filterable neighbor : potential.adjacent()) {
            if (neighbor.value() < potential.value()) {
                desired = potential;
                return true;
            }
        }
        return false;
    }
}
