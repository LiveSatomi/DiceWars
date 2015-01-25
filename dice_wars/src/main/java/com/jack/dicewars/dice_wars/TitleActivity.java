package com.jack.dicewars.dice_wars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TitleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
    }


    // Activity Navigation

    /**
     * Calls another activity GameMode
     */
    public void goToGameMode(View v) {
        Log.i("nav", "Go to Game Mode");

        Intent helpIntent = new Intent(this, ModeSelectActivity.class);
        startActivity(helpIntent);
    }

    /**
     * Calls another activity Options
     */
    public void goToOptions(View v) {
        Log.i("nav", "Go to Options");

    }

    /**
     * Calls another activity HowToPlay
     */
    public void goToHowToPlay(View v) {
        Log.i("nav", "Go to How To Play");

    }

}
