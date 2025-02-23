package org.game.entity;

import java.util.Set;

public class Rock extends Essence implements EssenceFactory<Rock> {

    public Rock(Symbol symbol, Location location) {
        super(symbol, location);
    }


    @Override
    protected Set<LocationTransitions> getEssenceTransitions() {
        return Set.of();
    }

    @Override
    public Rock create(Symbol symbol, Location location) {
        return new Rock(symbol, location);
    }
}


