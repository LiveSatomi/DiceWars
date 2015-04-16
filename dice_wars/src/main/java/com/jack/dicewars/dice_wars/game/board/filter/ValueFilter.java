package com.jack.dicewars.dice_wars.game.board.filter;

/**
 * Created by Jack Mueller on 4/15/15.
 */
public class ValueFilter implements Filter{

    private int value;
    private boolean greater;
    private boolean useGreater;

    public ValueFilter (int value) {
        this.value = value;
        this.useGreater = false;
    }

    public ValueFilter (int value, boolean greater) {
        this(value);
        this.greater = greater;
        this.useGreater = true;
    }

    // TODO javadoc
    @Override
    public boolean accepts(Filterable filterable) {
        int otherValue = filterable.value();

        if (useGreater) {
            if (greater) {
                return otherValue > value;
            } else {
                return otherValue < value;
            }
        } else {
            return value == otherValue;
        }
    }
}
