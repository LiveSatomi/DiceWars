package com.jack.dicewars.dice_wars;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * This Activity displays statistics of the last Game to the user.
 */
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