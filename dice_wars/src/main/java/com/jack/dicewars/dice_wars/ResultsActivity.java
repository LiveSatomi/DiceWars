package com.jack.dicewars.dice_wars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jack.dicewars.dice_wars.game.Configuration;
import com.jack.dicewars.dice_wars.game.Player;
import com.jack.dicewars.dice_wars.game.Results;
import com.jack.dicewars.dice_wars.setup.ModeSelectActivity;

import java.util.ArrayList;

/**
 * This Activity displays statistics of the last Game to the user.
 */
public class ResultsActivity extends Activity {


    private Bundle results;

    /**
     * Loads the configuration to use for the game.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_results);

        results = getIntent().getExtras();
        postResults();
    }

    /**
     * Takes the results from the Activity's intent's extras and populates the appropriate Views with the data.
     */
    private void postResults() {
        final ArrayList<Player> closedPlayers = results.getParcelableArrayList(Results.CLOSED_PLAYERS);
        final int roundNum = results.getInt(Results.ROUND_NUM);

        final ViewGroup resultsContainer = ((ViewGroup) findViewById(R.id.resultsContainer));

        int playerIndex = 0;

        // First, populate the results entries for Players that participated.
        for (; playerIndex < closedPlayers.size(); playerIndex++) {
            final Player currentPlayer = closedPlayers.get(playerIndex);

            final View resultsBar = resultsContainer.getChildAt(playerIndex);
            ((TextView) resultsBar.findViewById(R.id.resultsRanking)).setText((playerIndex + 1) + ".");
            ((TextView) resultsBar.findViewById(R.id.resultsName)).setText(currentPlayer.getName());
            ((TextView) resultsBar.findViewById(R.id.resultsColor)).setText(currentPlayer.getTerritoryColor().name());
        }

        // Then, delete the results entries for Players that did not participate (less expensive than cloning views).
        for (; playerIndex < Configuration.getMaxPlayers(); playerIndex++) {
            resultsContainer.removeView(resultsContainer.getChildAt(playerIndex));
        }

    }

    /**
     * Restarts the Game with a new Board but the same Configuration. Equivalent to pressing the Begin Game button on
     * the Configuration Activity with the same settings.
     * @param view The restart button that was clicked.
     */
    public void restartGame(View view) {
        Intent restart = new Intent(this, MainGameActivity.class);
        restart.putExtra(Configuration.RESTART, getIntent().getExtras().getBundle(Results.ORIGINAL_CONFIG));
        startActivity(restart);
    }

    /**
     * Finishes viewing the results screen and returns to the Mode Select Activity.
     * @param view the finish button that was clicked.
     */
    public void finish(View view) {
        Intent modeSelect = new Intent(this, ModeSelectActivity.class);
        startActivity(modeSelect);
    }
}