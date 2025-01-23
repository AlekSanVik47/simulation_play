package org.game.animals;

import org.game.entity.Location;
import org.game.entity.Essence;

public class Creature extends Essence {
    private int countSteps;
    private int strength;
    public Creature(Location location, char symbol) {
        super(location, symbol);
    }

    public void makeMove(int countSteps){

    }

    public static class Herbivore extends org.game.entity.Creature {

        public Herbivore(Location location, char symbol) {
            super(location, symbol);
        }
    }
}
