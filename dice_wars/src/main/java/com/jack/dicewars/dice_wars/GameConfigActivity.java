package com.jack.dicewars.dice_wars;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class GameConfigActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_game_config);
    }
    
    
    // Activity Navigation
    public void goToGame(View v) {
        Log.i("nav", "Go to Begin Game");
    }
}
