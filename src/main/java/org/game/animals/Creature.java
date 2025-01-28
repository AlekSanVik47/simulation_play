package org.game.animals;

import org.game.entity.Essence;
import org.game.entity.Location;
import org.game.entity.Symbol;

public class Creature extends Essence {
    private int countSteps;
    private int strength;

    public Creature(Symbol symbol, Location location) {
        super(symbol, location);
    }
    public void makeMove(int countSteps){

    }
}
