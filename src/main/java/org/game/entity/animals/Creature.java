package org.game.entity.animals;

import org.game.entity.Entity;
import org.game.entity.Location;
import org.game.entity.LocationTransitions;
import org.game.entity.Symbol;

import java.util.HashSet;
import java.util.Set;

public abstract class Creature extends Entity {
    public int countsSteps;
    public int strength;

    public Creature(Symbol symbol, Location location) {
        super(symbol, location);
    }

    @Override
    public Set<LocationTransitions> getEssenceTransitions() {
        Set<LocationTransitions> result = new HashSet<>();
        for (int i = 0; i < countsSteps; i++) {
            result.add(new LocationTransitions(i, i));
            result.add(new LocationTransitions(i, -i));
            result.add(new LocationTransitions(-i, i));
            result.add(new LocationTransitions(-i, -i));
        }
        return result;
    }

    public abstract void makeMove(int countSteps);
}
