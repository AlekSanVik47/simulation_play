package org.game.entity.immovableEntities;

import org.game.entity.*;

import java.util.Random;
import java.util.Set;

public class Grass extends Entity implements EntityFactory<Grass> {
    private int grassEnergy;
    private static final int DEFAULT_STRENGTH = 3;

    public Grass(Symbol symbol, Location location, int initialEnergy) {
        super(symbol, location);
        this.grassEnergy = initialEnergy;
    }

    public Grass(Symbol symbol, Location location) {
        super(symbol, location);
    }

    @Override
    protected Set<LocationTransitions> getTransitions() {
        return Set.of();
    }

    @Override
    public Location move(Set<Location> possibleCellsForMove) {
        Location currentLocation = getLocation();
        possibleCellsForMove.remove(currentLocation);
        Location randomCell = getRandomCell(possibleCellsForMove);
        if (randomCell != null) {
            setLocation(randomCell);
        }
        return randomCell;
    }

    private Location getRandomCell(Set<Location> possibleCellsForMove) {
        if (!possibleCellsForMove.isEmpty()) {
            return getRandomElement(possibleCellsForMove);
        } else {
            return null;
        }
    }

    private Location getRandomElement(Set<Location> possibleCellsForMove) {
        int randomIndex = getRandomNumber(possibleCellsForMove.size());
        int currentIndex = 0;
        for (Location location : possibleCellsForMove) {
            if (currentIndex == randomIndex) {
                return location;
            }
        }
        return null;
    }

    private int getRandomNumber(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }

    @Override
    public Entity create(Symbol symbol, Location location) {
        return new Grass(symbol, location, grassEnergy);
    }

    public int getGrassEnergy() {
        return DEFAULT_STRENGTH;
    }

    public void setGrassEnergy(int i) {
        this.grassEnergy = i;
    }
}