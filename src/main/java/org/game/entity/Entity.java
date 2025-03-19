package org.game.entity;

import java.util.Set;

public abstract class Entity {
    public Location location;
    private final Symbol symbol;

    public Entity(Symbol symbol, Location location) {
        this.symbol = symbol;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    protected abstract Set<LocationTransitions> getEssenceTransitions();

}
