package org.game.entity;

public abstract class SimulationWorld {
    private Location location;
    private char symbol;
    public Location getLocation() {
        return location;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public SimulationWorld(Location location, char simbol) {
        this.location = location;
        this.symbol = simbol;
    }
}
