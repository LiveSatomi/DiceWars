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


public class OptionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_options);

        final EditText editText = (EditText) findViewById(R.id.onlineProfile);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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
     * Prepares the edittext for editing.
     *
     * @param view
     */
    public void editOnlineProfile(View view) {
        TextView profile = (TextView) view;
        ((TextView) view).setCursorVisible(true);
        Log.i("profile", profile.getText().toString());
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
        InputMethodManager imm = (InputMethodManager) getSystemService(
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        // Hide the cursor while not in focus

        profile.setCursorVisible(false);

    }

    /**
     * Ensures that the profile text will conform to the constraints of online profiles. Changes the profile to the 
     * original form if it does not pass checks. If it does pass, it is saved in a bundle
     * 
     * @param profile The text field that defines the profile.
     */
    private void verifyProfile(EditText profile) {
        Log.i("not implemented", "profile is good");
        
        // No form to check as of yet
        String newProfile = profile.getText().toString();

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
