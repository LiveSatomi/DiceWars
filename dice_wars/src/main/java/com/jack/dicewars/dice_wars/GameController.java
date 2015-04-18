package com.jack.dicewars.dice_wars;

import android.os.AsyncTask;
import com.jack.dicewars.dice_wars.ai.SimpleAi;

/**
 * Created by Jack Mueller on 4/17/15.
 */
public interface GameController {
    /**
     * Called when a new phase begins. It is up to the controller to translate previous phase information to all
     * players as well as initiate AI moves when and AI turn begins.
     */
    void onPhaseChange();

    AsyncTask<SimpleAi, Void, Void> generateAiTask();
}
