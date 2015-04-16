package com.jack.dicewars.dice_wars.game;

import com.jack.dicewars.dice_wars.R;
import com.jack.dicewars.dice_wars.TerritoryColor;
import com.jack.dicewars.dice_wars.Debug;
import com.jack.dicewars.dice_wars.game.board.AbstractBoard;
import com.jack.dicewars.dice_wars.game.board.GridTextBoard;
import com.jack.dicewars.dice_wars.game.board.TerritoryBorder;
import com.jack.dicewars.dice_wars.game.progression.Phase;
import com.jack.dicewars.dice_wars.game.progression.Round;

import java.util.LinkedList;
import java.util.List;

/**
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

    private int userPrimaryActionPromptId;

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
        userPrimaryActionPromptId = R.string.end_phase;
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
        final List<TerritoryBorder> selectable = allSelectable();
        for (TerritoryBorder s: selectable) {
            s.setSelectable(true);
        }
    }

    /**
     * Tells whether a certain Territory is selectable based on the Context, which device selected the Territory,
     * whose Turn it is in the Game, what Phase it is, and what has already been selected.
     * @param territory The territory that was clicked.
     */
    public void requestSelection(TerritoryBorder territory) {
        if (isSelectable(territory)) {
            // Invalidate selectable property TODO make efficient
            for (TerritoryBorder s: board.getBoard()) {
                s.setSelectable(false);
            }

            territory.setSelected(true);
            currentPhase().pushTerritory(territory);
            updateSelectable();
            updatePrimaryAction();
        }
    }

    private void updatePrimaryAction() {
        if (getPendingAction()) {
            userPrimaryActionPromptId = R.string.undo;
        } else {
            userPrimaryActionPromptId = R.string.end_phase;
        }
    }

    /**
     * Tells whether a territory is selectable based on the current Phases' criteria and the state of the game.
     * @param territory The territory requesting to be selected.
     * @return Whether the territory can be selected
     */
    private boolean isSelectable(TerritoryBorder territory) {
        // If it's not the clicker's turn, the clicker can't do anything
        return myTurn() && board.passesFilter(territory, currentPhase().filters());
    }

    public void updateSelectable() {
        // Invalidate selectable property TODO make efficient
        for (TerritoryBorder s: board.getBoard()) {
            s.setSelectable(false);
        }

        final List<TerritoryBorder> selectable = allSelectable();
        for (TerritoryBorder s : selectable) {
            s.setSelectable(true);
        }
    }

    private List<TerritoryBorder> allSelectable() {
        LinkedList<TerritoryBorder> boardCopy = new LinkedList<>();
        for (TerritoryBorder territory : board.getBoard()) {
            if (board.passesFilter(territory, currentPhase().filters())) {
                boardCopy.add(territory);
            }
        }
        return boardCopy;
    }

    /**
     *
     * @return Returns true if it is the device owners turn.
     */
    public boolean myTurn() {
        return currentPlayerColor().equals(myColor());
    }

    /**
     *
     * @return The color of the device owner player.
     */
    private TerritoryColor myColor() {
        for (Player p : config.activePlayers()) {
            if (p.isMe()) {
                return p.getTerritoryColor();
            }
        }
        return null;
    }

    public void userPrimaryAction() {
        boolean pending = getPendingAction();
        if (pending) {
            undoPhaseAction();
            // Call again, because it may be different since the undo
            pending = getPendingAction();
        } else {
            advance();
        }

        if (pending) {
            userPrimaryActionPromptId = R.string.undo;
        } else {
            userPrimaryActionPromptId = R.string.end_phase;
        }

        updateSelectable();
    }

    private void undoPhaseAction() {
        round.undoPhaseAction();
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

    /**
     *
     * @return The name of the player currently controlling the turn.
     */
    public String currentPlayerName() {
        return round.currentPlayer().getName();
    }

    /**
     *
     * @return The color being used by the player currently controlling the turn.
     */
    public TerritoryColor currentPlayerColor() {
        return round.currentPlayer().getTerritoryColor();
    }

    /**
     *
     * @return The current Phase in effect
     */
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

    public boolean getPendingAction() {
        return round.getPendingAction();
    }

    public int getUserPrimaryActionPromptId() {
        return userPrimaryActionPromptId;
    }
}
