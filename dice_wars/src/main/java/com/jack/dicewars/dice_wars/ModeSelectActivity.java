package com.jack.dicewars.dice_wars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * This activity allows the user to determine if they are playing with alone or with others.
 */
public class ModeSelectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_mode_select);
    }

    // Activity Navigation

    /**
     * The game will be played alone, so go to the game activity with the default settings.
     *
     * @param view The button clicked to cause this mode.
     */
    public void goToGameConfig(View view) {
        Log.i(Debug.nav.s, "Go to Game Config");

        Intent configIntent = new Intent(this, GameConfigActivity.class);
        startActivity(configIntent);
    }

    /**
     * The game will be played with others, and this user will be the host.
     *
     * @param view The button clicked to cause this mode.
     */
    public void goToOpenRoom(View view) {
        Log.i(Debug.nav.s, "Go to Open Room");

    }

    /**
     * The game will be played with others, and a different user will be the host.
     *
     * @param view The button clicked to cause this mode.
     */
    public void goToJoinRoom(View view) {
        Log.i(Debug.nav.s, "Go to Join Room");
    }
}
