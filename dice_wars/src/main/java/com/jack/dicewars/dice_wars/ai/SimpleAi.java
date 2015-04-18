package com.jack.dicewars.dice_wars.ai;

import com.jack.dicewars.dice_wars.game.Game;
import com.jack.dicewars.dice_wars.game.board.TerritoryBorder;
import com.jack.dicewars.dice_wars.game.board.filter.Filterable;
import com.jack.dicewars.dice_wars.game.board.filter.Selectable;

import java.util.List;

/**
 * Created by Jack Mueller on 4/18/15.
 */
public class SimpleAi extends AbstractAi {

    Selectable desired;

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

    //Side Effect
    public boolean desiredSelection() {
        // Waiting time
        try {
            Thread.sleep(500);
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
