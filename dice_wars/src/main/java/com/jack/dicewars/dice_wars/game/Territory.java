package com.jack.dicewars.dice_wars.game;

/**
 * Created by Jack Mueller on 2/23/15.
 *
 * A Territory is the smallest division that can be owned by a Player in DiceWars. Ownership can change rapidly; In
 * fact owning all Territories is the win condition of a game. A Territory has a value and a Player owner, which can
 * be null. The territory inherits its color from its owner.
 */
public class Territory {

    private Player owner;
    private int value;

    /**
     * Creates an isolated territory that keeps track of it's owner and dice value.
     *
     * @param owner The first Player to own this Territory.
     * @param value The initial amount of dice on this Territory.
     */
    public Territory(Player owner, int value) {
        this.setOwner(owner);
        this.setValue(value);
    }


    /**
     * The player who can attack from this Territory.
     * @return This Territory's owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Changes the ownership of this Territory.
     * @param owner The new owner of this territory.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * How many dice the owner can use when attacking from and defending this Territory. Will be non-negative.
     * @return The number of dice on this Territory.
     */
    public int getValue() {
        return value;
    }

    /**
     * Assign a new value when when the dice on this Territory have won or lost a duel.
     * @param value The new value to assign.
     */
    public void setValue(int value) {
        this.value = value;
    }
}
