package com.jack.dicewars.dice_wars.game;

/**
 * Created by Jack Mueller on 3/8/15.
 */
public class AttackPhase implements Phase{

    Player player;

    AttackPhase(Player player) {
        this.player = player;
    }

    public String toString() {
        return "Attack Phase";
    }
}
