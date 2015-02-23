package com.jack.dicewars.dice_wars;

/**
 * This class is for strings I may want to have lying around for development while still complying to checkstyle rules.
 *
 * Created by jack on 2/22/15.
 */
public enum Debug {
    nav ("nav"),
    profile ("profile");

    public String s;

    /**
     * Initializes an enum to have the message of it's name.
     * @param message The string stored in this enum. Usually the same as the enum itself.
     */
    Debug(String message) {
        this.s = message;
    }
}


