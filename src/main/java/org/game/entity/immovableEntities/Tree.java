package org.game.entity.immovableEntities;

import org.game.entity.*;

import java.util.Set;

public class Tree extends Entity implements EntityFactory<Tree> {

    public Tree(Symbol symbol, Location location) {
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
    public Tree create(Symbol symbol, Location location) {
        return new Tree(symbol, location);
    }
}
