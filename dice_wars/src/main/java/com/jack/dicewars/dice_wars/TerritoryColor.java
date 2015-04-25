package com.jack.dicewars.dice_wars;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * A Color acts as the Primary ID for a Player during a given Game.
 */
public enum TerritoryColor implements Parcelable {
    colorless("N", R.drawable.tc_colorless, Color.LTGRAY),
    green("g", R.drawable.tc_green, Color.GREEN),
    yellow("y", R.drawable.tc_yellow, Color.YELLOW),
    red("r", R.drawable.tc_red, Color.RED),
    blue("b", R.drawable.tc_blue, Color.BLUE),
    purple("p", R.drawable.tc_purple, Color.parseColor("purple")),
    pink("i", R.drawable.tc_pink, Color.MAGENTA);

    /**
     * A color code that represents the actual color while in Text Mode.
     */
    private String code;

    /**
     * The id R.drawable saved for this TerritoryColor.
     */
    private int drawableId;

    /**
     * A hexadecimal color, dominant in the corresponding drawable.
     */
    private int hexColor;

    /**
     * A constructor to use colors in text mode.
     * @param text The one letter code that signifies the color.
     * @param id The id R.drawable saved for this TerritoryColor
     * @param hexColor The visual color of this TerritoryColor saved as RRGGBB
     */
    TerritoryColor(String text, int id, int hexColor) {
        code = text;
        drawableId = id;
        this.hexColor = hexColor;
    }

    /**
     * This color code should appear on color pickers and Territories in Text Mode.
     * @return The one letter, lowercase color code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the color code when being created from a parcel.
     * @param code The one letter lowercase color code
     */
    public void setCode(String code) {
        this.code = code;
    }

    public static final Parcelable.Creator<TerritoryColor> CREATOR = new Parcelable.Creator<TerritoryColor>() {

        public TerritoryColor createFromParcel(Parcel in) {
            TerritoryColor territoryColor = TerritoryColor.values()[in.readInt()];
            territoryColor.setCode(in.readString());
            territoryColor.setDrawableId(in.readInt());
            territoryColor.setHexColor(in.readInt());
            return territoryColor;
        }

        public TerritoryColor[] newArray(int size) {
            return new TerritoryColor[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(ordinal());
        out.writeString(code);
        out.writeInt(drawableId);
        out.writeInt(hexColor);
    }

    /**
     * An Android drawable that can be repeated on a View.
     * @return The drawable resource id for this TerritoryColor
     */
    public int getDrawableId() {
        return drawableId;
    }

    /**
     *
     * @param territoryId The drawable resource id for this TerritoryColor
     */
    public void setDrawableId(int territoryId) {
        this.drawableId = territoryId;
    }

    /**
     *
     * @return The dominant color of this TerritoryColor's drawable in hex form.
     */
    public int getHexColor() {
        return hexColor;
    }

    /**
     *
     * @param hexColor The dominant color of this TerritoryColor's drawable in hex form.
     */
    public void setHexColor(int hexColor) {
        this.hexColor = hexColor;
    }
}
