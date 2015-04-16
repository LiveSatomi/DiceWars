package com.jack.dicewars.dice_wars.game.board.filter;

import com.jack.dicewars.dice_wars.TerritoryColor;
import com.jack.dicewars.dice_wars.game.board.Territory;
import com.jack.dicewars.dice_wars.game.board.TerritoryBorder;

/**
 * Created by Jack Mueller on 4/15/15.
 */
public class ColorFilter implements Filter {

    private TerritoryColor target;

    /**
     * True if the target accept all matching TerritoriesColors, false if it should accept all non-matching
     * TerritoryColors.
     */
    private boolean match;

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