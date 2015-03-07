package com.jack.dicewars.dice_wars.game;

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
    }

    /**
     * Initializes this game's board and determines the order of players, as well as who will go first.
     */
    public void start() {
        board.startState(config);
        //TODO determine who is first/the order of play
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

}
