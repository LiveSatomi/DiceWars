package com.jack.dicewars.dice_wars.game;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jack on 1/28/15.
 */
public class Player implements Parcelable {

    public static final String STATUS_YOU = "YOU";
    public static final String STATUS_AI = "AI";
    public static final String STATUS_HUMAN = "HUMAN";
    public static final String STATUS_CLOSED = "CLOSED";

    private String name;
    private String status;
    private int color;

    public Player(String name, String status, int color) {
        this.name = name;
        this.status = status;
        this.color = color;
    }

    protected Player(Parcel in) {
        name = in.readString();
        status = in.readString();
        color = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(status);
        dest.writeInt(color);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}