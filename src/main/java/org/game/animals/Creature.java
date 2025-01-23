package org.game.animals;

import org.game.entity.Essence;
import org.game.entity.Location;

public class Creature extends Essence {
    private int countSteps;
    private int strength;
    public Creature(Location location, char symbol) {
        super(location, symbol);
    }

    public void makeMove(int countSteps){

    }
}
