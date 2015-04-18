package com.jack.dicewars.dice_wars.game.progression;

import android.util.Log;
import com.jack.dicewars.dice_wars.Debug;
import com.jack.dicewars.dice_wars.game.Player;
import com.jack.dicewars.dice_wars.game.board.filter.AdjacentFilter;
import com.jack.dicewars.dice_wars.game.board.filter.ColorFilter;
import com.jack.dicewars.dice_wars.game.board.filter.Filter;
import com.jack.dicewars.dice_wars.game.board.Territory;
import com.jack.dicewars.dice_wars.game.board.TerritoryBorder;
import com.jack.dicewars.dice_wars.game.board.filter.ValueFilter;

import java.util.HashSet;

/**
 * A Phase that takes a defending territory and defending territory, uses Territories' properties to change the
 * ownership of those Territories.
 */
public class AttackPhase extends AbstractPhase {

    /**
     * TODO move this field.
     */
    private static final int RESET_VALUE = 1;

    /**
     * Creates an Attack Phase controlled by player that will initiate an attack after selecting and attacking and
     * defending territory until it is ended.
     * @param p The player controlling the attacks
     */
    AttackPhase(Player p) {
        player = p;
        pendingAction = false;
        territoryLimit = 2;
    }

    @Override
    public String toString() {
        return "Attack Phase";
    }

    @Override
    public HashSet<Filter> filters() {
        HashSet<Filter> filters = new HashSet<>();

        if (selected.size() == 0) {
            // Pick anything that is your own color
            filters.add(new ColorFilter(player.getTerritoryColor(), true));
            // Pick anything that has more than one dice on it.
            filters.add(new ValueFilter(1, true));
            return filters;
        } else if (selected.size() == 1) {
            // Attack anything that is not your own color
            filters.add(new ColorFilter(player.getTerritoryColor(), false));
            // Make sure the second territory is reachable from the first.
            filters.add(new AdjacentFilter(selected.get(0)));
        } else {
            throw new IllegalStateException("Attack phase has too many territories selected.");
        }
        return filters;
    }

    @Override
    protected void consume() {
        // The first Territory selected is what the Player is attacking, the next is where the attack is from
        final TerritoryBorder attackBorder = ((TerritoryBorder) selected.remove(0));
        attackBorder.setSelected(false);
        Territory attacking = attackBorder.getInternal();
        final TerritoryBorder defendBorder = ((TerritoryBorder) selected.remove(0));
        defendBorder.setSelected(false);
        Territory defending = defendBorder.getInternal();
        // Do the attack
        // TODO implement this if statement in a Territory function
        if (attacking.roll() > defending.roll()) {
            Log.i(Debug.battle.s, "win");
            // Attacker wins, takes territory.
            defending.getOwner().loseOwnership(defending);
            // The attacker moves his dice to the losing territory, not necessarily the player's color.
            attacking.getOwner().claimOwnership(defending);
            // Set values of the newly owned Territories
            defending.setValue(attacking.getValue() - RESET_VALUE);
            attacking.setValue(RESET_VALUE);

        } else {
            Log.i(Debug.battle.s, "lose");
            // Defender wins, Attacker is knocked down to 1.
            attacking.setValue(RESET_VALUE);
        }
    }
}
