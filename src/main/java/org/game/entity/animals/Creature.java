package org.game.entity.animals;

import org.game.entity.Entity;
import org.game.entity.Location;
import org.game.entity.LocationTransitions;
import org.game.entity.Symbol;

import java.util.HashSet;
import java.util.Set;

public abstract class Creature extends Entity {
    protected int countsSteps;
    protected int strength;

    public Creature(Symbol symbol, Location location, int strength) {
        super(symbol, location);
        this.strength = strength;
    }

    @Override
    public Set<LocationTransitions> getTransitions() {
        Set<LocationTransitions> movements = new HashSet<>();
        for (int i = -countsSteps; i <= countsSteps; i++) {
            for (int j = -countsSteps; j <= countsSteps; j++) {
                if (Math.abs(i) + Math.abs(j) > countsSteps) continue;
                if (i == 0 && j == 0) continue;
                movements.add(new LocationTransitions(i, j));
            }
        }
        return movements;
    }

}
