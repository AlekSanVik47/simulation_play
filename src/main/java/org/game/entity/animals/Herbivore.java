package org.game.entity.animals;

import org.game.entity.EntityFactory;
import org.game.entity.Location;
import org.game.entity.Symbol;
import org.game.entity.immovableEntities.Grass;

import java.util.Set;

public class Herbivore extends Creature implements EntityFactory<Herbivore>, MovingAroundTerritory {
    private static final int STEPS_HERBIVORE = 3;
    private static final int DEFAULT_STRENGTH = 5;

    public Herbivore(Symbol symbol, Location location) {
        super(symbol, location, DEFAULT_STRENGTH);
        this.countsSteps = STEPS_HERBIVORE;
    }

    @Override
    public Location move(Set<Location> possibleCellsForMove) {
        if (possibleCellsForMove.isEmpty()) return null;
        return possibleCellsForMove.stream().findFirst().get();
    }

    public void eatGrass(Grass grass) {
        this.strength += grass.getGrassEnergy();
        grass.setGrassEnergy(0); // Трава полностью съедается
    }

    @Override
    public Herbivore create(Symbol symbol, Location location) {
        return new Herbivore(symbol, location);
    }
}
