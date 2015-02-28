package com.jack.dicewars.dice_wars.game;

/**
 * Created by Jack Mueller on 2/23/15.
 */
public class Game {

    private Configuration config;
    private Board board;

    public Game(Configuration config) {
        this.config = config;

        board = new GridTextBoard(config);

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
}
