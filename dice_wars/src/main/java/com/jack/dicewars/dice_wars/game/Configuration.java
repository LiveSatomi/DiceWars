package com.jack.dicewars.dice_wars.game;

import android.content.Intent;
import android.os.Bundle;
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

    private final String PLAYERS_KEY = "players";
    private final String COLORLESS_TERRITORY_KEY = "colorless_territory";
    private final String RANDOM_REINFORCE_KEY = "random_reinforce";

    private final Player[] players = new Player[MAX_PLAYERS];
    private boolean colorlessTerritory;
    private boolean randomReinforce;
    
    public Configuration(String[] names, String[] statuses, int[] colors, boolean cT, boolean rR) {
        for(int i = 0; i < MAX_PLAYERS; i++) {
            players[i] = new Player(names[i], statuses[i], colors[i]);
        }

        colorlessTerritory = cT;
        randomReinforce = rR;
    }

    public Configuration (Intent intent) {
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
     * *
     * @param intent The link to the activity that needs configuration settings
     */
    public Intent upload(Intent intent) {
        intent.putExtra(PLAYERS_KEY, players);
        intent.putExtra(COLORLESS_TERRITORY_KEY, colorlessTerritory);
        intent.putExtra(RANDOM_REINFORCE_KEY, randomReinforce);
        return intent;
    }

    public boolean isColorlessTerritory() {
        return colorlessTerritory;
    }

    public void setColorlessTerritory(boolean colorlessTerritory) {
        this.colorlessTerritory = colorlessTerritory;
    }

    public boolean isRandomReinforce() {
        return randomReinforce;
    }

    public void setRandomReinforce(boolean randomReinforce) {
        this.randomReinforce = randomReinforce;
    }
    
    public static int getMaxPlayers() {
        return MAX_PLAYERS;
        
    }
}
