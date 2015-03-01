package com.jack.dicewars.dice_wars;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jack Mueller on 2/25/15.
 */
public enum Color implements Parcelable{
    colorless("N"),
    green("g"),
    yellow("y"),
    red("r"),
    blue("b"),
    purple("p"),
    pink("i");

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

    public void setCode(String code) {
        this.code = code;
    }

    public static final Parcelable.Creator<Color> CREATOR = new Parcelable.Creator<Color>() {

        public Color createFromParcel(Parcel in) {
            Color color = Color.values()[in.readInt()];
            color.setCode(in.readString());
            return color;
        }

        public Color[] newArray(int size) {
            return new Color[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(ordinal());
        out.writeString(getCode());
    }
}
