package com.jack.dicewars.dice_wars.game.progression;

import com.jack.dicewars.dice_wars.game.Player;

import java.util.List;

/**
 * A Round is made up of multiple players that each get Turns within this Round.
 */
public class Round {

    private List<Player> players;
    private int currentPlayerIndex;

    private Turn turn;

    /**
     * Makes a Round where Turns will be taken by all Players in players, in the order of the passed list.
     * @param players The Players that will be given Turns
     */
    public Round(List<Player> players) {
        this.players = players;
        currentPlayerIndex = 0;

        Player currentPlayer = players.get(currentPlayerIndex);
        turn = new Turn(currentPlayer);
    }

    /**
     *
     * @return The Phase that is currently being played by the active Player
     */
    public Phase currentPhase() {
        return turn.currentPhase();
    }

    /**
     *
     * @return The Player who will be associated with an active Turn
     */
    public Player currentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     *
     * @return True if there are more Players to be given Turns, false if each Player has had a Turn.
     */
    public boolean advance() {
        if (turn.advance()) {
            // Advancing logic completed at a lower level
            return true;
        } else {
            // The Turn ended
            currentPlayerIndex++;

            if (currentPlayerIndex < players.size()) {
                // The Round has not ended
                Player currentPlayer = players.get(currentPlayerIndex);
                turn = new Turn(currentPlayer);
                return true;
            } else {
                // The Round has ended, notify the Game
                return false;
            }
        }
    }

    public boolean getPendingAction() {
        return turn.getPendingAction();
    }

    public void undoPhaseAction() {
        turn.undoPhaseAction();
    }
}
