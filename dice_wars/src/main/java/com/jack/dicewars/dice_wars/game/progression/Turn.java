package com.jack.dicewars.dice_wars.game.progression;

import com.jack.dicewars.dice_wars.game.*;

/**
 * Created by Jack Mueller on 3/8/15.
 */
public class Turn {

    private Player player;

    private Phase[] phases;
    private int currentPhase;

    Turn(Player player) {
        this.player = player;

        phases = new Phase[] {new AttackPhase(player), new ReinforcePhase(player), new EffectPhase(player)};
        currentPhase = 0;
    }

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
