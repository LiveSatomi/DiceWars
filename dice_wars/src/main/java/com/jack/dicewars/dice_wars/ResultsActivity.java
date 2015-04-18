package com.jack.dicewars.dice_wars;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jack.dicewars.dice_wars.ai.AbstractAi;
import com.jack.dicewars.dice_wars.ai.SimpleAi;
import com.jack.dicewars.dice_wars.game.Configuration;
import com.jack.dicewars.dice_wars.game.Game;
import com.jack.dicewars.dice_wars.game.board.filter.Filterable;
import com.jack.dicewars.dice_wars.game.board.filter.Selectable;
import com.jack.dicewars.dice_wars.setup.GameConfigActivity;


public class ResultsActivity extends Activity {


    /**
     * Loads the configuration to use for the game.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_results);

        String winnerName = (String) getIntent().getExtras().get("winner");
        ((TextView) findViewById(R.id.winnerName)).setText("Congratulations " + winnerName + "!");
    }


}