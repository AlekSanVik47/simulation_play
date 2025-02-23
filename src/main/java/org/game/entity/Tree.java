package org.game.entity;

import java.util.Set;

public class Tree extends Essence implements EssenceFactory<Tree>{

    public Tree(Symbol symbol, Location location) {
        super(symbol, location);
    }

    @Override
    protected Set<LocationTransitions> getEssenceTransitions() {
        return Set.of();
    }

    @Override
    public Tree create(Symbol symbol, Location location) {
        return new Tree(symbol, location);
    }
}
