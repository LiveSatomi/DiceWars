package com.jack.dicewars.dice_wars;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class GameConfigActivity extends Activity {
    
    private final int STATUS_INDEX = 2;
    private final String STATUS_YOU = "YOU";
    private final String STATUS_ACTIVE = "ACTIVE";
    private final String STATUS_CLOSED = "CLOSED";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.a_game_config);
        int position = getIntent().getExtras().getInt("position");
        LinearLayout config = (LinearLayout) findViewById(R.id.configContainer);
        LinearLayout viewingPlayerSlot = (LinearLayout) config.getChildAt(position);
        Button playerStatus = (Button) viewingPlayerSlot.getChildAt(STATUS_INDEX);
        playerStatus.setText(STATUS_YOU);
        // set "you" color on the button
        playerStatus.setEnabled(false);
    }
    
    // Activity Navigation
    public void goToGame(View v) {
        Log.i("nav", "Go to Begin Game");
    }
}
