package org.game.entity.immovableEntities;

import org.game.entity.*;

import java.util.Set;

public class Rock extends Entity implements EntityFactory<Rock> {

    public Rock(Symbol symbol, Location location) {
        super(symbol, location);
    }

    @Override
    protected Set<LocationTransitions> getTransitions() {
        return Set.of();
    }

    @Override
    public Location move(Set<Location> possibleCellsForMove) {
        return null;
    }

    @Override
    public Rock create(Symbol symbol, Location location) {
        return new Rock(symbol, location);
    }
}


