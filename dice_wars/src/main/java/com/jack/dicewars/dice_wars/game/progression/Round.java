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
//54213
    //5 2 4 1 3 j  5 2 4 1 3 j
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

            // TODO enum player status (see player class)
            while (currentPlayerIndex < players.size() && players.get(currentPlayerIndex).getStatus().equals(Player
                    .STATUS_CLOSED)) {
                currentPlayerIndex++;
            }

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

    /**
     * @return Whether or not the current phase has selected Territories and is waiting for more selections to resolve
     * the action.
     */
    public boolean getPendingAction() {
        return turn.getPendingAction();
    }

    /**
     * Reverses the phases action so that the game state is like before it was selected.
     */
    public void undoPhaseAction() {
        turn.undoPhaseAction();
    }

    /**
     *
     * @return True if the Round's list of Players has more than one Player that is not CLOSED, false otherwise.
     */
    public boolean gameHasEnded() {
        boolean foundActive = false;
        for (Player player : players) {
            if (!player.getStatus().equals(Player.STATUS_CLOSED)) {
                if (foundActive) {
                    return false;
                } else {
                    foundActive = true;
                }
            }
        }
        return true;
    }
}
