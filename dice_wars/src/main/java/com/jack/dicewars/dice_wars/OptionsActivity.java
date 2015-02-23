package com.jack.dicewars.dice_wars;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This activity will allow the user to set default options that can be used across the app. Global app configuration
 * is achieved by storing the device filesystem.
 */
public class OptionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout hierarchy
        setContentView(R.layout.a_options);

        // Save a reference to the profile for setup
        final EditText profile = (EditText) findViewById(R.id.onlineProfile);

        // Load the saved profile, if any
        BufferedReader reader;
        String setProfile;
        try {
            FileInputStream optionsFileStream = this.openFileInput(getString(R.string.file_path_options));
            reader = new BufferedReader(new InputStreamReader(optionsFileStream));
            setProfile = reader.readLine();
            profile.setText(setProfile);
            optionsFileStream.close();
        } catch (FileNotFoundException e) {
            Log.i(Debug.profile.s, "Profile has not been set");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Prep the profile to act correctly with the keyboard
        profile.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    closeProfileEdit(null);
                }
                return false;
            }
        });
    }

    /**
     * Prepares the profile field for editing.
     *
     * @param view The edittext that holds the online profile
     */
    public void editOnlineProfile(View view) {
        TextView profile = (TextView) view;
        ((TextView) view).setCursorVisible(true);
        Log.i(Debug.profile.s, profile.getText().toString());
    }

    /**
     * Closes the keyboard and saves its state, if valid. Can be called by onClick.
     *
     * @param view The view that was clicked to call this method, or null if it was called from onCreate
     */
    public void closeProfileEdit(View view) {
        EditText profile = (EditText) findViewById(R.id.onlineProfile);
        verifyProfile(profile);

        // Close the soft keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        getCurrentFocus().getWindowToken();
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        // Hide the cursor while not in focus
        profile.setCursorVisible(false);

    }

    /**
     * Ensures that the profile text will conform to the constraints of online profiles. Changes the profile to the
     * original form if it does not pass checks. If it does pass, it is saved in the options file of internal storage.
     *
     * @param profile The text field that defines the profile.
     */
    private void verifyProfile(EditText profile) {
        String newProfile = profile.getText().toString();
        try {
            FileOutputStream optionsFileStream = this.openFileOutput(getString(R.string.file_path_options), 
                    MODE_PRIVATE);
            optionsFileStream.write(newProfile.getBytes());
            optionsFileStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the color change palette.
     *
     * @param view custom color palette view TODO actually make it a palette
     */
    public void openPalette(View view) {
        Log.i("not implemented", "open palette");
    }
}
