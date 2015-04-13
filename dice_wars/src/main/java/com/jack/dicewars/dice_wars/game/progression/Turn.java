package com.jack.dicewars.dice_wars.game.progression;

import com.jack.dicewars.dice_wars.game.Player;

/**
 * A Turn is taken by one Player and made up of Phases. A Turn an only advance through as many phases as it is
 * instantiated with.
 */
public class Turn {

    private Player player;

    private Phase[] phases;
    private int currentPhase;

    /**
     * Starts a turn controlled by player that will go through an Attack, Reinforce, and Effect phase.
     * @param player The player controlling the turn.
     */
    Turn(Player player) {
        this.player = player;

        phases = new Phase[] {new AttackPhase(player), new ReinforcePhase(player), new EffectPhase(player)};
        currentPhase = 0;
    }

    /**
     *
     * @return The current phase of this turn.
     */
    public Phase currentPhase() {
        return phases[currentPhase];
    }

    /**
     * Advance this Turn to the next Phase.
     *
     * @return True if advancing logic completed at the Turn level, false if the Turn has ended.
     */
    public boolean advance() {
        if (currentPhase < phases.length - 1) {
            // simply advance to the next phase
            currentPhase++;
            return true;
        } else {
            //last phase, roll over to next turn
            return false;
        }
    }
}
