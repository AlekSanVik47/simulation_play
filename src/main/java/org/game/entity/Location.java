package org.game.entity;

import java.util.Objects;
import java.util.Set;

public class Location {
    private int x;
    private int y;

    public Location transition(LocationTransitions locationTransitions) {
        return new Location(x + locationTransitions.getTransitionX(), y + locationTransitions.getTransitionY());
    }

    public boolean isTransitable(LocationTransitions locationTransitions) {
        int newX = x + locationTransitions.getTransitionX();
        int newY = y + locationTransitions.getTransitionY();
        return newX > 0 && newY > 0 && newX < 10 && newY < 10;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isFree(Set<Location> occupiedLocations) {
        return !occupiedLocations.contains(this);
    }
 {

    }
}
