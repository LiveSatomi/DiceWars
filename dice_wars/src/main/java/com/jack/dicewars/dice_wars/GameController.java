package com.jack.dicewars.dice_wars;

import android.os.AsyncTask;
import com.jack.dicewars.dice_wars.ai.AbstractAi;
import com.jack.dicewars.dice_wars.game.board.filter.Filterable;

/**
 * Created by Jack Mueller on 4/17/15.
 * TODO subclass this and give an instance of the subclass to MainGameActivity instead of having MainGameActivity
 * itself implement this interface.
 */
public interface GameController {
    /**
     * Called when a new phase begins. It is up to the controller to translate previous phase information to all
     * players as well as initiate AI moves when and AI turn begins.
     */
    void onPhaseChange();

    // TODO abstract out AsyncTask
    AsyncTask<AbstractAi, Filterable, Void> generateAiTask();
}
