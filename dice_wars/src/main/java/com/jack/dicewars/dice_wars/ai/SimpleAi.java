package com.jack.dicewars.dice_wars.ai;

import com.jack.dicewars.dice_wars.game.Game;

/**
 * Created by Jack Mueller on 4/17/15.
 */
public class SimpleAi {
    Game game;


    public SimpleAi(Game game) {
        this.game = game;
    }

    public void takeTurn() {
        try {
            Thread.sleep(1700); // Do work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
