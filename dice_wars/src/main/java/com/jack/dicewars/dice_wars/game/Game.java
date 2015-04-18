package com.jack.dicewars.dice_wars.game;

import com.jack.dicewars.dice_wars.GameController;
import com.jack.dicewars.dice_wars.R;
import com.jack.dicewars.dice_wars.TerritoryColor;
import com.jack.dicewars.dice_wars.Debug;
import com.jack.dicewars.dice_wars.game.board.AbstractBoard;
import com.jack.dicewars.dice_wars.game.board.GridTextBoard;
import com.jack.dicewars.dice_wars.game.board.filter.Selectable;
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

    private GameController controller;
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

    private int primaryActionId;


    /**
     * Creates a Game, and determines what app mode this game will use, but does not create a board for the game, call
     * the start method before beginning a Game.
     *
     * @param config Defines all aspects of the Game that can change from one Game to another. Used for setup.
     * @param controller The controller that will update the View by the status of this Game.
     */
    public Game(Configuration config, GameController controller) {
        this.config = config;
        this.controller = controller;
        if (getAppMode() == Debug.gridText.f) {
            board = new GridTextBoard(this, config);
        } else {
            throw new EnumConstantNotPresentException(Debug.class, "App mode does not exist");
        }
        round = null;
        roundNum = 0;
        primaryActionId = R.string.end_phase;
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
        final List<Selectable> selectable = allSelectable();
        for (Selectable s: selectable) {
            s.setSelectable(true);
        }
    }

    /**
     * Tells whether a certain Territory is selectable based on the Context, which device selected the Territory,
     * whose Turn it is in the Game, what Phase it is, and what has already been selected.
     * @param territory The territory that was clicked.
     */
    public void requestSelection(Selectable territory) {
        if (isSelectable(territory)) {
            // Invalidate selectable property TODO make efficient
            for (TerritoryBorder s: board.getBoard()) {
                s.setSelectable(false);
            }

            territory.setSelected(true);
            currentPhase().pushTerritory(territory);
            updateSelectable();
            updateUserPrimaryAction();

            if (round.gameHasEnded()) {
                controller.onGameEnd();
            }
        }
    }

    /**
     * Updates what should be displayed as the Primary Action based on the game state.
     */
    private void updateUserPrimaryAction() {
        if (getPendingAction()) {
            primaryActionId = R.string.undo;
        } else {
            primaryActionId = R.string.end_phase;
        }
    }

    /**
     * Tells whether a territory is selectable based on the current Phases' criteria and the state of the game.
     * @param territory The territory requesting to be selected.
     * @return Whether the territory can be selected
     */
    private boolean isSelectable(Selectable territory) {
        // If it's not the clicker's turn, the clicker can't do anything
        return board.passesFilter(territory, currentPhase().filters());
    }

    /**
     * Resets selectable values on all Territories of this Game's board based on the current Phase's state.
     */
    public void updateSelectable() {
        // Invalidate selectable property TODO make efficient
        for (TerritoryBorder s: board.getBoard()) {
            s.setSelectable(false);
        }

        final List<Selectable> selectable = allSelectable();
        for (Selectable s : selectable) {
            s.setSelectable(true);
        }
    }

    /**
     *
     * @return Returns a list of all selectable Territories based on Filters of the current Phase's state.
     */
    public List<Selectable> allSelectable() {
        LinkedList<Selectable> boardCopy = new LinkedList<>();
        for (Selectable territory : board.getBoard()) {
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

    /**
     * Executes the process initiated by the user Primary Action based on the state of the Game.
     */
    public void doPrimaryAction() {
        boolean pending = getPendingAction();
        if (pending) {
            undoPhaseAction();
            // Call again, because it may be different since the undo
            pending = getPendingAction();
        } else {
            advance();
        }

        if (pending) {
            primaryActionId = R.string.undo;
        } else {
            primaryActionId = R.string.end_phase;
        }

        updateSelectable();
    }

    /**
     * Reverses the phases action so that the game state is like before it was selected.
     */
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
        controller.onPhaseChange();
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

    /**
     * @return Whether or not the current phase has selected Territories and is waiting for more selections to resolve
     * the action.
     */
    public boolean getPendingAction() {
        return round.getPendingAction();
    }

    /**
     *
     * @return The string resource id for what the current action's text should be for the game's primary button.
     */
    public int getPrimaryActionId() {
        return primaryActionId;
    }
}
