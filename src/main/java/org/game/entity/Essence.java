package org.game.entity;

import java.util.Set;

public abstract class Essence {
    public Location location;
    private final Symbol symbol;

    public Essence(Symbol symbol, Location location) {
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
