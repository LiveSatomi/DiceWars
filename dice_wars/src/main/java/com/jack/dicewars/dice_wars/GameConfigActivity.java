package com.jack.dicewars.dice_wars;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jack.dicewars.dice_wars.game.Configuration;
import com.jack.dicewars.dice_wars.game.Player;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Allows the user to set the names, colors, and number of players, board size, and other options.
 */
public class GameConfigActivity extends Activity {

    public static final String EX_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_game_config);

        if (getIntent().getExtras() == null) {
            return;
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.i("life", "onWindowFocusChanged Config");
        LinearLayout config = (LinearLayout) findViewById(R.id.configContainer);

        for (int i = 0; i < Configuration.getMaxPlayers(); i++) {
            ViewGroup configBar = (ViewGroup) config.getChildAt(i);
            for (int j = 0; j < configBar.getChildCount(); j++) {
                View v = configBar.getChildAt(j);
                v.setMinimumWidth(v.getMeasuredWidth());
            }
        }

        // Set up for players
        playerSetup(config);
        ownerSetup(config);

    }

    /**
     * Enables editing the name corresponding to the button pressed, if it is owned by the user.
     * @param view The button pressed to begin editing the name.
     */
    public void editName(View view) {
        // Find sibling views
        Button status = (Button) ((ViewGroup) view.getParent()).findViewById(R.id.config_status);
        String statusText = status.getText().toString();

        // Begin editing sibling name field if allowed
        if (statusText.equals(Player.STATUS_YOU) || statusText.equals(Player.STATUS_AI) || statusText.equals(Player
                .STATUS_CLOSED)) {
            final EditText name = (EditText) ((ViewGroup) view.getParent()).findViewById(R.id.config_name);
            Log.i("profile temp", name.getText().toString());

            // Add "Done" to this instance of the keyboard
            name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        name.clearFocus();
                    }
                    Log.i("test", "adding list");
                    return false;
                }
            });

            //Register a focus loss listener on first time focus
            if (name.getOnFocusChangeListener() == null) {
                name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        EditText name = (EditText) v;
                        if (hasFocus) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context
                                    .INPUT_METHOD_SERVICE);
                            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                            name.setCursorVisible(true);
                            name.setSelection(name.getText().length());

                        } else {
                            name.setCursorVisible(false);
                            name.setFocusableInTouchMode(false);
                        }
                    }
                });
            }
            name.setFocusableInTouchMode(true);
            name.requestFocus();
        }

    }

    /**
     * Toggles between legal status based on the context including current state and whether the corresponding player
     * is owned by the user.
     *
     * @param view The button being toggled.
     */
    public void toggleStatus(View view) {
        Button status = (Button) view;
        CharSequence statusText = status.getText();
        switch (statusText.toString()) {
            case Player.STATUS_YOU:
                status.setText(Player.STATUS_YOU);
                break;
            case Player.STATUS_AI:
                status.setText(Player.STATUS_CLOSED);
                break;
            case Player.STATUS_CLOSED:
                status.setText(Player.STATUS_AI);
                break;
            default:
                throw new IllegalStateException("Unknown Status");
        }
    }

    /**
     * Uploads all game configuration information to the intent for the {@link MainGameActivity}.
     *
     * @param view The Begin Game button.
     */
    public void goToGame(View view) {
        Log.i("nav", "Go to Begin Game");
        // Build the configuration from the options that are set

        Configuration config = constructConfiguration();

        // Push the configuration along the link to the activity
        Intent mainGame = new Intent(this, MainGameActivity.class);
        mainGame = config.upload(mainGame);
        startActivity(mainGame);
    }

    /**
     * Defines the minimum extras needed in case a null Bundle is passed with the Intent.
     *
     * @return A bundle with minimum extras for this activity. Position is 0.
     */
    static Bundle defaultExtras() {
        Bundle b = new Bundle();
        b.putInt(EX_POSITION, 0);
        return b;
    }

    /**
     * Sets user defaults appropriately based on the Intent's extras and the user preferences in {@OptionsActivity}.
     *
     * @param config The View with the group of settings that can be set.
     */
    private void ownerSetup(LinearLayout config) {
        int position = getIntent().getExtras().getInt(EX_POSITION);
        LinearLayout viewingPlayerSlot = (LinearLayout) config.getChildAt(position);

        // Name
        BufferedReader reader;
        String setProfile;
        try {
            FileInputStream optionsFileStream = this.openFileInput(getString(R.string.file_path_options));
            reader = new BufferedReader(new InputStreamReader(optionsFileStream));
            setProfile = reader.readLine();
            EditText name = (EditText) viewingPlayerSlot.findViewById(R.id.config_name);
            name.setText(setProfile);
            optionsFileStream.close();
        } catch (FileNotFoundException e) {
            Log.i("profile", "Profile has not been set");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Status
        Button playerStatus = (Button) viewingPlayerSlot.findViewById(R.id.config_status);
        playerStatus.setText(Player.STATUS_YOU);
        playerStatus.setEnabled(false);

        // Color
        // set "you" color on the button
    }

    /**
     * Sets game config defaults appropriately after loading the layout.
     *
     * @param config The View with the group of settings that can be set.
     */
    private void playerSetup(LinearLayout config) {

        for (int i = 0; i < Configuration.getMaxPlayers(); i++) {
            LinearLayout viewingPlayerSlot = (LinearLayout) config.getChildAt(i);

            // Name
            EditText name = (EditText) viewingPlayerSlot.findViewById(R.id.config_name);
            name.setText(String.format("%s %d", getString(R.string.common_player), i));

            // Status
            Button playerStatus = (Button) viewingPlayerSlot.findViewById(R.id.config_status);
            playerStatus.setText(Player.STATUS_AI);

            // Color
            String[] placeHolderColors = new String[]{"g", "p", "b", "r", "w", "y"};
            Button playerColor = (Button) viewingPlayerSlot.findViewById(R.id.config_color);
            playerColor.setText(placeHolderColors[i]);
        }
    }

    /**
     * Construct a game model Configuration object based on the contents of the View.
     *
     * @return A fully defined Configuration object.
     */
    private Configuration constructConfiguration() {
        LinearLayout playerConfigContainer = (LinearLayout) findViewById(R.id.configContainer);

        int playerNum = Configuration.getMaxPlayers();
        String[] playerNames = new String[playerNum];
        String[] playerStatuses = new String[playerNum];
        int[] playerColors = new int[playerNum];

        for (int i = 0; i < playerNum; i++) {
            LinearLayout currentPlayerSlot = (LinearLayout) playerConfigContainer.getChildAt(i);
            TextView name = (TextView) currentPlayerSlot.findViewById(R.id.config_name);
            Button status = (Button) currentPlayerSlot.findViewById(R.id.config_status);
            //TODO
            int colorPlaceHolder = -1;

            playerNames[i] = name.getText().toString();
            playerStatuses[i] = status.getText().toString();
            playerColors[i] = colorPlaceHolder;

        }

        boolean colorlessTerritories = ((CheckBox) findViewById(R.id.colorless)).isChecked();
        boolean randomReinforce = ((CheckBox) findViewById(R.id.randomReinforce)).isChecked();

        return new Configuration(playerNames, playerStatuses, playerColors, colorlessTerritories, randomReinforce);
    }


}
