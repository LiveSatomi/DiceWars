package com.jack.dicewars.dice_wars.game;

import android.util.Log;
import com.jack.dicewars.dice_wars.Debug;

/**
 * Created by Jack Mueller on 2/23/15.
 *
 * A Game of DiceWars that is driven by User and AI Player actions and structured by Rounds. It ends when one Player
 * controls all the Territories on the Board.
 */
public class Game {

    /**
     * The constraints for this game defined by the user or group of users. @see Configuration
     */
    private Configuration config;
    /**
     * The root of the interactive part of the model.
     */
    private AbstractBoard board;
    /**
     * The current Round that is being played.
     */
    private Round round;

    /**
     * Creates a Game, and determines what app mode this game will use, but does not create a board for the game, call
     * the start method before beginning a Game.
     *
     * @param config Defines all aspects of the Game that can change from one Game to another. Used for setup.
     */
    public Game(Configuration config) {
        this.config = config;
        if (getAppMode() == Debug.gridText.f) {
            board = new GridTextBoard(config);
        } else {
            throw new EnumConstantNotPresentException(Debug.class, "App mode does not exist");
        }
        round = null;
    }

    /**
     * Initializes this game's board and determines the order of players, as well as who will go first.
     * Completes setup of the game by initiating the first round, turn, and phase.
     */
    public void start() {
        board.startState(config);
        config.randomizePlayerOrder();
        round = new Round(config.activePlayers());
    }

    /**
     * Advances the game state by one Phase, which may roll over to a new Round or Turn.
     */
    public void advance() {
        if (!round.advance()) {
            // The Round has ended, start a new one
            round = new Round(config.activePlayers());
        } else {
            Log.i("active", "true at game");
        }
    }

    /**
     *
     * @return The model data to be updated when users interact with the game.
     */
    public AbstractBoard getBoard() {
        return board;
    }

    /**
     *
     * @return The mode that this game is using to run.
     */
    public int getAppMode() {
        return config.getAppMode();
    }

    public String currentPlayerName() {
        return round.currentPlayer().getName();
    }

    public String currentPhase() {
        return round.currentPhase();
    }


}