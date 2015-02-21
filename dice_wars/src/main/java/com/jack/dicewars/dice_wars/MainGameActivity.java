package com.jack.dicewars.dice_wars;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.jack.dicewars.dice_wars.game.Configuration;

public class MainGameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main_game);
        
        Intent i = getIntent();
        Configuration config = new Configuration(i);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.hardware_back_in_game).setTitle(R.string.common_warning);

        builder.setMessage(R.string.hardware_back_in_game)
                .setPositiveButton(R.string.common_continue, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("nav", "Go to Game Config");
                        goToGameConfig();
                    }

                    
                })
                .setNegativeButton(R.string.common_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("nav", "cancel");
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    
    private void goToGameConfig() {
        Intent i = new Intent(this, GameConfigActivity.class);
        i.putExtras(GameConfigActivity.defaultExtras());
        finish();
    }
}