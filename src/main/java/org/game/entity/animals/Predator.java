package org.game.entity.animals;

import org.game.entity.EntityFactory;
import org.game.entity.Location;
import org.game.entity.LocationTransitions;
import org.game.entity.Symbol;

import java.util.Set;

public class Predator extends Creature implements EntityFactory<Predator>, MovingAroundTerritory {
    private static final int STEPS_PREDATOR = 5;
    private static final int DEFAULT_STRENGTH = 10;

    public Predator(Symbol symbol, Location location) {
        super(symbol, location, DEFAULT_STRENGTH);
        this.countsSteps = STEPS_PREDATOR;
    }

    @Override
    public Location move(Set<Location> possibleCellsForMove) {
        if (possibleCellsForMove.isEmpty()) return null;
        return possibleCellsForMove.stream().findFirst().get();
    }

    public boolean attack(Herbivore herbivore) {
        if (this.strength <= 0) return false;

        int strengthDifference = herbivore.strength - this.strength;

        if (strengthDifference >= 0) {
            // Хищник не может атаковать
            this.strength = Math.max(this.strength - strengthDifference, 0);
            return false;
        }

        // Успешная атака
        herbivore.strength = 0;
        this.strength += Math.abs(strengthDifference);
        return true;
    }

    @Override
    public Predator create(Symbol symbol, Location location) {
        return new Predator(symbol, location);
    }

}
