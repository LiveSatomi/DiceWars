package com.jack.dicewars.dice_wars.game;

/**
 * Utility class used for transferring Results data between Activities by use of the String keys defined by this class.
 */
public final class Results {

    /**
     * Private constructor to override public default constructor.
     */
    private Results() {

    }

    /**
     * A key to the original Configuration instance that can be used to restart the Game that resulted in the rest of
     * the data being transferred.
     */
    public static final String ORIGINAL_CONFIG = "originalConfig";

    /**
     * The Game winners primary identifier defined by the winner's Color-enum's name.
     */
    public static final String CLOSED_PLAYERS = "closedPlayers";

    /**
     * A key to the number of rounds it took to finish the Game. The round number is 1 at the start of the Game and
     * * is incremented at the beginning of the first Player's Turn.
     */
    public static final String ROUND_NUM = "roundNum";
}
