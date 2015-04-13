package com.jack.dicewars.dice_wars.game;

import com.jack.dicewars.dice_wars.Color;
import com.jack.dicewars.dice_wars.Debug;
import com.jack.dicewars.dice_wars.game.board.AbstractBoard;
import com.jack.dicewars.dice_wars.game.board.GridTextBoard;
import com.jack.dicewars.dice_wars.game.board.TerritoryBorder;
import com.jack.dicewars.dice_wars.game.progression.Phase;
import com.jack.dicewars.dice_wars.game.progression.Round;

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
     * The number of current Round being played.
     */
    private int roundNum;

    /**
     * Creates a Game, and determines what app mode this game will use, but does not create a board for the game, call
     * the start method before beginning a Game.
     *
     * @param config Defines all aspects of the Game that can change from one Game to another. Used for setup.
     */
    public Game(Configuration config) {
        this.config = config;
        if (getAppMode() == Debug.gridText.f) {
            board = new GridTextBoard(this, config);
        } else {
            throw new EnumConstantNotPresentException(Debug.class, "App mode does not exist");
        }
        round = null;
        roundNum = 0;
    }

    /**
     * Initializes this game's board and determines the order of players, as well as who will go first.
     * Completes setup of the game by initiating the first round, turn, and phase.
     */
    public void start() {
        board.startState();
        config.randomizePlayerOrder();
        round = new Round(config.activePlayers());
        roundNum = 1;
    }

    /**
     * Tells whether a certain Territory is selectable based on the Context, which device selected the Territory,
     * whose Turn it is in the Game, what Phase it is, and what has already been selected.
     * @param territory The territory that was clicked.
     * @return True if the territory is selected, false otherwise.
     */
    public void select(TerritoryBorder territory) {
        if (isSelectable(territory)) {
            currentPhase().pushTerritory(territory);
            // invalidate view. What do we have access to here?
        }
    }

    private boolean isSelectable(TerritoryBorder territory) {
        // If it's not the clicker's turn, the clicker can't do anything
        if (myTurn()) {
            return board.passesFilter(territory, currentPhase().filters());
        } else {
            return false;
        }
    }

    public boolean myTurn() {
        return currentPlayerColor().equals(myColor());

    }

    private Color myColor() {
        for (Player p : config.activePlayers()) {
            if (p.isMe()) {
                return p.getColor();
            }
        }
        return null;
    }

    /**
     * Advances the game state by one Phase, which may roll over to a new Round or Turn.
     */
    public void advance() {
        if (!round.advance()) {
            // The Round has ended, start a new one
            round = new Round(config.activePlayers());
            roundNum++;
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

    public Color currentPlayerColor() {
        return round.currentPlayer().getColor();
    }


    public Phase currentPhase() {
        return round.currentPhase();
    }

    /**
     *
     * @return The number of rounds this game has been played, including the current round
     */
    public int getRoundNum() {
        return roundNum;
    }


}
