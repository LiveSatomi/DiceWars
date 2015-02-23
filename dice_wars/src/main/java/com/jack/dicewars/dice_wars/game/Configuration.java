package com.jack.dicewars.dice_wars.game;

import android.content.Intent;
import android.os.Parcelable;

/**
 * Created by Jack Mueller on 1/28/15.
 *
 * This class encapsulates all the different settings and variations a different game of Dice Wars can have.
 */
public class Configuration {

    private static final int MAX_PLAYERS = 6;
    private static final int BOARD_SIZE_SMALL = 0;
    private static final int BOARD_SIZE_MEDIUM = 1;
    private static final int BOARD_SIZE_LARGE = 2;

    private static final String PLAYERS_KEY = "players";
    private static final String COLORLESS_TERRITORY_KEY = "colorless_territory";
    private static final String RANDOM_REINFORCE_KEY = "random_reinforce";

    private final Player[] players = new Player[MAX_PLAYERS];
    private boolean colorlessTerritory;
    private boolean randomReinforce;

    /**
     * Full constructor for a game Configuration. Forces all of the Configuration properties to be set to their
     * game values. Useful for single player games.
     *
     * @param names A list of the players' screen names.
     * @param statuses A list of the players' statuses defined by {@link Player#STATUS_YOU} STATUS variables.
     * @param colors A list of the players' statuses defined by {@link Player} COLOR variables.
     * @param cT Whether this configuration will have colorless territories enabled.
     * @param rR Whether this configuration will have user defined or random reinforcements.
     */
    public Configuration(String[] names, String[] statuses, int[] colors, boolean cT, boolean rR) {
        for (int i = 0; i < MAX_PLAYERS; i++) {
            players[i] = new Player(names[i], statuses[i], colors[i]);
        }

        colorlessTerritory = cT;
        randomReinforce = rR;
    }

    /**
     * Defines a Configuration by the extras of the passed intent. Extra keys are defined at {@link #PLAYERS_KEY}.
     *
     * @param intent The intent with extras fully defining this Configuration.
     */
    public Configuration(Intent intent) {
        Parcelable[] parceledPlayers = intent.getParcelableArrayExtra(PLAYERS_KEY);
        for (int i = 0; i < MAX_PLAYERS; i++) {
            players[i] = ((Player) parceledPlayers[i]);
        }
        colorlessTerritory = intent.getBooleanExtra(COLORLESS_TERRITORY_KEY, false);
        randomReinforce = intent.getBooleanExtra(RANDOM_REINFORCE_KEY, false);
    }

    /**
     * Returns the passed intent with the configurations separated into primitive types and matched with string keys
     * that are defined as this class' members.
     *
     *
     * @param intent The link to the activity that needs configuration settings
     * @return An intent with extras that fully define a Configuration
     */
    public Intent upload(Intent intent) {
        intent.putExtra(PLAYERS_KEY, players);
        intent.putExtra(COLORLESS_TERRITORY_KEY, colorlessTerritory);
        intent.putExtra(RANDOM_REINFORCE_KEY, randomReinforce);
        return intent;
    }

    /**
     *
     * @return Whether the board will start with some colorless territories.
     */
    public boolean isColorlessTerritory() {
        return colorlessTerritory;
    }

    /**
     *
     * @param colorlessTerritory Setting to false will ensure all territories are assigned on game start.
     */
    public void setColorlessTerritory(boolean colorlessTerritory) {
        this.colorlessTerritory = colorlessTerritory;
    }

    /**
     *
     * @return Whether defense phase reinforcements will be randomly allocated
     */
    public boolean isRandomReinforce() {
        return randomReinforce;
    }

    /**
     *
     * @param randomReinforce Setting to true will allow players to choose how to allocate their reinforcements.
     */
    public void setRandomReinforce(boolean randomReinforce) {
        this.randomReinforce = randomReinforce;
    }

    /**
     *
     * @return The maximum number of players one game of DiceWars can handle.
     */
    public static int getMaxPlayers() {
        return MAX_PLAYERS;

    }
}