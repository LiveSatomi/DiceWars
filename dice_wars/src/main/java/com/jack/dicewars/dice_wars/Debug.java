package com.jack.dicewars.dice_wars;

/**
 * This class is for strings I may want to have lying around for development while still complying to checkstyle rules.
 */
public enum Debug {
    /* TODO possibly create a Debug package that can be imported with "logcat filter" and "appmode" classes so the
    constructors in this class don't look so weird*/

    //Logcat search filters
    // for methods like "goToActivity" that change foreground activities
    nav ("nav"),
    // for filesystem actions that affect the profile
    profile ("profile"),
    // for logging methods that are apart of the activity lifecycle: onCreate, onWindowFocusChanged, etc
    life ("life"),
    // for visibility on the results of battles
    battle ("battle"),
    // for ai related tasks
    ai ("AI"),

    // App mode flags
    // Bits 0 through 2 are Board Mode
    gridText(1),
    //Bits 3 through 5 are Start State
    // Most territories given to player 1
    easyWin(1 << 3);

    /**
     * A string to search for in logcat.
     */
    public String s;
    /**
     * A flag to determine app modes at runtime.
     */
    public int f;

    /**
     * Initializes an enum to have the message of it's name.
     * @param message The string stored in this enum. Usually the same as the enum itself.
     */
    Debug(String message) {
        this.s = message;
    }

    /**
     * Sets the flags to run a specific app mode. Abnormal flags are set by the bespoke options in app.
     * @param flag the flag to set
     */
    Debug(int flag) {
        this.f = flag;
    }
}


