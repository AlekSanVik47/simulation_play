package org.game.entity;

import java.util.Objects;

public class LocationTransitions {
    private final int transitionX;
    private final int transitionY;


    public LocationTransitions(int transitionX, int transitionY) {
        this.transitionX = transitionX;
        this.transitionY = transitionY;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LocationTransitions that = (LocationTransitions) o;
        return transitionX == that.transitionX && transitionY == that.transitionY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transitionX, transitionY);
    }

    @Override
    public String toString() {
        return "LocationTransitions{" +
                "transitionX=" + transitionX +
                ", transitionY=" + transitionY +
                '}';
    }

    public int getTransitionX() {
        return transitionX;
    }

    public int getTransitionY() {
        return transitionY;
    }
}
