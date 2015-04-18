package com.jack.dicewars.dice_wars.game.board.filter;

/**
 * Implementation of Filter that accepts Filterables based on their value compared to target values.
 */
public class ValueFilter implements Filter {

    /**
     * Value to be checked relative to accepted values.
     */
    private int target;
    /**
     * True if accepted values are greater than the target value, False if less than.
     */
    private boolean greater;
    /**
     * If false, only values that are equal to target will be accepted.
     */
    private boolean useGreater;

    /**
     * Accepted values are equal to the passed target for this ValueFilter.
     *
     * @param target The value accepted Filterable's are checked against.
     */
    public ValueFilter(int target) {
        this.target = target;
        this.useGreater = false;
    }

    /**
     * Accepted values are greater than target if greater is true, and less than target if greater is false.
     * @param target The value that will be compared against target
     * @param greater Determines whether accepted values should be greater or less than target.
     */
    public ValueFilter(int target, boolean greater) {
        this(target);
        this.greater = greater;
        this.useGreater = true;
    }

    /**
     *
     * @param filterable The Filterable to check the value of
     * @return Whether the Filterable's value is valid based on target and the comparison method.
     */
    @Override
    public boolean accepts(Filterable filterable) {
        int otherValue = filterable.value();

        if (useGreater) {
            if (greater) {
                return otherValue > target;
            } else {
                return otherValue < target;
            }
        } else {
            return target == otherValue;
        }
    }
}
