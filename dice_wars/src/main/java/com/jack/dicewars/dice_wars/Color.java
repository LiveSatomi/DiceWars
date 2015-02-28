package com.jack.dicewars.dice_wars;

/**
 * Created by Jack Mueller on 2/25/15.
 */
public enum Color {
    colorless("N");

    /**
     * A color code that represents the actual color while in Text Mode
     */
    private String code;


    /**
     * A constructor to use colors in text mode.
     * @param text The one letter code that signifies the color.
     */
    Color(String text) {
        code = text;
    }

    public String getCode() {
        return code;
    }
}
