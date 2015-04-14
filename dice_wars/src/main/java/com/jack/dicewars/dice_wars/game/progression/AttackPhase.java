package com.jack.dicewars.dice_wars.game.progression;

import android.util.Log;
import com.jack.dicewars.dice_wars.Debug;
import com.jack.dicewars.dice_wars.game.Player;
import com.jack.dicewars.dice_wars.game.board.AdjacentFilter;
import com.jack.dicewars.dice_wars.game.board.Filter;
import com.jack.dicewars.dice_wars.game.board.Territory;

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
            // TODO implement ColorFilter
            return filters;
        } else if (selected.size() == 1) {
            // Make sure the second territory is reachable from the first.
            Filter adjacentFilter = new AdjacentFilter(selected.get(0));
            filters.add(adjacentFilter);
        } else {
            throw new IllegalStateException("Attack phase has too many territories selected.");
        }
        return filters;
    }

    @Override
    protected void consume() {
        // The first Territory selected is what the Player is attacking, the next is where the attack is from
        Territory attacking = selected.remove(0).getInternal();
        Territory defending = selected.remove(0).getInternal();
        // Do the attack
        // TODO implement this if statement in a Territory function
        if (attacking.roll() > defending.roll()) {
            // Attacker wins, takes territory.
            defending.getOwner().loseOwnership(defending);
            // The attacker moves his dice to the losing territory, not necessarily the player's color.
            attacking.getOwner().claimOwnership(defending);
            Log.i(Debug.battle.s, "win");
            // Set values
            defending.setValue(attacking.getValue() - RESET_VALUE);
            attacking.setValue(RESET_VALUE);
        } else {
            Log.i(Debug.battle.s, "lose");
            // Defender wins, Attacker is knocked down to 1.
            attacking.setValue(RESET_VALUE);
        }
    }
}
