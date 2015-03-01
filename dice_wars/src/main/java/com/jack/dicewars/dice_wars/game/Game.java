package com.jack.dicewars.dice_wars.game;

import com.jack.dicewars.dice_wars.Debug;

/**
 * Created by Jack Mueller on 2/23/15.
 */
public class Game {

    private Configuration config;
    private Board board;

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
    }

    public Board getBoard() {
        return board;
    }

    public int getAppMode() {
        return config.getAppMode();
    }

    public void setAppMode(int appMode) {
        config.setAppMode(appMode);

    }
}
