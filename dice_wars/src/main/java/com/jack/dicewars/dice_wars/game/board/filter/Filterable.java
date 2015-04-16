package com.jack.dicewars.dice_wars.game.board.filter;

import com.jack.dicewars.dice_wars.TerritoryColor;

import java.util.List;

/**
 * Created by Jack Mueller on 4/13/15.
 */
public interface Filterable {

    List<Filterable> adjacent();

    TerritoryColor color();

    int value();

}
