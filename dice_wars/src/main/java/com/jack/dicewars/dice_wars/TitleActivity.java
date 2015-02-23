package com.jack.dicewars.dice_wars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * This activity is the entry point of the app.
 */
public class TitleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_title);
    }

    /**
     *
     * @param view The button clicked to call the {@link ModeSelectActivity}.
     */
    public void goToGameMode(View view) {
        Log.i(Debug.nav.s, "Go to Game Mode");

        Intent start = new Intent(this, ModeSelectActivity.class);
        startActivity(start);
    }

    /**
     *
     * @param view The button clicked to call the {@link OptionsActivity}.
     */
    public void goToOptions(View view) {
        Log.i(Debug.nav.s, "Go to Options");

        Intent options = new Intent(this, OptionsActivity.class);
        startActivity(options);
    }

    /**
     *
     * @param view The button clicked to call the {@link HowToPlayActivity}.
     */
    public void goToHowToPlay(View view) {
        Log.i(Debug.nav.s, "Go to How To Play");

        Intent howToPlay = new Intent(this, HowToPlayActivity.class);
        startActivity(howToPlay);

    }
}
