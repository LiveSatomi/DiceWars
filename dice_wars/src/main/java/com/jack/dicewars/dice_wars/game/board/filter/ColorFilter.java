package com.jack.dicewars.dice_wars.game.board.filter;

import com.jack.dicewars.dice_wars.TerritoryColor;
import com.jack.dicewars.dice_wars.game.board.TerritoryBorder;

/**
 * Implementation of Filter that accepts Filterables based on their color's relationship to a target Filterable's color.
 */
public class ColorFilter implements Filter {

    /**
     * This Color will be checked against accepted Colors.
     */
    private TerritoryColor target;

    /**
     * True if the target accept all matching TerritoriesColors, false if it should accept all non-matching
     * TerritoryColors.
     */
    private boolean match;

    /**
     *
     * @param territoryColor The Color that will be checked against accepted colors. TODO make this a FilterColor
     * (interface) type
     * @param matching Defines how to check against passed Colors.
     */
    public ColorFilter(TerritoryColor territoryColor, boolean matching) {
        target = territoryColor;
        match = matching;
    }

    /**
     * Checks equality of the passed Filterables' Color to target. Accepted if the equality is the same value as the
     * match field.
     * @param filterable The filterable with a color either the same as target, or not
     * @return True if the the target compared to the passed color have the same value as match, false otherwise.
     */
    @Override
    public boolean accepts(Filterable filterable) {
        return (((TerritoryBorder) filterable).color() == target) == match;
    }
}