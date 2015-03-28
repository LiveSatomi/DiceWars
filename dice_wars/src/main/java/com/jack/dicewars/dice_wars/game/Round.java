package com.jack.dicewars.dice_wars.game;

import android.util.Log;

import java.util.List;

/**
 * Created by Jack Mueller on 3/8/15.
 */
public class Round {

    private List<Player> players;
    private int currentPlayerIndex;

    private Turn turn;

    Round(List<Player> players) {
        this.players = players;
        currentPlayerIndex = 0;

        Player currentPlayer = players.get(currentPlayerIndex);
        turn = new Turn(currentPlayer);
    }

    public String currentPhase() {
        return turn.currentPhase();
    }

    public Player currentPlayer() {
        return players.get(currentPlayerIndex);

    }

    public boolean advance() {
        if(turn.advance()) {
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
}
